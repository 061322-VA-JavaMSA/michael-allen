package com.revature.services;

import java.util.List;

import com.revature.daos.OfferDAO;
import com.revature.daos.OfferPostgres;
import com.revature.models.Offer;

public class OfferService {
	
	private OfferDAO odao = new OfferPostgres();
	
	public Offer makeOffer(Offer o) throws Exception {
		odao.createOffer(o);
		return null;
	}
	
	public boolean offerExists(int id, String user) {
		int itemId = odao.retrieveOfferById(id, user);
		
		if(itemId > 0) {return true;}
		else {return false;}
	}
	
	public List<Offer> getUserOffers(String user) {
		return odao.retreiveUserOffers(user);
	}
	
	public List<Integer> getIds() {
		return odao.retrieveOfferIds();
	}
	
	public boolean cancelOffer(int id) {
		return odao.deleteOfferById(id);
	}
	
	public List<Offer> getPendingOffers() {
		return odao.retreivePendingOffers();
	}
	
	public boolean acceptOffer(int id) throws Exception{
		if(odao.acceptOffer(id)) {return true;}
		else {return false;}
	}
	
	public boolean rejectOffer(int id) throws Exception{
		if(odao.rejectOffer(id)) {return true;}
		else {return false;}
	}

}
