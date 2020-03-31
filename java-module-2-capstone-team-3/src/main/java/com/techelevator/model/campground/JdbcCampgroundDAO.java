package com.techelevator.model.campground;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.park.Park;

public class JdbcCampgroundDAO implements CampgroundDAO {
	
	private JdbcTemplate jdbcTemplate;

	public JdbcCampgroundDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}


	@Override
	public List<Campground> getAllCampgrounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Campground> searchCampgroundById() {
		// TODO Auto-generated method stub
		return null;
	}
		//
	@Override
	public List<Campground> getCampgroundsByParkId(Park insertPark) {
		List<Campground> theCamps = new ArrayList<Campground>();
				// execute the SQL query and return the results
				// defining a string to hold the SQL query
			String sqlGetCampByID =	"select campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee " + 
				"from campground " + 
				"where park_id = ? ";
			SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetCampByID,insertPark.getParkId());
			while(result.next()) {
				Campground aCamp = mapRowToCampground(result);
				theCamps.add(aCamp);
			}
			return theCamps;
	}

	
	
	
	  // when this function is called it will map and return a new Campground object
	private Campground mapRowToCampground(SqlRowSet result) {
		Campground theCamp = new Campground();				// instantiate a new Campground object
		theCamp.setCampId(result.getLong("campground_id"));	// populate the non-serial fields using the set methods for the class
		theCamp.setParkId(result.getLong("park_id"));
		theCamp.setName(result.getString("name"));
		theCamp.setOpen(result.getInt("open_from_mm"));
		theCamp.setClose(result.getInt("open_to_mm"));
		theCamp.setFee(result.getDouble("daily_fee"));     
	
	return theCamp;
}
	
	
	
	
		
		
		
	}
	




