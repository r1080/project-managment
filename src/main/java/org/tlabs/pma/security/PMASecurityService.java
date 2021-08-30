package org.tlabs.pma.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class PMASecurityService {
	
	private static final Logger logger = LoggerFactory.getLogger(PMASecurityService.class);
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	AuthenticationManager authenticationManager;

	public boolean authenticateLogin(String username, String password) {
		
		UserDetails user = userDetailsServiceImpl.loadUserByUsername(username);
		
		logger.info("User Details: {},{},{}" ,user.getUsername(),user.getPassword(),user.getAuthorities());
		
		UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(user,password, user.getAuthorities());
		logger.info("Before Authenticaton");
		authenticationManager.authenticate(authenticationToken);
		logger.info("After Authenticaton");
		boolean result = authenticationToken.isAuthenticated();
				logger.info("Result Of Authenticaton {}", result);
		if(result){
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		return result;
	}
	
	
	

}
