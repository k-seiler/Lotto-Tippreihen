package lotto;

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
		
		try {
			if (!file.createNewFile()) {
				
				Scanner scanner = new Scanner(file);
				
				while (scanner.hasNextLine()) {
					//TODO exception handling für falsche textfiles
					zahlen.add( Integer.parseInt( scanner.nextLine()));
				}
				
				scanner.close();
				
			} else {
				System.out.println("file erstellt");
			}
			
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			
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
		if( istVoll() ) {
			return "Es können nicht mehr als " + MAX_ANZAHL + " Unglückszahlen gespeichert werden";
		} else {
			try {
				int neueZahl = Integer.parseInt(input);
				if( istEnthalten(neueZahl) ) {
					return "Die Zahl " + neueZahl + " ist bereits als Unglückszahl gespeichert";
				} else {
					
				if( neueZahl < MIN_WERT || neueZahl > MAX_WERT || istEnthalten(neueZahl) ) {
					return "Die neue Unglückszahl muss zwischen " + MIN_WERT + " und " + MAX_WERT + " liegen";
				} else {				
					zahlen.add( neueZahl );
					Collections.sort( zahlen );
					speichern();
					return "Unglückszahl " + neueZahl + " erfolgreich hinzugefügt";
				}
				}
			}
			catch (NumberFormatException e) {
				//e.printStackTrace();
				return "Ungültige Eingabe. Bitte geben Sie eine Zahl zwischen " + MIN_WERT + " und " + MAX_WERT + " ein";
		}
		
		}
	}
	
	public static void allesLoeschen() {
		zahlen = new ArrayList<Integer>();
		speichern();
	}
	
	public static String entfernen( String input ) {
		boolean geloescht = false;
		try {
			int loeschWert = Integer.parseInt(input);
			for( int i = 0; i < zahlen.size(); i++) {
				if( loeschWert == zahlen.get(i) ) {
					zahlen.remove(i);
					i = zahlen.size();
					geloescht = true;
				} 
			}
			if( geloescht ) {
				return "Unglückszahl " + loeschWert + " erfolgreich gelöscht";
			} else {
				return "Die Zahl " + loeschWert + " ist nicht als Unglückszahl gespeichert";
			}
		}
		catch (NumberFormatException e) {
			return "Ungültige Eingabe. Bitte geben sie die Unglückszahl ein, die gelöscht werden soll";
		}
	}
	
	private static void speichern() {
		try {
			PrintStream printStream = new PrintStream(new FileOutputStream(DATEINAME));
			for( int i = 0; i < zahlen.size(); i++) {
				printStream.println(zahlen.get(i));
			}
			printStream.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
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
