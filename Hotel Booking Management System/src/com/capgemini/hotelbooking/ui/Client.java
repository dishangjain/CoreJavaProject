package com.capgemini.hotelbooking.ui;

import java.util.Scanner;

import com.capgemini.hotelbooking.bean.HotelBean;
import com.capgemini.hotelbooking.bean.UserBean;
import com.capgemini.hotelbooking.exception.BookingException;
import com.capgemini.hotelbooking.service.AdminService;
import com.capgemini.hotelbooking.service.CommonService;
import com.capgemini.hotelbooking.service.CustomerService;


public class Client {
	public static void main(String[] args) throws BookingException {
		Scanner scanner = new Scanner(System.in); 
		System.out.println("Welcome to ChaloGhume.com\n\n\n");
		System.out.println("-------------------------");
		boolean exitFlag = false;
		do{
			System.out.println("\n\n1.Login\n");
			System.out.println("2.Exit\n");
			System.out.println("Please select one of the options : ");
			int option = scanner.nextInt();
			switch(option){
				case 1:
					System.out.println("\n\nUsername : ");
					String username = scanner.next();
					System.out.println("\n\nPassword : ");
					String password = scanner.next();
					CommonService commonService = new CommonService();
					UserBean userBean = commonService.login(username, password);
					if(userBean == null){
						System.out.println("Please enter valid credentials and try again.");
					}
					else{
						if("admin".equals(userBean.getRole())){
							AdminService adminService = new AdminService();
							System.out.println("\n\n1.Add new hotel");
							System.out.println("\n\n2.Update existing hotel");
							System.out.println("\n\n3.Delete a hotel");
							System.out.println("\n\n4.Add new room");
							System.out.println("\n\n5.Update existing room");
							System.out.println("\n\n6.Delete a room");
							System.out.println("\n\n7.View all hotels");
							System.out.println("\n\n8.View bookings of specific hotel");
							System.out.println("\n\n9.View bookings of specific date");
							System.out.println("\n\n10.View guest list for a specific hotel");
							option = scanner.nextInt();
							switch(option){
								case 1:
									int hotelId = 0;
									System.out.println("\n\nPlease enter hotel name : ");
									String hotelName = scanner.next();
									System.out.println("\nPlease enter city : ");
									String city = scanner.next();
									System.out.println("\nPlease enter address of hotel : ");
									String address = scanner.next();
									System.out.println("\nPlease enter description about hotel : ");
									String description = scanner.next();
									System.out.println("\nPlease enter the estimate average rate of room per night : ");
									float averageRatePerNight = scanner.nextFloat();
									System.out.println("\nPlease enter primary phone number : ");
									String primaryPhone = scanner.next();
									System.out.println("\nPlease enter secondary phone number : ");
									String secondaryPhone = scanner.next();
									System.out.println("Please enter the rating of hotel : ");
									String rating = scanner.next();
									System.out.println("\nPlease enter hotel email : ");
									String email = scanner.next();
									System.out.println("\nPlease enter hotel fax : ");
									String fax = scanner.next();
									
									HotelBean hotelBean = new HotelBean(hotelId, city, hotelName, address, description, 
											averageRatePerNight, primaryPhone, secondaryPhone, rating, email, fax);
									hotelId = adminService.addHotelDetails(hotelBean);
									break;
								case 2:
									
									break;
								case 3:
									break;
								case 4:
									break;
								case 5:
									break;
								case 6:
									break;
								case 7:
									break;
								case 8:
									break;
								case 9:
									break;
								case 10:
									break;
								default:
									break;
							}
						}
						else if("customer".equals(userBean.getRole())){
							CustomerService customerService = new CustomerService();
						}
					}
					break;
				case 2:
					exitFlag = true;
					break;
				default:
					System.out.println("Please select valid option!!");
					break;
			}
		}while(exitFlag == false);
		scanner.close();
	}
}
