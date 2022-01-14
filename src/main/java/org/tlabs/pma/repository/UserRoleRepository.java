package org.tlabs.pma.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int save(int userId, int roleId) {
		int result = jdbcTemplate.update("insert into user_role values(?,?)",userId,roleId);
		return result;
	}

}
