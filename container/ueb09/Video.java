
/**
 * Beschreiben Sie hier die Klasse Video.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Video extends Artikel{
    //Attribute
    private String  titel;
    private int     spieldauer;
    private int     jahr;
    
    //Error-Messages
    private static final String ERROR_TITEL_LEER                        = "Der Titel muss mindestens ein Zeichen enthalten.\n";
    private static final String ERROR_SPIELDAUER_KLEINER_EINS           = "Die Spieldauer muss groesser 0 sein.\n";
    private static final String ERROR_ERSCHEINUNGSJAHR_NICHT_MOEGLICH   = "Das Erscheinungsjahr muss zwischen 1899 und 2023 liegen.\n";
    
    //Konstanten
    private static final String ARTIKELART = "Medien";
    
    public Video(int artikelNr, int bestand, double preis, String titel, int spieldauer, int jahr){
        super(artikelNr, ARTIKELART, bestand, preis);
        
        if(titel == null || titel.isBlank()){
            throw new IllegalArgumentException(ERROR_TITEL_LEER);
        }
        if(spieldauer < 1){
            throw new IllegalArgumentException(ERROR_SPIELDAUER_KLEINER_EINS);
        }
        if(jahr < 1900 || jahr > 2022){
            throw new IllegalArgumentException(ERROR_ERSCHEINUNGSJAHR_NICHT_MOEGLICH);
        }
        
        this.titel = titel.strip();
        this.spieldauer = spieldauer;
        this.jahr = jahr;
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
    
    @Override
    public String getBeschreibung(){
        return titel;
    }
    
    @Override
    public boolean equals(Object obj){
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
}
