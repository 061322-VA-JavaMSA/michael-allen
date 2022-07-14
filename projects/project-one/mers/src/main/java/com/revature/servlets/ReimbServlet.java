package com.revature.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exceptions.ReimbStatusNotUpdatedException;
import com.revature.exceptions.ReimbsNotFoundException;
import com.revature.exceptions.ReimbursementNotCreatedException;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbService;
import com.revature.util.CorsFix;

public class ReimbServlet extends HttpServlet {

	private ReimbService rs = new ReimbService();
	private ObjectMapper om = new ObjectMapper();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		CorsFix.addCorsHeader(req.getRequestURI(), res);
		res.addHeader("Content-Type", "application/json");
		
		String query = req.getQueryString();
		String[] arrOfStr = query.split("&");
		
		if(arrOfStr.length == 1) {
			String queryParam = arrOfStr[0];
			String[] arrOfQuery = queryParam.split("=");
			String status = arrOfQuery[1];
			
			try(PrintWriter pw = res.getWriter()) {
				List<Reimbursement> reimbs = rs.getPendingReimbs(status);
				pw.write(om.writeValueAsString(reimbs));
				res.setStatus(200);
			} catch(ReimbsNotFoundException e) {
				res.setStatus(404);
				e.printStackTrace();
			}
		}
		else if(arrOfStr.length == 2) {
			
			String fNameQuery = arrOfStr[0];
			String[] arrOfFName = fNameQuery.split("=");
			
			String lNameQuery = arrOfStr[1];
			String[] arrOflName = lNameQuery.split("=");
			
			String user = arrOfFName[1] + " " + arrOflName[1];
			
			try(PrintWriter pw = res.getWriter()) {
				List<Reimbursement> reimbs = rs.getEmployeeReimbs(user);
				pw.write(om.writeValueAsString(reimbs));
				res.setStatus(200);
			} catch(ReimbsNotFoundException e) {
				res.setStatus(404);
				e.printStackTrace();
			}
		}
		else {
		
			String fNameQuery = arrOfStr[0];
			String[] arrOfFName = fNameQuery.split("=");
			
			String lNameQuery = arrOfStr[1];
			String[] arrOflName = lNameQuery.split("=");
			
			String statusQuery = arrOfStr[2];
			String[] arrOfStatus = statusQuery.split("=");
	
			String user = arrOfFName[1] + " " + arrOflName[1];
			String status = arrOfStatus[1];
		
			if(!user.isEmpty() && !status.isEmpty()) {
				try(PrintWriter pw = res.getWriter()) {
					List<Reimbursement> reimbs = rs.getPendingReimbs(user, status);
					pw.write(om.writeValueAsString(reimbs));
					res.setStatus(200);
				} catch(ReimbsNotFoundException e) {
					res.setStatus(404);
					e.printStackTrace();
				}
			}
		}
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		CorsFix.addCorsHeader(req.getRequestURI(), res);
		
		InputStream reqBody = req.getInputStream();
		
		Reimbursement updatedReimb = om.readValue(reqBody, Reimbursement.class);
		
		int id = updatedReimb.getId();
		String status = updatedReimb.getStatus();
		String resolver = updatedReimb.getResolver();
		String resolved = updatedReimb.getResolved();

		try {
			rs.updateReimbStatus(id, status, resolver, resolved);
			
			res.setStatus(200); //Reimbursement was updated
		} catch (ReimbStatusNotUpdatedException e) {
			res.setStatus(400);;
			res.sendError(400, "Unable to create reimbursement.");
			e.printStackTrace();
		}
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
	
	//For preflight issues
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		CorsFix.addCorsHeader(req.getRequestURI(), res);
		super.doOptions(req, res);
	}
}
