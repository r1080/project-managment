package org.tlabs.pma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.tlabs.pma.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(nativeQuery = true, value = "select * from user where email= :email")
	public User findByEmail(@Param("email") String email);
		 
}
