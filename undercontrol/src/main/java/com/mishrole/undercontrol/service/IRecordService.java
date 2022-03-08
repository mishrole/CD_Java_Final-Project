package com.mishrole.undercontrol.service;

import java.util.List;
import com.mishrole.undercontrol.entity.Record;

public interface IRecordService {
	public abstract Record findRecordById(Long id);
	public abstract List<Record> getAll();
}
