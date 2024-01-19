package lotto;

public class Lotto {

	public interface Tippreihe {
		int getTippAnzahl();
		int getTippMaxwert();
		void neueTippreihe();
		String ausgabe();
	}
	
	public abstract class lottoTippreihe implements Tippreihe {
		private int tippAnzahl, tippMaxwert;
		private int[] tipps;
		
		public void neueTippreihe(int[] unglueckszahlen) {
			int zufallszahl = 0;
			int position = 0;
			boolean valideZahl = true;
			this.tipps = new int[tippAnzahl];
			
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
			
		}
		
		public int getTippAnzahl() {
			return tippAnzahl;
		}
		
		public int getTippMaxwert() {
			return tippMaxwert;
		}
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
