package com.revature.dtos;

import java.util.Objects;

public class UserDTO {
	
	private int user_id;
	private String username;
	private String user_fname;
	private String user_lname;
	private String user_email;
	private int user_role_id;
	public UserDTO() {
		super();
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
		return Objects.hash(user_email, user_fname, user_id, user_lname, user_role_id, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return Objects.equals(user_email, other.user_email) && Objects.equals(user_fname, other.user_fname)
				&& user_id == other.user_id && Objects.equals(user_lname, other.user_lname)
				&& user_role_id == other.user_role_id && Objects.equals(username, other.username);
	}
	@Override
	public String toString() {
		return "UserDTO [user_id=" + user_id + ", username=" + username + ", user_fname=" + user_fname + ", user_lname="
				+ user_lname + ", user_email=" + user_email + ", user_role_id=" + user_role_id + "]";
	}

}
