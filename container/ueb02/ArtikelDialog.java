import java.util.Scanner;

/**
 * 
 * @author Tim Mueller / Yannick Gross
 * @version 11.11.2022 / 17:28
 */
public class ArtikelDialog{
    private static final String FALSCHE_EINGABE = "Falsche Eingabe, Anweisungen genau lesen.";
    
    private static Scanner scanner = new Scanner(System.in);
    private static Artikel artikel = null;
    private static boolean killProgram = false;
    
    
    public static void main(String [] args){
        new ArtikelDialog().start();
    }
    
    public void start(){
        while(killProgram == false){
            try{
                optionAuswahl(); 
                
            } catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            } catch(Exception e){
                System.out.println("Ein unerwarteter Fehler trat auf: " + e.getMessage());
            }
        }
    }
    
    public void optionAuswahl(){
        
        try{
            String input = readString("\nVerfügbare Kommandos:\nerstellen - Artikel erstellen\nbucheZugang - Zugang auf Artikel buchen\n"+
                            "bucheAbgang - Abgang auf Artikel buchen\nsetBestand - Setze den Bestand neu\n"+
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
                    createArtikel();
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
                
                case "tostring":
                    System.out.println(artikel.toString());
                    break;
                    
                case "stopp":
                    killProgram = true;
                    break;
            }
        } catch(Exception e){
            System.out.println("Unerwarteter Fehler: " + e.getMessage());
        }
        
    }
    
    public void createArtikel(){
        String konstruktorWahl = readString("Soll der komplette Konstruktor aufgerufen werden? (Ja/Nein)\nEingabe:");
        if(konstruktorWahl.trim().isEmpty() == true || (!konstruktorWahl.equalsIgnoreCase("ja") && !konstruktorWahl.equalsIgnoreCase("nein"))){
            throw new IllegalArgumentException(FALSCHE_EINGABE);
        }
        
        int artikelNr = readInt("Die Artikelnummer: ");
        String art = readString("Die Artikelart: ");
        if(konstruktorWahl.trim().equalsIgnoreCase("ja")){
            int bestand = readInt("Der Bestand: ");
            
            artikel = new Artikel(artikelNr, art, bestand);
            return;
        }
        artikel = new Artikel(artikelNr, art);
    }
    
    public String readString(String prompt){
        System.out.println(prompt);
        return scanner.nextLine();
    }
    
    public int readInt(String prompt){
        System.out.println(prompt);
        int tmp = scanner.nextInt();
        scanner.nextLine();
        return tmp;
    }
}
