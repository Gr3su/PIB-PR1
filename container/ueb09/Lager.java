/**
 * Klasse zum Lagern und Verwalten von Artikel Objekten.
 * 
 * @author Tim Mueller / Yannick Gross
 * @version 03.12.2022 / 21:10
 */

public class Lager{
    //Fehlermeldungen
    private static final String ERROR_LAGERGROESSE_MEHR =      "Die Groesse des Lagers muss mindestens 1 betragen.\n";
    private static final String ERROR_KEIN_OBJEKT =            "Es wurde kein gueltiges Objekt angegeben.\n";
    private static final String ERROR_LAGER_VOLL =             "Artikel konnte nicht angelegt werden, weil das Lager voll ist.\n";
    private static final String ERROR_ARTIKEL_NICHT_GEFUNDEN = "Der gewuenschte Artikel wurde nicht im Lager gefunden.\n";
    private static final String ERROR_INDEX_FALSCH =           "Der angegebene Index ist falsch. Er muss liegen, zwischen 0 und ";
    private static final String ERROR_ARTIKEL_EXISTIERT =      "Die eingegebene Artikelnummer existiert bereits.\n";
    //Konstanten
    private static final int MAX_LAGER = 10;
    //Attribute
    private Artikel[] lagerFeld;
    private final int lagerGroesse;
    private int lagerBestand;
    
    
    /**
     * Konstruktor fuer Lager, der Array mit uebergebener Groesse initialisiert
     * und <code>lagerGroesse</code> sowie <code>lagerBestand</code> initialisiert.
     * 
     * @param lagerGroesse Der Wert fuer die laenge des Arrays und fue <code>lagerGroesse</code>
     */
    public Lager(int lagerGroesse){
        if(lagerGroesse < 1){
            throw new IllegalArgumentException(ERROR_LAGERGROESSE_MEHR);
        }
        
        this.lagerFeld = new Artikel[lagerGroesse];
        this.lagerGroesse = lagerGroesse;
        this.lagerBestand = 0;
    }
    
    /**
     * Standardkonstruktor, der den Ersten mit dem Uebergabeparameter 10 aufruft.
     */
    public Lager(){
        this(MAX_LAGER);
    }
    
    /**
     * Speichert die Referenz eines Artikels in das Lager Array ein.
     * 
     * @param artikel Der Artikel der in das Array gespeichert wird.
     * 
     * @throws IllegalArgumentException Wenn kein Objekt uebergeben wurde.
     * @throws IllegalArgumentException Wenn Array bereits voll ist.
     * @throws IllegalArgumentException Wenn Artikelnummer bereits vergeben ist.
     */
    public void legeAnArtikel(Artikel artikel){
        if(artikel == null){
            throw new IllegalArgumentException(ERROR_KEIN_OBJEKT);
        }
        if(lagerBestand == lagerGroesse){
            throw new IllegalArgumentException(ERROR_LAGER_VOLL);
        }
        if(istArtikelGelagert(artikel.getArtikelNr()) == true){
            throw new IllegalArgumentException(ERROR_ARTIKEL_EXISTIERT);
        }
        
        lagerFeld[lagerBestand] = artikel;
        lagerBestand++;
        
    }
    
    /**
     * Loescht die Referenz eines Artikels aus dem Array.
     * 
     * @param artikelNr Die Artikelnummer des zu loeschenden Artikels.
     * 
     * @throws IllegalArgumentException Wenn zu Artikelnummer gehoerender Artikel
     * in Array gefunden wurde.
     */
    public void entferneArtikel(int artikelNr){
        int index = getIndexArtikel(artikelNr);
        
        lagerFeld[index] = null;
        lagerFeld[index] = lagerFeld[lagerBestand - 1];
        lagerFeld[lagerBestand - 1] = null;
        lagerBestand--;
    }
    
