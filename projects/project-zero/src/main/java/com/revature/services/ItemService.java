package com.revature.services;

import java.util.List;

import com.revature.daos.ItemDAO;
import com.revature.daos.ItemPostgres;
import com.revature.models.Item;

public class ItemService {

	private ItemDAO idao = new ItemPostgres();
	
	public List<Item> getItems() {
		return idao.retrieveItems();
	}
	
	public String getItemName(int id) {
		return idao.retrieveItemName(id);
	}
	
	public void createItem(Item i) throws Exception {
		idao.createItem(i);
	}
	
	public List<Integer> getIds() {
		return idao.retrieveItemIds();
	}
	
	public boolean deleteItemById(int id) {
		return idao.deleteItemById(id);
	}
}
