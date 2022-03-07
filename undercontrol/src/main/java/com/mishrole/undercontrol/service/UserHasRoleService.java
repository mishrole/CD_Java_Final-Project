package com.mishrole.undercontrol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mishrole.undercontrol.entity.UserHasRole;
import com.mishrole.undercontrol.repository.UserHasRoleRepository;

@Service
public class UserHasRoleService implements IUserHasRoleService {

	@Autowired
	private UserHasRoleRepository userHasRoleRepository;
	
	@Override
	public UserHasRole save(UserHasRole userHasRole) {
		return userHasRoleRepository.save(userHasRole);
	}

	@Override
	public void delete(UserHasRole userHasRole) {
		userHasRoleRepository.delete(userHasRole);
	}

}
