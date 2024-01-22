package lottoTippreihenGenerator;

public class Eurojackpot extends LottoTippreihe {
	LottoTippreihe bonuszahlen = new LottoTippreihe();
	
	public Eurojackpot() {
		setTippAnzahl(5);
		setTippMaxwert(50);
		neueTippreihe();
		bonuszahlen.neueTippreihe(2, 10); 
	}
	
	@Override
	public String ausgabe() {
		int[] tipps = getTipps();
		String ausgabe = "Tippreihe für den Eurojackpot:    ";
		for( int i = 0; i < getTippAnzahl(); i++ ) {
			ausgabe += tipps[i];
			if( i + 1 < getTippAnzahl()) {
				ausgabe += ", ";
			}
		}
		ausgabe += " | Bonuszahlen: ";
		ausgabe += bonuszahlen.getTipps()[0];
		ausgabe += " und ";
		ausgabe += bonuszahlen.getTipps()[1];		
		return ausgabe;
	}
	
}
