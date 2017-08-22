package com.ascent.cms.core.constraint;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class HtmlValidator implements ConstraintValidator<NotHtml, String>
{

	public final static String tagStart = "\\<\\w+((\\s+\\w+(\\s*\\=\\s*(?:\".*?\"|'.*?'|[^'\"\\>\\s]+))?)+\\s*|\\s*)\\>";

	public final static String tagEnd = "\\</\\w+\\>";

	public final static String tagSelfClosing = "\\<\\w+((\\s+\\w+(\\s*\\=\\s*(?:\".*?\"|'.*?'|[^'\"\\>\\s]+))?)+\\s*|\\s*)/\\>";

	public final static String htmlEntity = "&[a-zA-Z][a-zA-Z0-9]+;";

	public final static Pattern htmlPattern = compilePattern();

	@Override
	public void initialize(NotHtml constraintAnnotation)
	{

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context)
	{
		if (StringUtils.isBlank(value))
		{
			return true;
		}
		boolean found = htmlPattern.matcher(value).find();
		// if html found than return invalid else return valid
		return found ? false : true;
	}

	private static Pattern compilePattern()
	{
		return Pattern.compile("(" + tagStart + ".*" + tagEnd + ")|(" + tagSelfClosing + ")|(" + htmlEntity + ")",
				Pattern.DOTALL);
	}

}
