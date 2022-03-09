package com.mishrole.undercontrol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mishrole.undercontrol.entity.Account;
import com.mishrole.undercontrol.entity.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
	public abstract Record findRecordById(Long id);
	public abstract List<Record> findRecordByAccount(Account account);
	
	@Query(value = "Select * from Records r, records_categories rc where r.record_category_id = rc.id and (r.name like :keyword or r.concept like :keyword or rc.name like :keyword)\r\n"
			+ "and ((:start IS NULL OR r.recordDate >= :start) and (:end IS NULL OR r.recordDate < :end)) and r.account_id = :accountId group by r.id", nativeQuery = true)
	public abstract List<Record> searchRecordByAccountAndFilters(@Param(value = "accountId") Long accountId, @Param(value = "keyword") String keyword, @Param(value = "start") String start, @Param(value = "end") String end);
}
