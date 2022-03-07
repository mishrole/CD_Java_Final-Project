package com.mishrole.undercontrol.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mishrole.undercontrol.entity.Role;
import com.mishrole.undercontrol.entity.User;
import com.mishrole.undercontrol.repository.UserRepository;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
	
	//@Autowired
	//private UserRepository userRepository;
	
    private UserRepository userRepository;
    
    public UserDetailsServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> potentialUser = userRepository.findByEmail(username);
		
		if (!potentialUser.isPresent()) {
			throw new UsernameNotFoundException("User not found");
		}
		
		User user = potentialUser.get();
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user));
	}
	
	private List<GrantedAuthority> getAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for(Role role : user.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
            authorities.add(grantedAuthority);
        }
        return authorities;
	}
	
	
}
