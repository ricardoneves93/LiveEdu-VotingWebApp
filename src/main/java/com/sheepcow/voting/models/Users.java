package com.sheepcow.voting.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;


public class Users {
	@Id
	public String id;
	public String username;
	public String password;
	public List<String> roles;
	
	public Users(String username, String password, List<String> roles) {
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", password=" + password + ", roles=" + roles + "]";
	}
	
	
}
