package lottoTippreihenGenerator;

public interface Tippreihe {
	int getTippAnzahl();
	int getTippMaxwert();
	
	void neueTippreihe();
	
	String ausgabe();
}
