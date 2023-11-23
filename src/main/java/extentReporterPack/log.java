package extentReporterPack;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class log {

	private static Logger logger =Logger.getLogger(log.class.getName());
	
	public static void info(String msg) {
		PropertyConfigurator.configure("log4j.properties");
		logger.info(msg);
	}
	
	public static void debug(String msg) {
		PropertyConfigurator.configure("log4j.properties");
		logger.debug(msg);
	}
	
	
	
}
