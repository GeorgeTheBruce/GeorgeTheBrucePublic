package com.techelevator.model.reservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.campground.Campground;
import com.techelevator.model.site.Site;

public class JdbcReservationDAO implements ReservationDAO {
	private JdbcTemplate  jdbcTemplate;			// connecting to our data source
	public JdbcReservationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	// getter for the sites that runs our query based on camp id and dates
	// will return a list of 5 available campsites
	@Override		
	public List<Site> getSitesByRes(Campground campId, LocalDate aDate, LocalDate dDate) {
		List<Site> theSites = new ArrayList<Site>();
		
		String sqlGetAllSites = "Select *, "
				+ "((r.to_date ::date - r.from_date ::date) * daily_fee) as cost_of_stay " + 
				"from site s " + 
				"join reservation r on r.site_id = s.site_id " + 
				"join campground c on c.campground_id = s.campground_id " + 
				"where c.campground_id = ? and s.site_id  " + 
				"not in( " + 
				"select r.site_id from reservation r " + 
				"        where(r.to_date > ? and r.from_date < ?) " + //aDate aDate
				"        or   (r.to_date > ? and r.from_date < ?) " + //dDate dDate
				"        or   (r.from_date > ? and r.to_date < ?) " + //aDate dDate
				") "
				+ "limit 5" ;
				SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetAllSites, campId.getCampId(),
				aDate,aDate,dDate,dDate,aDate ,dDate);
		while (result.next()) {
			Site aSite = mapRowToSite(result);
			theSites.add(aSite);
		}
		return theSites;
	}

		// taking the information entered by the user in the UI and 
		// adding the information to the table so we can store it in the database
		// makes a reservation object so we can return the reservation is and name to the user
	@Override
	public Reservation makeReservation(Site siteId, String resName, LocalDate aDate, LocalDate dDate) {
		String sqlMakeReservation = " insert into reservation " + 
				" (reservation_id, site_id, name, from_date, to_date, create_date) " + 
				" values (?, ?, ?, ?, ?, ?)";
		Long reservationId = getNextReservationId();
		LocalDate cDate = LocalDate.now();
		jdbcTemplate.update(sqlMakeReservation, 
							reservationId,
							siteId.getSiteId(), 
							resName, 
							aDate, 
							dDate,
							cDate);

		Reservation reservation = new Reservation();
		reservation.setCreateDate(cDate);
		reservation.setFromDate(aDate);
		reservation.setToDate(dDate);
		reservation.setName(resName);
		reservation.setReservationId(reservationId);
		return reservation;
	}

	
		// 
//	private Reservation mapRowToRes(SqlRowSet result) {
//		Reservation theRes = new Reservation();
//		theRes.setReservationId(result.getLong("reservation_id"));
//		theRes.setName(result.getString("name"));
//		return theRes;
//	}
	 	// this method goes to SQL and returns the next available to store the reservation in the database
	private Long getNextReservationId() {
		SqlRowSet nextId = jdbcTemplate.queryForRowSet("SELECT nextval('reservation_reservation_id_seq')");
		if (nextId.next()) {
			return nextId.getLong(1);
		}else {
			throw new RuntimeException("God help us all");
		}
	}
	
	
	
	
	
	
	
		// Retrieving the information from the query we ran and returning a site object
	private Site mapRowToSite(SqlRowSet result) {
		Site theSite = new Site();
		theSite.setSiteId(result.getLong("site_id"));
		theSite.setCampId(result.getLong("campground_id"));
		theSite.setSiteNum(result.getLong("site_number"));
		theSite.setMaxCap(result.getInt("max_occupancy"));
		theSite.setAccess(result.getBoolean("accessible"));
		theSite.setMaxRvLen(result.getInt("max_rv_length"));
		theSite.setUtil(result.getBoolean("utilities"));
		theSite.setCost(result.getString("cost_of_stay"));
		return theSite;
	} 
	
}
