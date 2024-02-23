package lotteryNumberClasses;

import java.util.ArrayList;

/**
 * Klasse für Eurojackpot-Tippreihen
 */
public class Eurojackpot extends LotteryNumbers {
    public static final int PICKCOUNT = 5;
    public static final int MAXPICK = 50;

    EurojackpotBonusNumbers bonusNumbers = new EurojackpotBonusNumbers();

    public Eurojackpot() {
        setPickCount(PICKCOUNT);
        setMaxPick(MAXPICK);
    }

    /**
     * @return String der die Tippreihe und die Bonuszahlen in lesbarer Form darstellt
     */
    @Override
    public String print() {
        int[] picks = getPicks();
        String output = "Tippreihe für den Eurojackpot:    ";
        for (int i = 0; i < getPickCount(); i++) {
            output += picks[i];
            if (i + 1 < getPickCount()) {
                output += ", ";
            }
        }
        output += " | Bonuszahlen: ";
        output += bonusNumbers.getPicks()[0];
        output += " und ";
        output += bonusNumbers.getPicks()[1];
        return output;
    }

    /**
     * Zur Erstellung einer neuen Eurojackpot-Tippreihe werden 5-aus-50 Tipps und 2-aus-10 Bonuszahlen erzeugt
     */
    @Override
    public void newPick(ArrayList < Integer > unluckyNumbers) {
        super.newPick(unluckyNumbers);
        bonusNumbers.newPick(unluckyNumbers);
    }
}

/**
 * Die Bonuszahlen einer Eurojackpot-Tippreihe werden als 2-aus-10-Tippreihe implementiert
 */
class EurojackpotBonusNumbers extends LotteryNumbers {

    public EurojackpotBonusNumbers() {
        setPickCount(2);
        setMaxPick(10);
    }

}