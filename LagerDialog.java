import java.util.Scanner;

/**
 * Konsolendialog fuer Lager Klasse.
 * 
 * @author Tim Mueller / Yannick Gross
 * @version 12.01.2023 / 20:00
 */

public class LagerDialog{
    //Attribute
    private static Scanner scanner      = new Scanner(System.in);
    private static Lager lager          = null;
    private static boolean killProgram  = false;
    
    //Error-Messages
    private static final String ERROR_OPTION_NICHT_GEFUNDEN     = "#ERR# Option wurde nicht gefunden\n";
    private static final String ERROR_OPTION_NUMMER             = "#ERR# Es koennen nur folgende Option-Nummern angenommen werden: 1-";
    private static final String ERROR_INDEX_NICHT_BELEGT        = "#ERR# Gewuenschter Index ist nicht belegt.\n";
    private static final String ERROR_FALSCHER_DATENTYP         = "#ERR# Falscher Datentyp wurde eingegeben.\n";
    
    private LagerDialog(){}
    
    /**
     * Methode in der das Hauptprogramm laeuft.
     * While Schleife laeuft so lange, bis User Programm stoppen moechte,
     * also bis <code>killProgram</code> auf true gesetzt wird.
     */
    private final void start(){
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
     * um sie dann auszuwerten und die passende Methode auf das Lager
     * Objekt anzuwenden.
     * 
     * @throws IllegalArgumentException Wenn Eingabe keine auswaehlbare Option ist.
     * @throws IllegalArgumentException Default bei switch-case.
     */
    private final void optionAuswahl(){
        byte input = leseByte("\nVerfuegbare Kommandos:\n" +
                                "1  - Lager erstellen\n" +
                                "2  - Artikel anlegen\n"+
                                "3  - Artikel entfernen\n" +
                                "4  - Zugang auf Artikel buchen\n" +
                                "5  - Abgang auf Artikel buchen\n"+
                                "6  - Preis von EINEM Artikel aendern\n" +
                                "7  - Preis von ALLEN Artikeln aendern\n" +
                                "8  - Preis von EINEM Artikel neu setzen\n" +
                                "9  - Gibt in einem String das komplette Lager zurueck\n" +
                                "10 - Bestandsliste ausgeben\n" +
                                "11 - Dialog beenden\n" +
                                "Eingabe: ");
        if(input < 1 || input > 11){
            throw new IllegalArgumentException(ERROR_OPTION_NUMMER + "10\n");
        }
                                    
        if(lager == null && input > 1 && input < 11){
            System.out.println("Lager wurde noch nicht initialisiert. Objekt initialisierung wird gestartet.");
            input = 1;
        }
        
        switch(input){
            case 1:
                if(lager != null){
                    input = leseByte("Lager existiert bereits, Objekt wirklich Ueberschreiben? (1: Ja / 2: Nein)");
                    
                    if(input != 1){
                        System.out.println("Lager wird nicht Ueberschrieben.");
                        break;
                    }
                }
                lager = erstelleLager();
                break;
            
            case 2:
                lager.legeAnArtikel(erstelleArtikel());
                break;
            
            case 3:
                lager.entferneArtikel(leseInt("Artikelnummer des zu entfernenden Artikels: "));
                break;
                
            case 4:
                lager.bucheZugang(  leseInt("Artikelnummer des Artikels, auf den gebucht werden soll: "),
                                    leseInt("Menge die dazugebucht werden soll: "));
                break;
                
            case 5:
                lager.bucheAbgang(  leseInt("Artikelnummer des Artikels, auf den abgebucht werden soll: "),
                                    leseInt("Menge die abgebucht werden soll: "));
                break;
            
            case 6:
                lager.aenderePreisEinesArtikels(leseInt("Artikelnummer des Artikels, dessen Preis geeandert werden soll: "),
                                                leseDouble("Prozentsatz (ohne Prozent-Zeichen): "));
                break;
                
            case 7:
                lager.aenderePreisAllerArtikel(leseDouble("Prozentsatz (ohne Prozent-Zeichen): "));
                break;
                
            case 8:
                lager.getArtikel(lager.getIndexArtikel(
                                leseInt("Artikelnummer des Artikels, dessen Preis neu gesetzt werden soll: ")))
                                .setPreis(leseDouble("Neuer Preis des Artikels: "));          
                break;
                
            case 9:
                System.out.println(lager);
                break;
            
            case 10:
                System.out.println(lager.ausgebenBestandsListe());
                break;
                
            case 11:
                killProgram = true;
                break;
                
            default:
                throw new IllegalArgumentException(ERROR_OPTION_NICHT_GEFUNDEN);
        }
        
    }
    
    /**
     * Methode um ein Lager zu erstellen.
     * Wird aufgerufen wenn <code>lager</code> auf <code>null</code>
     * zeigt oder wenn User das Objekt Ueberschreiben will.
     * 
     * @throws IllegalArgumentException Wenn etwas anderes als 1 oder 2 eingegeben wurde.
     */
    private final Lager erstelleLager(){
        byte konstruktorWahl = leseByte("Moechtest du eine Lagergroesse vorgeben? (Bei Nein: Groesse = 10) (1:Ja / 2:Nein)\nEingabe:");
        
        if(konstruktorWahl < 1 || konstruktorWahl > 2){
            throw new IllegalArgumentException(ERROR_OPTION_NUMMER + "2\n");
        }
        
        
        if(konstruktorWahl == 1){
            int lagerGroesse = leseInt("Die Lagergroesse: ");
            return new Lager(lagerGroesse);
        }
        
        return new Lager();
    }
    
    /**
     * Methode um einen Artikel zu erstellen.
     * Wird aufgerufen wenn <code>artikel</code> auf <code>null</code>
     * zeigt oder wenn User das Objekt Ueberschreiben will.
     * 
     * @throws IllegalArgumentException Wenn etwas anderes als Ja oder Nein eingegeben wurde.
     */
    private final static Artikel erstelleArtikel(){
        byte artikelWahl = leseByte("Welchen Artikel moechtest du erstellen:\n" +
                                        "1 - CD\n" +
                                        "2 - Buch\n" +
                                        "3 - Video\n" +
                                        "4 - Allgemeiner Artikel\n");
        
        if(artikelWahl < 1 || artikelWahl > 4){
            throw new IllegalArgumentException(ERROR_OPTION_NUMMER + "4\n");
        }
        
        if(artikelWahl == 1){
            return erstelleCD();
        }
        if(artikelWahl == 2){
            return erstelleBuch();
        }
        if(artikelWahl == 3){
            return erstelleVideo();
        }
        
        return erstelleAllgemeinenArtikel();
    }
    
    private static Artikel erstelleAllgemeinenArtikel(){
        byte konstruktorWahl = leseByte("Welcher Konstruktor soll aufgerufen werden:\n" +
                                        "1 - Hauptkonstruktor\n" +
                                        "2 - ohne Bestand\n" +
                                        "3 - ohne Bestand und Preis");               
        
        int artikelNr = leseInt("Die Artikelnummer: ");
        String art = leseString("Die Artikelart: ");
        
        if(konstruktorWahl == 1){
            int bestand = leseInt("Der Bestand: ");
            double preis = leseDouble("Der Preis: ");
            
            return new Artikel(artikelNr, art, bestand, preis);
        }
        
        if(konstruktorWahl == 2){
            double preis = leseDouble("Der Preis: ");
            
            return new Artikel(artikelNr, art, preis);
        }
        
        return new Artikel(artikelNr, art);
    }
    
    private static CD erstelleCD(){
        byte konstruktorWahl = leseByte("Welcher Konstruktor soll aufgerufen werden:\n" +
                                        "1 - Hauptkonstruktor\n" +
                                        "2 - ohne Bestand\n" +
                                        "3 - ohne Bestand und Preis");
                                        
        int artikelNr = leseInt("Die Artikelnummer: ");
        String interpret = leseString("Der Interpret: ");
        String titel = leseString("Der Titel: ");
        int anzahlTitel = leseInt("Anzahl der Titel auf der CD: ");
        
        if(konstruktorWahl == 1){
            int bestand = leseInt("Der Bestand: ");
            double preis = leseDouble("Der Preis: ");
            
            return new CD(artikelNr, bestand, preis, interpret, titel, anzahlTitel);
        }
        
        if(konstruktorWahl == 2){
            double preis = leseDouble("Der Preis: ");
            
            return new CD(artikelNr, preis, interpret, titel, anzahlTitel);
        }
        
        return new CD(artikelNr, interpret, titel, anzahlTitel);
    }
    
    private static Video erstelleVideo(){
        byte konstruktorWahl = leseByte("Welcher Konstruktor soll aufgerufen werden:\n" +
                                        "1 - Hauptkonstruktor\n" +
                                        "2 - ohne Bestand\n" +
                                        "3 - ohne Bestand und Preis");
                                        
        int artikelNr = leseInt("Die Artikelnummer: ");
        String titel = leseString("Der Titel: ");
        int spieldauer = leseInt("Die Spieldauer: ");
        int jahr = leseInt("Das Erscheinungsjahr: ");
        
        if(konstruktorWahl == 1){
            int bestand = leseInt("Der Bestand: ");
            double preis = leseDouble("Der Preis: ");
            
            return new Video(artikelNr, bestand, preis, titel, spieldauer, jahr);
        }
        
        if(konstruktorWahl == 2){
            double preis = leseDouble("Der Preis: ");
            
            return new Video(artikelNr, preis, titel, spieldauer, jahr);
        }
        
        return new Video(artikelNr, titel, spieldauer, jahr);
    }
    
    private static Buch erstelleBuch(){
        byte konstruktorWahl = leseByte("Welcher Konstruktor soll aufgerufen werden:\n" +
                                        "1 - Hauptkonstruktor\n" +
                                        "2 - ohne Bestand\n" +
                                        "3 - ohne Bestand und Preis");
                                        
        int artikelNr = leseInt("Die Artikelnummer: ");
        String titel = leseString("Der Titel: ");
        String autor = leseString("Der Autor: ");
        String verlag = leseString("Der Verlag: ");
        
        if(konstruktorWahl == 1){
            int bestand = leseInt("Der Bestand: ");
            double preis = leseDouble("Der Preis: ");
            
            return new Buch(artikelNr, bestand, preis, titel, autor, verlag);
        }
        
        if(konstruktorWahl == 2){
            double preis = leseDouble("Der Preis: ");
            
            return new Buch(artikelNr, preis, titel, autor, verlag);
        }
        
        return new Buch(artikelNr, titel, autor, verlag);
    }
    
    /**
     * Methode um uebergebenen Text auszugeben und String einzulesen.
     * 
     * @param prompt Text der ausgegeben werden soll.
     * 
     * @return Eingabe von User.
     */
    private final static String leseString(String prompt){
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
    private final static byte leseByte(String prompt){
        System.out.println(prompt);
        while(!scanner.hasNextByte()){
            scanner.next();
            System.out.println(ERROR_FALSCHER_DATENTYP + prompt);
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
    private final static int leseInt(String prompt){
        System.out.println(prompt);
        while(!scanner.hasNextInt()){
            scanner.next();
            System.out.println(ERROR_FALSCHER_DATENTYP + prompt);
        }
        int tmp = scanner.nextInt();
        scanner.nextLine();
        
        return tmp;
    }
    
    /**
     * Methode um uebergebenen Text auszugeben und double einzulesen.
     * Wenn kein double eingegeben wurde, wiederholt sich die Aufforderung,
     * bis einer eingegeben wurde.
     * 
     * @param prompt Text der ausgegeben werden soll.
     * 
     * @return Eingabe von User.
     */
    private final static double leseDouble(String prompt){
        System.out.println(prompt);
        while(!scanner.hasNextDouble()){
            scanner.next();
            System.out.println(ERROR_FALSCHER_DATENTYP + prompt);
        }
        double tmp = scanner.nextDouble();
        scanner.nextLine();
        
        return tmp;
    }
    
    /**
     * Main Methode, durch die das Programm gestartet wird.
     * Erstellt Objekt der eigenen Klasse, um nich statische Methode start() aufzurufen.
     */
    public static void main(String [] args){
        new LagerDialog().start();
    }
    
}