package org.tlabs.pma.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		LOGGER.info("Username {} ", username);
		User user = userRepository.findByEmail(username);
		
		LOGGER.info("User in loadByUserName {} ", user);

		if (user == null) {
			throw new UsernameNotFoundException("Invalid User - Not Found!");
		}

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
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