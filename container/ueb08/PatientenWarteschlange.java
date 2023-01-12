package container.ueb08;


/**
 * Verkettete Liste fuer die PatientenWarteschlange.
 * 
 * @author Yannick Gross / Tim Mueller
 * @version 23.12.2022 / 17:30Uhr
 */
public final class PatientenWarteschlange{
    //Error-Messages
    private static final String ERROR_WARTESCHLANGE_VOLL        = "Warteschlange ist voll.\n";
    private static final String ERROR_WARTESCHLANGE_LEER        = "Warteschlange ist leer.\n";
    private static final String ERROR_MAXPATIENTEN_NICHT_NAT    = "Maximale Anzahl an Patienten muss natuerliche Zahl sein.\n";
    
    //Attribute
    private Patient anfang;
    private Patient ende;
    private int maxPatienten;
    private int anzahlPatienten;
    
    /**
     * Konstruktor mit allen Attributen.
     * 
     * @param maxPatienten Maximale Anzahl an Patienten die in die Warteschlange koennen.
     * 
     * @throws IllegalArgumentException Wenn maxPatienten keine natuerliche Zahl.
     */
    public PatientenWarteschlange(int maxPatienten){
        if(maxPatienten < 1){
            throw new IllegalArgumentException(ERROR_MAXPATIENTEN_NICHT_NAT);
        }
        
        this.maxPatienten = maxPatienten;
        this.anzahlPatienten = 0;
        this.anfang = null;
        this.ende = null;
    }
    
    /**
     * Standardkonstruktor um Warteschlange mit Groesse 5 zu erstellen.
     */
    public PatientenWarteschlange(){
        this(5);
    }
    
    /**
     * Fuegt neuen Patienten zur Warteschlange hinzu.
     * 
     * @param patientenNr Patientennummer des Patienten.
     * @param vorname Vorname des Patienten.
     * @param nachname Nachname des Patienten.
     * 
     * @throws IllegalArgumentException Wenn Warteschlange bereits voll ist.
     */
    public final void neuerPatient(int patientenNr, String vorname, String nachname){
        if(ende == null){
            anfang = new Patient(patientenNr, vorname, nachname);
            ende = anfang;
            anzahlPatienten++;
            return;
        }
        
        if(anzahlPatienten == maxPatienten){
            throw new IllegalArgumentException(ERROR_WARTESCHLANGE_VOLL);
        }
        Patient tmp = new Patient(patientenNr, vorname, nachname);
        ende.setNextPatient(tmp);
        ende = tmp;
        anzahlPatienten++;
    }
    
    /**
     * Gibt den naechsten Patienten zurueck und loescht ihn aus der Liste.
     * 
     * @return Der naechste Patient aus der Warteschlange.
     * 
     * @throws IllegalArgumentException 
     */
    public final Patient derNaechsteBitte(){
        if(anfang == null){
            throw new IllegalArgumentException(ERROR_WARTESCHLANGE_LEER);
        }
        
        Patient tmp = anfang;
        anfang = tmp.getNextPatient();
        tmp.setNextPatient(null);
        anzahlPatienten--;
        
        return tmp;
    }
    
    /**
     * Entfernt einen Patienten durch uebergebne Patientennummer,
     * entfernt nur das erste Auftauchen der Patientennummer.
     * 
     * @param patientenNr Patientennummer des zu loeschenden Patientens.
     * 
     * @return Den geloeschten Patienten.
     */
    public final Patient entfernePatient(int patientenNr){
        if(anzahlPatienten == 0){
            throw new IllegalArgumentException(ERROR_WARTESCHLANGE_LEER);
        }
        
        Patient tmp             = anfang;
        Patient previous_tmp    = anfang;
        Patient rueckgabe       = null;
        
        while(  tmp.getNextPatient() != null &&
                tmp.getPatientenNr() != patientenNr){
            previous_tmp = tmp;
            tmp = tmp.getNextPatient();
        }
        
        if(tmp.getPatientenNr() == patientenNr){
            rueckgabe = tmp;
            previous_tmp.setNextPatient(tmp.getNextPatient());
            
            if(tmp == ende){
                ende = previous_tmp;
            }
            if(tmp == anfang){
                anfang = tmp.getNextPatient();
            }
            if(anzahlPatienten == 1){
                anfang = null;
                ende = null;
            }
            anzahlPatienten--;
        }
        
        return rueckgabe;
    }
    
    @Override
    public final String toString(){
        StringBuilder ausgabe = new StringBuilder();
        ausgabe.append("Warteliste\n" + String.format("%-15s%15s  %-15s\n", "Patientennummer","Vorname","Nachname"));
        
        Patient tmp = anfang;
        while(tmp != null){
            ausgabe.append(String.format("%1$15s%2$15s, %3$-15s\n",tmp.toString().split("( )+")));
            tmp = tmp.getNextPatient();
        }
        
        return ausgabe.toString();
        
    }
}
