package com.techelevator.view;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.CampgroundApp;
import com.techelevator.model.campground.Campground;
import com.techelevator.model.campground.CampgroundDAO;
import com.techelevator.model.campground.JdbcCampgroundDAO;
import com.techelevator.model.park.JdbcParkDAO;
import com.techelevator.model.park.Park;
import com.techelevator.model.park.ParkDAO;
import com.techelevator.model.reservation.Reservation;
import com.techelevator.model.reservation.ReservationDAO;
import com.techelevator.model.site.JdbcSiteDAO;
import com.techelevator.model.site.Site;
import com.techelevator.model.site.SiteDAO;




public class CampgroundUI {

	/*******************************************************************************************************
	 * This is the CampgroundUI class
	 * 
	 * All user interactions should be coded here
	 * 
	 * The application program will instantiate an object of this class and use the
	 * object to interact with the user.
	 * 
	 * And data required from the user for the application will be acquired by
	 * methods of this class and sent back to the user either as the return value
	 * from the method or in an object returned from the method.
	 * 
	 * Any messages the application programmer wishes to display to the user may be
	 * sent to methods of this class as variables or objects. Any messaging method
	 * may also have "canned" messages the user may request by a message id.
	 * 
	 *******************************************************************************************************/

	/*******************************************************************************************************
	 * Menu class object
	 * 
	 * Use this Menu object for ALL Menu choices presented to the user
	 * 
	 * This is the same Menu class discussed in module 1 and made available in the
	 * module 1 capstone
	 * 
	 * 
	 ******************************************************************************************************/

	Menu CampMenu = new Menu(System.in, System.out); // Define menu for keyboard input and screen output
	CampgroundApp campApp = new CampgroundApp();
	/*******************************************************************************************************
	 * Class member variables, objects, constants and methods should be coded here.
	 ******************************************************************************************************/
	/********************************************************************************
	 * Define String constants for the menu option text/choices named constants are
	 * a good idea s they make the code easier to follow and use
	 *******************************************************************************/

	
	
