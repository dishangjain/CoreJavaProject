package com.capgemini.hotelbooking.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.capgemini.hotelbooking.bean.UserBean;
import com.capgemini.hotelbooking.exception.BookingException;
import com.capgemini.hotelbooking.util.ConnectionUtil;

public class CommonDao implements ICommonDao {
	private Connection connect;
	static Logger myLogger = Logger.getLogger("myLogger");
	
	public CommonDao() throws BookingException{
		super();
		ConnectionUtil util = new ConnectionUtil();
		connect = util.getConnection();
		myLogger.info("Connection procured in CommonDao.");
	}
	
	private String generatePasswordHash(String password) throws BookingException{
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			myLogger.error("Algorithm for hash not found.");
			throw new BookingException("System Error.");
		}
		messageDigest.update(password.getBytes());
		byte[] bytes = messageDigest.digest();
		StringBuilder stringBuilder = new StringBuilder();
		for(int i=0;i < bytes.length;i++){
			stringBuilder.append(Integer.toHexString(0xff & bytes[i]));
		}
		return stringBuilder.toString();
	}
	
	@Override
	public UserBean Login(String username, String password)
			throws BookingException {
		myLogger.info("Execution in Login()");
		String hashedPassword = generatePasswordHash(password);
		String query = "SELECT username,password FROM users WHERE username=? AND password=?";
		ResultSet resultSet = null;
		UserBean userBean = null;
		try(
			PreparedStatement preparedStatement = connect.prepareStatement(query);
		){
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, hashedPassword);
			myLogger.info("Query Execution : " + query);
			resultSet = preparedStatement.executeQuery(); 
			
			while(resultSet.next()){
				String userId = resultSet.getString("user_id");
				String role = resultSet.getString("role");
				String mobileNumber = resultSet.getString("mobile_no");
				String phoneNumber = resultSet.getString("phone");
				String address = resultSet.getString("address");
				String email = resultSet.getString("email");
				
				userBean = new UserBean(userId, hashedPassword, role, username, mobileNumber, address, email, phoneNumber);
			}
			
		} catch (SQLException e) {
			myLogger.error("Exception from Login()", e);
			throw new BookingException("Problem in Login in the user.", e);
		}
		return userBean;
	}
}
