package lotto;

public class Lotto6aus49 extends LottoTippreihe {
	
	public Lotto6aus49() {
		setTippAnzahl(6);
		setTippMaxwert(49);
		neueTippreihe();
	}
	
	@Override
	public String ausgabe() {
		int[] tipps = getTipps();
		String ausgabe = "Tippreihe für Lotto 6 aus 49:    ";
		for( int i = 0; i < getTippAnzahl(); i++ ) {
			ausgabe += tipps[i];
			if( i + 1 < getTippAnzahl()) {
				ausgabe += ", ";
			}
		}
		return ausgabe;
	}
	
}
