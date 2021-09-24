package beans;

public class Logger {
	
	private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Logger.class);
	
	public void erro () {
		
		try {
			throw new Exception("New Exception");			
		} catch (Exception e) {
			log.error(e);
		}
		
	}
	
	public void info (String INFO) {
		log.info(INFO);
	}
	
	public void debug (String DEBUG) {
		log.debug(DEBUG);
	}

}
