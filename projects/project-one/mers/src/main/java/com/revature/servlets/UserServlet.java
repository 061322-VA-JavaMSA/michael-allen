package com.revature.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.UserDTO;
import com.revature.exceptions.UserNotUpdatedException;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.CorsFix;

public class UserServlet extends HttpServlet {

	UserService us = new UserService();
	private ObjectMapper om = new ObjectMapper();
	private static Logger log = LogManager.getLogger(UserServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		CorsFix.addCorsHeader(req.getRequestURI(), res);
		res.addHeader("Content-Type", "application/json");
		
		String query = req.getQueryString();
		
		String[] arrOfQuery = query.split("=");
		String role = arrOfQuery[1];
		
		if(role.equals("Employee")) {
			try {
				List<User> users = us.getEmployees();
				List<UserDTO> usersDTO = new ArrayList<>();
				
				//convert Users to UserDTOs
				users.forEach(u -> usersDTO.add(new UserDTO(u)));
				
				PrintWriter pw = res.getWriter();
				pw.write(om.writeValueAsString(usersDTO));
				res.setStatus(200);
				log.info("Employees retrieved successfully.");
				pw.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error(e.fillInStackTrace());
			}
		} //End If
		
	} //End doGet
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		CorsFix.addCorsHeader(req.getRequestURI(), res);
		
		InputStream reqBody = req.getInputStream();
		
		User updatedUser = om.readValue(reqBody, User.class);
		
		int id = updatedUser.getId();
		String fname = updatedUser.getFirstName();
		String lname = updatedUser.getLastName();
		String username = updatedUser.getUsername();
		String email = updatedUser.getEmail();
		
		try {
			us.updateUser(id, fname, lname, username, email);
			
			res.setStatus(200);
			log.info("User information was updated successfully.");
		} catch (UserNotUpdatedException e) {
			res.setStatus(400);
			res.sendError(400, "Unable to update user information.");
			log.error("Unable to update user information.");
		}
	}
	
	//For preflight issues
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		CorsFix.addCorsHeader(req.getRequestURI(), res);
		super.doOptions(req, res);
	}
	
} //End UserServlet
