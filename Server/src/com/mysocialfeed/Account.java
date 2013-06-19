package com.mysocialfeed;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.*;

@Entity
@Index
@Cache
public class Account implements java.io.Serializable {
	private @Id
	Long id;
	private @Parent
	Key<User> user;
	private String accountType;
	private String accountName;
	private String token;
	private @Unindex
	String tokenSecret;

	private Account() {
	} // Obligatoire pour Objectify

	public Account(Key<User> user, String accountType, String accountName,
			String token, String tokenSecret) {
		this.user = user;
		this.accountType = accountType;
		this.accountName = accountName;
		this.token = token;
		this.tokenSecret = tokenSecret;
	}

	public Key<User> getUser() {
		return user;
	}

	public void setUser(Key<User> user) {
		this.user = user;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenSecret() {
		return tokenSecret;
	}

	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;
	}
}
