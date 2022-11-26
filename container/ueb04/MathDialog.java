package container.ueb04;

import java.util.Scanner;
/**
 * Beschreiben Sie hier die Klasse MathDialog.
 * 
 * @author Yannick Gross / Tim Mueller
 * @version 26.11.2022 / 14:00Uhr
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
     * Fuehrt den Dialog und faengt die Fehler ab.
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
                                "4 : Summe von Potenzen berechnen\n" +
                                "5 : Den gemeinsamen Teiler zweier Zahlen berechnen\n" +
                                "6 : Die Fakultaet einer Zahl berechnen\n" +
                                "7 : Berechnen einer Reihensumme aus einer Anzahl und einem Wert x\n" +
                                "8 : Berechnen einer Reihensumme aus einer Anzahl und einem Wert x, REKURSIV\n" +
                                "9 : Dialog stoppen";
                                
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
                long zahlP = leseLong("zu Pruefende Zahl: ");
                long zahlP = leseLong("Zu Pruefende Ziffer: ");
                
                boolean istSummeP = MathFunctions.istSummeVonPotenzen(zahlP);
                
                if(istSummeP == true){
                  System.out.println(zahlP + " kann als a^4+b^3+c^2 dargestellt werden.");
                }
                else{
                  System.out.println(zahlP + " kann nicht als a^4+b^3+c^2 dargestellt werden.");
                }
                option = -1;
                break;
                
            case 5:
                int zahl1 = leseInt("Erste Zahl: ");
                int zahl2 = leseInt("Zweite Zahl: ");
                int gemeinsamerTeiler = MathFunctions.berechneGgt(zahl1, zahl2);
                System.out.println(gemeinsamerTeiler + " ist der gemeinsame Teiler von " + zahl1 + " und " + zahl2);
                option = -1;
                break;
                
            case 6:
                int zahlF = leseInt("Zahl eingeben zur berechnung der Fakultaet: ");
                long fakultaet = MathFunctions.berechneFakultaet(zahlF);
                System.out.println( fakultaet + " ist die Fakultaet von " + zahlF);
                option = -1;
                break;
                
            case 7:
                int anzahl = leseInt("Die Anzahl der Durchlaeufe fuer die Reihe: ");
                double x = leseDouble("Der Wert x der Reihensumme: ");
                double reihensumme = MathFunctions.berechneReihensumme(anzahl,x);
                System.out.println( reihensumme + " ist die Summe");
                option = -1;
                break;
                
            case 8:
                int anzahlR = leseInt("Die Anzahl der Durchlaeufe fuer die Reihe: ");
                double xR = leseDouble("Der Wert x der Reihensumme: ");
                double reihensummeR = MathFunctions.berechneReihensummeRekursiv(anzahlR, xR);
                System.out.println( reihensummeR + " ist die Summe");
                option = -1;
                break;
                
            case 9:
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
     * Wartet bis ein Wert des Typs Integer eingegeben wird.
     * 
     * @param prompt String der ausgegeben werden soll.
     * 
     * @return Integer die weiter verwendet werden kann.
     */
    public int leseInt(String prompt){
        System.out.println(prompt);
        while(!scanner.hasNextInt()){
            System.out.println(ERROR_FALSCHE_EINGABE);
            scanner.next();
        }
        
        return scanner.nextInt();
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
