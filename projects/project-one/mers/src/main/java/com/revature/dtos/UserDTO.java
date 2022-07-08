package com.revature.dtos;

import java.util.Objects;

import com.revature.models.Role;
import com.revature.models.User;

public class UserDTO {

	private int id;
	private String username;
	private String firstName;
	private String lastName;
	private String userEmail;
	private Role role;

	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDTO(User u) {
		id = u.getId();
		username = u.getUsername();
		firstName = u.getFirstName();
		lastName = u.getLastName();
		userEmail = u.getUserEmail();
		role = u.getRole();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUser_email() {
		return userEmail;
	}

	public void setUser_email(String user_email) {
		this.userEmail = user_email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, id, lastName, role, userEmail, username);
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
		return Objects.equals(firstName, other.firstName) && id == other.id && Objects.equals(lastName, other.lastName)
				&& Objects.equals(role, other.role) && Objects.equals(userEmail, other.userEmail)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", user_email=" + userEmail + ", role=" + role + "]";
	}

}
