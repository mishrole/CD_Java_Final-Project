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

import com.mishrole.undercontrol.service.RecordService;
import com.mishrole.undercontrol.entity.Record;
import com.mishrole.undercontrol.util.Constant;

@RestController
@RequestMapping(value = "/api/v1/records")
@CrossOrigin(origins = {"http://localhost:9091", "http://localhost:4200"}, methods = {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE})
public class RecordController {

	@Autowired
	private RecordService recordService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> list() {
		List<Record> records = recordService.getAll();
		return Constant.responseMessage(HttpStatus.OK, "Success", records);
	}
}