	//main menu
	final String MAIN_MENU_OPTION_1 = "Parks";
	final String MAIN_MENU_OPTION_EXIT = "Exit";
	final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_1, MAIN_MENU_OPTION_EXIT };
	//campgrounds menu
	final String PARK_MENU_OPTION_1 ="View Campgrounds";
	//final String PARK_MENU_OPTION_2	="Search information";
	final String PARK_MENU_OPTION_3 ="Return to previous Screen";
	final String[] PARKS_MENU_OPTIONS = { PARK_MENU_OPTION_1, PARK_MENU_OPTION_3};

	//site menu
	final String SITE_MENU_OPTION_1 ="Find sites you can make a reservation at";
	//final String SITE_MENU_OPTION_2	="Make a reservation";
	final String SITE_MENU_OPTION_3 = "Return to the previous screen";
	final String[] SITE_MENU_OPTIONS = { SITE_MENU_OPTION_1,SITE_MENU_OPTION_3};

		// storing global variables 
	Park savedPark;
	Campground savedSites;
	Campground savedCamp;
	LocalDate fromUserDate;
	LocalDate toUserDate;
	


		// main menu 
	public void run()  {
		// DISPLAY BANNER HERE
		while (true) {
			printHeading("Main Menu");
			String choice = (String) CampMenu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_1)) {
				showAllParks();
				parksMenu();  // sending user to sub menu
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.exit(0); // exiting if sub menu not selected
			}
		}
	}
	
	
	private void parksMenu()  {

		while (true) {
			printHeading("What would you like to do next?");
			String choice = (String) CampMenu.getChoiceFromOptions(PARKS_MENU_OPTIONS);
			if (choice.equals(PARK_MENU_OPTION_1)) {
			
				showAllCampgrounds(savedPark); 	// shows all the information for the park selected
				siteMenu();						// takes user to the sites

			}else if(choice.equals(PARK_MENU_OPTION_3)) {
				run();							// take user to the previous screen
			}
			
		}
	}
	
	
	private void siteMenu()  {			// site menu
		while (true) {
		printHeading("Please select a campground?");
		String choice = (String) CampMenu.getChoiceFromOptions(SITE_MENU_OPTIONS);
		if (choice.equals(SITE_MENU_OPTION_1)) {
			//showAllCampgrounds(savedPark);
			reservations(savedCamp);	// sending user to the reservations method where they can book a reservation
		}else if(choice.equals(SITE_MENU_OPTION_3)) {
			parksMenu();				// returns user to the previous menu
		}
	}}
	
	
	

	private void reservations(Campground savedSites) {
			// taking user input
			// to check reservation database to show possible campsites that can be booked
		System.out.println("Here is the campground you selected");
		System.out.println(savedSites.getName());
		Campground campId = savedSites;
		System.out.println("What is the arrival date? YYYY-MM-DD");
		//look at localdate for 
		String aDate = scannerCreate();
		 LocalDate fromDate= LocalDate.parse(aDate);  
		System.out.println("What is the departure date? YYYY-MM-DD");
		String dDate = scannerCreate();
		LocalDate toDate=LocalDate.parse(dDate);  
		System.out.println(campId+" "+fromDate+" "+toDate);
		fromUserDate = fromDate;
		toUserDate =toDate;
		showSites(campId, fromDate, toDate);
		System.out.println();
		
	}
	
	
	private void showAllParks() {
		printHeading("Here are all the parks");
		List<Park> allParks = campApp.getAllParks();
			// method to print out park information based on user selected Park
		Park selectedPark = (Park) CampMenu.getChoiceFromOptions(allParks.toArray());
		savedPark =selectedPark;
		//printPark(selectedPark.toString());
		System.out.println();
		System.out.println("Welcome to " +selectedPark.getName()+" National Park");
		System.out.println("Location:\t\t\t"+ selectedPark.getLocation());
		System.out.println("Established:\t\t\t"+ selectedPark.getEstablish_date().toString());
		System.out.println("Area:\t\t\t\t"+ selectedPark.getArea().toString());
		System.out.println("Annual Visitors:\t\t"+ selectedPark.getVisitors().toString());
		System.out.println("Description:\n"+ selectedPark.getDescription());
		
	}
			// uses the information from reservation to print out the available sites
	private void showSites(Campground campId, LocalDate fromDate, LocalDate toDate) {
		printHeading("Here are available sites");
		 System.out.println("Site\t\tMax Occup.\tAccessible?\tMax Rv Length\tUtility\t\tCost");
		List<Site> displaySites = campApp.getSitesByRes(campId, fromDate, toDate);
		 Site selectedSite = (Site) CampMenu.getChoiceFromOptions(displaySites.toArray());
		 
		 System.out.println("You selected site:");
//		 System.out.print("# " + selectedSite.getSiteNum() + "\t\t");
//		 System.out.print(selectedSite.getMaxCap() + "\t\t");
//		 System.out.print(selectedSite.isAccess() + "\t\t");
//		 System.out.print(selectedSite.getMaxRvLen() + "\t\t");
//		 System.out.print(selectedSite.isUtil() + "\t\t\n");
		// System.out.print(selectedSite.g);
		  siteRes(selectedSite,fromUserDate, toUserDate);
	}
	
		// taking the camp the user selected and returning the serial generated reservation number 
														//	and name under which reservation was made
	public void siteRes(Site selectedSite, LocalDate fromUserDate, LocalDate toUserDate) {
//		System.out.println("What site would you like to reserve");
//		scannerCreate();
		
		System.out.println("What name should the reservation be under");
		String resNameEntered = scannerCreate();
		Reservation confirm = campApp.makeReservation(selectedSite, resNameEntered, fromUserDate, toUserDate);
		
		System.out.println("Reservation created here is your reservation number ");
		System.out.println(confirm.getReservationId() + " under the name " + confirm.getName());
		
		
	}
	
	
		// creating the instance of a scanner for us to call upon when user input is required
	private String scannerCreate() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String hold = input.nextLine();
		return hold;
	}
	
	
	
	
		// method to show all campgrounds based on global saved park
	public void showAllCampgrounds(Park savedPark) {
		
		printHeading("Here are all the campgrounds");
		printHeading("Name\t\tOpen Month\t\tClose Month\t\tDaily Fee"); 
		List<Campground> allCamps = campApp.showAllCampgrounds(savedPark);

		Campground selectedCamp = (Campground) CampMenu.getChoiceFromOptions(allCamps.toArray());
		savedCamp = selectedCamp;
		
	}
	
		// method to show all campsites based on global saved Campground
	public void showAllCampSites(Campground savedCamp) {
		
		printHeading("Here are all the sites");
		printHeading("Name\t\tOpen Month\t\tClose Month\t\tDaily Fee"); 
		List<Site> allSites = campApp.getSiteByCampgroundId(savedCamp);

		Campground selectedSites = (Campground) CampMenu.getChoiceFromOptions(allSites.toArray());
		savedSites =selectedSites;
		
	}
	
	
	

	
	

	
	
	

		// taking a String and returning a heading
	private void printHeading(String headingText) {
		System.out.println("\n" + headingText);
		for (int i = 0; i < headingText.length(); i++) {
			System.out.print("---");
		}
		System.out.println();
	}

}
