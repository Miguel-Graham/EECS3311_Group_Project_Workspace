package persistence;

import java.util.ArrayList;
import java.util.List;

import domain_objects.Account;
import domain_objects_Rooms.Reservation;
import domain_objects_Rooms.Room;
import domain_objects_Users.Employee;
import domain_objects_Users.Profile;

public class DatabaseStubs implements Database {
	private static List<Profile> customers = new ArrayList<>();
	private static List<Reservation> reservations = new ArrayList<>();
	private static List<String> employee = new ArrayList<>();
	private static List<String> rooms = new ArrayList<>();
	private static List<Account> accounts = new ArrayList<>(); 
	
	@Override
	public boolean getUser(String userName, String passHash, String jobType) {
		for (Account account : accounts) {
			if (userName.equals(account.getUserName())&& passHash.equals(account.getPassword())) {
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean addUser(String userName, String passHash, String jobType) {
		Account account = null;
		try {
			account = new Account(userName, passHash, jobType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accounts.add(account);
		
	}
	@Override
	public Reservation getReservation(int resNum) {
		for (Reservation reservation : reservations) {
			if (reservation.getResNumber() == resNum) {
				return reservation;
			}
		}
		return null;
	}
	
	@Override
	public boolean addReservation(Reservation reservation) {
		return reservations.add(reservation);
		
	}
	@Override
	public boolean removeReservation(int resNum) {
		for (Reservation reservation : reservations) {
			if(reservation.getResNumber()== resNum) {
			return reservations.remove(reservation);
		}
		}
		return false;
	}
	@Override
	public int getLastResNum() {
		int lastResNum = 0;
		if (!reservations.isEmpty()) {
			return lastResNum;
		  }
		for (Reservation reservation : reservations) {
			lastResNum = reservation.getResNumber();
		}
		return lastResNum;
	}
	@Override
	public boolean updateReservation(Reservation changedReservation) {
		for (Reservation reservation : reservations) {
			if (changedReservation.getResNumber() == reservation.getResNumber()) {
				reservation = changedReservation;
				return true;
			}
		}
		return false;
	}
	@Override
	public ArrayList<Room> getRoomStatus(String roomNumStart, String roomNumEnd) {
		
		return null;
	}
	@Override
	public Employee getEmployee(String employeeNum) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateRoomStatus(String roomNum, String roomStatus) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ArrayList<Reservation> getResByDate(String date, String caller) {
		// TODO Auto-generated method stub
		return null;
	}
}

