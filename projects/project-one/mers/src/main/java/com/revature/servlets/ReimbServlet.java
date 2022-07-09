package com.revature.servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exceptions.ReimbursementNotCreatedException;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbService;
import com.revature.util.CorsFix;

public class ReimbServlet extends HttpServlet {

	private ReimbService rs = new ReimbService();
	private ObjectMapper om = new ObjectMapper();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		CorsFix.addCorsHeader(req.getRequestURI(), res);
		
		InputStream reqBody = req.getInputStream();
		
		Reimbursement newReimb = om.readValue(reqBody, Reimbursement.class);
		
		try {
			rs.createReimbursement(newReimb);
			
			res.setStatus(201); //Reimbursement was created
		} catch (ReimbursementNotCreatedException e) {
			res.setStatus(400);;
			res.sendError(400, "Unable to create reimbursement.");
			e.printStackTrace();
		}
	}
}
