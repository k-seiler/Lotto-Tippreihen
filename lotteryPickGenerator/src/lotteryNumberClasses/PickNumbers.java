package lotteryNumberClasses;

import java.util.ArrayList;

/**
 * Interface, welches Methoden und Parameter für Lotto-Tippreihen benennt
 */
public interface PickNumbers {
    /**
     * @return Anzahl der Zahlen in einer Tippreihe
     */
    int getPickCount();
    /**
     * @return Höchstwert für Tipps
     */
    int getMaxPick();

    /**
     * Generierung einer neuen Tippreihe
     * @param unluckyNumbers Unglückszahlen, die bei der Generierung ausgeschlossen werden
     */
    void newPick(ArrayList < Integer > unluckyNumbers);

    /**
     * @return Ausgabe der Tippreihe in lesbarer Form
     */
    String print();
}