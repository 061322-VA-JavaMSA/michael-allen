package com.revature.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Item;
import com.revature.util.ConnectionUtil;

public class ItemPostgres implements ItemDAO {

	@Override
	public Item createItem(Item i) {
		String sql = "INSERT INTO items (name, price, owned_status) VALUES (?,?,?);";
		
		try(Connection c = ConnectionUtil.getConnection()){
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, i.getName());
			ps.setDouble(2, i.getPrice());
			ps.setString(3, i.getOwnedStatus());
			ps.executeUpdate();

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}
	
	@Override
	public String retrieveItemName(int id) {
		String sql = "SELECT name FROM items WHERE id = ?;";
		String itemName = null;
		
		try(Connection c = ConnectionUtil.getConnection()){
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				itemName = rs.getString("name");
			}

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return itemName;
	}

	@Override
	public List<Item> retrieveItems() {
		String sql = "SELECT * FROM items;";
		List<Item> items = new ArrayList<>();
		
		try(Connection c = ConnectionUtil.getConnection()) {
			
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				Item i = new Item();
				i.setId(rs.getInt("id"));
				i.setName(rs.getString("name"));
				i.setPrice(rs.getDouble("price"));
				
				items.add(i);
			}
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return items;
	}
	
	public List<Item> retrieveAvailableItems() {
		String sql = "SELECT * FROM items WHERE owned_status = 'Available';";
		List<Item> items = new ArrayList<>();
		
		try(Connection c = ConnectionUtil.getConnection()) {
			
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				Item i = new Item();
				i.setId(rs.getInt("id"));
				i.setName(rs.getString("name"));
				i.setPrice(rs.getDouble("price"));
				
				items.add(i);
			}
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return items;
	}
	
	@Override
	public List<String> retrieveOwnedItems(String user) {
		String sql = "SELECT * FROM offers WHERE customer = '" + user + "' AND status = 'Accepted';";
		List<String> items = new ArrayList<>();
		
		try(Connection c = ConnectionUtil.getConnection()) {
			
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				String name = rs.getString("item");
				items.add(name);
			}
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return items;
	}
	
	@Override
	public List<Integer> retrieveItemIds() {
		String sql = "SELECT id FROM items;";
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
	public void updateOwnedStatus(int id) {
		String sql = "UPDATE items SET owned_status = 'Owned' WHERE id = ?";
		
		try(Connection c = ConnectionUtil.getConnection()){
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);	
			ps.executeUpdate();

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean deleteItemById(int id) {
		String sql = "DELETE FROM items WHERE id = ?;";
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


}
