package com.revature.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.revature.models.Offer;
import com.revature.services.ItemService;
import com.revature.util.ConnectionUtil;

public class OfferPostgres implements OfferDAO {
	
	@Override
	public Offer createOffer(Offer o) {
		String sql = "INSERT INTO offers (customer, item, status, item_id) VALUES (?,?,?,?);";
		
		try(Connection c = ConnectionUtil.getConnection()){
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, o.getCustomer());
			ps.setString(2, o.getItem());
			ps.setString(3, o.getStatus());
			ps.setInt(4, o.getItem_id());
			ps.executeUpdate();

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return o;
	}

	@Override
	public List<Offer> retreiveUserOffers(String user) {
		String sql = "SELECT * FROM offers WHERE customer=?;";
		List<Offer> offers = new ArrayList<>();
		
		try(Connection c = ConnectionUtil.getConnection()) {
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, user);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Offer o = new Offer();
				o.setId(rs.getInt("id"));
				o.setCustomer(rs.getString("customer"));
				o.setItem(rs.getString("item"));
				o.setStatus(rs.getString("status"));
				
				offers.add(o);
			}
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return offers;
	}
	
	@Override
	public int retrieveOfferById(int id, String user) {
		String sql = "SELECT item_id FROM offers WHERE item_id = ? AND customer = ?;";
		int itemId = 0;
		
		try(Connection c = ConnectionUtil.getConnection()){
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, user);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				itemId = rs.getInt("item_id");
			}
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return itemId;
	}
	
	@Override
	public List<Integer> retrieveOfferIds() {
		String sql = "SELECT id FROM offers;";
		List<Integer> ids = new ArrayList<>();
		
		try(Connection c = ConnectionUtil.getConnection()) {
			
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				ids.add(rs.getInt("id"));
			}
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ids;
	}
	
	@Override
	public boolean deleteOfferById(int id) {
		String sql = "DELETE FROM offers WHERE id = ?;";
		int rowsChanged = 0;

		try(Connection c = ConnectionUtil.getConnection()){
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setInt(1, id);
			rowsChanged = ps.executeUpdate();
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(rowsChanged == 0) {return false;}
		else {return true;}
	}
	
	@Override
	public List<Offer> retreivePendingOffers() {
		String sql = "SELECT * FROM offers WHERE status = 'Pending';";
		List<Offer> offers = new ArrayList<>();
		
		try(Connection c = ConnectionUtil.getConnection()) {
			
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				Offer o = new Offer();
				o.setId(rs.getInt("id"));
				o.setCustomer(rs.getString("customer"));
				o.setItem(rs.getString("item"));
				o.setStatus(rs.getString("status"));
				
				offers.add(o);
			}
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return offers;
	}

	@Override
	public boolean acceptOffer(int id) {
		
		ItemService is = new ItemService();
		int itemId = 0;
		
		String sql1 = "UPDATE offers SET status = 'Accepted' WHERE id = ?;";
		int rowsChanged = 0;
		
		try(Connection c = ConnectionUtil.getConnection()){
			PreparedStatement ps = c.prepareStatement(sql1);
			
			ps.setInt(1, id);
			rowsChanged = ps.executeUpdate();
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql2 = "SELECT item_id FROM offers WHERE id = ?";
		
		try(Connection c = ConnectionUtil.getConnection()){
			PreparedStatement ps = c.prepareStatement(sql2);
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				itemId = rs.getInt("item_id");
			}
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String itemName = is.getItemName(itemId);
		String sql3 = "UPDATE offers SET status = 'Rejected' WHERE item = '" + itemName + "' AND status = 'Pending';";
		
		try(Connection c = ConnectionUtil.getConnection()){
			
			Statement s = c.createStatement();
			s.executeUpdate(sql3);
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		is.updateOwnedStatus(itemId);
		
		if(rowsChanged == 0) {return false;}
		else {return true;}
		
	}

	@Override
	public boolean rejectOffer(int id) {
		String sql1 = "UPDATE offers SET status = 'Rejected' WHERE id = ?;";
		int rowsChanged = 0;
		
		try(Connection c = ConnectionUtil.getConnection()){
			PreparedStatement ps = c.prepareStatement(sql1);
			
			ps.setInt(1, id);
			rowsChanged = ps.executeUpdate();
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(rowsChanged == 0) {return false;}
		else {return true;}
	}

}
