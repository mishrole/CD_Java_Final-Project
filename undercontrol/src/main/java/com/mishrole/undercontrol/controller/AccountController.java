package com.mishrole.undercontrol.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mishrole.undercontrol.entity.Account;
import com.mishrole.undercontrol.service.AccountService;
import com.mishrole.undercontrol.util.Constant;
import com.mishrole.undercontrol.util.ValidationErrors;

@RestController
@RequestMapping(value = "/api/v1/accounts")
@CrossOrigin(origins = {"http://localhost:9091", "http://localhost:4200"}, methods = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE})
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> list() {
		List<Account> accounts = accountService.getAll();
		return Constant.responseMessage(HttpStatus.OK, "Success", accounts);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@Valid @RequestBody Account account, BindingResult result) {
		
		if (result.hasErrors()) {
			List<Map<String, Object>> errors = ValidationErrors.mapErrors(result);
			return Constant.responseMessageErrors(HttpStatus.BAD_REQUEST, "Error", "An error occurred while performing the operation, the account has not been saved", errors);
		}
		
		Account accountResult = accountService.save(account, result);
		
		List<Map<String, Object>> errors = ValidationErrors.mapErrors(result);
		
		if (accountResult == null) {
			return Constant.responseMessageErrors(HttpStatus.BAD_REQUEST, "Error", "An error occurred while performing the operation, the account has not been saved", errors);
		}
		
		return Constant.responseMessage(HttpStatus.OK, "Success", accountResult);
	}
	
	@GetMapping(value = "owner/{ownerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findByOwner(@PathVariable("ownerId") Long ownerId) {
		List<Account> accounts = accountService.getAllByOwner(ownerId);
		
		if (accounts == null) {
			return Constant.responseMessageError(HttpStatus.NOT_FOUND, "Error", "An error ocurred while performing the operation, the accounts were not found", String.format("Accounts for user with id %s were not found", ownerId), "id");
		}
		
		return Constant.responseMessage(HttpStatus.OK, "Success", accounts);
		
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Account account = accountService.findAccountById(id);
		
		if (account == null) {
			return Constant.responseMessageError(HttpStatus.NOT_FOUND, "Error", "An error ocurred while performing the operation, account not found", String.format("Account with id %s not found", id), "id");
		}
		
		return Constant.responseMessage(HttpStatus.OK, "Success", account);
	}
	
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody Account account, BindingResult result) {
		
		if (result.hasErrors()) {
			List<Map<String, Object>> errors = ValidationErrors.mapErrors(result);
			return Constant.responseMessageErrors(HttpStatus.BAD_REQUEST, "Error", "An error occurred while performing the operation, the account has not been updated", errors);
		}
		
		Account updatedResult = accountService.update(id, account, result);
		
		List<Map<String, Object>> errors = ValidationErrors.mapErrors(result);
		
		if (updatedResult == null) {
			return Constant.responseMessageErrors(HttpStatus.BAD_REQUEST, "Error", "An error occurred while performing the operation, the account has not been updated", errors);
		}
		
		return Constant.responseMessage(HttpStatus.OK, "Success", updatedResult);
	}
}
