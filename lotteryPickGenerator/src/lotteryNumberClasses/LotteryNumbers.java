package lotteryNumberClasses;

import java.util.ArrayList;
import java.util.Arrays;

import logs.Logfile;

/**
 * Abstrakte Klasse, welche die generischen Funktionalitäten zur Erstellung von Lotto-Tippreihen bereitstellt
 */
public abstract class LotteryNumbers implements PickNumbers {
    /**
     * pickCount ist die Anzahl der Zahlen in einer Tippreihe
     * maxPick ist der Höchstwert den eine Zahl in der Tippreihe annehmen kann
     */
    private int pickCount, maxPick;
    private int[] picks;


    /**
     * Erstellung einer zufälligen Tippreihe ohne Berücksichtigung von Unglückszahlen
     */
    public void newPick() {
        int randomNumber = 0;
        int position = 0;
        boolean validNumber = true;
        picks = new int[pickCount];

        while (position < pickCount) {
            validNumber = true;

            randomNumber = (int)(Math.random() * maxPick + 1); //Erstellt eine Zufallszahl zwischen 1 und maxPick

            for (int i: picks)
                if (i == randomNumber)
                    validNumber = false;

            if (validNumber) {
                picks[position] = randomNumber;
                position++;
            }
        }

        Arrays.sort(picks);
        Logfile.logger.info("Neue Tippreihe generiert: " + pickCount + " aus " + maxPick);
    }

    /**
     * Erstellung einer zufälligen Tippreihe unter Ausschluss von Unglückszahlen
     * @param unluckyNumbers ArrayList der Unglückszahlen, welche nicht in die Tippreihe aufgenommen werden
     */
    public void newPick(ArrayList < Integer > unluckyNumbers) {
        if (unluckyNumbers == null) {
            newPick();
        } else {


            int randomNumber = 0;
            int position = 0;
            boolean validNumber = true;
            picks = new int[pickCount];

            while (position < pickCount) {
                validNumber = true;
                randomNumber = (int)(Math.random() * maxPick + 1); //Erstellt eine Zufallszahl zwischen 1 und maxPick

                for (int i: unluckyNumbers)
                    if (i == randomNumber)
                        validNumber = false;
                for (int i: picks)
                    if (i == randomNumber)
                        validNumber = false;

                if (validNumber) {
                    picks[position] = randomNumber;
                    position++;
                }

            }

            Arrays.sort(picks);

            Logfile.logger.info("Neue Tippreihe generiert: " + pickCount + " aus " + maxPick);
        }
    }

    /**
     * @return String der die Tippreihe und die Bonuszahlen in lesbarer Form darstellt
     */
    public String print() {
        String output = "Tippreihe " + pickCount + " aus " + maxPick + ":      ";
        for (int i: picks) {
            output += picks[i];
            if (i + 1 < getPickCount()) {
                output += ", ";
            }
        }
        return output;
    }

    public void setPickCount(int i) {
        pickCount = i;
    }

    public void setMaxPick(int i) {
        maxPick = i;
    }

    public int getPickCount() {
        return pickCount;
    }

    public int getMaxPick() {
        return maxPick;
    }

    public int[] getPicks() {
        return picks;
    }
}