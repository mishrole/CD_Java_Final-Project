package com.mishrole.undercontrol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mishrole.undercontrol.entity.Role;
import com.mishrole.undercontrol.service.RoleService;
import com.mishrole.undercontrol.util.Constant;

@RestController
@RequestMapping(value = "/api/v1/roles")
@CrossOrigin(origins = {"http://localhost:9091", "http://localhost:4200"}, methods = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE})
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> list() {
		List<Role> roles = roleService.getAll();
		return Constant.responseMessage(HttpStatus.OK, "Success", roles);
	}
}
