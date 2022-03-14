package com.mishrole.undercontrol.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.mishrole.undercontrol.entity.Mail;
import com.mishrole.undercontrol.entity.Role;
import com.mishrole.undercontrol.entity.User;
import com.mishrole.undercontrol.entity.request.ChangePwdUser;
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
	
	@Autowired
	private JavaMailSender emailSender;

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
		
		//Role role = roleRepository.findRoleById(user.getRoleId());
		// Only user role
		Role role = roleRepository.findRoleById(2l);
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		
		newUser.setRoles(roles);
		
		return userRepository.save(newUser);
		
	}

	@Override
	public List<User> findUserByEmailOrFullname(String keyword) {
		return userRepository.findUserByEmailOrName(keyword+"%");
	}

	@Override
	public User update(Long id, User user, BindingResult result) {
		Optional<User> checkUser = userRepository.findById(id);
		Optional<User> potentialUser = userRepository.findByEmail(user.getEmail());
		
		if (!(potentialUser.isPresent()) || !(checkUser.isPresent())) {
			result.rejectValue("id", "Matches", "User with Id " + id + " not found");
			return null;
		}
		
		if (!(potentialUser.get().getId().equals(checkUser.get().getId()))) {
			result.rejectValue("email", "Matches", "Email provided doesn't match the user with Id " + id);
			return null;
		}
		
		Boolean isValid = true;
		
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
		
		User savedUser = checkUser.get();
		savedUser.setFirstname(user.getFirstname());
		savedUser.setLastname(user.getLastname());
		savedUser.setBirthday(user.getBirthday());
		
		return userRepository.save(savedUser);
	}

	@Override
	public User changePassword(Long id, ChangePwdUser user, BindingResult result) {
		Optional<User> checkUser = userRepository.findById(id);
		User savedUser = checkUser.get();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		
		if (!savedUser.getEmail().equals(username)) {
			result.rejectValue("id", "Matches", "Id provided doesn't match logged user");
			return null;
		}
		

		String rawPassword = user.getCurrent();

		if (!passwordEncoder.matches(rawPassword, savedUser.getPassword())) {
			result.rejectValue("current", "Matches", "Current Password provided doesn't match the user password");
			return null;
		}
		
		Boolean isValid = true;
		
		if (!(user.getPassword().equals(user.getConfirm()))) {
			result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
			isValid = false;
		}
		
		if (!isValid) {
			return null;
		}

		String encryptedPassword = passwordEncoder.encode(user.getPassword());
		savedUser.setPassword(encryptedPassword);
		
		return userRepository.save(savedUser);
	}

	public void sendSimpleMessage(Mail mail) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject(mail.getSubject());
		message.setText(mail.getContent());
		message.setTo(mail.getTo());
		message.setFrom(mail.getFrom());
		
		emailSender.send(message);
	}

}
