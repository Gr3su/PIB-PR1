
/**
 * Beschreiben Sie hier die Klasse PersonQueue.
 * 
 * @author Tim Mueller / Yannick Gross
 * @version 19.01.2023 / 11:00Uhr
 */
public class StringQueue implements Queue{
    
    private String [] queue;
    private int size;
    private int capacity;
    
    public StringQueue(int queueGroesse){
        if(queueGroesse < 1){
            throw new IllegalArgumentException(ErrorMessages.QUEUE_ZU_KLEIN.getMessage());
        }
        
        queue = new String[queueGroesse];
        this.capacity = queueGroesse;
        this.size = 0;
    }
    
    public StringQueue(){
        this(10);
    }
    
    @Override
    public int getCapacity(){
        return capacity;
    }
    
    @Override
    public void addLast(Object obj){
        if(!(obj instanceof String)){
            throw new IllegalArgumentException(ErrorMessages.KEIN_STRINGOBJEKT.getMessage());
        }
        if(obj == null){
            throw new IllegalArgumentException(ErrorMessages.OBJEKT_IST_NULL.getMessage());
        }
        if(size == queue.length){
            throw new IllegalArgumentException(ErrorMessages.QUEUE_VOLL.getMessage());
        }
        
        queue[size] = (String)obj;
        size++;
    }
    
    @Override
    public Object removeFirst(){
        if(size == 0){
            throw new IllegalArgumentException(ErrorMessages.QUEUE_LEER.getMessage());
        }
        
        String tmp = queue[0];
        
        for(int i = 0; i < size - 1; i++){
            queue[i] = queue[i+1];
        }
        size--;
        queue[size] = null;
        
        return tmp;
    }
    
    @Override
    public Object get(int i){
        return queue[i];
    }
    
    @Override
    public boolean empty(){
        if(size == 0){
            return true;
        }
        return false;
    }
    
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
