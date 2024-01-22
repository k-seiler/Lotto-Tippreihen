package lottoTippreihenGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Unglueckszahlen {
	private static ArrayList<Integer> zahlen;
	private static File file;
	
	static final String DATEINAME = "unglueckszahlen.txt";
	static final int MIN_WERT = 1;
	static final int MAX_WERT = 50;
	static final int MAX_ANZAHL = 6;
	
	public static void init() {
		zahlen = new ArrayList<Integer>();
		file = new File(DATEINAME);
		boolean korrekteDatei = true;
		
		try {
			if (!file.createNewFile()) {				
				Scanner scanner = new Scanner(file);
				int i = 0;
				
				while (scanner.hasNextLine() && i < MAX_ANZAHL) {
					zahlen.add( Integer.parseInt( scanner.nextLine()));
				}
				
				if(i == MAX_ANZAHL) {
					korrekteDatei = false;
				}
				
				scanner.close();
				
			} else {
				Logfile.logger.info("Unglückszahlen-Datei erstellt");
			}
			
		} catch (IOException e) {
			Logfile.logger.warning("Fehler bei der Verarbeitung der Unglückszahlen-Datei: " + e.toString());
			korrekteDatei = false;
		} catch(NumberFormatException e) {
			Logfile.logger.warning("Fehler: Ungültiger Wert in der Unglückszahlen-Datei");
			korrekteDatei = false;
		}
		
		if(!korrekteDatei) {
			zahlen = new ArrayList<Integer>();
		}
		
	}
	
	public static String ausgabe() {
		if( zahlen.size() == 0 ) {
			return( "Sie haben keine Unglückszahlen angegeben" );
		} else {
			String ausgabe = "Ihre Unglückszahlen sind: " + zahlen.get(0);
			int i = 1;
			while( i < zahlen.size() ) {
				ausgabe += ", " + zahlen.get(i);
				i++;
			}
			return ausgabe;
		}
	}
	
	public static String hinzufuegen( String input ) {
		Logfile.logger.info(ausgabe());
		Logfile.logger.info("Input für neue Unglückszahl: " + input);
		String ergebnis = "";
		if( istVoll() ) {
			ergebnis = "Es können nicht mehr als " + MAX_ANZAHL + " Unglückszahlen gespeichert werden";
		} else {
			try {
				int neueZahl = Integer.parseInt(input);
				if( istEnthalten(neueZahl) ) {
					ergebnis = "Die Zahl " + neueZahl + " ist bereits als Unglückszahl gespeichert";
				} else {
					
				if( neueZahl < MIN_WERT || neueZahl > MAX_WERT || istEnthalten(neueZahl) ) {
					ergebnis = "Die neue Unglückszahl muss zwischen " + MIN_WERT + " und " + MAX_WERT + " liegen";
				} else {				
					zahlen.add( neueZahl );
					Collections.sort( zahlen );
					speichern();
					ergebnis = "Unglückszahl " + neueZahl + " erfolgreich hinzugefügt";
				}
				}
			}
			catch (NumberFormatException e) {
				ergebnis = "Ungültige Eingabe. Bitte geben Sie eine Zahl zwischen " + MIN_WERT + " und " + MAX_WERT + " ein";
		}
		
		}
		Logfile.logger.info("Ergebnis: " + ergebnis);
		return ergebnis;
	}
	
	public static void allesLoeschen() {
		Logfile.logger.info("Alle Unglückszahlen gelöscht");
		zahlen = new ArrayList<Integer>();
		speichern();
	}
	
	public static String entfernen( String input ) {
		Logfile.logger.info("Ausgabe");
		Logfile.logger.info("Input für zu löschende Unglückszahl: " + input);
		
		boolean geloescht = false;
		String ergebnis = "";
		
		try {
			int loeschWert = Integer.parseInt(input);
			for( int i = 0; i < zahlen.size(); i++) {
				if( loeschWert == zahlen.get(i) ) {
					zahlen.remove(i);
					speichern();
					i = zahlen.size();
					geloescht = true;
					ergebnis = "Unglückszahl " + loeschWert + " erfolgreich gelöscht";
				} 
			}
			if( !geloescht ) {
				ergebnis = "Die Zahl " + loeschWert + " ist nicht als Unglückszahl gespeichert";
			}
		}
		catch (NumberFormatException e) {
			ergebnis = "Ungültige Eingabe. Bitte geben sie die Unglückszahl ein, die gelöscht werden soll";
		}
		
		Logfile.logger.info("Ergebnis: " + ergebnis);
		return ergebnis;
	}
	
	private static void speichern() {
		try {
			PrintStream printStream = new PrintStream(new FileOutputStream(DATEINAME));
			for( int i = 0; i < zahlen.size(); i++) {
				printStream.println(zahlen.get(i));
			}
			printStream.close();
			Logfile.logger.info("Unglückszahlen-Datei erfolgreich aktualisiert");
		} catch (IOException e) {
			Logfile.logger.warning("Fehler beim Schreiben zur Unglückszahlen-Datei: " + e.toString());
		}
	}
	
	public static boolean istVoll() {
		return zahlen.size() == MAX_ANZAHL;
	}
	
	public static boolean istLeer() {
		return zahlen.isEmpty();
	}
	
	public static boolean istEnthalten(int zahl) {
		boolean erg = false;
		for( int i = 0; i < zahlen.size(); i++ ) {
			if ( zahlen.get(i) == zahl ) {
				erg = true;
			}
		}
		return erg;
	}
	
	public static Integer[] getZahlen() {
		Integer[] zahlenArray = new Integer[zahlen.size()];
		zahlenArray = zahlen.toArray(zahlenArray);
		return zahlenArray;
	}


}
