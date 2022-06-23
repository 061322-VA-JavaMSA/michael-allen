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
	
	public void createItem(Item i) throws Exception {
		idao.createItem(i);
	}
}
