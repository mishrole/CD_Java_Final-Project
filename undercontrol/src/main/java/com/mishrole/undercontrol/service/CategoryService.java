package com.mishrole.undercontrol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mishrole.undercontrol.entity.Category;
import com.mishrole.undercontrol.repository.CategoryRepository;

@Service
public class CategoryService implements ICategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category findCategoryById(Long id) {
		return categoryRepository.findCategoryById(id);
	}

	@Override
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}

}
