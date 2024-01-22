package lotto;

import java.util.Arrays;

public class LottoTippreihe implements Tippreihe {
	private int tippAnzahl, tippMaxwert;
	private int[] tipps;
	
	public void neueTippreihe(int anzahl, int maxwert) {
		tippAnzahl = anzahl;
		tippMaxwert = maxwert;
		neueTippreihe();
	}
	
	public void neueTippreihe() {
		Integer[] unglueckszahlen = Unglueckszahlen.getZahlen();
		int zufallszahl = 0;
		int position = 0;
		boolean valideZahl = true;
		tipps = new int[tippAnzahl];
		
		while(position < tippAnzahl) {
			valideZahl = true;
			zufallszahl = (int)(Math.random() * tippMaxwert + 1);
			
			for(int i : unglueckszahlen)
				if(i == zufallszahl) 
					valideZahl = false;
			for(int i : tipps)
				if(i == zufallszahl)
					valideZahl = false;
			
			if(valideZahl) {
				tipps[position] = zufallszahl;
				position++;
			}
			
		}
		
		Arrays.sort(tipps);
	}

	public String ausgabe() {
		String ausgabe = "Tippreihe " + tippAnzahl + " aus " + tippMaxwert + ":      ";
		for( int i : tipps ) {
			ausgabe += tipps[i];
			if( i + 1 < getTippAnzahl()) {
				ausgabe += ", ";
			}
		}
		return ausgabe;
	}
	
	public void setTippAnzahl(int i) {
		tippAnzahl = i;
	}
	
	public void setTippMaxwert(int i) {
		tippMaxwert = i;
	}
	
	public int getTippAnzahl() {
		return tippAnzahl;
	}
	
	public int getTippMaxwert() {
		return tippMaxwert;
	}
	
	public int[] getTipps() {
		return tipps;
	}
}
