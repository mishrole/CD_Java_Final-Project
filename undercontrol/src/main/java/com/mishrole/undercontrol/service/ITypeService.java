package com.mishrole.undercontrol.service;

import java.util.List;

import com.mishrole.undercontrol.entity.Type;

public interface ITypeService {
	public abstract Type findTypeById(Long id);
	public abstract List<Type> getAll();
}
