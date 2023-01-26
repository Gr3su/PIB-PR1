import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Beschreiben Sie hier die Klasse LOCAuswertung.
 * 
 * @author Tim Mueller / Yannick Gross
 * @version 26.01.2023 / 
 */

public final class LOCAuswertung{
    
    private static void start(String [] args){
        File file = null;
        int linesOfCode = 0;
        int sumOfLines = 0;
        int failures = 0;
        int successes = 0;
        StringBuilder ausgabe = new StringBuilder("Ausgabe\nAuswertung Lines of Code (LOC):\n");
        
        for(String path: args){
            try{
                file = fileInitAndValidation(path);
                linesOfCode = countLOC(file);
                sumOfLines += linesOfCode;
                successes++;
                
                ausgabe.append(String.format("%-10d LOC:%-20s\n", linesOfCode, path));
            }
            
            catch(InvalidFileException e){
                System.err.println(e.getMessage());
                failures++;
            }
            catch(FileNotReadableException e){
                System.err.println(e.getMessage());
                failures++;
            }
            catch(IOException e){
                System.err.println(e.getMessage());
                failures++;
            }
            catch(Exception e){
                System.err.println(ErrorMessages.UNERWARTETER_FEHLER.getMessage());
                failures++;
            }            
        }
        
        ausgabe.append( "\nGesamt:\n" + successes + " Dateien: " +
                        sumOfLines + " LOC,\ndavon unabhaengig " +
                        failures + " fehlerhaft.");
        
        System.out.println(ausgabe.toString());
    }
    
    private static File fileInitAndValidation(String path){
        File file = new File(path);
        
        if( !path.endsWith(".java")){
            throw new InvalidFileException( String.format(ErrorMessages.KEINE_JAVA_DATEI.getMessage(),
                                            path));  
        }
        if(!file.exists()){
            throw new InvalidFileException( String.format(ErrorMessages.DATEI_EXISTIERT_NICHT.getMessage(),
                                            path));
        }
        if(!file.canRead()){
            throw new FileNotReadableException( String.format(ErrorMessages.DATEI_NICHT_LESBAR.getMessage(),
                                                path));
        }
        if(!file.isFile()){
            throw new InvalidFileException( String.format(ErrorMessages.PFAD_KEINE_DATEI.getMessage(),
                                            path));
        }
        
        return file;
    }
    
    private static int countLOC(File file) throws IOException{
        int linesOfCode = 0;
        String line = null;
        String REGEX_KOMMENTARZEILE = "^ *//";
        
        try(BufferedReader fileReader = new BufferedReader(new FileReader(file))){
            while((line = fileReader.readLine()) != null){
                
                if(!line.isBlank() && !line.matches(REGEX_KOMMENTARZEILE)){
                    linesOfCode++;
                }
                
            }
        }
        
        return linesOfCode;
    }
    
    public static void main(String [] args){
        start(args);
    }
}
