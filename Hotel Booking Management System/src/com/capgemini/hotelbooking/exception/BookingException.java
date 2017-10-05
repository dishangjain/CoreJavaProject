package com.capgemini.hotelbooking.exception;

public class BookingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookingException() {
		super();
	}

	public BookingException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public BookingException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public BookingException(String arg0) {
		super(arg0);
	}

	public BookingException(Throwable arg0) {
		super(arg0);
	}

}
