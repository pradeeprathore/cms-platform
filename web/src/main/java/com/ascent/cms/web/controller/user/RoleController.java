package com.ascent.cms.web.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ascent.cms.core.ErrorCode;
import com.ascent.cms.core.domain.user.Role;
import com.ascent.cms.core.vo.ListResponse;
import com.ascent.cms.core.vo.SearchCriteria;
import com.ascent.cms.services.user.RoleService;
import com.ascent.cms.web.controller.BaseController;
import com.ascent.cms.web.util.WebUtils;

@Controller
@RequestMapping("/roles")
public class RoleController extends BaseController
{
	@Autowired
	private RoleService roleService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ListResponse> getAll(HttpServletRequest request)
	{
		SearchCriteria searchCriteria = WebUtils.getSearchCriteria(request);
		ListResponse result = roleService.getAll(searchCriteria);
		return new ResponseEntity<ListResponse>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Role> get(@PathVariable("id") Short id)
	{
		Role role = roleService.getById(Role.class, id);
		if (role == null)
		{
			throw new ResourceNotFoundException(ErrorCode.ResourceNotFoundConstants.ROLE_NOT_FOUND);
		}
		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Role> create(@RequestBody Role role)
	{
		validate(role);
		Role saved = roleService.save(role);
		return new ResponseEntity<Role>(saved, HttpStatus.CREATED);
	}
}
