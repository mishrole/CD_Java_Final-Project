package com.mishrole.undercontrol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mishrole.undercontrol.entity.Account;
import com.mishrole.undercontrol.entity.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
	public abstract Record findRecordById(Long id);
	public abstract List<Record> findRecordByAccount(Account account);
}
