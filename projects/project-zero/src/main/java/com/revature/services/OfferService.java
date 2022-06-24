package com.revature.services;

import com.revature.daos.OfferDAO;
import com.revature.daos.OfferPostgres;
import com.revature.models.Offer;

public class OfferService {
	
	private OfferDAO odao = new OfferPostgres();
	
	public Offer makeOffer(Offer o) throws Exception {
		odao.createOffer(o);
		return null;
	}
}
