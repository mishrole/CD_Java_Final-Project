package com.mishrole.undercontrol.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mishrole.undercontrol.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public abstract Optional<User> findByEmail(String email);
	public abstract Optional<User> findById(Long id);
}
