package container.ueb02;

import java.util.Scanner;

/**
 * 
 * @author Tim Mueller / Yannick Gross
 * @version 12.11.2022 / 23:14
 */
public class ArtikelDialog{
    //Fehlermeldung
    private static final String FALSCHE_EINGABE = "Falsche Eingabe, Anweisungen genau lesen.";
    
    private static Scanner scanner = new Scanner(System.in);
    private static Artikel artikel = null;
    private static boolean killProgram = false;
    
    /**
     * Methode in der das Hauptprogramm laeuft.
     * While Schleife laeuft so lange, bis User Programm stoppen moechte,
     * also bis <code>killProgram</code> auf true gesetzt wird.
     */
    public void start(){
        while(killProgram == false){
            try{
                
                optionAuswahl(); 
                
            } catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            } catch(Exception e){
                System.out.println("Ein unerwarteter Fehler trat auf: " + e.getMessage());
                e.printStackTrace(System.out);
            }
        }
        scanner.close();
    }
    
    /**
     * Methode die aufgerufen wird um die Eingabe des Users abzufangen,
     * um sie dann auszuwerten und die passende Methode auf das Artikel
     * Objekt anzuwenden.
     * 
     * @throws IllegalArgumentException Wenn eine Eingabe ohne Zeichen getaetigt wurde.
     * @throws IllegalArgumentException Wenn eine falsche Eingabe getaetigt wurde.
     */
    public void optionAuswahl(){
        byte input = leseByte("\nVerfuegbare Kommandos:\n" +
                                    "1 - Artikel erstellen\n" +
                                    "2 - Zugang auf Artikel buchen\n"+
                                    "3 - Abgang auf Artikel buchen\n" +
                                    "4 - Setzt die Artikelart neu\n" +
                                    "5 - Setze den Bestand neu\n"+
                                    "6 - Gebe in einem String alle Attribute zurueck\n" +
                                    "7 - Beendet den Dialog\n" +
                                    "Eingabe: ");
        if(input < 1 || input > 7){
            throw new IllegalArgumentException(FALSCHE_EINGABE);
        }
                                    
        if(artikel == null && (input != 1 || input != 7)){
            System.out.println("Artikel wurde noch nicht initialisiert. Objekt initialisierung wird gestartet.");
            input = 1;
        }
        
        switch(input){
            case 1:
                if(artikel != null){
                    input = leseByte("Artikel existiert bereits, Objekt wirklich Ueberschreiben? (1: Ja / 0: Nein)");
                    
                    if(input != 1){
                        System.out.println("Artikel wird nicht Ueberschrieben.");
                        break;
                    }
                }
                erstelleArtikel();
                break;
            
            case 2:
                artikel.bucheZugang(leseInt("Menge die dazugebucht werden soll: "));
                break;
            
            case 3:
                artikel.bucheAbgang(leseInt("Menge die abgebucht werden soll: "));
                break;
                
            case 4:
                artikel.setBestand(leseInt("Menge auf die der Bestand geaendert werden soll: "));
                break;
                
            case 5:
                artikel.setArt(leseString("Zeichenkette auf die die Art geaendert werden soll: "));
                break;
            
            case 6:
                System.out.println(artikel);
                break;
                
            case 7:
                killProgram = true;
                break;
                
            default:
                throw new IllegalArgumentException(FALSCHE_EINGABE);
        }
        
    }
    
    /**
     * Methode um einen Artikel zu erstellen.
     * Wird aufgerufen wenn <code>artikel</code> auf <code>null</code>
     * zeigt oder wenn User das Objekt Ueberschreiben will.
     * 
     * @throws IllegalArgumentException Wenn etwas anderes als Ja oder Nein eingegeben wurde.
     */
    public void erstelleArtikel(){
        byte konstruktorWahl = leseByte("Soll der komplette Konstruktor aufgerufen werden? (1:Ja / 0:Nein)\nEingabe:");
        
        if(konstruktorWahl < 0 || konstruktorWahl > 1){
            throw new IllegalArgumentException(FALSCHE_EINGABE);
        }
        
        int artikelNr = leseInt("Die Artikelnummer: ");
        String art = leseString("Die Artikelart: ");
        
        if(konstruktorWahl == 1){
            int bestand = leseInt("Der Bestand: ");
            
            artikel = new Artikel(artikelNr, art, bestand);
            return;
        }
        
        artikel = new Artikel(artikelNr, art);
    }
    
    /**
     * Methode um uebergebenen Text auszugeben und String einzulesen.
     * 
     * @param prompt Text der ausgegeben werden soll.
     * 
     * @return Eingabe von User.
     */
    public String leseString(String prompt){
        System.out.println(prompt);
        
        return scanner.nextLine();
    }
    
    /**
     * Methode um uebergebenen Text auszugeben und Byte einzulesen.
     * 
     * @param prompt Text der ausgegeben werden soll.
     * 
     * @return Eingabe von User.
     */
    public byte leseByte(String prompt){
        System.out.println(prompt);
        while(!scanner.hasNextByte()){
            scanner.next();
            System.out.println("Falsche Eingabe\n" + prompt);
        }
        byte tmp = scanner.nextByte();
        scanner.nextLine();
        
        return tmp;
    }
    
    /**
     * Methode um uebergebenen Text auszugeben und int einzulesen.
     * Wenn kein integer eingegeben wurde, wiederholt sich die Aufforderung,
     * bis einer eingegeben wurde.
     * 
     * @param prompt Text der ausgegeben werden soll.
     * 
     * @return Eingabe von User.
     */
    public int leseInt(String prompt){
        System.out.println(prompt);
        while(!scanner.hasNextInt()){
            scanner.next();
            System.out.println("Falsche Eingabe\n" + prompt);
        }
        int tmp = scanner.nextInt();
        scanner.nextLine();
        
        return tmp;
    }
    
    /**
     * Main Methode, durch die das Programm gestartet wird.
     * Erstellt Objekt der eigenen Klasse, um nich statische Methode start() aufzurufen.
     */
    public static void main(String [] args){
        new ArtikelDialog().start();
    }
    
}
