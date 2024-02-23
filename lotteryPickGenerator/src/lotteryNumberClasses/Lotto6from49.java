package lotteryNumberClasses;

/**
 * Klasse für 6-aus-49 Lotto-Tippreihen
 */
public class Lotto6from49 extends LotteryNumbers {
    public static final int PICKCOUNT = 6;
    public static final int MAXPICK = 49;

    public Lotto6from49() {
        setPickCount(PICKCOUNT);
        setMaxPick(MAXPICK);
    }

    /**
     * @return String der die Tippreihe und die Bonuszahlen in lesbarer Form darstellt
     */
    @Override
    public String print() {
        int[] picks = getPicks();
        String output = "Tippreihe für Lotto 6 aus 49:    ";
        for (int i = 0; i < getPickCount(); i++) {
            output += picks[i];
            if (i + 1 < getPickCount()) {
                output += ", ";
            }
        }
        return output;
    }

}