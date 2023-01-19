
/**
 * Buch Klasse die von Artikel erbt.
 * 
 * @author Tim Mueller / Yannick Gross
 * @version 12.01.2023 / 20:00
 */
public class Buch extends Artikel{
    //Attribute
    private String titel;
    private String autor;
    private String verlag;

    //Konstanten
    private static final String ARTIKELART = "Medien";
    
    /**
    * Konstruktor fuer die Klasse Buch mit allen Attributen.
    * 
    * @param artikelNr Artikelnummer des Buches.
    * @param bestand Aktueller Bestand / Anzahl des Buches.
    * @param preis Preis des Buches.
    * @param autor Autor des Buches.
    * @param titel Titel des Buches
    * @param verlag Verlag des Buches
    * 
    * @throws IllegalArgumentException titel leer ist.
    * @throws IllegalArgumentException autor leer ist.
    * @throws IllegalArgumentException verlag leer ist.
    */
    public Buch(int artikelNr, int bestand, double preis, String titel, String autor, String verlag){
        super(artikelNr, ARTIKELART, bestand, preis);
        
        FehlerPruefung.fehlerPruefung(  ErrorMessages.ERROR_BUCH_AUTOR_LEER,
                                        autor);
        FehlerPruefung.fehlerPruefung(  ErrorMessages.ERROR_BUCH_TITEL_LEER,
                                        titel);
        FehlerPruefung.fehlerPruefung(  ErrorMessages.ERROR_BUCH_VERLAG_LEER,
                                        verlag);
        
        this.titel = titel.strip();
        this.autor = autor.strip();
        this.verlag = verlag.strip();
    }
    
    public Buch(int artikelNr,double preis, String titel, String autor, String verlag){
        this(artikelNr, 0, preis, titel, autor, verlag);
    }
    
    public Buch(int artikelNr,String titel, String autor, String verlag){
        this(artikelNr, 0, 0.0, titel, autor, verlag);
    }
    
    public String getTitel(){
        return titel;
    }
    
    public String getAutor(){
        return autor;
    }
    
    public String getVerlag(){
        return verlag;
    }
    
    /**
     * Gibt einen String mit Autor und Titel zurueck.
     * 
     * @return String mit Parametern.
     */
    @Override
    public String getBeschreibung(){
        return autor + " : " + titel;
    }
    
    /**
     * Vergleicht ob Objekte selben Titel und Autor haben.
     * 
     * @return true oder false je nachdem ob Objekte gleich sind.
     */
    @Override
    public boolean equals(Object obj){
        super.equals(obj);
        
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(obj instanceof Buch){
            Buch newObj = (Buch)obj;
            
            if( newObj.getTitel().equals(getTitel()) &&
                newObj.getAutor().equals(getAutor())){
                    return true;
                }
        }
        return false;
    }
    
    /**
     * Gibt einen String mit allen Buch-spezifischen Attributen zurueck.
     * 
     * @return String mit Attributen.
     */
    @Override
    public String toString(){
        return super.toString() + "; Titel: " + titel + "; Autor: " + autor + "; Verlag: " + verlag;
    }
}
