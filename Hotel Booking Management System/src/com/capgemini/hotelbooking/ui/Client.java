package com.capgemini.hotelbooking.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.capgemini.hotelbooking.bean.BookingBean;
import com.capgemini.hotelbooking.bean.HotelBean;
import com.capgemini.hotelbooking.bean.RoomBean;
import com.capgemini.hotelbooking.bean.UserBean;
import com.capgemini.hotelbooking.exception.BookingException;
import com.capgemini.hotelbooking.service.AdminService;
import com.capgemini.hotelbooking.service.CommonService;
import com.capgemini.hotelbooking.service.CustomerService;
import com.capgemini.hotelbooking.service.ICommonService;


public class Client {
	public static void main(String[] args) throws BookingException {
		Scanner scanner = new Scanner(System.in); 
		System.out.println("Welcome to ChaloGhume.com\n\n\n");
		System.out.println("-------------------------");
		boolean exitFlag = false;
		do{
			System.out.println("\n\n1.Login");
			System.out.println("2.Sign Up");
			System.out.println("3.Exit\n");
			System.out.print("Please select one of the options : ");
			int option = scanner.nextInt();
			ICommonService commonService;
			switch(option){
				case 1:
					System.out.print("\nUsername : ");
					String username = scanner.next();
					System.out.print("Password : ");
					String password = scanner.next();
					commonService = new CommonService();
					UserBean userBean = commonService.login(username, password);
					if(userBean == null){
						System.out.println("Please enter valid credentials and try again.");
					}
					else{
						System.out.println("\nWelcome " + userBean.getUserName());
						if("admin".equals(userBean.getRole())){
							boolean adminExitFlag = false;
							do{
								AdminService adminService = new AdminService();
								System.out.println("\n1.Add new hotel");
								System.out.println("2.Update existing hotel");
								System.out.println("3.Delete a hotel");
								System.out.println("4.Add new room");
								System.out.println("5.Update existing room");
								System.out.println("6.Delete a room");
								System.out.println("7.View all hotels");
								System.out.println("8.View bookings of specific hotel");
								System.out.println("9.View bookings of specific date");
								System.out.println("10.View guest list for a specific hotel");
								System.out.println("11.Logout");
								System.out.print("\nPlease select one of the options : ");
								option = scanner.nextInt();
								switch(option){
									case 1:
										int hotelId = 0;
										System.out.print("\nPlease enter hotel name : ");
										String hotelName = scanner.next();
										System.out.print("\nPlease enter city : ");
										String city = scanner.next();
										System.out.print("\nPlease enter address of hotel : ");
										String address = scanner.next();
										System.out.print("\nPlease enter description about hotel : ");
										String description = scanner.next();
										System.out.print("\nPlease enter the estimate average rate of room per night : ");
										float averageRatePerNight = scanner.nextFloat();
										System.out.print("\nPlease enter primary phone number : ");
										String primaryPhone = scanner.next();
										System.out.print("\nPlease enter secondary phone number : ");
										String secondaryPhone = scanner.next();
										System.out.print("Please enter the rating of hotel : ");
										String rating = scanner.next();
										System.out.print("\nPlease enter hotel email : ");
										String email = scanner.next();
										System.out.print("\nPlease enter hotel fax : ");
										String fax = scanner.next();
										
										HotelBean hotelBean = new HotelBean(hotelId, city, hotelName, address, description, 
												averageRatePerNight, primaryPhone, secondaryPhone, rating, email, fax);
										hotelId = adminService.addHotelDetails(hotelBean);
										if(hotelId > 0){
											System.out.println("Hotel registration successful.");
										}
										else{
											System.out.println("Hotel registration error.");
										}
										break;
									case 2:
										String[] attributeNames = {"","CITY","HOTEL_NAME","ADDRESS","DESCRIPTION","AVG_RATE_PER_NIGHT",
																	"PHONE_NO1","PHONE_NO2","RATING","EMAIL","FAX"};
										List<HotelBean> hotelList = commonService.retrieveHotels();
										if(hotelList.size() > 0){
											System.out.println("\n\nFollowing are the list of hotels : \n\n");
											System.out.println(hotelList);
											System.out.println("\n\nPlease enter hotel ID for which you want to update a detail : ");
											int hotelID = scanner.nextInt();
											System.out.println("1.City");
											System.out.println("2.Hotel Name");
											System.out.println("3.Address");
											System.out.println("4.Description");
											System.out.println("5.Average Rate Per Night");
											System.out.println("6.Primary Phone Number ");
											System.out.println("7.Secondary Phone Number");
											System.out.println("8.Rating");
											System.out.println("9.Email");
											System.out.println("10.Fax");
											System.out.print("Please enter your choice from the list : ");
											int attributeOption = scanner.nextInt();
											System.out.println("\nPlease enter the new value : ");
											String attributeValue = scanner.next();
											
											int recsAffected = adminService.updateHotelDetails(hotelID, attributeNames[attributeOption], attributeValue);
											if(recsAffected == 0){
												throw new BookingException("Update Error.");
											}
											else{
												System.out.println("Hotel Details successfully updated.");
											}
											
										}
										else{
											System.out.println("No hotels found.");
										}
										break;
									case 3:
										hotelList = commonService.retrieveHotels();
										if(hotelList.size() > 0){
											System.out.println("\n\nFollowing are the list of hotels : \n\n");
											System.out.println(hotelList);
											System.out.println("Please enter the hotel ID for the hotels you want to delete : ");
											int hotelID = scanner.nextInt();
											boolean statusCode = adminService.deleteHotelDetails(hotelID);
											if(statusCode){
												System.out.println("Hotel Details successfully deleted.");
											}
											else{
												throw new BookingException("Hotel deletion error. Please try again later.");
											}
										}
										else{
											System.out.println("No hotels found.");
										}
										break;
									case 4:
										int roomId = 0;
										hotelList = commonService.retrieveHotels();
										if(hotelList.size() > 0){
											System.out.println("\n\nFollowing are the list of hotels : \n\n");
											System.out.println(hotelList);
											System.out.println("Please enter the hotel ID in which you want to add a room : ");
											int hotelID = scanner.nextInt();
											System.out.println("Please enter room number :");
											String roomNumber = scanner.next();
											String[] roomTypes = {"","Non AC","AC Prime","AC Deluxe","Super AC Deluxe"};
											System.out.println("1.Non AC");
											System.out.println("2.AC Prime");
											System.out.println("3.AC Deluxe");
											System.out.println("4.Super AC Deluxe");
											System.out.print("Please enter option of room type :");
											option = scanner.nextInt();
											System.out.println("Please enter cost/night of the room :");
											float perNightRate = scanner.nextFloat();
											System.out.println("Please enter the filename of the photo :");
											String photo = scanner.next();
											
											RoomBean roomBean = new RoomBean(hotelID, roomId, roomNumber, roomTypes[option], perNightRate, true, photo);
											roomId = adminService.addRoomDetails(roomBean);
											if(roomId > 0){
												System.out.println("Room registration successful.");
											}
											else{
												System.out.println("Room registration error.");
											}
										}
										else{
											System.out.println("No hotels found.");
										}
										
										break;
									case 5:
										String[] attributeList = {"","ROOM_NO","ROOM_TYPE","PER_NIGHT_RATE","PHOTO"};
										List<RoomBean> roomList = commonService.retrieveRooms();
										if(roomList.size() > 0){
											System.out.println("\n\nFollowing are the list of rooms : \n\n");
											System.out.println(roomList);
											System.out.println("\n\nPlease enter room ID for which you want to update a detail : ");
											int roomID = scanner.nextInt();
											System.out.println("\n\n1.Room Number");
											System.out.println("\n\n2.Room Type");
											System.out.println("\n\n3.Cost per night");
											System.out.println("\n\n4.Photo Filename");
											System.out.println("\n\nPlease enter your choice from the list : ");
											int attributeOption = scanner.nextInt();
											System.out.println("\nPlease enter the new value : ");
											String attributeValue = scanner.next();
											
											int recsAffected = adminService.updateRoomDetails(roomID, attributeList[attributeOption], attributeValue);
											if(recsAffected == 0){
												throw new BookingException("Update Error.");
											}
											else{
												System.out.println("Room Details successfully updated.");
											}
										}
										else{
											System.out.println("No rooms found.");
										}
										break;
									case 6:
										roomList = commonService.retrieveRooms();
										if(roomList.size() > 0){
											System.out.println("\n\nFollowing are the list of rooms : \n\n");
											System.out.println(roomList);
											System.out.println("Please enter the room ID for the room you want to delete : ");
											int roomID = scanner.nextInt();
											boolean statusCode = adminService.deleteRoomDetails(roomID);
											if(statusCode){
												System.out.println("Room Details successfully deleted.");
											}
											else{
												throw new BookingException("Room deletion error. Please try again later.");
											}
										}
										break;
									case 7:
										hotelList = commonService.retrieveHotels();
										System.out.println(hotelList);
										break;
									case 8:
										break;
									case 9:
										break;
									case 10:
										break;
									case 11:
										System.out.println("\nAll changes saved. Logging you out " + userBean.getUserName() +"...\n");
										adminExitFlag = true;
										break;
									default:
										break;
								}
							}while(adminExitFlag == false);
						}
						else if("customer".equals(userBean.getRole())){
							boolean customerExitFlag = false;
							do{
								CustomerService customerService = new CustomerService();
								
								System.out.println("\n\n1.Search for hotel rooms");
								System.out.println("2.Book Hotel Rooms");
								System.out.println("3.View Booking status");
								System.out.println("4.Log out\n");
								System.out.print("Please select one of the options : ");
								option = scanner.nextInt();
								switch(option){
									case 1:
										System.out.println("\nEnter the city in which you want to find a room : ");
										String city = scanner.next();
										List<RoomBean> roomList = customerService.searchAvailableRooms(city.toLowerCase());
										System.out.println(roomList);
										break;
									case 2:
										roomList = commonService.retrieveRooms();
										if(roomList.size() > 0){
											int bookingID = 0;
											System.out.println("\n\nFollowing are the list of rooms : \n\n");
											System.out.println(roomList);
											System.out.println("Please enter the room ID from the above list : ");
											int roomID = scanner.nextInt();
											int userID = userBean.getUserID(); 
											DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
											System.out.println("Enter start date : (dd-mm-yyyy)");
											String bookedFrom = scanner.next();
											LocalDate parsedBookedFromDate = LocalDate.parse(bookedFrom, formatter);
											System.out.println("Enter end date : (dd-mm-yyyy)");
											String bookedTo = scanner.next();
											LocalDate parsedBookedToDate = LocalDate.parse(bookedTo, formatter);
											System.out.println("Enter no of Adults : ");
											int numAdults = scanner.nextInt();
											System.out.println("Enter no of children : ");
											int numChildren = scanner.nextInt();
											float amount = 0;
											
											BookingBean bookingBean = new BookingBean(bookingID, roomID, userID, numAdults, numChildren, amount, parsedBookedFromDate, parsedBookedToDate);
											bookingID = customerService.bookRoom(bookingBean);
										}
										break;
									case 3:
										int bookingId = 0;
										List<List<Object>> bookingList = customerService.viewBookingStatus(bookingId,userBean.getUserID());
										if(bookingList.size() > 0){
											System.out.println("Your bookings are : \n");
											System.out.println(bookingList);
										}
										else{
											System.out.println("No bookings done. Book rooms and get exciting offers.");
										}
										break;
									case 4:
										System.out.println("\nAll changes saved. Logging you out " + userBean.getUserName() + "...\n");
										customerExitFlag = true;
										break;
									default:
										break;
								}
							}while(customerExitFlag == false);
						}
					}
					break;
				case 2:
					String role = null;
					int userID = 0;
					System.out.print("\nPlease enter a unique username : ");
					username = scanner.next();
					System.out.print("\nPlease enter password : ");
					password = scanner.next();
					System.out.print("\nPlease enter your mobile number : ");
					String mobileNumber = scanner.next();
					System.out.print("\nPlease enter your phone number : ");
					String phoneNumber = scanner.next();
					System.out.print("\nPlease enter your address : ");
					String address = scanner.next();
					System.out.print("\nPlease enter your email : ");
					String email = scanner.next();
					
					userBean = new UserBean(userID, password, role, username, mobileNumber, address, email, phoneNumber);
					
					commonService = new CommonService();
					userID = commonService.registerUser(userBean);
					if(userID <= 0){
						throw new BookingException("Problem in signing up the user.");
					}
					else{
						System.out.println("Sign up successful. Your user ID is " + userID);
					}
					break;
				case 3:
					System.out.println("\nThank you. Please visit again.");
					exitFlag = true;
					break;
				default:
					System.out.println("Please select valid option!");
					break;
			}
		}while(exitFlag == false);
		scanner.close();
	}
}
