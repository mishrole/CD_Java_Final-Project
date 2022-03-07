package com.mishrole.undercontrol.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.mishrole.undercontrol.entity.Role;
import com.mishrole.undercontrol.entity.User;
import com.mishrole.undercontrol.entity.request.RegisterUser;
import com.mishrole.undercontrol.repository.RoleRepository;
import com.mishrole.undercontrol.repository.UserRepository;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public User findUserByEmail(String email) {
		Optional<User> potentialUser = userRepository.findByEmail(email);
	
		if (!potentialUser.isPresent()) {
			return null;
		}
		
		return potentialUser.get();
	}

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public User findUserById(Long id) {
		Optional<User> potentialUser = userRepository.findById(id);
		
		if (!potentialUser.isPresent()) {
			return null;
		}
		
		return potentialUser.get();
	}

	@Override
	public User register(RegisterUser user, BindingResult result) {
		Optional<User> potentialUser = userRepository.findByEmail(user.getEmail());
		
		Boolean isValid = true;
		
		if (potentialUser.isPresent()) {
			result.rejectValue("email", "Matches", "The email is already taken!");
			isValid = false;
		}
		
		if (!(user.getPassword().equals(user.getConfirm()))) {
			result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
			isValid = false;
		}
		
		try {		
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date today = sdf.parse(sdf.format(new Date()));
			
	        Date birthday = user.getBirthday();
	        
	        if (birthday.after(today)) {
            	result.rejectValue("birthday", "Matches", "Birthday must be in the past");
            	isValid = false;
            }
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if (!isValid) {
			return null;
		}
		
		String encryptedPassword = passwordEncoder.encode(user.getPassword());
		
		User newUser = new User();
		BeanUtils.copyProperties(user, newUser);
		newUser.setPassword(encryptedPassword);
		
		Role role = roleRepository.findRoleById(user.getRoleId());
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		
		newUser.setRoles(roles);
		
		return userRepository.save(newUser);
		

		
	}

}
