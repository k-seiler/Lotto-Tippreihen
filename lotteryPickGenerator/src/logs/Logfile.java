package logs;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Diese Klasse ist für die Protokollierung des Programmablaufs in einem Logfile verantwortlich
 */
public class Logfile {
    /**
     * FileHandler zum erstellen einer Textdatei als Logfile
     */
    private static FileHandler fileHandler;
    /**
     * Logger der von anderen Klassen aufgerufen werden kann um neue Logeinträge hinzuzufügen
     */
    public static Logger logger = Logger.getLogger("LottoTestreihenLogger");

    /**
     * Initializer, in dem das Logfile erstellt wird und der Logger dieser neuen Datei zugewiesen wird
     */
    static {
        try {

            // Logger und Logfile initialisieren
            fileHandler = new FileHandler("logfile.log");
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);

            logger.setUseParentHandlers(false); // Um Ausgabe der Logs in der Konsole zu verhindern

            logger.info("Programm gestartet");

        } catch (Exception e) {
            System.out.println("Fehler beim Erstellen des Logfiles");
            e.printStackTrace();
        }
    }

}