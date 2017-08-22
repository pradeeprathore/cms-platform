package com.ascent.cms.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ascent.cms.core.ErrorCode;
import com.ascent.cms.core.constants.SearchScope;
import com.ascent.cms.core.exception.AuthenticationFailureException;
import com.ascent.cms.core.exception.BookingFailureException;
import com.ascent.cms.core.exception.CoreRuntimeException;
import com.ascent.cms.core.exception.InvalidArgumentException;
import com.ascent.cms.core.exception.ResourceNotFoundException;
import com.ascent.cms.core.exception.ValidationFailureException;
import com.ascent.cms.core.util.BeanValidator;
import com.ascent.cms.core.vo.ErrorInfo;
import com.ascent.cms.core.vo.SearchCriteria;
import com.ascent.cms.web.util.WebUtils;
import com.sun.servicetag.UnauthorizedAccessException;

public class BaseController implements MessageSourceAware
{

	private static final String APPLICATION_JSON = "application/json";

	private static final String ACCEPT_HEADER_KEY = "Accept";

	private static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json; charset=UTF-8";

	private static final String CONTENT_TYPE_HEADER_KEY = "Content-Type";

	public static final Locale DEFAULT_LOCALE = Locale.US;

	private static final Logger LOGGER = Logger.getLogger(BaseController.class);

	protected static final String S_ECHO = "sEcho";

	private MessageSource messageSource;

	@Autowired
	private BeanValidator beanValidator;

	

	@ExceptionHandler(ValidationFailureException.class)
	public ResponseEntity<Object> handleValidationFailureException(ValidationFailureException vfe)
	{
		HttpHeaders headers = getHeaders();

		Set<ConstraintViolation<?>> cvs = vfe.getConstraintViolations();

		List<ErrorInfo> errors = new ArrayList<ErrorInfo>(cvs.size());

		for (ConstraintViolation<?> constraintViolation : cvs)
		{
			String message = constraintViolation.getMessage();
			String fieldName = constraintViolation.getPropertyPath().toString();
			Object invalidValue = constraintViolation.getInvalidValue();

			// remove secure stuff to be send back
			if (fieldName != null && (fieldName.endsWith("password") || fieldName.endsWith("userName")))
			{
				invalidValue = null;
			}

			ErrorInfo errorInfo = new ErrorInfo(fieldName, invalidValue, message);
			errors.add(errorInfo);
		}
		if (CollectionUtils.isNotEmpty(errors))
		{
			Collections.sort(errors);
		}
		return new ResponseEntity<Object>(errors, headers, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException re)
	{
		HttpHeaders headers = getHeaders();
		List<ErrorInfo> errors = getErrors(re);
		LOGGER.error(re.getMessage(), re);
		return new ResponseEntity<Object>(errors, headers, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidArgumentException.class)
	public ResponseEntity<Object> handleInvalidArgumentException(InvalidArgumentException re)
	{   
		HttpHeaders headers = getHeaders();
		List<ErrorInfo> errors = getErrors(re);
		LOGGER.error(re.getMessage(), re);
		return new ResponseEntity<Object>(errors, headers, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AuthenticationFailureException.class)
	public ResponseEntity<Object> handleAuthenticationFailureException(AuthenticationFailureException ae)
	{
		HttpHeaders headers = getHeaders();
		List<ErrorInfo> errors = getErrors(ae);
		LOGGER.error(ae.getMessage(), ae);
		return new ResponseEntity<Object>(errors, headers, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(UnauthorizedAccessException.class)
	public ResponseEntity<String> handleUnauthorizedAccessException(UnauthorizedAccessException ae)
	{
		HttpHeaders headers = getHeaders();
		LOGGER.error(ae.getMessage(), ae);
		return new ResponseEntity<String>(headers, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ae)
	{
		HttpHeaders headers = getHeaders();
		LOGGER.error(ae.getMessage(), ae);
		return new ResponseEntity<String>(headers, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(BookingFailureException.class)
	public ResponseEntity<Object> handleBookingFailureException(BookingFailureException re)
	{
		HttpHeaders headers = getHeaders();
		List<ErrorInfo> errors = getErrors(re);
		LOGGER.error(re.getMessage(), re);
		return new ResponseEntity<Object>(errors, headers, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntimeException(RuntimeException rte)
	{
		HttpHeaders headers = getHeaders();
		LOGGER.error(rte.getMessage(), rte);
		return new ResponseEntity<String>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public void setMessageSource(MessageSource messageSource)
	{
		this.messageSource = messageSource;
	}

	protected String getMessage(final String key)
	{
		return messageSource.getMessage(key, null, DEFAULT_LOCALE);
	}

	private HttpHeaders getHeaders()
	{
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE_HEADER_KEY, APPLICATION_JSON_CHARSET_UTF_8);
		headers.add(ACCEPT_HEADER_KEY, APPLICATION_JSON);
		return headers;
	}

	private List<ErrorInfo> getErrors(CoreRuntimeException re)
	{
		List<ErrorInfo> errors = new ArrayList<ErrorInfo>();
		
		ErrorInfo errorInfo = new ErrorInfo(null, null, getMessage(re.getErrorCode()));
		errors.add(errorInfo);
		return errors;
	}

	protected <T> void validate(final T model)
	{
		this.beanValidator.validate(model);
	}

	protected <T> void validate(final T model, Class<?>... groups)
	{
		this.beanValidator.validate(model, groups);
	}



	protected void resolveSearchScope(SearchScope searchScope, SearchCriteria searchCriteria)
	{
		if (searchScope == null)
		{
			searchScope = SearchScope.All;
		}
		searchCriteria.setSearchScope(searchScope);
	}

	
	

}
