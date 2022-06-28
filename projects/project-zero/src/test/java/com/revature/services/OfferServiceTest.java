package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.revature.models.Offer;

class OfferServiceTest {

	OfferService os = new OfferService();
	
	
	void createOfferInDB() throws Exception {
		Offer actual = new Offer("mikeyboi", "Oreo Cookies", 3, "Pending");
		Offer expected = os.makeOffer(actual);
		
		assertEquals(expected, actual);
	}
	
	
	void checkIfUserOfferIsInDB() {
		assertEquals(true, os.offerExists(1, "mikeyboi"));
	}
	
	
	void getUserOffersFromDB() {
		List<Offer> expected = new ArrayList<>();
		Offer offer1 = new Offer(1, "mikeyboi", "Simply Lemonade", 1, "Accepted");
		Offer offer2 = new Offer(6, "mikeyboi", "Oreo Cookies", 3, "Pending");
		
		expected.add(offer1);
		expected.add(offer2);
		
		List<Offer> actual = os.getUserOffers("mikeyboi");
		
		assertEquals(expected, actual);
	}
	
	
	void getOfferIdsFromDB() {
		List<Integer> expected = new ArrayList<>();
		Integer int1 = 1;
		Integer int2 = 2;
		Integer int3 = 6;
		
		expected.add(int1);
		expected.add(int2);
		expected.add(int3);
		
		List<Integer> actual = os.getIds();
		
		assertEquals(expected, actual);
	}
	
	
	void deleteOfferFromDB() {
		assertEquals(true, os.cancelOffer(6));
	}
	
	
	void returnPendingOffersFromDB() {
		List<Offer> expected = new ArrayList<>();
		Offer offer = new Offer(7, "mikeyboi", "Oreo Cookies", 3, "Pending");
		
		expected.add(offer);
		
		List<Offer> actual = os.getPendingOffers();
		
		assertEquals(expected, actual);
		
	}
	
	
	void offerAcceptedInDB() throws Exception {
		assertEquals(true, os.acceptOffer(7));
	}
	
	@Test
	void offerRejectedInDB() throws Exception {
		assertEquals(true, os.rejectOffer(7));
	}

}
