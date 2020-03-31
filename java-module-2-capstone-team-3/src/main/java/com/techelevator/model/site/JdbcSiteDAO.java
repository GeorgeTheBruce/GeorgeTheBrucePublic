package com.techelevator.model.site;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.campground.Campground;

public class JdbcSiteDAO implements SiteDAO {

	private JdbcTemplate jdbcTemplate;
	
	public JdbcSiteDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
		// inputting a campground id selected by the user and returning all campsite information
	@Override
	public List<Site> getSiteByCampgroundId(Campground campId) {
		List<Site> theSites = new ArrayList<Site>();
		Site aSite = null;
		String sqlSite = "select site_id, campground_id, site_number, max_occupancy, accessible, max_rv_length, utilities " + 
				"from site " + 
				"where campground_id = ?" ;
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSite,campId.getCampId());
		
		while(result.next()) {
			aSite = mapRowToSite(result);
			theSites.add(aSite);
		}
		return theSites;
	
		
		
		
		
		
	}	
//	@Override 
//	public Site 
//	
//	
	
	
	
		// getting information from the JDBC 
		// using it to return a theSite object
	
	private Site mapRowToSite(SqlRowSet result) {
		Site theSite = new Site();
		theSite.setSiteId(result.getLong("site_id"));
		theSite.setCampId(result.getLong("campground_id"));
		theSite.setSiteNum(result.getLong("site_number"));
		theSite.setMaxCap(result.getInt("max_occupancy"));
		theSite.setAccess(result.getBoolean("accessible"));
		theSite.setMaxRvLen(result.getInt("max_rv_length"));
		theSite.setUtil(result.getBoolean("utilities"));
		return theSite;
	} 
	
	
}
