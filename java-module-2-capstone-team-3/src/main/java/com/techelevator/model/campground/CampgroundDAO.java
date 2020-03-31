package com.techelevator.model.campground;

import java.util.List;

import com.techelevator.model.park.Park;

public interface CampgroundDAO {
	
	
	//getting all of the campgrounds from the database
	public List<Campground> getAllCampgrounds();
	
	//pulling a campground byId when selected
	public List<Campground> searchCampgroundById();
	
	
	//being able to get all campgrounds by the park id it is
	//associated with
	public List<Campground> getCampgroundsByParkId(Park aPark);
	
	

}
