
/**
 * InvalidFileException wird geworfen wenn Datei nicht fuer
 * Programm geeignet ist oder keine Datei ist.
 *
 * @author Tim Mueller / Yannick Gross 
 * @version 26.01.2023 / 13:50Uhr
 */
public class InvalidFileException extends RuntimeException{
    public InvalidFileException(String message){
        super(message);
    }
    
    public InvalidFileException(){
        super();
    }
}
