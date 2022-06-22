package com.revature.mikeshop.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long id;
	private String name;
	private double price;
	private String offerStatus;
	private int ownerUserId;
	
	public Item(Long id, String name, double price, String offerStatus, int ownerUserId) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.offerStatus = offerStatus;
		this.ownerUserId = ownerUserId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getOfferStatus() {
		return offerStatus;
	}
	public void setOfferStatus(String offerStatus) {
		this.offerStatus = offerStatus;
	}
	public int getOwnerUserId() {
		return ownerUserId;
	}
	public void setOwnerUserId(int ownerUserId) {
		this.ownerUserId = ownerUserId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, name, offerStatus, ownerUserId, price);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(offerStatus, other.offerStatus) && ownerUserId == other.ownerUserId
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price);
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", price=" + price + ", offerStatus=" + offerStatus
				+ ", ownerUserId=" + ownerUserId + "]";
	}
	
}
