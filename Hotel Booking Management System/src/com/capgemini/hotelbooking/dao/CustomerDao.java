package com.capgemini.hotelbooking.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.capgemini.hotelbooking.bean.BookingBean;
import com.capgemini.hotelbooking.bean.RoomBean;
import com.capgemini.hotelbooking.exception.BookingException;
import com.capgemini.hotelbooking.util.ConnectionUtil;

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

	@Override
	public int bookRoom(BookingBean bookingBean) throws BookingException {
		myLogger.info("Execution in bookRoom()");
		
		String query = "insert into BOOKINGDETAILS(BOOKING_ID, ROOM_ID, USER_ID, BOOKED_FROM, BOOKED_TO, NO_OF_ADULTS, NO_OF_CHILDREN, "
						+ "AMOUNT) values (?, ?, ?, ?, ?, ?, ?, ?)";
		String supportQuery = "SELECT per_night_rate FROM roomdetails where room_id = ?";
		int recsAffected = 0;
		
		try(
			PreparedStatement preparedStatement = connect.prepareStatement(query);
		){
			preparedStatement.setInt(1, bookingBean.getRoomID());
			myLogger.info("Support query Execution : " + supportQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			float perNightRate = 0;
			while(resultSet.next()){
				perNightRate = resultSet.getFloat("AMOUNT"); 
			}
			long numberOfDays = bookingBean.getBookedFrom().until(bookingBean.getBookedTo(), ChronoUnit.DAYS);
			bookingBean.setAmount(numberOfDays * perNightRate);
			bookingBean.setBookingID(getBookingID());
			
			preparedStatement.setInt(1, bookingBean.getBookingID());
			preparedStatement.setInt(2, bookingBean.getRoomID());
			preparedStatement.setInt(3, bookingBean.getUserID());
			preparedStatement.setDate(4, Date.valueOf(bookingBean.getBookedFrom()));
			preparedStatement.setDate(5, Date.valueOf(bookingBean.getBookedTo()));
			preparedStatement.setInt(6, bookingBean.getNumAdults());
			preparedStatement.setInt(7, bookingBean.getNumChildren());
			preparedStatement.setFloat(8, bookingBean.getAmount());
						
			myLogger.info("Query Execution : " + query);
			recsAffected = preparedStatement.executeUpdate();
			
			if(recsAffected > 0){
				myLogger.info("New Entry -> Booking ID : "+ bookingBean.getBookingID()
							+ "\nRoom ID : " + bookingBean.getRoomID()
							+ "\nUser ID : " + bookingBean.getUserID()
							+ "\nBooked From Date : " + bookingBean.getBookedFrom()
							+ "\nBooked To Date : " + bookingBean.getBookedTo()
							+ "\nNumber of adults : " + bookingBean.getNumAdults()
							+ "\nNumber of children : " + bookingBean.getNumChildren()
							+ "\nAmount : " + bookingBean.getAmount());
			}
			else{
				myLogger.error("System Error");
				throw new BookingException("System Error. Try Again Later.");
			}
			
		} catch (SQLException e) {
			myLogger.error("Exception from bookRoom()", e);
			throw new BookingException("Problem in booking room.", e);
		}
		return bookingBean.getBookingID();
	}

	@Override  
	public List<List<Object>> viewBookingStatus(int bookingId, int userId) throws BookingException {
		List<List<Object>> bookingList = new ArrayList<List<Object>>();
		List<Object> bookingStatus = new ArrayList<Object>();
		myLogger.info("Execution in viewBookingStatus()");
		
		String query = "SELECT r.room_no,b.booking_id,b.booked_from,b.booked_to FROM bookingdetails b,"
				+ "roomretails r WHERE b.room_id=r.room_id AND b.booking_id = ? AND b.user_id = ? ";
		ResultSet resultSet = null;
		
		try(
			PreparedStatement preparedStatement = connect.prepareStatement(query);
		){
			preparedStatement.setInt(1, bookingId);	
			preparedStatement.setInt(2, userId);
			myLogger.info("Query Execution : " + query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				int roomNumber = resultSet.getInt("ROOM_NO");
				LocalDate bookedFrom = resultSet.getDate("BOOKED_FROM").toLocalDate();
				LocalDate bookedTo = resultSet.getDate("BOOKED_TO").toLocalDate();
				bookingStatus.add(roomNumber);
				bookingStatus.add(bookingId);
				bookingStatus.add(bookedFrom);
				bookingStatus.add(bookedTo);
				bookingList.add(bookingStatus);
			}
		} catch (SQLException e) {
			myLogger.error("Exception from viewBookingStatus()", e);
			throw new BookingException("Problem in retrieving booking status.", e);
		}
		return bookingList;
	}

	@Override
	public List<RoomBean> searchAvailableRooms(String city) throws BookingException {
		List<RoomBean> roomList = new ArrayList<RoomBean>();
		myLogger.info("Execution in searchAvailableRooms()");
		
		ResultSet resultSet=null;
		String query = "SELECT * FROM roomdetails where availability='T' and hotel_id in (select hotel_id from hotels where LOWER(city) = ?) ";
		try(
			PreparedStatement preparedStatement = connect.prepareStatement(query);
				
		){
			preparedStatement.setString(1, city); 
			resultSet = preparedStatement.executeQuery();
			myLogger.info("Query Execution : " + query);
			
			while(resultSet.next()){
				int roomId = resultSet.getInt("room_Id");
				int hotelId = resultSet.getInt("hotel_Id");
				String roomNumber = resultSet.getString("room_No");
				String roomType = resultSet.getString("room_type");
				float perNightRate = resultSet.getFloat("per_night_rate");
				String availabilityString = resultSet.getString("availability");
				String photo = resultSet.getString("photo");
				
				boolean availability = false;
				if(availabilityString.equals('T')){
					availability = true;
				}
				else{
					availability = false;
				}
				
				roomList.add(new RoomBean(hotelId, roomId, roomNumber, roomType, perNightRate, availability, photo));
			}
		} catch (SQLException e) {
			myLogger.error("Exception from searchAvailableRooms()", e);
			throw new BookingException("Problem in retrieving data.", e);
		}
		return roomList;
	}

}
