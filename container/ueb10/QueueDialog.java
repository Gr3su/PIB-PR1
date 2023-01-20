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
                                                                    "5 - Dialog beenden\n";
                                                                    
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
    
    private static          Scanner     scanner;
    private static          boolean     killProgramm;
    private static          Queue []    warteschlangen;
    private static          int         queueGroesse;
    
    private QueueDialog(){}
    
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
    
    private static void menueAuswahl(){
        byte option = leseByte(MENUE_AUSWAHL);
        
        if(option > 5 || option < 1){
            throw new IllegalArgumentException(ErrorMessages.MENUE_AUSWAHL_BIS5.getMessage());
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
                byte auswahl = auswahlQueue();
                print(warteschlangen[auswahl]);
                break;
                
            case 5:
                killProgramm = true;
                break;
                
            default:
                throw new IllegalStateException();
        }
    }
    
    private static void erstelleQueue(){
        if(queueGroesse == warteschlangen.length){
            throw new IllegalArgumentException(ErrorMessages.MAX_QUEUES_ERREICHT.getMessage());
        }
        
        byte option = leseByte(QUEUE_ERSTELLEN_AUSWAHL);
        
        if(option > 2 || option < 1){
            throw new IllegalArgumentException(ErrorMessages.MENUE_AUSWAHL_BIS2.getMessage());
        }
        
        int groesse = leseInt(EINGABE_QUEUE_GROESSE);
        
        if(option == 1){
            warteschlangen[queueGroesse] = new PersonQueue(groesse);
            queueGroesse++;
        }
        else{
            warteschlangen[queueGroesse] = new StringQueue(groesse);
            queueGroesse++;
        }
    }
    
    private static void fuegeEintragEin(){
        byte option = leseByte(QUEUE_EINTRAG_AUSWAHL);
        byte auswahl = -1;
        Object obj = null;
        
        if(option > 2 || option < 1){
            throw new IllegalArgumentException(ErrorMessages.MENUE_AUSWAHL_BIS2.getMessage());
        }
        
        if(option == 1){
            String vorname = leseString(EINGABE_VORNAME);
            String nachname = leseString(EINGABE_NACHNAME);
                
            obj = new Person(vorname, nachname);
            auswahl = auswahlQueue();
        }
        else{
            obj = leseString(EINGABE_STRING);
            auswahl = auswahlQueue();
        }
        
        warteschlangen[auswahl].addLast(obj);
    }
    
    private static byte auswahlQueue(){
        byte option = leseByte(AUSWAHL_QUEUE + ausgabeQueueArray());
        if(option < 0 || option > queueGroesse){
            throw new IllegalArgumentException(ErrorMessages.AUSWAHL_AUSSERHALB_WERTEBREICH.getMessage());
        }
        if(warteschlangen[option].full()){
            throw new IllegalArgumentException(ErrorMessages.QUEUE_VOLL.getMessage());
        }
        
        return option;
    }
    
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
    
    private static void loescheEintrag(){
        byte auswahl = auswahlQueue();
        
        Object obj = warteschlangen[auswahl].removeFirst();
        System.out.println("Geloeschter Inhalt: " + obj);
    }
    
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
