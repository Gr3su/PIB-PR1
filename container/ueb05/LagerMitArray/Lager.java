/**
*
* Lager zum sammeln von Artikeln in Form eines Arrays.
* ACHTUNG: Lager.java enthaelt weit aus mehr Details, diese hier dient nur
* der Veranschaulichung wie es mit einem Array funktioniert, statt mit einer
* verketteten Liste.
*
* Alle Tests aus Uebung 6 laufen dennoch fehlferfrei ab.
* 
* @author Yannick Gross
* @version 20.02.2023 / 20:00Uhr
*/

public class Lager {
	private int maxGroesse;
	private int fuellstand;
	private Artikel [] lager;
	
	public Lager(int maxGroesse) {
		if(maxGroesse < 1) {
			throw new IllegalArgumentException("Zu klein.");
		}
		
		this.maxGroesse = maxGroesse;
		this.fuellstand = 0;
		this.lager = new Artikel[maxGroesse];
	}
	
	public Lager() {
		this(10);
	}
	
	public void legeAnArtikel(Artikel artikel) {
		if(artikel == null) {
			throw new IllegalArgumentException("Kein Objekt.");
		}
		
		lager[fuellstand++] = artikel;
	}
	
	public void entferneArtikel(int artikelNr) {
		int index = getArtikelIndex(artikelNr);
		
		lager[index] = lager[fuellstand-1];
		lager[fuellstand-1] = null;
		fuellstand--;
	}
	
	public void bucheZugang(int artikelNr, int zugang) {
		int index = getArtikelIndex(artikelNr);
		lager[index].bucheZugang(zugang);
	}
	
	public void bucheAbgang(int artikelNr, int abgang) {
		int index = getArtikelIndex(artikelNr);
		lager[index].bucheAbgang(abgang);
	}
	
	public void aenderePreisEinesArtikels(int artikelNr, double prozent) {
		int index = getArtikelIndex(artikelNr);
		lager[index].aenderePreis(prozent);
	}
	
	public void aenderePreisAllerArtikel(double prozent) {
		for(int i = 0; i < fuellstand; i++) {
			lager[i].aenderePreis(prozent);
		}
	}
	
	public Artikel getArtikel(int index) {
		if(index < 0 || index >= fuellstand) {
			throw new IllegalArgumentException("Index out of range.");
		}
		
		return lager[index];
	}
	
	@Override
	public String toString() {
		StringBuilder ausgabe = new StringBuilder("Lagerbestand:\n");
		for(int i = 0; i < fuellstand; i++) {
			ausgabe.append(lager[i].toString());
		}
		
		return ausgabe.toString();
	}
	
	public int getArtikelAnzahl() {
		return fuellstand;
	}
	
	public int getLagerGroesse() {
		return maxGroesse;
	}
	
	public int getArtikelIndex(int artikelNr) {
		for(int i = 0; i < fuellstand; i++) {
			if(lager[i].getArtikelNr() == artikelNr) {
				return i;
			}
		}
		throw new IllegalArgumentException("Artikel nicht vorhanden.");
	}
}
