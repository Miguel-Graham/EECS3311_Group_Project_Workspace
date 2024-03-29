package business_logic;

import java.time.temporal.ChronoUnit;
import java.time.format.*;
import java.time.LocalDate;
import java.util.ArrayList;

import domain_objects_Rooms.DeluxeRoom;
import domain_objects_Rooms.ExecutiveSuite;
import domain_objects_Rooms.PresidentialSuite;
import domain_objects_Rooms.Reservation;
import domain_objects_Rooms.Room;
import domain_objects_Rooms.StandardRoom;
import domain_objects_Rooms.SuiteRoom;
import persistence.Database;
import persistence.RealDatabase;

public class ReservationLogic {
	
	
    private static int resNum = 0;
    
	private final Database database;
	
	public ReservationLogic(Database database) {
		this.database = database;
	}
	/*This adds a reservation into the database this acts as a middleman
	 * and removes the need for other classes to add reservations to the database directly*/
	public boolean addReservation(Reservation reso) {
		if(database.addReservation(reso)) {
		reso.setResNumber(database.getLastResNum());
		return true;
		}else return false;
	}

	/*This method removes a reservation of given reservation number and returns 
	 * true if the reservation is removed*/
	public boolean removeReservation(int resNum) {
		return database.removeReservation(resNum);
	}
	
	public boolean updatReservation(Reservation reservation){
		return database.updateReservation(reservation);
	}
	
   public Reservation getReservation(int resNum) {
	   return database.getReservation(resNum);
   }
   
   public ArrayList<Reservation> getReservationsByDate(String date, String type) {
	   return database.getResByDate(date, type);
   }
   
   public boolean getUser(String userName, String password, String jobType) {
	   return database.getUser(userName, password, jobType);
   }
	
	public Reservation changeResDates(Reservation reservation, String newArrivalDate, String newDepartDate) {
		reservation.arrival_date = newArrivalDate;
		reservation.departure_date = newDepartDate;
		return reservation;
	}
	
	public boolean updateReservationStatus(int roomNum, String resNum, String caller) {
		return database.updateResStatus(roomNum, resNum, caller);
	}
	
	public static Room roomAvailable(String roomType) {
		Room room = null;
		switch(roomType) {
		case "Standard":
			StandardRoom standard = new StandardRoom();
				room = standard;
			break;
		case "Deluxe":
			DeluxeRoom deluxe = new DeluxeRoom();
				room = deluxe;
			break;
		case "Suite":
			SuiteRoom suite = new SuiteRoom(); 
				room = suite;
			break;
		case "Executive":
			ExecutiveSuite executive = new ExecutiveSuite();
				room = executive;
			break;
		case "Presidential":
			PresidentialSuite presidential = new PresidentialSuite();
				room = presidential;
			break;
		}
		return room;
	} 
	
	//Gets the number of days between arrival and departure date of reservation
	public long daysBetween(Reservation reservation) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yy-MM-dd");
		
		LocalDate arrival = LocalDate.parse(reservation.arrival_date, format);
		LocalDate departure = LocalDate.parse(reservation.departure_date, format);
		
		long daysBetween = ChronoUnit.DAYS.between(arrival, departure);
		
		return daysBetween;
	}

	//fetches room rate for the reservation
	public double getRoomPrice(int resNum) {
		Reservation res = database.getReservation(resNum);
		Room room = roomAvailable(res.getRoomType());
		return room.getRate();
	}
   

}
