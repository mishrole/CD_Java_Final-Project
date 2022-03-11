package com.mishrole.undercontrol.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.mishrole.undercontrol.entity.Account;
import com.mishrole.undercontrol.entity.Category;
import com.mishrole.undercontrol.entity.Record;
import com.mishrole.undercontrol.entity.Type;
import com.mishrole.undercontrol.entity.User;
import com.mishrole.undercontrol.repository.AccountRepository;
import com.mishrole.undercontrol.repository.CategoryRepository;
import com.mishrole.undercontrol.repository.RecordRepository;
import com.mishrole.undercontrol.repository.TypeRepository;

@Service
public class RecordService implements IRecordService {
	
	@Autowired
	private RecordRepository recordRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private TypeRepository typeRepository;

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
		
		Boolean isValid = true;
		
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
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date today = sdf.parse(sdf.format(new Date()));
			
			Date recordDate = sdf.parse(sdf.format(record.getRecordDate()));
			
			if (recordDate.after(today)) {
            	result.rejectValue("recordDate", "Matches", "Record Date must be in the past");
            	isValid = false;
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if (!(record.getAmount().compareTo(new BigDecimal(0.001)) > 0)) {
			result.rejectValue("amount", "Matches", "Amount must be greater or equals than 0.001");
			isValid = false;
		}
		
		if (!isValid) {
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

	@Override
	public Record update(Long id, Record record, BindingResult result) {
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
		
		Optional<Record> potentialRecord = recordRepository.findById(id);
		
		if (!potentialRecord.isPresent()) {
			result.rejectValue("id", "Matches", "Record with Id " + id + " not found");
			return null;
		}
		
		Record savedRecord = potentialRecord.get();
		savedRecord.setName(record.getName());
		savedRecord.setConcept(record.getConcept());
		savedRecord.setAmount(record.getAmount());
		savedRecord.setRecordDate(record.getRecordDate());
		
		Account account = accountRepository.findAccountById(record.getAccount().getId());
		savedRecord.setAccount(account);
		
		Category category = categoryRepository.findCategoryById(record.getCategory().getId());
		savedRecord.setCategory(category);
		
		Type type = typeRepository.findTypeById(record.getType().getId());
		savedRecord.setType(type);
		
		return recordRepository.save(savedRecord);

	}

	@Override
	public List<Record> searchRecordByAccountAndFilters(Long accountId, String keyword, String start, String end) {
		Optional<Account> potentialAccount = accountRepository.findById(accountId);
		
		if (!potentialAccount.isPresent()) {
			return null;
		}
		
		Account savedAccount = potentialAccount.get();
		
		if (start == null || start.length() == 0) {
			start = "0000-00-00";
		}
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
//        c.setTimeInMillis(43199000);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
		
		if (end == null || end.length() == 0) {
	        c.add(Calendar.DAY_OF_MONTH, 1);
	        Date today = c.getTime();
			end = df.format(today);
		} else {
			try {
				c.setTime(df.parse(end));
				c.add(Calendar.DAY_OF_MONTH, 1);
				Date today = c.getTime();
				end = df.format(today);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		
		List<Record> result = recordRepository.searchRecordByAccountAndFilters(savedAccount.getId(), "%"+keyword+"%", start, end);
		
		return result;
	}

	
	@Override
	public void delete(Long id) {
		recordRepository.deleteById(id);
	}
	
}
