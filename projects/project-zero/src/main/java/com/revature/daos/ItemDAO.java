package com.revature.daos;

import java.util.List;

import com.revature.models.Item;

public interface ItemDAO {
	Item createItem(Item i);
	List<Item> retrieveItems();
	boolean deleteItemById(Item i);
}
