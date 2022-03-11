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

import com.mishrole.undercontrol.entity.Record;
import com.mishrole.undercontrol.service.RecordService;
import com.mishrole.undercontrol.util.Constant;
import com.mishrole.undercontrol.util.ValidationErrors;

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
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@Valid @RequestBody Record record, BindingResult result) {
		
		if (result.hasErrors()) {
			List<Map<String, Object>> errors = ValidationErrors.mapErrors(result);
			return Constant.responseMessageErrors(HttpStatus.BAD_REQUEST, "Error", "An error occurred while performing the operation, the record has not been saved", errors);
		}
		
		Record recordResult = recordService.save(record, result);
		
		List<Map<String, Object>> errors = ValidationErrors.mapErrors(result);
		
		if (recordResult == null) {
			return Constant.responseMessageErrors(HttpStatus.BAD_REQUEST, "Error", "An error occurred while performing the operation, the record has not been saved", errors);
		}
		
		return Constant.responseMessage(HttpStatus.OK, "Success", recordResult);
	}
	
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody Record record, BindingResult result) {
		
		if (result.hasErrors()) {
			List<Map<String, Object>> errors = ValidationErrors.mapErrors(result);
			return Constant.responseMessageErrors(HttpStatus.BAD_REQUEST, "Error", "An error occurred while performing the operation, the record has not been updated", errors);
		}
		
		Record updatedResult = recordService.update(id, record, result);
		
		List<Map<String, Object>> errors = ValidationErrors.mapErrors(result);
		
		if (updatedResult == null) {
			return Constant.responseMessageErrors(HttpStatus.BAD_REQUEST, "Error", "An error occurred while performing the operation, the record has not been updated", errors);
		}
		
		return Constant.responseMessage(HttpStatus.OK, "Success", updatedResult);
	}
	
	@GetMapping(value = "account/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findByOwner(@PathVariable("accountId") Long accountId) {
		List<Record> records = recordService.getAllByAccountId(accountId);
		
		if (records == null) {
			return Constant.responseMessageError(HttpStatus.NOT_FOUND, "Error", "An error ocurred while performing the operation, the records were not found", String.format("Records for account with id %s were not found", accountId), "id");
		}
		
		return Constant.responseMessage(HttpStatus.OK, "Success", records);
		
	}
	
	@ResponseBody
	@GetMapping(value = "account/{accountId}/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchByAccountAndFilters(@PathVariable("accountId") Long accountId, @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword, @RequestParam(value = "start", required = false) String start, @RequestParam(value = "end", required = false) String end) {
		List<Record> records = recordService.searchRecordByAccountAndFilters(accountId, keyword, start, end);
		
		if (records == null) {
			return Constant.responseMessageError(HttpStatus.NOT_FOUND, "Error", "An error ocurred while performing the operation, the records were not found", String.format("Records for account with id %s were not found", accountId), "id");
		}
		
		return Constant.responseMessage(HttpStatus.OK, "Success", records);
		
	}
}
