package com.revature.daos;

import java.util.List;

import com.revature.models.Item;

public interface ItemDAO {
	Item createItem(Item i);
	String retrieveItemName(int id);
	List<Item> retrieveItems();
	List<Item> retrieveAvailableItems();
	List<String> retrieveOwnedItems(String user);
	List<Integer> retrieveItemIds();
	boolean updateOwnedStatus(int id);
	boolean deleteItemById(int id);
}
