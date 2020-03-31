package com.techelevator.model.reservation;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.techelevator.model.campground.Campground;
import com.techelevator.model.site.Site;

public interface ReservationDAO {

	//getting a list of sites by whether the arrival and departure date conflicts with any made reservations
	public List<Site> getSitesByRes(Campground campId, LocalDate aDate, LocalDate dDate);
	
	
	// take the reservation date and insert it to the database
	public Reservation makeReservation(Site siteId, String resName, LocalDate aDate, LocalDate dDate);
}
