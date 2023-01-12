
/**
 * Beschreiben Sie hier die Klasse CD.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class CD extends Artikel{
    //Attribute
    private String  interpret;
    private String  titel;
    private int     anzahlTitel;
    
    //Error-Messages
    private static final String ERROR_INTERPRET_LEER             = "Der Interpret muss mindestens ein Zeichen enthalten.\n";
    private static final String ERROR_TITEL_LEER                 = "Der Titel muss mindestens ein Zeichen enthalten.\n";
    private static final String ERROR_ANZAHLTITEL_KLEINER_EINS   = "Die Anzahl der Titel muss eine natürliche Zahl sein.\n";
    
    //Konstanten
    private static final String ARTIKELART = "Medien";
    
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
    
    public String getInterpret(){
        return interpret;
    }
    
    public String getTitel(){
        return titel;
    }
    
    public int getAnzahlTitel(){
        return anzahlTitel;
    }
    
    @Override
    public String getBeschreibung(){
        return interpret + " : " + titel;
    }
    
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
                newObj.getInterpret().equals(getInterpret())){
                    return true;
                }
        }
        return false;
    }
}
