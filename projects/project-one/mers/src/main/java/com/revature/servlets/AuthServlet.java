package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.UserDTO;
import com.revature.exceptions.LoginException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.util.CorsFix;

public class AuthServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private AuthService as = new AuthService();
	private ObjectMapper om = new ObjectMapper();
	private Logger log = LogManager.getLogger(AuthServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		CorsFix.addCorsHeader(req.getRequestURI(), res);
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		try {
			User principal = as.login(username, password);
			
			UserDTO principalDTO = new UserDTO(principal);
			try(PrintWriter pw = res.getWriter()) {
				pw.write(om.writeValueAsString(principalDTO));
				res.setStatus(200);
				log.info("User successfully logged in.");
			}
			
		} catch(UserNotFoundException | LoginException e) {
			res.sendError(400, "Login unsuccessful.");
			log.error("Login unsuccessful.");
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		CorsFix.addCorsHeader(req.getRequestURI(), res);
		
		HttpSession session = req.getSession();
		
		session.invalidate();
	}
	
	//For preflight issues
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		CorsFix.addCorsHeader(req.getRequestURI(), res);
		super.doOptions(req, res);
	}
	
}
