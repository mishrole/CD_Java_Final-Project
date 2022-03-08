package com.mishrole.undercontrol.service;

import java.util.List;

import com.mishrole.undercontrol.entity.Category;

public interface ICategoryService {
	public abstract Category findCategoryById(Long id);
	public abstract List<Category> getAll();
}
