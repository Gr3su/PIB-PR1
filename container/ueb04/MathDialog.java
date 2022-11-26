package container.ueb04;

import java.util.Scanner;
/**
 * Beschreiben Sie hier die Klasse MathDialog.
 * 
 * @author Yannick Gross / Tim Mueller
 * @version 20.11.2022 / 21:00Uhr
 */
public class MathDialog{
    private static final String ERROR_FALSCHE_EINGABE = "Falsche Eingabe, bitte erneut versuchen\n";
    private static final String ERROR_OPTION_NICHT_GEFUNDEN = "Option wurde nicht gefunden\n";
    
    private boolean killProgram;
    private Scanner scanner;
    private byte option;
    
    
    public MathDialog(){
       killProgram = false;
       scanner = new Scanner(System.in);
       option = -1;
    }
    
    /**
     * Dialog der von main Methode gestartet wird.
     * Führt den Dialog und faengt die Fehler ab.
     */
    public void start(){
        
        while(!killProgram){
            try{

                option = optionAuswahl();
                methodeAusfuehren(option);
            
            }
            
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
            catch(Exception e){
                e.printStackTrace(System.out);
            }
        }
    }
    
    /**
     * Gibt moegliche Optionen fuer User aus und wartet auf seine Eingabe.
     * 
     * @return Eingabe des Users.
     */
    public byte optionAuswahl(){
        String optionAusgabe = "Folgende Funktionen sind auswaehlbar:\n" +
                                "1 : Teilersumme berechnen\n" +
                                "2 : Pruefziffer einer ISBN berechnen\n" +
                                "3 : Nullstellen von quad. Gleichung mit pq-Formel berechnen\n" +
                                "4 : Dialog stoppen";
                                
        return leseByte(optionAusgabe);
    }
    
    /**
     * Methode die gewuenschte Methode startet und noetige Werte einfordert.
     * 
     * @param option Methode die der User ausfuehren moechte.
     * 
     * @throws IllegalArgumentException Wenn Falsche Eingabe getaetigt wurde.
     */
    public void methodeAusfuehren(int option){
        switch(option){
            case 1:
                long zahl = leseLong("Zahl eingeben zur Berechnung der Teilersumme:");
                long summe = MathFunctions.berechneTeilersumme(zahl);
                System.out.println("Teilersumme von " + zahl + ": " + summe + "\n");
                option = -1;
                break;
                
            case 2:
                long isbn = leseLong("9 Stellige ISBN Nummer eingeben:");
                String pruefziffer = MathFunctions.berechneChecksummeIsbn(isbn);
                System.out.println("Pruefziffer zu " + isbn + ": " + pruefziffer + "\n");
                option = -1;
                break;
                
            case 3:
                double p = leseDouble("p: ");
                double q = leseDouble("q: ");
                String nullstellen = MathFunctions.berechneNullstellen(p,q);
                System.out.println(nullstellen + "\n");
                option = -1;
                break;
                
            case 4:
                killProgram = true;
                break;
            
            default:
                throw new IllegalArgumentException(ERROR_OPTION_NICHT_GEFUNDEN);
        }
    }
    
    /**
     * Wartet bis ein Wert des Typs Short eingegeben wird.
     * 
     * @param prompt String der ausgegeben werden soll.
     * 
     * @return Short die weiter verwendet werden kann.
     */
    public byte leseByte(String prompt){
        System.out.println(prompt);
        while(!scanner.hasNextByte()){
            System.out.println(ERROR_FALSCHE_EINGABE);
            scanner.next();
        }
        
        return scanner.nextByte();
    }
    
    /**
     * Wartet bis ein Wert des Typs Long eingegeben wird.
     * 
     * @param prompt String der ausgegeben werden soll.
     * 
     * @return Long die weiter verwendet werden kann.
     */
    public long leseLong(String prompt){
        System.out.println(prompt);
        while(!scanner.hasNextLong()){
            System.out.println(ERROR_FALSCHE_EINGABE);
            scanner.next();
        }
        
        return scanner.nextLong();
    }
    
    /**
     * Wartet bis ein Wert des Typs Double eingegeben wird.
     * 
     * @param prompt String der ausgegeben werden soll.
     * 
     * @return Double die weiter verwendet werden kann.
     */
    public double leseDouble(String prompt){
        System.out.println(prompt);
        while(!scanner.hasNextDouble()){
            System.out.println(ERROR_FALSCHE_EINGABE);
            scanner.next();
        }
        
        return scanner.nextDouble();
    }
    
    public static void main(String[]args){
        new MathDialog().start();
    }
}
