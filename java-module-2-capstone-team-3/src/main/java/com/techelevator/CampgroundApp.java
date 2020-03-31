package com.techelevator;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.model.campground.Campground;
import com.techelevator.model.campground.CampgroundDAO;
import com.techelevator.model.campground.JdbcCampgroundDAO;
import com.techelevator.model.park.JdbcParkDAO;
import com.techelevator.model.park.Park;
import com.techelevator.model.park.ParkDAO;
import com.techelevator.model.reservation.*;
import com.techelevator.model.site.JdbcSiteDAO;
import com.techelevator.model.site.Site;
import com.techelevator.model.site.SiteDAO;
import com.techelevator.view.*;

public class CampgroundApp {

	
	/****************************************************************************************************
	 * This is the Campground Reservation system application program
	 * 
	 * Any and all user interactions should be placed in the CampgroundUI class 
	 *     which is in the com.techelevator.view package.
	 *     
	 * This application program should instantiate a CampgroundUI object 
	 *      and use its methods to interact with the user.
	 *      
	 * This application program should contain no user interactions.
	 * 
	 * Any and all database accesses should be placed in the appropriate
	 *     com.techelevator.model.tablename package component
	 *     
	 * This application program should instantiate DAO objects and use the methods
	 *     in the DAO to interact with the database tables.   
	 *     
	 * There should be no SQL in this application program
	 *   
	 ***************************************************************************************************/
	private static ParkDAO parkDAO;		// instantiating DAO objects
	private  static CampgroundDAO campgroundDAO;
	private static SiteDAO siteDAO;
	private static ReservationDAO resDAO;
	
	
	public static void main(String[] args) throws Exception {
		// the application program is defining the data source we want to  use
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campgrounds");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		// Instantiating the DAO and passing it the data source
		parkDAO = new JdbcParkDAO(dataSource);
		campgroundDAO = new JdbcCampgroundDAO(dataSource);
		siteDAO = new JdbcSiteDAO(dataSource);
		resDAO = new JdbcReservationDAO(dataSource);
		
		CampgroundUI userInterface = new CampgroundUI();  // Object to manage user interactions;
		userInterface.run();
		
			
	}
	 	// all these methods connect our user interface with the JDBC
	public List<Park> getAllParks(){
		return parkDAO.getAllParks();
	}
	public List<Site> getSiteByCampgroundId(Campground campId){
		return siteDAO.getSiteByCampgroundId(campId);
	}
	public List<Campground> showAllCampgrounds(Park savedPark){
		return campgroundDAO.getCampgroundsByParkId(savedPark);
	}
	public List<Site> getSitesByRes(Campground campId, LocalDate fromDate, LocalDate toDate) {
		return resDAO.getSitesByRes(campId, fromDate, toDate);
	}
	public Reservation makeReservation(Site siteId, String resName, LocalDate aDate, LocalDate dDate) {
		return resDAO.makeReservation(siteId, resName, aDate, dDate);
	}
}
