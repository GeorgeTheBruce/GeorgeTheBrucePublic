package com.techelevator.model.park;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcParkDAO implements ParkDAO {

	private JdbcTemplate jdbcTemplate;

	public JdbcParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Park> getAllParks() {
		//created list to store all parks
		List<Park> allParks = new ArrayList<Park>();
		//query to pull all park information
		String sqlListParks = "select park_id, name, location, establish_date, area, visitors, description "
				+ "from park";
		//taking information in line form
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlListParks);
		
		//taking information from database and creating a park obj / while there is a next park
		//then storing them to the list with the .add
		while (result.next()) {
			Park thePark = mapRowToPark(result);
			allParks.add(thePark);
		}
		return allParks;

	}
	
	
	
	
	
	
	

	@Override
	public Park getParkById(Long parkId) {
		//Instantiating a new park obj
		Park aPark = null;
		//running query to find a park by id
		String sqlFindParkById = "select park_id, name, location, establish_date, area, visitors, description " + 
				" from park " + 
				" where park_id = ?";
		//taking information in line form
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlFindParkById,parkId);
		//creating a new park and saving to aPark
		// if statement is checking to see if information was returned
		//before creating a park obj
		if(result.next()) {
			aPark = mapRowToPark(result);
		}
	
		return aPark;
	}

	private Park mapRowToPark(SqlRowSet result) {
		Park thePark = new Park();
		thePark.setParkId(result.getLong("park_id"));
		thePark.setName(result.getString("name"));
		thePark.setLocation(result.getString("location"));
		thePark.setEstablish_date(result.getDate("establish_date").toLocalDate());
		thePark.setArea(result.getLong("area"));
		thePark.setVisitors(result.getLong("visitors"));
		thePark.setDescription(result.getString("description"));
		return thePark;

	}

}
