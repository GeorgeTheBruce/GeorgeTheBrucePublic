package com.techelevator.model.site;

import java.util.List;

import com.techelevator.model.campground.Campground;

public interface SiteDAO {

	// DAO to set up getAllSites by campground id
	
	public List<Site> getSiteByCampgroundId(Campground campId);
	
	
	
}
