package com.revature.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.revature.models.Offer;
import com.revature.util.ConnectionUtil;

public class OfferPostgres implements OfferDAO {

	@Override
	public Offer createOffer(Offer o) {
		String sql = "INSERT INTO offers (customer, offer, status) VALUES (?,?,?);";
		
		try(Connection c = ConnectionUtil.getConnection()){
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, o.getCustomer());
			ps.setString(2, o.getItem());
			ps.setString(3, o.getStatus());
			ps.executeUpdate();

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return o;
	}

	@Override
	public List<Offer> retreiveUserOffers(String user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Offer> retreivePendingOffers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean acceptOffer(int i) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean rejectOffer(int i) {
		// TODO Auto-generated method stub
		return false;
	}

}
