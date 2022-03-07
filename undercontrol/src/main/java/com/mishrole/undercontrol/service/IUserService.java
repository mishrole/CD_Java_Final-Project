package com.mishrole.undercontrol.service;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.mishrole.undercontrol.entity.User;
import com.mishrole.undercontrol.entity.request.RegisterUser;

public interface IUserService {
	public abstract User findUserByEmail(String emial);
	public abstract List<User> getAll();
	public abstract User save(User user);
	public abstract User register(RegisterUser user, BindingResult result);
	public abstract void delete(Long id);
	public abstract User findUserById(Long id);
}
