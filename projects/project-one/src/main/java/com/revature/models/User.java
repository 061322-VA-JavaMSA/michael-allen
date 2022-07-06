package com.revature.models;

import java.util.Objects;

public class User {

	private int user_id;
	private String username;
	private String password;
	private String user_fname;
	private String user_lname;
	private String user_email;
	private int user_role_id;
	
	public User() {
		super();
	}
	
	public User(int user_id, String username, String password, String user_fname, String user_lname, String user_email,
			int user_role_id) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.user_fname = user_fname;
		this.user_lname = user_lname;
		this.user_email = user_email;
		this.user_role_id = user_role_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_fname() {
		return user_fname;
	}

	public void setUser_fname(String user_fname) {
		this.user_fname = user_fname;
	}

	public String getUser_lname() {
		return user_lname;
	}

	public void setUser_lname(String user_lname) {
		this.user_lname = user_lname;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public int getUser_role_id() {
		return user_role_id;
	}

	public void setUser_role_id(int user_role_id) {
		this.user_role_id = user_role_id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(password, user_email, user_fname, user_id, user_lname, user_role_id, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(password, other.password) && Objects.equals(user_email, other.user_email)
				&& Objects.equals(user_fname, other.user_fname) && user_id == other.user_id
				&& Objects.equals(user_lname, other.user_lname) && user_role_id == other.user_role_id
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username + ", password=" + password + ", user_fname="
				+ user_fname + ", user_lname=" + user_lname + ", user_email=" + user_email + ", user_role_id="
				+ user_role_id + "]";
	}
	
}