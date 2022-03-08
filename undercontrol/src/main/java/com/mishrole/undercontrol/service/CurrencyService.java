package com.mishrole.undercontrol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mishrole.undercontrol.entity.Currency;
import com.mishrole.undercontrol.repository.CurrencyRepository;

@Service
public class CurrencyService implements ICurrencyService {
	
	@Autowired
	private CurrencyRepository currencyRepository;

	@Override
	public Currency findCurrencyById(Long id) {
		return currencyRepository.findCurrencyById(id);
	}

	@Override
	public List<Currency> getAll() {
		return currencyRepository.findAll();
	}

}
