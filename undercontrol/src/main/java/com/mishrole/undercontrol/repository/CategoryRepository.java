package com.mishrole.undercontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mishrole.undercontrol.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	public abstract Category findCategoryById(Long id);
}
