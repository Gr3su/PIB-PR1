import java.util.Scanner;
/**
 * Dialog für die Klassen mit Queue.
 * 
 * @author Tim Mueller / Yannick Gross
 * @version 19.01.2023 / 11:00Uhr
 */
public final class QueueDialog{
    //Texte
    private static final    String      MENUE_AUSWAHL           =   "Bitte waehlen Sie eine Option:\n" + 
                                                                    "1 - Queue erstellen\n" +
                                                                    "2 - Objekt erstellen\n" +
                                                                    "3 - Erstes Objekt aus Queue entfernen\n" +
                                                                    "4 - Gewuenschte Queue ausgeben\n" +
                                                                    "5 - Size / Full / Empty - Test\n" +
                                                                    "6 - Dialog beenden\n";
                                                                    
    private static final    String      QUEUE_ERSTELLEN_AUSWAHL =   "Bitte waehlen Sie eine Warteschlangen-Art:\n" +
                                                                    "1 - Personen Queue\n" +
                                                                    "2 - String Queue";
                                                                    
    private static final    String      QUEUE_EINTRAG_AUSWAHL   =   "Bitte waehlen Sie eine Klasse von der ein Objekt"+
                                                                    " erstellt werden soll:\n"+
                                                                    "1 - Person\n" +
                                                                    "2 - String\n";
                                                                    
    private static final    String      EINGABE_VORNAME         =   "Der gewuenschte Vorname:\n";
    private static final    String      EINGABE_NACHNAME        =   "Der gewuenschte Nachname:\n";
    private static final    String      AUSWAHL_QUEUE           =   "Folgende Warteschlangen koennen ausgewaehlt werden:\n";
    private static final    String      EINGABE_STRING          =   "Der gewuenschte String:\n";
    private static final    String      EINGABE_QUEUE_GROESSE   =   "Die gewuenschte Groesse der Queue:\n";
    private static final    String      KEINE_QUEUE_ERSTELLT    =   "Es wurde noch keine Queue erstellt, Initialisierung wird erzwungen.\n";
    private static final    String      QUEUES_UEBERSCHREIBEN   =   "Wollen Sie die Warteschlangen von 0 an ueberschreiben? (1: Ja / 2: Nein)\n";
    private static final    String      NICHT_UEBERSCHREIBEN    =   "Queues werden nicht uberschrieben.\n";
    
    //Konstanten
    private static final    byte        MIN_OPTIONEN            =   1;
    private static final    byte        MAX_OPTIONEN            =   6;
    
    //Allgemeingueltige Variablen
    private static          Scanner     scanner;
    private static          boolean     killProgramm;
    private static          Queue []    warteschlangen;
    private static          int         queueGroesse;
    
    private QueueDialog(){}
    
    /**
     * Start Methode die den Dialog am laufen haelt und Fehler abfaengt.
     */
    private static void start(){
        killProgramm    = false;
        warteschlangen  = new Queue[5];
        queueGroesse    = 0;
        
        while(killProgramm == false){
            try{
                menueAuswahl();
            }
            catch(IllegalArgumentException e){
                System.err.println(e.getMessage());
            }
            catch(Exception e){
                System.err.println("Unerwarteter Fehler: " + e.getMessage());
                e.printStackTrace(System.err);
            }
        }
    }
    
    /**
     * Funktion zum Einlesen der gewuenschten Option, Abarbeitung welche Option
     * gewuenscht ist und aufrufen der passenden Methoden.
     * 
     * @throws IllegalArgumentException Wenn Menue-Option ausserhalb des Wertebereichs gewaehlt wird.
     * @throws IllegalArgumentException Wenn Switch-Case keinen Case findet, unerwarteter Fehler.
     */
    private static void menueAuswahl(){
        byte option = leseByte(MENUE_AUSWAHL);
        byte auswahl;
        
        if(option > MAX_OPTIONEN || option < MIN_OPTIONEN){
            throw new IllegalArgumentException(ErrorMessages.MENUE_AUSWAHL_BIS6.getMessage());
        }
        if(warteschlangen[0] == null && option != MIN_OPTIONEN && option != MAX_OPTIONEN){
            System.out.println(KEINE_QUEUE_ERSTELLT);
            option = 1;
        }
        
        switch(option){
            case 1:
                erstelleQueue();
                break;
            
            case 2:
                fuegeEintragEin();
                break;
                
            case 3:
                loescheEintrag();
                break;
            
            case 4:
                auswahl = auswahlQueue();
                print(warteschlangen[auswahl]);
                break;
                
            case 5:
                auswahl = auswahlQueue();
                System.out.println("Groesse: " + warteschlangen[auswahl].size());
                System.out.println("Full: " + warteschlangen[auswahl].full());
                System.out.println("Empty: " + warteschlangen[auswahl].empty() + "\n");
                break;
                
            case 6:
                killProgramm = true;
                break;
                
            default:
                throw new IllegalArgumentException(ErrorMessages.UNMOEGLICH_ZU_ERREICHEN.getMessage());
        }
    }
    
