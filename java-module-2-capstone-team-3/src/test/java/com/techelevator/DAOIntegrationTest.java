package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.List;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.model.campground.JdbcCampgroundDAO;
import com.techelevator.model.park.JdbcParkDAO;
import com.techelevator.model.park.Park;
import com.techelevator.model.park.ParkDAO;
import com.techelevator.model.site.JdbcSiteDAO;

public class DAOIntegrationTest {

	/* Using this particular implementation of DataSource so that
	 * every database interaction is part of the same database
	 * session and hence the same database transaction */
	private static SingleConnectionDataSource dataSource;
	private static ParkDAO parkDao;

	/* Before any tests are run, this method initializes the datasource for testing. */
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campgrounds");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		/* The following line disables autocommit for connections
		 * returned by this DataSource. This allows us to rollback
		 * any changes after each test */
		dataSource.setAutoCommit(false);
		
		parkDao = new JdbcParkDAO(dataSource);
		//campgroundDao = new JdbcCampgroundDAO(dataSource);
		//siteDao = new JdbcSiteDAO(dataSource);
	}

	/* After all tests have finished running, this method will close the DataSource */
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}

	/* After each test, we rollback any changes that were made to the database so that
	 * everything is clean for the next test */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	/* This method provides access to the DataSource for subclasses so that
	 * they can instantiate a DAO for testing */
	protected DataSource getDataSource() {
		return dataSource;
	}
	
	
//	}
	@Test
	public void test_get_all_parks() {
		ArrayList<Park>parks =  (ArrayList<Park>) parkDao.getAllParks();
		assertNotNull(parks);
		assertTrue(parks.size() > 0);	
	}
	
	@Test 
	public void test_get_park_by_id() {
		Park thePark = parkDao.makePark("java park");
		Park newPark = parkDao.createPark(thePark);
		Park foundPark = parkDao.getParkById(newPark.getParkId());
		assertEquals("expected java park", newPark.getParkId(), foundPark.getParkId());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
