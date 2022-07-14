package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.UserDTO;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.util.CorsFix;

public class UserServlet extends HttpServlet {

	UserService us = new UserService();
	private ObjectMapper om = new ObjectMapper();
	
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
				pw.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} //End If
		
	} //End doGet
	
} //End UserServlet
