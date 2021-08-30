package org.tlabs.pma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tlabs.pma.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	/*@Query(nativeQuery=true,value="select * from user where first_name=?1 and last_name=?2")
	public User findUserByFullName(String firstName,String lastName);*/
	
	public User findByFirstName(String firstName);
 
}
