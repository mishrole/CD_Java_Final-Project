package com.mishrole.undercontrol.service;

import java.math.BigDecimal;
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
		Account account = accountRepository.findAccountById(id);
		
		if (account != null) {
			account.setIncome(getTotalIncomeByAccount(account.getId()));
			account.setExpense(getTotalExpenseByAccount(account.getId()));
			account.setTotal(getTotalBalanceByAccount(account.getId()));
		}
		
		return account;
	}

	@Override
	public List<Account> getAll() {
		List<Account> accounts = accountRepository.findAll();
		
		accounts.forEach(account -> {
			account.setIncome(getTotalIncomeByAccount(account.getId()));
			account.setExpense(getTotalExpenseByAccount(account.getId()));
			account.setTotal(getTotalBalanceByAccount(account.getId()));
		});
		
		return accounts;
	}

	@Override
	public void delete(Long id) {
		accountRepository.deleteById(id);
	}

	@Override
	public Account save(Account account, BindingResult result) {
		Optional<User> owner = userRepository.findById(account.getOwner().getId());
		
		if (!owner.isPresent()) {
			result.rejectValue("owner", "Matches", "User with Id " + account.getOwner().getId() + " not found");
			return null;
		}
		
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
		
		List<Account> accounts = owner.get().getAccounts();
		
		accounts.forEach(account -> {
			account.setIncome(getTotalIncomeByAccount(account.getId()));
			account.setExpense(getTotalExpenseByAccount(account.getId()));
			account.setTotal(getTotalBalanceByAccount(account.getId()));
		});

		return accounts;
	}
	
	private BigDecimal getTotalBalanceByAccount(Long accountId) {
		BigDecimal total = accountRepository.totalBalanceByAccount(accountId);
		
		if (total == null) {
			total = new BigDecimal(0);
		}
		
		return total;
	}
	
	private BigDecimal getTotalIncomeByAccount(Long accountId) {
		BigDecimal total = accountRepository.totalIncomeByAccount(accountId);
		
		if (total == null) {
			total = new BigDecimal(0);
		}
		
		return total;
	}
	
	private BigDecimal getTotalExpenseByAccount(Long accountId) {
		BigDecimal total = accountRepository.totalExpenseByAccount(accountId);
		
		if (total == null) {
			total = new BigDecimal(0);
		}
		
		return total;
	}

}