    /**
     * Erstellt eine Warteschlange des gewuenschten Typs und speichert
     * sie in einem Array. Ist das Array voll, wird angeboten es von vorne zu ueberschreiben.
     * 
     * @throws IllegalArgumentException Wenn Option ausserhalb des Wertebereichs ausgewaehlt wird.
     */
    private static void erstelleQueue(){
        if(queueGroesse == warteschlangen.length){
            System.err.println(ErrorMessages.MAX_QUEUES_ERREICHT.getMessage());
            byte auswahl = leseByte(QUEUES_UEBERSCHREIBEN);
            
            if(auswahl != 1){
                System.out.println(NICHT_UEBERSCHREIBEN);
                return;
            }
            queueGroesse = 0;
        }
        
        byte option = leseByte(QUEUE_ERSTELLEN_AUSWAHL);
        
        if(option > 2 || option < MIN_OPTIONEN){
            throw new IllegalArgumentException(ErrorMessages.MENUE_AUSWAHL_BIS2.getMessage());
        }
        
        int groesse = leseInt(EINGABE_QUEUE_GROESSE);
        
        if(option == MIN_OPTIONEN){
            warteschlangen[queueGroesse] = new PersonQueue(groesse);
            queueGroesse++;
        }
        else{
            warteschlangen[queueGroesse] = new StringQueue(groesse);
            queueGroesse++;
        }
    }
    
    /**
     * Fuegt an ein Warteschlangen Ende ein Element des passenden Typs ein.
     * 
     * @throws IllegalArgumentException Wenn Option ausserhalb des Wertebereichs ausgewaehlt wird.
     * @throws IllegalArgumentException Wenn ausgewaehlte Warteschlange voll ist.
     */
    private static void fuegeEintragEin(){
        byte option = leseByte(QUEUE_EINTRAG_AUSWAHL);
        byte auswahl = -1;
        Object obj = null;
        
        if(option > 2 || option < MIN_OPTIONEN){
            throw new IllegalArgumentException(ErrorMessages.MENUE_AUSWAHL_BIS2.getMessage());
        }
        
        if(option == MIN_OPTIONEN){
            String vorname = leseString(EINGABE_VORNAME);
            String nachname = leseString(EINGABE_NACHNAME);
                
            obj = new Person(vorname, nachname);
            // obj = new Person();
            auswahl = auswahlQueue();
        }
        else{
            obj = leseString(EINGABE_STRING);
            auswahl = auswahlQueue();
        }
        
        if(warteschlangen[auswahl].full()){
            throw new IllegalArgumentException(ErrorMessages.QUEUE_VOLL.getMessage());
        }
        
        warteschlangen[auswahl].addLast(obj);
    }
    
    /**
     * Laesst den User die Position einer Queue auswaehlen.
     * 
     * @return Position der gewuenschten Warteschlange.
     * 
     * @throws IllegalArgumentException Wenn Auswahl ausserhalb des Bereichs von 0 und der Anzahl
     * der gespeicherten Queues - 1 ist.
     */
    private static byte auswahlQueue(){
        byte option = leseByte(AUSWAHL_QUEUE + ausgabeQueueArray());
        if(option < 0 || option > queueGroesse){
            throw new IllegalArgumentException(ErrorMessages.AUSWAHL_AUSSERHALB_WERTEBREICH.getMessage());
        }
        
        return option;
    }
    
    /**
     * Gibt die gespeicherten Queues stueckweise aus.
     * Enthaelt pro Element: Implementierungsname | Fuellstand / Kapazitaet
     * 
     * @return Inhalt des warteschlangen-Arrays.
     */
    private static String ausgabeQueueArray(){
        StringBuilder ausgabe = new StringBuilder("");
        
        for(int i = 0; i < warteschlangen.length; i++){
            if(warteschlangen[i] != null){
                ausgabe.append(i + " : " + warteschlangen[i].getClass().getSimpleName() + 
                                " | " + warteschlangen[i].size() + "/" + warteschlangen[i].getCapacity() +"\n");
            }
        }
        
        return ausgabe.toString();
    }
    
    /**
     * Gibt das erste Element der ausgewaehlten Queue zurueck und loescht es.
     * 
     * @return Geloeschtes Element.
     */
    private static void loescheEintrag(){
        byte auswahl = auswahlQueue();
        
        Object obj = warteschlangen[auswahl].removeFirst();
        System.out.println("Geloeschter Inhalt: " + obj);
    }
    
    /**
     * Print Methode fuer einzelne Queues.
     * Gibt einzeln die Elemente einer uebergebenen Queue aus.
     * 
     * @param q Queue die ausgegeben werden soll.
     */
    private static void print(Queue q){
        StringBuilder ausgabe = new StringBuilder("Warteschlangen Inhalt:\n");
        
        for(int i = 0; i < q.size(); i++){
            ausgabe.append(i + " : " + q.get(i) + "\n");
        }
        
        System.out.println(ausgabe.toString());
    }
    
    private static int leseInt(String prompt){
        System.out.println(prompt);
        
        while(!scanner.hasNextInt()){
            System.err.println(ErrorMessages.KEIN_BYTE.getMessage());
            scanner.nextLine();
        }
        
        int tmp = scanner.nextInt();
        scanner.nextLine();
        
        return tmp;
    }
    
    private static byte leseByte(String prompt){
        System.out.println(prompt);
        
        while(!scanner.hasNextByte()){
            System.err.println(ErrorMessages.KEIN_BYTE.getMessage());
            scanner.nextLine();
        }
        
        byte tmp = scanner.nextByte();
        scanner.nextLine();
        
        return tmp;
    }
    
    private static String leseString(String prompt){
        System.out.println(prompt);
        
        return scanner.nextLine();
    }
    
    public static void main(String [] args){
        scanner = new Scanner(System.in);
        start();
    }
    
}
