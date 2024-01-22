package lottoTippreihenGenerator;

//Scanner zur Verarbeitung der Userinputs
import java.util.Scanner;

public class Main {
	
	static Scanner scanner;
	
	public static void main(String[] args) {
		final String INPUT_NEUETIPPREIHE = "neu";
		
		boolean beenden = false;
		
		Logfile.init();
		Unglueckszahlen.init();
		
		scanner = new Scanner(System.in);
		
		System.out.println("Willkommen zum Tippreihen-Generator");
		
		while(!beenden) {
			
			System.out.println(startRoutine().ausgabe());
			
			System.out.println("");
			System.out.println();
			System.out.println("Um eine neue Tippreihe zu generieren, geben Sie [" + INPUT_NEUETIPPREIHE + "] ein, ansonsten wird das Programm beendet");
			String input = scanner.nextLine();
			if(!input.equalsIgnoreCase(INPUT_NEUETIPPREIHE)) {
				beenden = true;
				scanner.close();
			}
		}
		
	}


	private static Tippreihe startRoutine() {
		final String INPUT_LOTTO = "lotto";
		final String INPUT_EUROJACKPOT = "euro";

		String input;
		
		Tippreihe tippreihe = null;

		System.out.println("");
		System.out.println("Bitte wählen Sie die gewünschte Option");
		System.out.println("");
		System.out.println("[" + INPUT_LOTTO + "] - Lotto Tippreihe erstellen (6 aus 49)");
		System.out.println("[" + INPUT_EUROJACKPOT + "] - Eurojackpot Tippreihe erstellen (5 aus 50 mit 2 aus 10 Bonuszahlen)");
		
		while( tippreihe == null ) {
		
			input = scanner.nextLine();
			Logfile.logger.info("Auswahl Lotto oder Eurojackpot - Userinput: " + input);
			
			if(input.equals("") || input.equalsIgnoreCase(INPUT_LOTTO)) {
				System.out.println("Lotto-Tippreihe wird erstellt");
				unglueckszahlenRoutine();
				tippreihe = new Lotto6aus49();
			}
			else if( input.equalsIgnoreCase(INPUT_EUROJACKPOT) ) {
				System.out.println("Eurojackpot-Tippreihe wird erstellt");
				unglueckszahlenRoutine();
				tippreihe = new Eurojackpot();
			}
			else {
				System.out.println("Ungültige Eingabe. Folgende Eingaben sind möglich:");
				System.out.println(INPUT_LOTTO);
				System.out.println(INPUT_EUROJACKPOT);
			}
		
		}

		System.out.println("");
		
		return tippreihe;
	}
	
	private static void unglueckszahlenRoutine() {
		final String INPUT_HINZUFUEGEN = "1";
		final String INPUT_LOESCHEN = "2";
		final String INPUT_WEITER = "3";
		
		boolean weiter = false;
		
		while (!weiter) {
			System.out.println("");
			System.out.println(Unglueckszahlen.ausgabe());
			System.out.println("");
			System.out.println("[" + INPUT_HINZUFUEGEN + "] - Neue Unglückszahl(en) hinzufügen");
			System.out.println("[" + INPUT_LOESCHEN + "] - Unglückszahl(en) löschen");
			System.out.println("[" + INPUT_WEITER + "] - Weiter zur Tippreihe");
			
			String input = scanner.nextLine();
			Logfile.logger.info("Optionswahl für die Unglückszahlen - Userinput: " + input);
			
			boolean fertig;
			switch(input) {
				case INPUT_HINZUFUEGEN:
						fertig = false;
						while (!fertig) {
							System.out.println("");
							System.out.println(Unglueckszahlen.ausgabe());
							System.out.println("");
							System.out.println("Bitte geben Sie die neue Unglückszahl ein | [z]urück zum vorherigen Menü.");
							String neueZahlInput = scanner.nextLine();
							if( neueZahlInput.equalsIgnoreCase("z")) {
								fertig = true;
							}
							else {
								System.out.println(Unglueckszahlen.hinzufuegen(neueZahlInput));
							}					
						}
						
					break;
				case INPUT_LOESCHEN:
					fertig = false;
					while (!fertig) {						
						Unglueckszahlen.ausgabe();
						if (Unglueckszahlen.istLeer()) {
							fertig = true;
						} else {
							System.out.println("");
							System.out.println(Unglueckszahlen.ausgabe());
							System.out.println("");
							System.out.println("Bitte geben Sie die zu löschende Zahl ein | [a]lle Zahlen löschen | [z]urück zum vorherigen Menü");
							String loeschZahlInput = scanner.nextLine();
							if( loeschZahlInput.equals("z")) {
								fertig = true;
							} else if( loeschZahlInput.equals("a") ) {
								Unglueckszahlen.allesLoeschen();
								System.out.println("Alle Unglückszahlen gelöscht");
								fertig = true;
							} else {
								System.out.println(Unglueckszahlen.entfernen(loeschZahlInput));
							}
						}
					}
					break;
				case INPUT_WEITER:
					weiter = true;
					break;
				default:
					System.out.println("Ungültige Eingabe. Bitte geben Sie die Zahl für ihre gewünschte Option ein");
					break;
			}
		}
	}
}
