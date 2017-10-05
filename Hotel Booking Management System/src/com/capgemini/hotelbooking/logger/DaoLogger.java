package com.capgemini.hotelbooking.logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.apache.log4j.*;

public class DaoLogger {
	
	public static Logger myLogger = Logger.getLogger(DaoLogger.class);
	
	static WriterAppender appender = null;
	static HTMLLayout hlay = new HTMLLayout();
	
	static
	{
		try
		{
			FileOutputStream fout = new FileOutputStream("logger.html");
			appender = new WriterAppender(hlay, fout);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		myLogger.addAppender(appender);
		
	}
	
}

