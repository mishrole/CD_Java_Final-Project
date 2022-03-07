package com.mishrole.undercontrol.entity.response;

import java.util.List;

import org.springframework.lang.Nullable;

public class APIResponse {
	
	@Nullable
	private String title;
	@Nullable
	private String detail;
	@Nullable
	private Object data;
	@Nullable
	private List<?> errors;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public List<?> getErrors() {
		return errors;
	}
	
	public void setErrors(List<?> errors) {
		this.errors = errors;
	}
	
	public APIResponse(String title, Object data) {
		this.title = title;
		this.data = data;
	}
	
	public APIResponse(String title, String detail, List<?> errors) {
		this.title = title;
		this.detail = detail;
		this.errors = errors;
	}
}
