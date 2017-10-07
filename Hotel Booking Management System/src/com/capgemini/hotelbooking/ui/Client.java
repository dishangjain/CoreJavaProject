package com.capgemini.hotelbooking.ui;

import java.util.Scanner;

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
					UserBean userBean = commonService.Login(username, password);
					if(userBean == null){
						System.out.println("Please enter valid credentials and try again.");
					}
					else{
						if("admin".equals(userBean.getRole())){
							AdminService adminService = new AdminService();
						}
						else if("customer".equals(userBean.getRole())){
							CustomerService customerService = new CustomerService();
						}
					}
					break;
				case 2:
					break;
				default:
					System.out.println("Please select valid option!!");
					break;
			}
		}while(exitFlag == false);
		scanner.close();
	}
}
