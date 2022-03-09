package com.mishrole.undercontrol.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.mishrole.undercontrol.entity.Account;
import com.mishrole.undercontrol.entity.Record;
import com.mishrole.undercontrol.entity.User;
import com.mishrole.undercontrol.repository.AccountRepository;
import com.mishrole.undercontrol.repository.RecordRepository;

@Service
public class RecordService implements IRecordService {
	
	@Autowired
	private RecordRepository recordRepository;
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Record findRecordById(Long id) {
		return recordRepository.findRecordById(id);
	}

	@Override
	public List<Record> getAll() {
		return recordRepository.findAll();
	}

	@Override
	public Record save(Record record, BindingResult result) {
		Optional<Account> potentialAccount = accountRepository.findById(record.getAccount().getId());
		
		if (!potentialAccount.isPresent()) {
			result.rejectValue("account.id", "Matches", "Account with Id " + record.getAccount().getId() + " not found");
			return null;
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		Account savedAccount = potentialAccount.get();
		
		// Performance issue?
		
		User accountOwner = (User) Hibernate.unproxy(savedAccount.getOwner());
		
		if (!accountOwner.getEmail().equals(username)) {
			result.rejectValue("account.id", "Matches", "Only the owner of the account can add records");
			return null;
		}
		
		return recordRepository.save(record);
	}

	@Override
	public List<Record> getAllByAccountId(Long accountId) {
		Optional<Account> potentialAccount = accountRepository.findById(accountId);
		
		if (!potentialAccount.isPresent()) {
			return null;
		}
		
		Account savedAccount = potentialAccount.get();
		
		return recordRepository.findRecordByAccount(savedAccount);
	}

}
