package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.models.User;
import com.revature.services.UserService;

public class UserServlet extends HttpServlet {

	UserService us = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("In doGet Method (UserServlet).");
		
		try {
			User user = us.getUserById(1);
			
			PrintWriter pw = res.getWriter();
			pw.write(user.toString());
			pw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
