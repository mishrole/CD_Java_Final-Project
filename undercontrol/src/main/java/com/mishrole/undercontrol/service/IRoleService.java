package com.mishrole.undercontrol.service;

import java.util.List;

import com.mishrole.undercontrol.entity.Role;

public interface IRoleService {
	public abstract Role findRoleById(Long id);
	public abstract List<Role> getAll();
}
