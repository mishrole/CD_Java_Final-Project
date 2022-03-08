package com.mishrole.undercontrol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mishrole.undercontrol.entity.Type;
import com.mishrole.undercontrol.repository.TypeRepository;

@Service
public class TypeService implements ITypeService {
	
	@Autowired
	private TypeRepository typeRepository;

	@Override
	public Type findTypeById(Long id) {
		return typeRepository.findTypeById(id);
	}

	@Override
	public List<Type> getAll() {
		return typeRepository.findAll();
	}

}
