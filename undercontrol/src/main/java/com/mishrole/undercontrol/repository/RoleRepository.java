package com.mishrole.undercontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mishrole.undercontrol.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	//public abstract List<Role> findRoleByUsers(User user);
	public abstract Role findRoleById(Long id);
}
