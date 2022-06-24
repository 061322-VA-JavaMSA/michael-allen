package com.revature.daos;

import java.util.List;

import com.revature.models.Offer;

public interface OfferDAO {
	//Customer offer methods
	Offer createOffer(Offer o);
	List<Offer> retreiveUserOffers(String user);
	
	//Employee offer methods
	List<Offer> retreivePendingOffers();
	boolean acceptOffer(int i);
	boolean rejectOffer(int i);
}
