package com.revature.daos;

import java.util.List;

import com.revature.models.Offer;

public interface OfferDAO {
	//Customer offer methods
	Offer createOffer(Offer o);
	int retrieveOfferById(int id, String user);
	List<Offer> retreiveUserOffers(String user);
	List<Integer> retrieveOfferIds();
	boolean deleteOfferById(int id);
	
	//Employee offer methods
	List<Offer> retreivePendingOffers();
	boolean acceptOffer(int id);
	boolean rejectOffer(int id);
}
