package org.tlabs.pma.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="role")
public class Role implements GrantedAuthority {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private String name;
	
	@ManyToMany(mappedBy="roles")
	private Set<User> users;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return name;
	}

	public Role() {

	}

}
