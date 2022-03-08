package com.mishrole.undercontrol.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mishrole.undercontrol.entity.Account;
import com.mishrole.undercontrol.entity.User;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	public abstract Account findAccountById(Long id);
	public abstract Optional<Account> findByName(String name);
	public abstract List<Account> findByOwner(User user);
}
