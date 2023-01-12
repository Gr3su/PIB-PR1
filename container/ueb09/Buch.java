
/**
 * Beschreiben Sie hier die Klasse Buch.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Buch extends Artikel{
    //Attribute
    private String titel;
    private String autor;
    private String verlag;
    
    //Error-Messages
    private static final String ERROR_TITEL_LEER    = "#ERR# Der Titel muss mindestens aus einem Zeichen bestehen.\n";
    private static final String ERROR_AUTOR_LEER    = "#ERR# Der Autor muss mindestens aus einem Zeichen bestehen.\n";
    private static final String ERROR_VERLAG_LEER   = "#ERR# Der Verlag muss mindestens aus einem Zeichen bestehen.\n";
    
    //Konstanten
    private static final String ARTIKELART = "Medien";
    
    public Buch(int artikelNr, int bestand, double preis, String titel, String autor, String verlag){
        super(artikelNr, ARTIKELART, bestand, preis);
        
        if(titel == null || titel.isBlank()){
            throw new IllegalArgumentException(ERROR_TITEL_LEER);
        }
        if(autor == null || autor.isBlank()){
            throw new IllegalArgumentException(ERROR_AUTOR_LEER);
        }
        if(verlag == null || verlag.isBlank()){
            throw new IllegalArgumentException(ERROR_VERLAG_LEER);
        }
        
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
    
    public String getVelrag(){
        return verlag;
    }
    
    @Override
    public String getBeschreibung(){
        return autor + " : " + titel;
    }
    
    @Override
    public boolean equals(Object obj){
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
    
    @Override
    public String toString(){
        return super.toString() + "; Titel: " + titel + "; Autor: " + autor + "; Verlag: " + verlag;
    }
}
