package com.mishrole.undercontrol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mishrole.undercontrol.entity.Record;
import com.mishrole.undercontrol.repository.RecordRepository;

@Service
public class RecordService implements IRecordService {
	
	@Autowired
	private RecordRepository recordRepository;

	@Override
	public Record findRecordById(Long id) {
		return recordRepository.findRecordById(id);
	}

	@Override
	public List<Record> getAll() {
		return recordRepository.findAll();
	}

}
