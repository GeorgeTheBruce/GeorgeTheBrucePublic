package com.techelevator.model.park;

import java.util.List;

public interface ParkDAO {
	
	//create a list of all parks
	public List<Park> getAllParks();
	
	//choose a park by its id
	public Park getParkById(Long parkId);

}
