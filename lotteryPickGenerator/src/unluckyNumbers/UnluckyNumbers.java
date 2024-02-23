package unluckyNumbers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import logs.Logfile;

/**
 * Diese Klasse enthält die Funktionalitäten zur Festlegung von Unglückszahlen.
 */
public class UnluckyNumbers {
    private ArrayList < Integer > numbers;
    private File file;

    static final String FILENAME = "unglueckszahlen.txt";
    static final int MIN_VALUE = 1;
    static final int MAX_VALUE = 50;
    static final int MAX_COUNT = 6;

    /**
     * Bei Erstellung des Unglückszahlen-Objekts wird zunächst geprüft, ob eine Speicherdatei für Unglückszahlen existiert.
     * Ist dies der Fall, werden die dort gespeicherten Zahlen geladen. Ansonsten wird eine neue Speicherdatei erstellt.
     */
    public UnluckyNumbers() {
        numbers = new ArrayList < Integer > ();
        file = new File(FILENAME);

        boolean validFile = true;

        try {
            if (!file.createNewFile()) {
                Scanner scanner = new Scanner(file);
                int i = 0;

                while (scanner.hasNextLine() && i < MAX_COUNT) {
                    numbers.add(Integer.parseInt(scanner.nextLine()));
                }

                if (i == MAX_COUNT) {
                    validFile = false;
                }

                scanner.close();

            } else {
                Logfile.logger.info("Unglückszahlen-Datei erstellt");
            }

        } catch (IOException e) {
            Logfile.logger.warning("Fehler bei der Verarbeitung der Unglückszahlen-Datei: " + e.toString());
            validFile = false;
        } catch (NumberFormatException e) {
            Logfile.logger.warning("Fehler: Ungültiger Wert in der Unglückszahlen-Datei");
            validFile = false;
        }

        if (!validFile) {
            numbers = new ArrayList < Integer > ();
        }

    }

    /**
     * @return String der die Unglückszahlen in lesbarer Form darstellt
     */
    public String print() {
        if (numbers.size() == 0) {
            return ("Sie haben keine Unglückszahlen angegeben");
        } else {
            String output = "Ihre Unglückszahlen sind: " + numbers.get(0);
            int i = 1;
            while (i < numbers.size()) {
                output += ", " + numbers.get(i);
                i++;
            }
            return output;
        }
    }

    /**
     * Methode zum Hinzufügen einer neuen Unglückszahl
     * @param input Nutzereingabe für die hinzuzufügende Zahl
     * @throws Exception Wird im Fehlerfall mit einer entsprechenden Fehlermeldung geworfen. Mögliche Fehlerfälle:
     * 1. Maximum an Unglückszahlen bereits erreicht.
     * 2. Zahl ist bereits als Unglückszahl registriert.
     * 3. Zahl ist zu niedrig oder zu hoch.
     * 4. Eingabe ist keine Zahl. 
     */
    public void addNumber(String input) throws Exception {
        Logfile.logger.info(print());
        Logfile.logger.info("Input für neue Unglückszahl: " + input);
        if (isFull()) {
            throw new Exception("Es können nicht mehr als " + MAX_COUNT + " Unglückszahlen gespeichert werden");
        } else {
            try {
                int newNumber = Integer.parseInt(input);
                if (isUnluckyNumber(newNumber)) {
                    throw new Exception("Die Zahl " + newNumber + " ist bereits als Unglückszahl gespeichert");
                } else if (newNumber < MIN_VALUE || newNumber > MAX_VALUE || isUnluckyNumber(newNumber)) {
                    throw new Exception("Die neue Unglückszahl muss zwischen " + MIN_VALUE + " und " + MAX_VALUE + " liegen");
                } else {
                    numbers.add(newNumber);
                    Collections.sort(numbers);
                    save();
                }
            } catch (NumberFormatException e) {
                throw new Exception("Ungültige Eingabe. Bitte geben Sie eine Zahl zwischen " + MIN_VALUE + " und " + MAX_VALUE + " ein");
            }

        }
    }

    /**
     * Löschung aller Unglückszahlen
     */
    public void clearNumbers() {
        Logfile.logger.info("Alle Unglückszahlen gelöscht");
        numbers = new ArrayList < Integer > ();
        save();
    }

    /**
     * Methode zum Löschen einer Unglückszahl
     * @param input Nutzereingabe für die zu löschende Zahl
     * @throws Exception Wird im Fehlerfall mit einer entsprechenden Fehlermeldung geworfen. Mögliche Fehlerfälle:
     * 1. Zahl ist nicht als Unglückszahl registriert.
     * 2. Eingabe ist keine Zahl.
     */
    public void remove(String input) throws Exception {
        Logfile.logger.info("Ausgabe");
        Logfile.logger.info("Input für zu löschende Unglückszahl: " + input);

        boolean deleted = false;

        try {
            int deleteValue = Integer.parseInt(input);
            for (int i = 0; i < numbers.size(); i++) {
                if (deleteValue == numbers.get(i)) {
                    numbers.remove(i);
                    save();
                    i = numbers.size();
                    deleted = true;
                }
            }
            if (!deleted) {
                throw new Exception("Die Zahl " + deleteValue + " ist nicht als Unglückszahl gespeichert");
            }
        } catch (NumberFormatException e) {
            throw new Exception("Ungültige Eingabe. Bitte geben sie die Unglückszahl ein, die gelöscht werden soll");
        }
    }

    /**
     * Die derzeitigen Unglückszahlen werden in die Speicherdatei geschrieben. Wird bei jeder Änderung an den Unglückszahlen aufgerufen.
     */
    private void save() {
        try {
            PrintStream printStream = new PrintStream(new FileOutputStream(FILENAME));
            for (int i = 0; i < numbers.size(); i++) {
                printStream.println(numbers.get(i));
            }
            printStream.close();
            Logfile.logger.info("Unglückszahlen-Datei erfolgreich aktualisiert");
        } catch (IOException e) {
            Logfile.logger.warning("Fehler beim Schreiben zur Unglückszahlen-Datei: " + e.toString());
        }
    }

    private boolean isFull() {
        return numbers.size() == MAX_COUNT;
    }

    public boolean isEmpty() {
        return numbers.isEmpty();
    }

    /**
     * Prüft, ob eine Zahl als Unglückszahl gespeichert ist.
     * @param number Zu prüfende Zahl
     * @return true wenn diese Zahl eine Unglückszahl ist, false wenn nicht
     */
    public boolean isUnluckyNumber(int number) {
        boolean result = false;
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) == number) {
                result = true;
            }
        }
        return result;
    }

    /**
     * @return ArrayList mit den derzeitigen Unglückszahlen
     */
    public ArrayList < Integer > getNumbers() {
        return numbers;
    }


}