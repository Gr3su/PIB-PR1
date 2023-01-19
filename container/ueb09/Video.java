
/**
 * Video Klasse die von Artikel erbt.
 * 
 * @author Tim Mueller / Yannick Gross
 * @version 12.01.2023 / 20:00
 */
public class Video extends Artikel{
    //Attribute
    private String  titel;
    private int     spieldauer;
    private int     jahr;
    
    //Konstanten
    private static final String ARTIKELART = "Medien";
    
    /**
    * Konstruktor fuer die Klasse Video mit allen Attributen.
    * 
    * @param artikelNr Artikelnummer des Videos.
    * @param bestand Aktueller Bestand / Anzahl des Videos.
    * @param preis Preis des Videos.
    * @param titel Titel des Videos.
    * @param spieldauer Spieldauer des Videos.
    * @param jahr Erscheinungsjahr des Videos.
    * 
    * @throws IllegalArgumentException titel leer ist.
    * @throws IllegalArgumentException spieldauer kleiner 1 ist.
    * @throws IllegalArgumentException jahr kleiner als 1900 oder groesser als 2022.
    */
    public Video(int artikelNr, int bestand, double preis, String titel, int spieldauer, int jahr){
        super(artikelNr, ARTIKELART, bestand, preis);
        
        FehlerPruefung.fehlerPruefung(  ErrorMessages.ERROR_VIDEO_TITEL_LEER,
                                        titel);
        FehlerPruefung.fehlerPruefung(  ErrorMessages.ERROR_VIDEO_SPIELDAUER_KLEINER_EINS,
                                        spieldauer);
        FehlerPruefung.fehlerPruefung(  ErrorMessages.ERROR_VIDEO_ERSCHEINUNGSJAHR_NICHT_MOEGLICH,
                                        jahr);
        
        this.titel = titel.strip();
        this.spieldauer = spieldauer;
        this.jahr = jahr;
    }
    
    public Video(int artikelNr,double preis, String titel, int spieldauer, int jahr){
        this(artikelNr, 0, preis, titel, spieldauer, jahr);
    }
    
    public Video(int artikelNr, String titel, int spieldauer, int jahr){
        this(artikelNr, 0, 0.0, titel, spieldauer, jahr);
    }
    
    public String getTitel(){
        return titel;
    }
    
    public int getSpieldauer(){
        return spieldauer;
    }
    
    public int getJahr(){
        return jahr;
    }
    
    /**
     * Gibt einen String mit Titel zurueck.
     * 
     * @return String mit Parameter.
     */
    @Override
    public String getBeschreibung(){
        return titel;
    }
    
    /**
     * Vergleicht ob Objekte selben Titel, Spieldauer und Erscheinungsjahr haben.
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
        if(obj instanceof Video){
            Video newObj = (Video)obj;
            
            if( newObj.getTitel().equals(getTitel()) &&
                newObj.getSpieldauer() == getSpieldauer() &&
                newObj.getJahr() == getJahr()){
                    return true;
                }
        }
        return false;
    }
    
    /**
     * Gibt einen String mit allen Video-spezifischen Attributen zurueck.
     * 
     * @return String mit Attributen.
     */
    @Override
    public String toString(){
        return super.toString() + "; Titel: " + titel + "; Spieldauer: " + spieldauer + "; Erscheinungsjahr: " + jahr;
    }
}
