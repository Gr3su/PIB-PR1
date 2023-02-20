
/**
 * Implementierung von Queue mit Personen.
 * 
 * @author Tim Mueller / Yannick Gross
 * @version 19.01.2023 / 11:00Uhr
 */
public class PersonQueue implements Queue{
    
    private Person [] queue;
    private int size;
    private int capacity;
    
    /**
     * Erstellt eine Queue fuer Personen.
     * 
     * @param queueGroesse Kapazitaet der Warteschlange.
     * 
     * @throws IllegalArgumentException Wenn queueGroesse kleiner 1 ist.
     */
    public PersonQueue(int queueGroesse){
        if(queueGroesse < 1){
            throw new IllegalArgumentException(ErrorMessages.QUEUE_ZU_KLEIN.getMessage());
        }
        
        queue = new Person[queueGroesse];
        this.capacity = queueGroesse;
        this.size = 0;
    }
    
    /**
     * Erstellt Standard-Queue der Groesse 10 wenn nichts uebergeben.
     */
    public PersonQueue(){
        this(10);
    }
    
    @Override
    public int getCapacity(){
        return capacity;
    }
    
    /**
     * Fuegt ein Element ans Ende der Warteschlange ein.
     * 
     * @param obj Objekt das angefuegt werden soll.
     * 
     * @throws IllegalArgumentException Wenn falsches Objekt uebergeben wurde.
     * @throws IllegalArgumentException Wenn null Objekt uebergeben wurde.
     * @throws IllegalArgumentException Wenn Warteschlange bereits voll ist.
     */
    @Override
    public void addLast(Object obj){
        if(!(obj instanceof Person)){
            throw new IllegalArgumentException(ErrorMessages.KEIN_PERSONENOBJEKT.getMessage());
        }
        if(obj == null){
            throw new IllegalArgumentException(ErrorMessages.OBJEKT_IST_NULL.getMessage());
        }
        if(full()){
            throw new IllegalArgumentException(ErrorMessages.QUEUE_VOLL.getMessage());
        }
        
        queue[size] = (Person)obj;
        size++;
    }
    
    /**
     * Loescht das erste Element einer Warteschlange und gibt es zurueck.
     * 
     * @return Erstes Element der Warteschlange.
     * 
     * @throws IllegalArgumentException Wenn Warteschlange leer ist.
     */
    @Override
    public Object removeFirst(){
        if(empty()){
            throw new IllegalArgumentException(ErrorMessages.QUEUE_LEER.getMessage());
        }
        
        Person tmp = queue[0];
        
        for(int i = 0; i < size - 1; i++){
            queue[i] = queue[i+1];
        }
        size--;
        queue[size] = null;
        
        return tmp;
    }
    
    /**
     * Gibt Objekt an Stelle i zurueck.
     * 
     * @param i Stelle des gewuenschten Objekts.
     * 
     * @return Element an Stelle i der Queue.
     * 
     * @throws IllegalArgumentException Wenn i kleiner 0 oder groesser als Queue Kapazitaet.
     */
    @Override
    public Object get(int i){
        if(i < 0 || i > getCapacity() - 1){
            throw new IllegalArgumentException(ErrorMessages.AUSWAHL_AUSSERHALB_WERTEBREICH.getMessage());
        }
        
        return queue[i];
    }
    
    /**
     * Prueft ob Queue leer oder nicht.
     * 
     * @return true (Queue leer) oder false (Queue nicht leer).
     */
    @Override
    public boolean empty(){
        if(size == 0){
            return true;
        }
        return false;
        // return size == 0;
    }
    
    /**
     * Prueft ob Queue voll oder nicht.
     * 
     * @return true (Queue voll) oder false (Queue nicht voll).
     */
    @Override
    public boolean full(){
        if(size == queue.length){
            return true;
        }
        return false;
    }
    
    @Override
    public int size(){
        return size;
    }
    
    @Override
    public String toString(){
        StringBuilder ausgabe = new StringBuilder("");
        
        for(int i = 0; i < size; i++){
            ausgabe.append(i + " : " + queue[i] + "\n");
        }
        
        return ausgabe.toString();
    }
}
