package com.mishrole.undercontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mishrole.undercontrol.entity.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
	public abstract Currency findCurrencyById(Long id);
}
