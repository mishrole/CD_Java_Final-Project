package com.mishrole.undercontrol.service;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.mishrole.undercontrol.entity.Record;

public interface IRecordService {
	public abstract Record findRecordById(Long id);
	public abstract List<Record> getAll();
	public abstract Record save(Record record, BindingResult result);
	public abstract List<Record> getAllByAccountId(Long accountId);
}
