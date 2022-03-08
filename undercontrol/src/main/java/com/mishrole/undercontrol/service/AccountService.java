package com.mishrole.undercontrol.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.mishrole.undercontrol.entity.Account;
import com.mishrole.undercontrol.entity.User;
import com.mishrole.undercontrol.repository.AccountRepository;
import com.mishrole.undercontrol.repository.UserRepository;

@Service
public class AccountService implements IAccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Account findAccountById(Long id) {
		return accountRepository.findAccountById(id);
	}

	@Override
	public List<Account> getAll() {
		return accountRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		accountRepository.deleteById(id);
	}

	@Override
	public Account save(Account account, BindingResult result) {
		Optional<Account> isNameUnique = accountRepository.findByNameAndOwner(account.getName(), account.getOwner());
		
		Boolean isValid = true;
		
		if (isNameUnique.isPresent()) {
			result.rejectValue("name", "Matches", "The name is already taken!");
			isValid = false;
		}
		
		if (!isValid) {
			return null;
		}
		
		return accountRepository.save(account);
	}

	@Override
	public Account update(Long id, Account account, BindingResult result) {
		Optional<Account> potentialAccount = accountRepository.findById(id);
		
		if (!potentialAccount.isPresent()) {
			result.rejectValue("id", "Matches", "Account with Id " + id + " not found");
			return null;
		}
		
		if(!(account.getName().equals(potentialAccount.get().getName()))) {
			Optional<Account> isNameUnique = accountRepository.findByNameAndOwner(account.getName(), account.getOwner());
			
			if (isNameUnique.isPresent()) {
				result.rejectValue("name", "Matches", "The name is already taken!");
				return null;
			}
		}
		
		Account savedAccount = potentialAccount.get();
		savedAccount.setName(account.getName());
		
		return accountRepository.save(savedAccount);
	}

	@Override
	public List<Account> getAllByOwner(Long id) {
		Optional<User> owner = userRepository.findById(id);
		
		if (!owner.isPresent()) {
			return null;
		}
		
		return owner.get().getAccounts();
	}

}
