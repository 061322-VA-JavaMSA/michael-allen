package com.revature.daos;

import java.util.List;

import com.revature.models.Item;

public interface ItemDAO {
	Item createItem(Item i);
	String retrieveItemName(int id);
	List<Item> retrieveItems();
	List<Integer> retrieveItemIds();
	boolean deleteItemById(int id);
}
