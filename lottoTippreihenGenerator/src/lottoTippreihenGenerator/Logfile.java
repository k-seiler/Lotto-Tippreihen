package lottoTippreihenGenerator;

import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.IOException;
import java.util.logging.FileHandler;

public class Logfile {
	public static Logger logger = Logger.getLogger("LottoTestreihenLogger");
	private static FileHandler fileHandler;
	
	public static void init() {
		
		try {  

	        // Logger und Logfile initialisieren
	        fileHandler = new FileHandler("logfile.log");  
	        logger.addHandler(fileHandler);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fileHandler.setFormatter(formatter);  

	        logger.setUseParentHandlers(false); //Um Ausgabe der Logs in der Konsole zu verhindern
	        
	        logger.info("Programm gestartet");  

	    } catch (Exception e) { 
	    	System.out.println("Fehler beim Erstellen des Logfiles");
	        e.printStackTrace();  
	    }
		
	}
	
}
