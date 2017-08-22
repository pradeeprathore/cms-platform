package com.ascent.cms.core.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ascent.cms.core.constants.AccessLevel;
import com.ascent.cms.core.constants.ActionType;
import com.ascent.cms.core.constants.RoleName;
import com.ascent.cms.core.dao.RoleDAO;
import com.ascent.cms.core.domain.user.AppUser;
import com.ascent.cms.core.domain.user.Role;

@Component(value = "customAuthManager")
@Transactional
public class CustomAuthManager
{



	@Autowired
	private RoleDAO roleDAO;

	public boolean isPermitted(String desiredAccessLevel)
	{
		return isPermitted(desiredAccessLevel, ActionType.NON_BILLING);
	}

	@Transactional(readOnly = true)
	public boolean isPermitted(String desiredAccessLevel, ActionType actionType)
	{
		Assert.notBlank(desiredAccessLevel, "'desiredAccessLevel' is required");

		AccessLevel accessLevel = AccessLevel.valueOf(desiredAccessLevel);

		// if user is assistant than check the permissions
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Long userId = Long.valueOf((String) authentication.getPrincipal());
		List<Role> roles = roleDAO.findRoles(new AppUser(userId));
		for (Role current : roles)
		{
			if (hasNonAssistantRole(current))
			{
				return true; // TODO: change to true once testing is done
			}
		}

		// return not permitted otherwise
		return false;
	}


	private boolean hasNonAssistantRole(Role role)
	{
		return     RoleName.ROLE_USER.name().equals(role.getName())
				|| RoleName.ROLE_SYSTEM_ADMIN.name().equals(role.getName());
				
	}

}
