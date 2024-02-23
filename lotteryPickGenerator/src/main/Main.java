package main;

import java.util.Scanner;

import logs.Logfile;
import lotteryNumberClasses.Eurojackpot;
import lotteryNumberClasses.Lotto6from49;
import lotteryNumberClasses.PickNumbers;
import unluckyNumbers.UnluckyNumbers;

/**
 * Klasse für die Kommunikation mit dem Nutzer. Hier finden die Ausgaben, sowie die Eingaben des Nutzers in der Kommandozeile statt.
 */
public class Main {

    static Scanner scanner; //Scanner zur Verarbeitung der Userinputs

    static UnluckyNumbers unluckyNumbers;

    /**
     * Methode die bei Programmstart aufgerufen wird
     */
    public static void main(String[] args) {
        final String INPUT_NEWLOTTERYPICK = "neu";

        boolean exit = false;

        unluckyNumbers = new UnluckyNumbers();

        scanner = new Scanner(System.in);

        System.out.println("Willkommen zum Tippreihen-Generator");

        while (!exit) {

            System.out.println(startRoutine().print());

            System.out.println("");
            System.out.println();
            System.out.println("Um eine neue Tippreihe zu generieren, geben Sie [" + INPUT_NEWLOTTERYPICK + "] ein, ansonsten wird das Programm beendet");
            String input = scanner.nextLine();
            if (!input.equalsIgnoreCase(INPUT_NEWLOTTERYPICK)) {
                exit = true;
                scanner.close();
            }
        }

    }


    /**
     * In dieser Methode wird das Spiel ausgewählt, für welches die Tippreihe erstellt werden soll. Anschließend wird die entsprechende Reihe generiert.
     * @return Tippreihen-Objekt für das gewählte Spiel
     */
    private static PickNumbers startRoutine() {
        final String INPUT_LOTTO = "lotto";
        final String INPUT_EUROJACKPOT = "euro";

        String input;

        PickNumbers pickNumbers = null;

        System.out.println("");
        System.out.println("Bitte wählen Sie die gewünschte Option");
        System.out.println("");
        System.out.println("[" + INPUT_LOTTO + "] - Lotto Tippreihe erstellen (6 aus 49)");
        System.out.println("[" + INPUT_EUROJACKPOT + "] - Eurojackpot Tippreihe erstellen (5 aus 50 mit 2 aus 10 Bonuszahlen)");

        while (pickNumbers == null) {

            input = scanner.nextLine();
            Logfile.logger.info("Auswahl Lotto oder Eurojackpot - Userinput: " + input);

            if (input.equals("") || input.equalsIgnoreCase(INPUT_LOTTO)) {
                System.out.println("Lotto-Tippreihe wird erstellt");
                unluckyNumberRoutine();
                pickNumbers = new Lotto6from49();
            } else if (input.equalsIgnoreCase(INPUT_EUROJACKPOT)) {
                System.out.println("Eurojackpot-Tippreihe wird erstellt");
                unluckyNumberRoutine();
                pickNumbers = new Eurojackpot();
            } else {
                System.out.println("Ungültige Eingabe. Folgende Eingaben sind möglich:");
                System.out.println(INPUT_LOTTO);
                System.out.println(INPUT_EUROJACKPOT);
            }

        }

        pickNumbers.newPick(unluckyNumbers.getNumbers());

        System.out.println("");

        return pickNumbers;
    }

    /**
     * Methode zur Verwaltung der Unglückszahlen. Sie wird nach der Auswahl des Spiels aufgerufen. Hier können Unglückszahlen angezeigt, hinzugefügt und gelöscht werden.
     */
    private static void unluckyNumberRoutine() {
        final String INPUT_ADD = "1";
        final String INPUT_DELETE = "2";
        final String INPUT_CONTINUE = "3";

        boolean isContinue = false;

        while (!isContinue) {
            System.out.println("");
            System.out.println(unluckyNumbers.print());
            System.out.println("");
            System.out.println("[" + INPUT_ADD + "] - Neue Unglückszahl(en) hinzufügen");
            System.out.println("[" + INPUT_DELETE + "] - Unglückszahl(en) löschen");
            System.out.println("[" + INPUT_CONTINUE + "] - Weiter zur Tippreihe");

            String input = scanner.nextLine();
            Logfile.logger.info("Optionswahl für die Unglückszahlen - Userinput: " + input);

            boolean isDone;

            switch (input) {
                case INPUT_ADD:
                    isDone = false;
                    while (!isDone) {
                        System.out.println("");
                        System.out.println(unluckyNumbers.print());
                        System.out.println("");
                        System.out.println("Bitte geben Sie die neue Unglückszahl ein | [z]urück zum vorherigen Menü.");
                        String newNumberInput = scanner.nextLine();
                        if (newNumberInput.equalsIgnoreCase("z")) {
                            isDone = true;
                        } else {
                            try {
                                unluckyNumbers.addNumber(newNumberInput);
                                System.out.println("Unglückszahl " + newNumberInput + " erfolgreich hinzugefügt");
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                                Logfile.logger.info("Fehler: " + e.getMessage());
                            }
                        }
                    }

                    break;
                case INPUT_DELETE:
                    isDone = false;
                    while (!isDone) {
                        unluckyNumbers.print();
                        if (unluckyNumbers.isEmpty()) {
                            isDone = true;
                        } else {
                            System.out.println("");
                            System.out.println(unluckyNumbers.print());
                            System.out.println("");
                            System.out.println("Bitte geben Sie die zu löschende Zahl ein | [a]lle Zahlen löschen | [z]urück zum vorherigen Menü");
                            String deleteInput = scanner.nextLine();
                            if (deleteInput.equals("z")) {
                                isDone = true;
                            } else if (deleteInput.equals("a")) {
                                unluckyNumbers.clearNumbers();
                                System.out.println("Alle Unglückszahlen gelöscht");
                                isDone = true;
                            } else {
                                try {
                                    unluckyNumbers.remove(deleteInput);
                                    System.out.println("Unglückszahl " + deleteInput + " erfolgreich gelöscht");
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                    Logfile.logger.info("Fehler: " + e.getMessage());
                                }
                            }
                        }
                    }
                    break;
                case INPUT_CONTINUE:
                    isContinue = true;
                    break;
                default:
                    System.out.println("Ungültige Eingabe. Bitte geben Sie die Zahl für ihre gewünschte Option ein");
                    break;
            }
        }
    }
}