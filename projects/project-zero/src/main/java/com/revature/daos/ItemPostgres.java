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
		String sql = "INSERT INTO items (name, price) VALUES (?,?);";
		
		try(Connection c = ConnectionUtil.getConnection()){
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, i.getName());
			ps.setDouble(2, i.getPrice());	
			ps.executeUpdate();

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
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

	@Override
	public boolean deleteItemById(Item i) {
		// TODO Auto-generated method stub
		return false;
	}

}
