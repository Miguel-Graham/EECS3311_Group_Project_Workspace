package application;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;

import application.controllers.ReservationController;
import application.frames.CreateReservationFrame;

import java.util.Date;
import java.util.Calendar;

import persistence.DatabaseStubs;

public class ReservationControllerTest {
	
	JTextField fName = new JTextField();
	JTextField lName = new JTextField();
	JPasswordField creditCard = new JPasswordField();
	JTextField adress = new JTextField();
	JTextField phoneNum = new JTextField();
	JComboBox<String> roomtype = new JComboBox<String>();
	JDateChooser startDate = new JDateChooser();
	JDateChooser endDate = new JDateChooser();
	
	DatabaseStubs stubs = new DatabaseStubs();

	ReservationController testController = new ReservationController(fName, lName, creditCard, adress, phoneNum, roomtype, startDate, endDate);
		
	@Before
	public void setUp() throws Exception {
		testController.setLogic(stubs); //setup stubs for testing
		
		//Setup class requirements
		CreateReservationFrame.feedback = new JTextArea();
		roomtype.setModel(new DefaultComboBoxModel<String>(new String[] {"Standard", "Deluxe", "Suite", "Executive", "Presidential"}));
		
		//Setup valid test data
		fName.setText("Bob");
		lName.setText("Smith");
		creditCard.setText("1234123412341234");
		adress.setText("123 Home");
		phoneNum.setText("1231234567");
		
		//Setup valid test dates
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(2024, 1, 1);
		Date tempDate = calendar.getTime();
		startDate.setDate(tempDate);
		
		calendar.set(2024, 1, 31);
		tempDate = calendar.getTime();
		endDate.setDate(tempDate);	
	}
	
	//Test create reservation button press
	@Test
	public void buttonPressed() {
		roomtype.setSelectedItem("Deluxe");
		
		testController.actionPerformed(null);
		
		assertNotNull(testController.newRes);
	}
	
	//Tests input valid when input is valid
	@Test
	public void inputValid() {
		assertTrue("Error: Valid input is recognized as invalid", testController.inputValid("1234123412341234"));
	}
		
	//Tests inputValid when phone input not valid (not 10 digit number)
	@Test
	public void inputPhoneNotValid() {
		phoneNum.setText("123");
			
		assertFalse("Error: Invalid phone input is recognized as valid", testController.inputValid("1234123412341234"));
	}
		
	//Tests inputValid when credit card input not valid (not 16 digit number)
	@Test
	public void inputCardNotValid() {	
		assertFalse("Error: Invalid phone input is recognized as valid", testController.inputValid("123"));
	}
	
	//Tests inputValid when start date is after end date (invalid)
	@Test
	public void inputStartAfterEndNotValid() {
		//Setup start date after end date
		Calendar calendar = Calendar.getInstance();
			
		calendar.set(2024, 2, 1);
		Date tempDate = calendar.getTime();
		startDate.setDate(tempDate);
			
		calendar.set(2024, 1, 1);
		tempDate = calendar.getTime();
		endDate.setDate(tempDate);	
			
		assertFalse("Error: Start date cannot be after end date", testController.inputValid("1234123412341234"));
	}
		
	//Tests inputValid when start date is after end date (invalid)
	@Test
	public void inputStartAfterCurrentNotValid() {
		//Setup start date before current date
		Calendar calendar = Calendar.getInstance();
					
		calendar.set(2022, 1, 1);
		Date tempDate = calendar.getTime();
		startDate.setDate(tempDate);
					
		assertFalse("Error: Start date cannot be before current date", testController.inputValid("1234123412341234"));
	}
}

