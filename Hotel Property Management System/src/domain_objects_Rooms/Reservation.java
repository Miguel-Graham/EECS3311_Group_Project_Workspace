package domain_objects_Rooms;

import domain_objects_Users.Customer;

public class Reservation {
	
	public String arrival_date = "";
	public String departure_date = "";
	public Customer customer = new Customer();
	private int resNumber;
	private String roomType ="";
	private String roomNum;
	private String checkedOut;
	private String checkedIn;

	private Room room;
	
	public Reservation(String first_name, String last_name, String address, String phone_num, String credit_card) {
		customer.setLast_name(last_name);
		customer.setFirst_name(first_name);
		customer.setAddress(address);
		customer.setPhone_num(phone_num);
		customer.setCredit_card(credit_card);
	}

	public String getArrival_date() {
		return arrival_date;
	}

	public String getCheckedOut() {
		return checkedOut;
	}

	public void setCheckedOut(String checkedOut) {
		this.checkedOut = checkedOut;
	}

	public String getCheckedIn() {
		return checkedIn;
	}

	public void setCheckedIn(String checkedIn) {
		this.checkedIn = checkedIn;
	}

	public void setArrival_date(String arrival_date) {
		this.arrival_date = arrival_date;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getDeparture_date() {
		return departure_date;
	}

	public void setDeparture_date(String departure_date) {
		this.departure_date = departure_date;
	}
	
	public Room getRoom() {
		return this.room;
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}
	@Override
	public String toString() {
		return "  Arrival_date: " + arrival_date +  "\n" + "  Departure_date: " + departure_date + "\n" +"  Customer: "
				+ customer.getFirst_name() + " " + customer.getLast_name() + "\n" + "  RoomType: " + roomType+ "\n" + "  Reservation Number: " + resNumber  ;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getResNumber() {
		return resNumber;
	}

	public void setResNumber(int resNumber) {
		this.resNumber = resNumber;
	}
}