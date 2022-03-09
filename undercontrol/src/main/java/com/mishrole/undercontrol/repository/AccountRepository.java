package com.mishrole.undercontrol.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mishrole.undercontrol.entity.Account;
import com.mishrole.undercontrol.entity.User;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	public abstract Account findAccountById(Long id);
	public abstract Optional<Account> findByNameAndOwner(String name, User owner);
	public abstract List<Account> findByOwner(User user);
	public abstract Optional<Account> findById(Long id);
	
	@Query(value = "Select (Select sum(r.amount) as Income from Records r, records_types rt where r.record_type_id = rt.id and r.record_type_id = 1 and r.account_id = :accountId) as total;", nativeQuery = true)
	public abstract BigDecimal totalIncomeByAccount(@Param(value = "accountId") Long accountId);
	
	@Query(value = "Select (Select sum(r.amount) as Expense from Records r, records_types rt where r.record_type_id = rt.id and r.record_type_id = 2 and r.account_id = :accountId) as total;", nativeQuery = true)
	public abstract BigDecimal totalExpenseByAccount(@Param(value = "accountId") Long accountId);
	
	@Query(value = "Select (Select sum(r.amount) as Income from Records r, records_types rt where r.record_type_id = rt.id and r.record_type_id = 1 and r.account_id = :accountId) - (Select sum(r.amount) as Expense from Records r, records_types rt where r.record_type_id = rt.id and r.record_type_id = 2 and r.account_id = :accountId) as total;", nativeQuery = true)
	public abstract BigDecimal totalBalanceByAccount(@Param(value = "accountId") Long accountId);
}
