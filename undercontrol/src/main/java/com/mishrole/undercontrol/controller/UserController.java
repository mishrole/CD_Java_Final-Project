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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mishrole.undercontrol.entity.User;
import com.mishrole.undercontrol.entity.request.ChangePwdUser;
import com.mishrole.undercontrol.entity.request.RegisterUser;
import com.mishrole.undercontrol.service.UserService;
import com.mishrole.undercontrol.util.Constant;
import com.mishrole.undercontrol.util.ValidationErrors;

@RestController
@RequestMapping(value = "/api/v1/users")
@CrossOrigin(origins = {"http://localhost:9091", "http://localhost:4200"}, methods = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE})
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> list() {
		List<User> users = userService.getAll();
		return Constant.responseMessage(HttpStatus.OK, "Success", users);
	}
	
	@ResponseBody
	@GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> search(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
		List<User> users = userService.findUserByEmailOrFullname(keyword);
		
		return Constant.responseMessage(HttpStatus.OK, "Success", users);
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> find(@PathVariable("id") Long id) {
		User user = userService.findUserById(id);
		
		if (user == null) {
			return Constant.responseMessageError(HttpStatus.NOT_FOUND, "Error", "An error ocurred while performing the operation, the user has not been found", String.format("User with id %s not found", id), "id");
		}
		
		return Constant.responseMessage(HttpStatus.OK, "Success", user);
	}
	
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody User user, BindingResult result) {
		
		if (result.hasErrors()) {
			List<Map<String, Object>> errors = ValidationErrors.mapErrors(result);
			return Constant.responseMessageErrors(HttpStatus.BAD_REQUEST, "Error", "An error occurred while performing the operation, the user has not been updated", errors);
		}
		
		User updatedResult = userService.update(id, user, result);
		
		List<Map<String, Object>> errors = ValidationErrors.mapErrors(result);
		
		if (updatedResult == null) {
			return Constant.responseMessageErrors(HttpStatus.BAD_REQUEST, "Error", "An error occurred while performing the operation, the user has not been updated", errors);
		}
		
		return Constant.responseMessage(HttpStatus.OK, "Success", updatedResult);
	}
	
	
	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUser user, BindingResult result) {
		
		if (result.hasErrors()) {
			List<Map<String, Object>> errors = ValidationErrors.mapErrors(result);
			return Constant.responseMessageErrors(HttpStatus.BAD_REQUEST, "Error", "An error occurred while performing the operation, the user has not been saved", errors);
		}
		
		User registerResult = userService.register(user, result);
		
		List<Map<String, Object>> errors = ValidationErrors.mapErrors(result);
		
		if (registerResult == null) {
			return Constant.responseMessageErrors(HttpStatus.BAD_REQUEST, "Error", "An error occurred while performing the operation, the user has not been saved", errors);
		}
		
		return Constant.responseMessage(HttpStatus.OK, "Success", registerResult);
	}
	
	@PutMapping(value = "/{id}/changepassword", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> changePassword(@PathVariable("id") Long id, @Valid @RequestBody ChangePwdUser user, BindingResult result) {
		
		if (result.hasErrors()) {
			List<Map<String, Object>> errors = ValidationErrors.mapErrors(result);
			return Constant.responseMessageErrors(HttpStatus.BAD_REQUEST, "Error", "An error occurred while performing the operation, the user has not been updated", errors);
		}
		
		User updatedResult = userService.changePassword(id, user, result);
		
		List<Map<String, Object>> errors = ValidationErrors.mapErrors(result);
		
		if (updatedResult == null) {
			return Constant.responseMessageErrors(HttpStatus.BAD_REQUEST, "Error", "An error occurred while performing the operation, the user has not been updated", errors);
		}
		
		return Constant.responseMessage(HttpStatus.OK, "Success", updatedResult);
	}
	
}
