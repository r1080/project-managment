package org.tlabs.pma.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.tlabs.pma.model.User;
import org.tlabs.pma.repository.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException("Invalid User - Not Found!");
		}

		return new org.springframework.security.core.userdetails.User(user.getFirstName(), user.getPassword(),
				user.getRoles());
	}

	public boolean registerUser(User user) {
		
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		User savedUser = userRepository.save(user);
		
		//return savedUser!=null ? true : false;
		return savedUser != null;
	}
	
	
	
}