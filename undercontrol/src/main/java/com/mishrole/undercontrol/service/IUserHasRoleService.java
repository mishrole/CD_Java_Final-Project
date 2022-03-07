package com.mishrole.undercontrol.service;

import com.mishrole.undercontrol.entity.UserHasRole;

public interface IUserHasRoleService {
	public UserHasRole save(UserHasRole userHasRole);
	public void delete(UserHasRole userHasRole);
}
