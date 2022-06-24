package com.revature.models;

import java.util.Objects;

public class Offer {
	private int id;
	private String item;
	private String customer;
	private String status;
	
	public Offer() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		return Objects.hash(customer, id, item, status);
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
				&& Objects.equals(status, other.status);
	}
	@Override
	public String toString() {
		return "Offer [id=" + id + ", item=" + item + ", customer=" + customer + ", status=" + status + "]";
	}
	
}
