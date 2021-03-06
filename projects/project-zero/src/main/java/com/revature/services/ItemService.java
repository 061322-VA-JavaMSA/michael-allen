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
	
	public List<Item> getAvailableItems() {
		return idao.retrieveAvailableItems();
	}
	
	public List<String> getOwnedItems(String user) {
		return idao.retrieveOwnedItems(user);
	}
	
	public String getItemName(int id) {
		return idao.retrieveItemName(id);
	}
	
	public Item createItem(Item i) throws Exception {
		return idao.createItem(i);
	}
	
	public List<Integer> getIds() {
		return idao.retrieveItemIds();
	}
	
	public boolean updateOwnedStatus(int id) {
		return idao.updateOwnedStatus(id);
	}
	
	public boolean deleteItemById(int id) {
		return idao.deleteItemById(id);
	}
}
