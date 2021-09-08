package org.tlabs.pma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tlabs.pma.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
		
	public User findByEmail(String email);
		 
}
