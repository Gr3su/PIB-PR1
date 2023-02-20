import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Zaehlt die Anzahl der LOC (Lines of Code) der uebergebenen Pfade von Dateien.
 * 
 * @author Tim Mueller / Yannick Gross
 * @version 26.01.2023 / 
 */

public final class LOCAuswertung{
    
    /**
     * Hauptmethode:
     * geht die einzelnen uebergebenen Pfade durch,
     * erstellt die Ausgabe mit den LOC der einzelnen Dateien,
     * bei Fehlerhaften Pfaden wird eine ErrorMessage gedruckt,
     * Fehler werden abgefangen.
     * 
     * @param args String Array mit den Pfaden der zu pruefenden Dateien.
     */
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
    
    /**
     * Ueberprueft ob der Pfad zu einer Datei:
     *  - ob wirklich eine Datei ist
     *  - auf .java endet
     *  - ob diese Datei wirklich existiert
     *  - ob Datei lesbar ist
     *  
     * @param path Pfad zu einer Datei, ausgehend vom Pfad wo das Programm gestartet wird.
     * 
     * @throws InvalidFileException Wenn Pfad nicht zu einer Datei fuehrt.
     * @throws InvalidFileException Wenn Datei keine Java Datei.
     * @throws InvalidFileException Wenn Datei nicht existiert.
     * @throws FileNotReadableException Wenn Datei nicht lesbar ist.
     */
    private static File fileInitAndValidation(String path){
        File file = new File(path);
        
        if(!file.isFile()){
            throw new InvalidFileException( String.format(ErrorMessages.PFAD_KEINE_DATEI.getMessage(),
                                            path));
        }
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
        
        return file;
    }
    
    /**
     * Zaehlt die Zeilen von Code die nicht leer sind und nicht mit einem
     * Ein-Zeilen-Kommentar beginnen.
     * 
     * @param file Datei die eingelesen werden soll.
     * 
     * @throws IOException Wenn bei BufferedReader ein Fehler auftritt.
     */
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
