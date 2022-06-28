package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.revature.models.Item;

class ItemServiceTest {

	ItemService is = new ItemService();
	
	public void getsItemsFromDB() {
		List<Item> expected = new ArrayList<>();
		Item item1 = new Item(1, "Simply Lemonade", 2, "Owned");
		Item item2 = new Item(3, "Oreo Cookies", 1, "Available");
		Item item3 = new Item(4, "Almond Breeze almond milk", 2.5, "Owned");
		
		expected.add(item1);
		expected.add(item2);
		expected.add(item3);
		
		List<Item> actual = is.getItems();
		
		assertEquals(expected, actual);
	}
	

	public void getsAvailableItemsFromDB() {
		List<Item> expected = new ArrayList<>();
		Item availItem1 = new Item(3, "Oreo Cookies", 1, "Available");
		
		expected.add(availItem1);
		
		List<Item> actual = is.getAvailableItems();
		
		assertEquals(expected, actual);
	}
	
	
	public void getsOwnedItemsFromDB() {
		List<String> expected = new ArrayList<>();
		String string1 = "Simply Lemonade";
		
		expected.add(string1);
		
		List<String> actual = is.getOwnedItems("mikeyboi");
		
		assertEquals(expected, actual);
	}
	

	public void getsItemNameFromDB() {
		assertEquals("Simply Lemonade", is.getItemName(1));
	}
	
	@Test
	public void createItemInDB() throws Exception {
		Item expected = new Item("Hot dog", 3, "Available");
		Item actual = is.createItem(expected);
		
		assertEquals(expected, actual);
	}
	
	
	public void getIdsOfDBItems() {
		List<Integer> expected = new ArrayList<>();
		
		expected.add(1);
		expected.add(3);
		expected.add(4);
		expected.add(5);
		
		List<Integer> actual = is.getIds();
		
		assertEquals(expected, actual);
	}
	
	public void updateOwnedStatusInDB() {
		boolean expected = true;
		boolean actual = is.updateOwnedStatus(3);
		
		assertEquals(expected, actual);
	}
	
	public void deleteItemFromDB() {
		boolean expected = true;
		boolean actual = is.deleteItemById(6);
		
		assertEquals(expected, actual);
	}

}
