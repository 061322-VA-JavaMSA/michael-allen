package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserPostgres implements UserDAO {

	@Override
	public User createUser(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User retrieveUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> retrieveUsers() {
		String sql = "select * from users;";
		List<User> users = new ArrayList<>();
		
		try(Connection c = ConnectionUtil.getConnectionFromEnv()) {
			//no user input taken, no need for prepared statement
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				// extract each field from rs for each record, map them to a User object and add them to the users array list
				User u= new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				
				users.add(u);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}

	@Override
	public User retrieveUserByUserName(String username) {
		String sql = "select * from users where username = ?;";
		User u = null;
		
		try (Connection c = ConnectionUtil.getHardcodedConnection();) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, username); // this refers to the 1st "?" in the sql string, allows us to inject data
			
			ResultSet rs = ps.executeQuery();
			
			u = new User();
			u.setId(rs.getInt("id"));
			u.setUsername(rs.getString("username"));
			u.setPassword(rs.getString("password"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean updateUser(User u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUserById(int id) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
