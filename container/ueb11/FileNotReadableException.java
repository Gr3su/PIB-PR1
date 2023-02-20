
/**
 * FileNotReadableException Datei nicht gelesen werden kann.
 * 
 * @author Tim Mueller / Yannick Gross 
 * @version 27.01.2023 / 11:00Uhr
 */
public class FileNotReadableException extends RuntimeException{
    
    public FileNotReadableException(String message){
        super(message);
    }
    
    public FileNotReadableException(){
        super();
    }
}
