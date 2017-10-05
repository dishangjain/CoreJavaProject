package com.capgemini.hotelbooking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.capgemini.hotelbooking.bean.BookingBean;
import com.capgemini.hotelbooking.bean.UserBean;
import com.capgemini.hotelbooking.exception.BookingException;
import com.capgemini.hotelbooking.util.ConnectionUtil;

import org.apache.log4j.Logger;

public class CustomerDao implements ICustomerDao {
	private Connection connect;
	static Logger myLogger = Logger.getLogger("myLogger");
	
	public CustomerDao() throws BookingException{
		super();
		ConnectionUtil util = new ConnectionUtil();
		connect = util.getConnection();
		myLogger.info("Connection procured in CustomerDao.");
	}
	
	private int getBookingID(){
		int bookingId = 0;
		String query = "SELECT booking_id_seq.NEXTVAL FROM DUAL";
		try
		{
			PreparedStatement pstmt= connect.prepareStatement(query);
			myLogger.info("Query Execution : " + query);
			ResultSet resultSet = pstmt.executeQuery();
			if(resultSet.next())
			{
				bookingId = resultSet.getInt(1);
			}
		}
		catch(SQLException e)
		{
			myLogger.error("Unable to generate booking ID.");
		}
		return bookingId;
	}
	
	private int getUserID(){
		int userID = 0;
		String query = "SELECT user_id_seq.NEXTVAL FROM DUAL";
		try
		{
			PreparedStatement pstmt= connect.prepareStatement(query);
			myLogger.info("Query Execution : " + query);
			ResultSet resultSet = pstmt.executeQuery();
			if(resultSet.next())
			{
				userID = resultSet.getInt(1);
			}
		}
		catch(SQLException e)
		{
			myLogger.error("Unable to generate user ID.");
		}
		return userID;
	}
	
	@Override
	public int registerUser(UserBean userBean) throws BookingException {
		myLogger.info("Execution in bookTrucks()");
		
		String query = "insert into BOOKINGDETAILS(BOOKINGID, ROOMID, USERID, BOOKEDFROM, BOOKEDTO, NUMADULTS, NUMCHILDREN, "
						+ "AMOUNT) values (?, ?, ?, ?, ?, ?, ?, ?)";
		int recsAffected = 0;
		
		try(
			PreparedStatement preparedStatement = connect.prepareStatement(query);
		){
			userBean.setUserID(Integer.toString(getUserID()));
			preparedStatement.setString(1, userBean.getUserID());
			preparedStatement.setString(2, userBean.getRole());
			preparedStatement.setString(3,userBean.getUserName());
			preparedStatement.setString(6, userBean.getMobileNumber());
			preparedStatement.setString(7, userBean.getPhoneNumber());
			preparedStatement.setString(8, userBean.getAddress());
			preparedStatement.setString(9, userBean.getEmail());
						
			myLogger.info("Query Execution : " + query);
			recsAffected = preparedStatement.executeUpdate(); // 1 for successful insert
			
			if(recsAffected > 0){
				//Logging the New Entry
				/*myLogger.info("New Entry -> Booking ID : "+ bookingBean.getBookingID()
									+ "\nCustomer ID : " + bookingBean.getCustId()
									+ "\nCustomer Mobile : " + bookingBean.getCustMobile()
									+ "\nTruck ID : " + bookingBean.getTruckId()
									+ "\nNo Of Trucks : " + bookingBean.getNoOfTrucks()
									+ "\nDate Of Transport : " + bookingBean.getDateOfTransport());*/
			}
			else{
				myLogger.error("System Error");
				throw new BookingException("System Error. Try Again Later.");
			}
			
		} catch (SQLException e) {
			myLogger.error("Exception from bookTrucks()", e);
			throw new BookingException("Problem in inserting data.", e);
		}
		return Integer.parseInt(userBean.getUserID());
	}

	

	@Override
	public int bookRoom(BookingBean bookingBean) throws BookingException {
		myLogger.info("Execution in bookTrucks()");
		
		String query = "insert into BOOKINGDETAILS(BOOKINGID, ROOMID, USERID, BOOKEDFROM, BOOKEDTO, NUMADULTS, NUMCHILDREN, "
						+ "AMOUNT) values (?, ?, ?, ?, ?, ?, ?, ?)";
		int recsAffected = 0;
		
		try(
			PreparedStatement preparedStatement = connect.prepareStatement(query);
		){
			bookingBean.setBookingID(getBookingID());
			preparedStatement.setInt(1, bookingBean.getBookingID());
			preparedStatement.setString(2, bookingBean.getRoomID());
			preparedStatement.setString(3, bookingBean.getUserID());
			preparedStatement.setInt(6, bookingBean.getNumAdults());
			preparedStatement.setInt(7, bookingBean.getNumChildren());
			preparedStatement.setFloat(8, bookingBean.getAmount());
			
			int year = bookingBean.getBookedFrom().getYear();
			int month = bookingBean.getBookedFrom().getMonthValue();
			int date = bookingBean.getBookedFrom().getDayOfMonth();
			String dateString = Integer.toString(date) + "-" + Integer.toString(month) + "-" + Integer.toString(year);
			preparedStatement.setString(4, dateString);
			
			year = bookingBean.getBookedTo().getYear();
			month = bookingBean.getBookedTo().getMonthValue();
			date = bookingBean.getBookedTo().getDayOfMonth();
			dateString = Integer.toString(date) + "-" + Integer.toString(month) + "-" + Integer.toString(year);
			preparedStatement.setString(5, dateString);
						
			myLogger.info("Query Execution : " + query);
			recsAffected = preparedStatement.executeUpdate(); // 1 for successful insert
			
			if(recsAffected > 0){
				//Logging the New Entry
				/*myLogger.info("New Entry -> Booking ID : "+ bookingBean.getBookingID()
									+ "\nCustomer ID : " + bookingBean.getCustId()
									+ "\nCustomer Mobile : " + bookingBean.getCustMobile()
									+ "\nTruck ID : " + bookingBean.getTruckId()
									+ "\nNo Of Trucks : " + bookingBean.getNoOfTrucks()
									+ "\nDate Of Transport : " + bookingBean.getDateOfTransport());*/
			}
			else{
				myLogger.error("System Error");
				throw new BookingException("System Error. Try Again Later.");
			}
			
		} catch (SQLException e) {
			myLogger.error("Exception from bookTrucks()", e);
			throw new BookingException("Problem in inserting data.", e);
		}
		return bookingBean.getBookingID();
	}

	@Override  
	public BookingBean viewBookingStatus(int bookingId) throws BookingException {
		BookingBean bookingBean = null;
		myLogger.info("Execution in bookTrucks()");
		
		String query = "SELECT * FROM bookingdetails WHERE bookingid = ?";
		
		
		ResultSet resultSet = null;
		
		try(
			PreparedStatement preparedStatement = connect.prepareStatement(query);
		){
			preparedStatement.setInt(1, bookingId);
						
			myLogger.info("Query Execution : " + query);
			resultSet = preparedStatement.executeQuery(); // 1 for successful insert
			
			if(resultSet.next()){
				return bookingBean;
			}
			else{
				myLogger.error("System Error");
				throw new BookingException("System Error. Try Again Later.");
			}
			
		} catch (SQLException e) {
			myLogger.error("Exception from bookTrucks()", e);
			throw new BookingException("Problem in inserting data.", e);
		}
	}

}
