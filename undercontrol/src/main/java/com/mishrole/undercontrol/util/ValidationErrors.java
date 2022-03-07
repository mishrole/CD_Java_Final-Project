package com.mishrole.undercontrol.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ValidationErrors {
	
	public static List<Map<String, Object>> mapErrors(BindingResult result) {
		List<Map<String, Object>> errors = new ArrayList<Map<String, Object>>();
		
		if (result.hasErrors()) {

			for (ObjectError error : result.getAllErrors()) {
				if (error instanceof FieldError) {
					FieldError fe = (FieldError) error;
					Map<String, Object> current = new HashMap<String, Object>();
					current.put(fe.getField(), fe.getDefaultMessage());
					errors.add(current);
				}
			}
			
		}
		
		return errors;
	}
}
