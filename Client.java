package com.capgemini.hotelbooking.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.capgemini.hotelbooking.bean.BookingBean;
import com.capgemini.hotelbooking.bean.UserBean;
import com.capgemini.hotelbooking.exception.BookingException;
import com.capgemini.hotelbooking.service.CustomerService;
import com.capgemini.hotelbooking.service.ICustomerService;


public class Client {
	public static void main(String[] args) throws BookingException {
		
		ICustomerService service = new CustomerService();
		int choice = 0, bookingID = 0, truckID = 0, noOfTrucks = 0, actualCount = 0;
		long customerMobile = 0;
		String customerID = null;
		LocalDate dateOfTransport = null;
		boolean customerIdFlag = false, truckIdFlag = false, numberFlag = false, mobileFlag = false, 
				dateFlag = false;
		List<UserBean> truckList =  service.retrieveTruckDetails();
		
		try(Scanner scanner = new Scanner(System.in)) {
			do {
				System.out.println("1. Book Trucks");
				System.out.println("2. Exit");
				System.out.println("******************************");
				System.out.print("\nPlease Enter a Choice : ");
				choice = scanner.nextInt();
				System.out.println("\n******************************");
				
				switch(choice)
				{
					case 1 :// Add booking Information
					{
						do{
							System.out.print("Enter Customer ID : ");
							customerID = scanner.next();
							customerIdFlag = CustomerService.validateCustomerID(customerID);
							if(customerIdFlag == false){
								System.out.println("Enter the valid customer ID (eg. A111111)");
							}
						}while(customerIdFlag==false);
						
						System.out.println("Please see the truck details below : ");
						for(UserBean truck : truckList){
							System.out.println(truck);
						}
						
						do{
							System.out.print("Please enter the truck Id : ");
							truckID = scanner.nextInt();
							for(UserBean truck : truckList){
								if(truck.getTruckID() == truckID){
									truckIdFlag = true;
									actualCount = truck.getAvailableNos();
								}
							}
							if(truckIdFlag == false){
								System.out.println("Enter the valid truck ID");
							}
						}while(truckIdFlag == false);
						
						do{
							System.out.print("Enter the number of trucks : ");
							noOfTrucks = scanner.nextInt();
							numberFlag = CustomerService.validateNumberOfTrucks(noOfTrucks, actualCount);
							if(numberFlag == false){
								System.out.println("Enter the valid number of trucks");
							}
						}while(numberFlag == false);
						
						do{
							System.out.print("Enter customer mobile : ");
							customerMobile = scanner.nextLong();
							mobileFlag = CustomerService.validatePhoneNumber(Long.toString(customerMobile));
							if(mobileFlag == false){
								System.out.println("Enter the valid mobile number");
							}
						}while(mobileFlag == false);
						
						do{
							System.out.print("Enter date of transportation (yyyy-mm-dd) : ");
							String dateString = scanner.next();
							dateFlag = CustomerService.validateDate(dateString);
							if(dateFlag == true){
								int year = Integer.parseInt(dateString.substring(0, 4));
								int month = Integer.parseInt(dateString.substring(5, 7));
								int date = Integer.parseInt(dateString.substring(8, 10));
								dateOfTransport = LocalDate.of(year, month, date);
							}
							else{
								System.out.println("Enter the valid date");
							}
						}while(dateFlag == false);
			
						BookingBean bookingBean = new BookingBean(customerID, customerMobile, truckID, noOfTrucks, dateOfTransport);
						
						try {
							bookingID = service.bookTrucks(bookingBean);
							System.out.println("Thank you. Your booking id is " + bookingID);
							
						}
						catch(BookingException e) {
							System.out.println(e.getMessage());
						}
						break;
					}
					case 2:
					{
						System.out.println("Thanks and visit again.");
						break;
					}	
				}
			}while(choice!=2); //End of do while
		} // End of try with resources block
	} // End of main method
} // End of client class
