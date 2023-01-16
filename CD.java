
/**
 * CD Klasse die von Artikel erbt.
 * 
 * @author Tim Mueller / Yannick Gross
 * @version 12.01.2023 / 20:00
 */
public class CD extends Artikel{
    //Attribute
    private String  interpret;
    private String  titel;
    private int     anzahlTitel;
    
    //Error-Messages
    private static final String ERROR_INTERPRET_LEER             = "#ERR# Der Interpret muss mindestens ein Zeichen enthalten.\n";
    private static final String ERROR_TITEL_LEER                 = "#ERR# Der Titel muss mindestens ein Zeichen enthalten.\n";
    private static final String ERROR_ANZAHLTITEL_KLEINER_EINS   = "#ERR# Die Anzahl der Titel muss eine natürliche Zahl sein.\n";
    
    //Konstanten
    private static final String ARTIKELART = "Medien";
    
    /**
    * Konstruktor fuer die Klasse CD mit allen Attributen.
    * 
    * @param artikelNr Artikelnummer der CD.
    * @param bestand Aktueller Bestand / Anzahl der CD.
    * @param preis Preis der CD
    * @param interpret Interpret der CD
    * @param titel Titel der CD
    * @param anzahlTitel Anzahl der Titel auf der CD
    * 
    * @throws IllegalArgumentException interpret leer ist.
    * @throws IllegalArgumentException titel leer ist.
    * @throws IllegalArgumentException anzahlTitel kleiner 1 ist.
    */
    public CD(int artikelNr, int bestand, double preis, String interpret, String titel, int anzahlTitel){
        super(artikelNr, ARTIKELART, bestand, preis);
        
        if(interpret == null || interpret.isBlank()){
            throw new IllegalArgumentException(ERROR_INTERPRET_LEER);
        }
        if(titel == null || titel.isBlank()){
            throw new IllegalArgumentException(ERROR_TITEL_LEER);
        }
        if(anzahlTitel < 1){
            throw new IllegalArgumentException(ERROR_ANZAHLTITEL_KLEINER_EINS);
        }
        
        this.interpret = interpret.strip();
        this.titel = titel.strip();
        this.anzahlTitel = anzahlTitel;
        
    }
    
    public CD(int artikelNr, double preis, String interpret, String titel, int anzahlTitel){
        this(artikelNr, 0, preis, interpret, titel, anzahlTitel);
    }
    
    public CD(int artikelNr, String interpret, String titel, int anzahlTitel){
        this(artikelNr, 0, 0.0, interpret, titel, anzahlTitel);
    }
    
    public String getInterpret(){
        return interpret;
    }
    
    public String getTitel(){
        return titel;
    }
    
    public int getAnzahlTitel(){
        return anzahlTitel;
    }
    
    /**
     * Gibt einen String mit Interpret und Titel zurueck.
     * 
     * @return String mit Parametern.
     */
    @Override
    public String getBeschreibung(){
        return interpret + " : " + titel;
    }
    
    /**
     * Vergleicht ob Objekte selben Titel, Interpret und Titelanzahl haben.
     * 
     * @return true oder false je nachdem ob Objekte gleich sind.
     */
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(obj instanceof CD){
            CD newObj = (CD)obj;
            if( newObj.getTitel().equals(getTitel()) &&
                newObj.getInterpret().equals(getInterpret()) &&
                newObj.getAnzahlTitel() == getAnzahlTitel()){
                    return true;
                }
        }
        return false;
    }
    
    /**
     * Gibt einen String mit allen CD-spezifischen Attributen zurueck.
     * 
     * @return String mit Attributen.
     */
    @Override
    public String toString(){
        return super.toString() + "; Interpret: " + interpret + "; Titel: " + titel + "; Anzahl Titel: " + anzahlTitel;
    }
}
