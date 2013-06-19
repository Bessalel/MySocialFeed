package com.mysocialfeed;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.objectify.annotation.*;

@Entity
@Index
@Cache
public class User implements java.io.Serializable {
	private @Id
	Long id;
	@Parent Key parent;
	private String username;
	private String email;
	private @Unindex
	String password;

	// Obligatoire pour Objectify
	private User() {
	}

	public User(String username, String email, String password) {
		this.parent = KeyFactory.createKey("MSFUser", "MSFUser");
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
