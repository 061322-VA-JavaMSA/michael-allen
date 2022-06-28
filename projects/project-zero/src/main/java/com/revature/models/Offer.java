package com.revature.models;

import java.util.Objects;

public class Offer {
	private int id;
	private String customer;
	private String item;
	private int item_id;
	private String status;
	
	public Offer() {
		super();
	}
	
	public Offer(int id, String customer, String item, int item_id, String status) {
		this.id = id;
		this.customer = customer;
		this.item = item;
		this.item_id = item_id;
		this.status = status;
	}
	
	public Offer(String customer, String item, int item_id, String status) {
		this.customer = customer;
		this.item = item;
		this.item_id = item_id;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customer, id, item, item_id, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offer other = (Offer) obj;
		return Objects.equals(customer, other.customer) && id == other.id && Objects.equals(item, other.item)
				&& item_id == other.item_id && Objects.equals(status, other.status);
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", customer=" + customer + ", item=" + item + ", item_id=" + item_id + ", status="
				+ status + "]";
	}
	
	
}
