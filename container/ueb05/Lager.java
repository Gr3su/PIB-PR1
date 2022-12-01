package container.ueb05;


/**
 * Klasse zum Lagern und Verwalten von Artikel Objekten.
 * 
 * @author Tim Mueller / Yannick Gross
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Lager{
    //Fehlermeldungen
    private static final String ERROR_LAGERGROESSE_MEHR =      "Die Groesse des Lagers muss mindestens 1 betragen.\n";
    private static final String ERROR_KEIN_OBJEKT =            "Es wurde kein gueltiges Objekt angegeben.\n";
    private static final String ERROR_LAGER_VOLL =             "Artikel konnte nicht angelegt werden, weil das Lager voll ist.\n";
    private static final String ERROR_ARTIKEL_NICHT_GEFUNDEN = "Der gewuenschte Artikel wurde nicht im Lager gefunden.\n";
    private static final String ERROR_PROZENT_POSITIV =        "Der Prozentsatz muss positiv sein.\n";
    private static final String ERROR_INDEX_FALSCH =           "Der angegebene Index ist falsch. Er muss liegen, zwischen 0 und ";
    //Attribute
    private Artikel[] lagerFeld;
    private final int lagerGroesse;
    private int lagerBestand;
    
    public Lager(int lagerGroesse){
        if(lagerGroesse < 1){
            throw new IllegalArgumentException(ERROR_LAGERGROESSE_MEHR);
        }
        
        this.lagerFeld = new Artikel[lagerGroesse];
        this.lagerGroesse = lagerGroesse;
        this.lagerBestand = 0;
    }
    
    public Lager(){
        this(10);
    }
    
    public void legeAnArtikel(Artikel artikel){
        if(artikel == null){
            throw new IllegalArgumentException(ERROR_KEIN_OBJEKT);
        }
        if(lagerBestand == lagerGroesse){
            throw new IllegalArgumentException(ERROR_LAGER_VOLL);
        }
        
        lagerFeld[lagerBestand] = artikel;
        lagerBestand++;
    }
    
    public void entferneArtikel(int artikelNr){
        
        int index = getIndexArtikel(artikelNr);
        
        if(index != -1){
            lagerFeld[index] = null;
            lagerFeld[index] = lagerFeld[lagerBestand];
            lagerFeld[lagerBestand] = null;
            lagerBestand--;
            return;
        }
        
        throw new IllegalArgumentException(ERROR_ARTIKEL_NICHT_GEFUNDEN);
    }
    
    public void bucheZugang(int artikelNr, int zugang){
        int index = getIndexArtikel(artikelNr);
        
        lagerFeld[index].bucheZugang(zugang);
    }
    
    public void bucheAbgang(int artikelNr, int abgang){
        int index = getIndexArtikel(artikelNr);
        
        lagerFeld[index].bucheAbgang(abgang);
    }
    
    public void aenderePreisEinesArtikels(int artikelNr, double prozent){
        if(prozent < 0){
            throw new IllegalArgumentException(ERROR_PROZENT_POSITIV);
        }
        
        int index = getIndexArtikel(artikelNr);
        Artikel tmp = lagerFeld[index];
        
        tmp.setPreis(tmp.getPreis() * prozent);
    }
    
    public void aenderePreisAllerArtikel(double prozent){
        if(prozent < 0){
            throw new IllegalArgumentException(ERROR_PROZENT_POSITIV);
        }
        
        for(Artikel tmp: lagerFeld){
            tmp.setPreis(tmp.getPreis() * prozent);
        }
    }
    
    public Artikel getArtikel(int index){
        if(index < 0 || index > lagerGroesse - 1){
            throw new IllegalArgumentException(ERROR_INDEX_FALSCH + String.valueOf(lagerGroesse - 1));
        }
        if(lagerFeld[index] == null){
            throw new IllegalArgumentException(ERROR_ARTIKEL_NICHT_GEFUNDEN);
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
        String lager = "; LagerFeld: ";
        for(Artikel tmp: lagerFeld){
            if(tmp != null){
                lager += tmp.toString() + "\n";
            }
        }
        
        return "Lagergroesse: " + String.valueOf(lagerGroesse) + "; Lagerbestand: " 
                + String.valueOf(lagerBestand) + lager;
    }
    
    private int getIndexArtikel(int artikelNr){
        int index = -1;
        for(int i = 0; i < lagerBestand; i++){
            if(lagerFeld[i].getArtikelNr() == artikelNr){
                index = i;
                break;
            }
        }
        
        return index;
    }
}
