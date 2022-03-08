package com.mishrole.undercontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mishrole.undercontrol.entity.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
	public abstract Type findTypeById(Long id);
}
