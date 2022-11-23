package ueb02

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
                if(artikel == null){
                    System.out.println("Artikel Initialisierung wird gestartet\n");
                    erstelleArtikel();
                }
                
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
        String input = readString("\nVerfügbare Kommandos:\nerstellen - Artikel erstellen\nbucheZugang - Zugang auf Artikel buchen\n"+
                        "bucheAbgang - Abgang auf Artikel buchen\nsetArt - Setzt die Artikelart neu\nsetBestand - Setze den Bestand neu\n"+
                        "toString - Gebe in einem String alle Attribute zurück\nStopp - Beendet den Dialog\nEingabe: ").trim();
                        
        if(input.isEmpty() == true){
            throw new IllegalArgumentException(FALSCHE_EINGABE);
        }
        if(artikel == null && !input.equalsIgnoreCase("erstellen") && !input.equalsIgnoreCase("stopp")){
            System.out.println("Artikel wurde noch nicht initialisiert. Objekt initialisierung wird gestartet.");
            input = "erstellen";
        }
        
        switch(input.toLowerCase()){
            case "erstellen":
                if(artikel != null){
                    input = readString("Artikel existiert bereits, Objekt wirklich überschreiben? (Ja / Nein)");
                    
                    if(!input.equalsIgnoreCase("ja")){
                        System.out.println("Artikel wird nicht überschrieben.");
                        break;
                    }
                }
                erstelleArtikel();
                break;
            
            case "buchezugang":
                artikel.bucheZugang(readInt("Menge die dazugebucht werden soll: "));
                break;
            
            case "bucheabgang":
                artikel.bucheAbgang(readInt("Menge die abgebucht werden soll: "));
                break;
                
            case "setbestand":
                artikel.setBestand(readInt("Menge auf die der Bestand geaendert werden soll: "));
                break;
                
            case "setart":
                artikel.setArt(readString("Zeichenkette auf die die Art geaendert werden soll: "));
                break;
            
            case "tostring":
                System.out.println(artikel.toString());
                break;
                
            case "stopp":
                killProgram = true;
                break;
                
            default:
                throw new IllegalArgumentException(FALSCHE_EINGABE);
        }
        
    }
    
    /**
     * Methode um einen Artikel zu erstellen.
     * Wird aufgerufen wenn <code>artikel</code> auf <code>null</code>
     * zeigt oder wenn User das Objekt überschreiben will.
     * 
     * @throws IllegalArgumentException Wenn etwas anderes als Ja oder Nein eingegeben wurde.
     */
    public void erstelleArtikel(){
        String konstruktorWahl = readString("Soll der komplette Konstruktor aufgerufen werden? (Ja/Nein)\nEingabe:");
        if(konstruktorWahl.trim().isEmpty() == true || (!konstruktorWahl.equalsIgnoreCase("ja") && !konstruktorWahl.equalsIgnoreCase("nein"))){
            throw new IllegalArgumentException(FALSCHE_EINGABE);
        }
        
        int artikelNr = readInt("(Nummern kleiner als 1000 werden mit fuehrenden 0 aufgefuellt!)\nDie Artikelnummer: ");
    
        String art = readString("Die Artikelart: ");
        if(konstruktorWahl.trim().equalsIgnoreCase("ja")){
            int bestand = readInt("Der Bestand: ");
            
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
    public String readString(String prompt){
        System.out.println(prompt);
        return scanner.nextLine();
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
    public int readInt(String prompt){
        System.out.println(prompt);
        while(!scanner.hasNextInt()){
            scanner.nextLine();
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

