package com.mishrole.undercontrol.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mishrole.undercontrol.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public abstract Optional<User> findByEmail(String email);
	public abstract Optional<User> findById(Long id);
	
	@Query("Select x from User x where x.email like :keyword or concat(x.firstname, '', x.lastname) like :keyword or concat(x.lastname, '', x.firstname) like :keyword")
	public abstract List<User> findUserByEmailOrName(@Param(value = "keyword") String keyword);
}
