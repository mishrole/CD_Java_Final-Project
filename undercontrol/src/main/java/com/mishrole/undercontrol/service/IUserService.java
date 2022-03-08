package com.mishrole.undercontrol.service;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.mishrole.undercontrol.entity.User;
import com.mishrole.undercontrol.entity.request.ChangePwdUser;
import com.mishrole.undercontrol.entity.request.RegisterUser;

public interface IUserService {
	public abstract User findUserByEmail(String emial);
	public abstract List<User> getAll();
	//public abstract User save(User user);
	public abstract User update(Long id, User user, BindingResult result);
	public abstract User register(RegisterUser user, BindingResult result);
	public abstract void delete(Long id);
	public abstract User findUserById(Long id);
	public abstract List<User> findUserByEmailOrFullname(String keyword);
	public abstract User changePassword(Long id, ChangePwdUser user, BindingResult result);
}
