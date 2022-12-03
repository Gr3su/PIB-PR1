package container.ueb05;

import java.util.Scanner;

/**
 * 
 * @author Tim Mueller / Yannick Gross
 * @version 12.11.2022 / 23:14
 */
public class LagerDialog{
    //Fehlermeldung
    private static final String ERROR_OPTION_NICHT_GEFUNDEN ="Option wurde nicht gefunden\n";
    private static final String ERROR_OPTION_NUMMER =        "Es koennen nur folgende Option-Nummern angenommen werden: 1-";
    private static final String ERROR_INDEX_NICHT_BELEGT =   "Gewuenschter Index ist nicht belegt.\n";
    
    private static Scanner scanner =                        new Scanner(System.in);
    private static Lager lager =                            null;
    private static boolean killProgram =                    false;
    
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
     * um sie dann auszuwerten und die passende Methode auf das Lager
     * Objekt anzuwenden.
     * 
     * @throws IllegalArgumentException Wenn eine Eingabe ohne Zeichen getaetigt wurde.
     * @throws IllegalArgumentException Wenn eine falsche Eingabe getaetigt wurde.
     */
    public void optionAuswahl(){
        byte input = ArtikelDialog.leseByte("\nVerfuegbare Kommandos:\n" +
                                            "1  - Lager erstellen\n" +
                                            "2  - Artikel anlegen\n"+
                                            "3  - Artikel entfernen\n" +
                                            "4  - Zugang auf Artikel buchen\n" +
                                            "5  - Abgang auf Artikel buchen\n"+
                                            "6  - Preis von EINEM Artikel aendern\n" +
                                            "7  - Preis von ALLEN Artikeln aendern\n" +
                                            "8  - Preis von EINEM Artikel neu setzen\n" +
                                            "9  - Gibt in einem String das komplette Lager zurueck\n" +
                                            "10 - Dialog beenden\n" +
                                            "Eingabe: ");
        if(input < 1 || input > 10){
            throw new IllegalArgumentException(ERROR_OPTION_NUMMER + "10\n");
        }
                                    
        if(lager == null && input > 1 && input < 10){
            System.out.println("Lager wurde noch nicht initialisiert. Objekt initialisierung wird gestartet.");
            input = 1;
        }
        
        switch(input){
            case 1:
                if(lager != null){
                    input = ArtikelDialog.leseByte("Lager existiert bereits, Objekt wirklich Ueberschreiben? (1: Ja / 2: Nein)");
                    
                    if(input != 1){
                        System.out.println("Artikel wird nicht Ueberschrieben.");
                        break;
                    }
                }
                lager = erstelleLager();
                break;
            
            case 2:
                lager.legeAnArtikel(ArtikelDialog.erstelleArtikel());
                break;
            
            case 3:
                lager.entferneArtikel(ArtikelDialog.leseInt("Artikelnummer des zu entfernenden Artikels: "));
                break;
                
            case 4:
                lager.bucheZugang(  ArtikelDialog.leseInt("Artikelnummer des Artikels, auf den gebucht werden soll: "),
                                    ArtikelDialog.leseInt("Menge die dazugebucht werden soll: "));
                break;
                
            case 5:
                lager.bucheAbgang(  ArtikelDialog.leseInt("Artikelnummer des Artikels, auf den abgebucht werden soll: "),
                                    ArtikelDialog.leseInt("Menge die abgebucht werden soll: "));
                break;
            
            case 6:
                lager.aenderePreisEinesArtikels(ArtikelDialog.leseInt("Artikelnummer des Artikels, dessen Preis geeandert werden soll: "),
                                                ArtikelDialog.leseDouble("Prozentsatz (ohne Prozent-Zeichen): "));
                break;
                
            case 7:
                lager.aenderePreisAllerArtikel(ArtikelDialog.leseDouble("Prozentsatz (ohne Prozent-Zeichen): "));
                break;
                
            case 8:
                lager.getArtikel(lager.getIndexArtikel(
                                ArtikelDialog.leseInt("Artikelnummer des Artikels, dessen Preis neu gesetzt werden soll: ")))
                                .setPreis(ArtikelDialog.leseDouble("Neuer Preis des Artikels: "));          
                break;
                
            case 9:
                System.out.println(lager);
                break;
            
            case 10:
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
     * @throws IllegalArgumentException Wenn etwas anderes als Ja oder Nein eingegeben wurde.
     */
    public Lager erstelleLager(){
        byte konstruktorWahl = ArtikelDialog.leseByte("Möchtest du eine Lagergroesse vorgeben? (Bei Nein: Groesse = 10) (1:Ja / 2:Nein)\nEingabe:");
        
        if(konstruktorWahl < 1 || konstruktorWahl > 2){
            throw new IllegalArgumentException(ERROR_OPTION_NUMMER + "2\n");
        }
        
        
        if(konstruktorWahl == 1){
            int lagerGroesse = ArtikelDialog.leseInt("Die Lagergroesse: ");
            return new Lager(lagerGroesse);
        }
        
        return new Lager();
    }
    
    
    /**
     * Main Methode, durch die das Programm gestartet wird.
     * Erstellt Objekt der eigenen Klasse, um nich statische Methode start() aufzurufen.
     */
    public static void main(String [] args){
        new LagerDialog().start();
    }
    
}
