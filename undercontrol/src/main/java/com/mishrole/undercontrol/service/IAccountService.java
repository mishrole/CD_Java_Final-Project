package com.mishrole.undercontrol.service;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.mishrole.undercontrol.entity.Account;

public interface IAccountService {
	public abstract Account findAccountById(Long id);
	public abstract List<Account> getAll();
	public abstract void delete(Long id);
	public abstract Account save(Account account, BindingResult result);
	public abstract Account update (Long id, Account account, BindingResult result);
	public abstract List<Account> getAllByOwner(Long id);
}