    /**
     * Ruft die bucheZugang Methode auf einen Artikel aus dem Lager auf.
     * 
     * @param artikelNr Artikelnummer des Artikels auf den gebucht werden soll.
     * @param zugang Menge die dazugebucht werden soll.
     */
    public void bucheZugang(int artikelNr, int zugang){
        int index = getIndexArtikel(artikelNr);
        
        getArtikel(index).bucheZugang(zugang);
    }
    
    /**
     * Ruft die bucheAbgang Methode auf einen Artikel aus dem Lager auf.
     * 
     * @param artikelNr Artikelnummer des Artikels auf den gebucht werden soll.
     * @param zugang Menge die abgebucht werden soll.
     */
    public void bucheAbgang(int artikelNr, int abgang){
        int index = getIndexArtikel(artikelNr);
        
        getArtikel(index).bucheAbgang(abgang);
    }
    
    /**
     * Aendert den Preis eines Artikels aus dem Lager um einen Prozentsatz.
     * 
     * @param artikelNr Artikelnummer des Artikels, bei dem der Preis geandert wird.
     * @param prozent Der Prozentsatz um den der Preis geeandert wird.
     */
    public void aenderePreisEinesArtikels(int artikelNr, double prozent){
        int index = getIndexArtikel(artikelNr);
        Artikel tmp = getArtikel(index);
        
        tmp.aenderePreis(prozent);
    }
    
    /**
     * Aendert den Preis aller Artikel aus dem Lager um einen Prozentsatz.
     * 
     * @param prozent Der Prozentsatz um den der Preis geeandert wird.
     */
    public void aenderePreisAllerArtikel(double prozent){
        for(Artikel tmp: lagerFeld){
            if(tmp != null){
                tmp.aenderePreis(prozent);
            }
        }
    }
    
    /**
     * Gibt einen Artikel an einem uebergebenen Index aus dem Feld zurueck.
     * 
     * @param index Stelle im Array die zurueckgegeben werden soll.
     * 
     * @return Gewuenschter Artikel.
     * 
     * @throws IllegalArgumentException Wenn Index ausserhalb des Bereichs vom Array ist.
     */
    public Artikel getArtikel(int index){
        if(index < 0 || index > lagerGroesse - 1){
            throw new IllegalArgumentException(ERROR_INDEX_FALSCH + String.valueOf(lagerGroesse - 1));
        }
        
        return lagerFeld[index];
    }
        
    public int getArtikelAnzahl(){
        return lagerBestand;
    }
    
    public int getLagerGroesse(){
        return lagerGroesse;
    }
    
    @Override
    public String toString(){
        String lager = ";\nLagerFeld:\n";
        for(Artikel tmp: lagerFeld){
            if(tmp != null){
                lager += tmp + "\n";
            }
        }
        
        return "Lagergroesse: " + String.valueOf(lagerGroesse) + "; Lagerbestand: " 
                + String.valueOf(lagerBestand) + lager;
    }
    
    /**
     * Prueft ob Artikel im Lager ist.
     * 
     * @param artikelNr Artikelnummer nach der im Array gesucht wird.
     * 
     * @return true wenn Artikel gefunden, false wenn Artikel nicht gefunden.
     */
    public boolean istArtikelGelagert(int artikelNr){
        for(int i = 0; i < lagerBestand; i++){
            if(lagerFeld[i].getArtikelNr() == artikelNr){
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Sucht an welchem Index ein Artikel steht.
     * 
     * @param artikelNr Artikelnummer des Artikels der gesucht werden soll.
     * 
     * @return Index des Artikels, wenn er gefunden wurde.
     * 
     * @throws IllegalArgumentException Wenn Artikel nicht gefunden wurde.
     * Es wird kein Index zurueckgegeben.
     */
    public int getIndexArtikel(int artikelNr){
        int index;
        for(int i = 0; i < lagerBestand; i++){
            if(lagerFeld[i].getArtikelNr() == artikelNr){
                index = i;
                return index;
            }
        }
        
        throw new IllegalArgumentException(ERROR_ARTIKEL_NICHT_GEFUNDEN);
    }
}