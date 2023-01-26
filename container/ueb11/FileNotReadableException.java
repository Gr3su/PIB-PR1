
/**
 * Beschreiben Sie hier die Klasse FileNotReadableException.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class FileNotReadableException extends RuntimeException{
    
    public FileNotReadableException(String message){
        super(message);
    }
    
    public FileNotReadableException(){
        super();
    }
}
