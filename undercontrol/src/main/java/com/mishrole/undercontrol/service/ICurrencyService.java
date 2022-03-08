package com.mishrole.undercontrol.service;

import java.util.List;

import com.mishrole.undercontrol.entity.Currency;

public interface ICurrencyService {
	public abstract Currency findCurrencyById(Long id);
	public abstract List<Currency> getAll();
}
