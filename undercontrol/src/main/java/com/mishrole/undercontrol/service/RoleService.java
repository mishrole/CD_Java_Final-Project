package com.mishrole.undercontrol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mishrole.undercontrol.entity.Role;
import com.mishrole.undercontrol.repository.RoleRepository;

@Service
public class RoleService implements IRoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role findRoleById(Long id) {
		return roleRepository.findRoleById(id);
	}

	@Override
	public List<Role> getAll() {
		return roleRepository.findAll();
	}

}
