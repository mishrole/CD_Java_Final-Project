package com.mishrole.undercontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mishrole.undercontrol.entity.UserHasRole;

@Repository
public interface UserHasRoleRepository extends JpaRepository<UserHasRole, Long> {

}
