package container.ueb08;


/**
 * Klasse in dem die Daten der Patienten und deren Reihenfolge gespeichert wird.
 * 
 * @author Yannick Gross / Tim Mueller
 * @version 23.12.2022 / 17:30Uhr
 */
public final class Patient{
    //Error-Messages
    private static final String ERROR_PATIENTENNR_VIER_STELLEN  = "Patientennummer muss vierstellig sein, ohne fuehrende Nullen.\n";
    private static final String ERROR_VORNAME_LEER              = "Vorname muss aus Zeichen bestehen.\n";
    private static final String ERROR_NACHNAME_LEER             = "NACHNAME muss aus Zeichen bestehen.\n";
    
    //Attribute
    private int patientenNr;
    private String vorname;
    private String nachname;
    private Patient nextPatient;
    
    /**
     * Konstruktor mit allen Attributen.
     * @param patientenNr Patientennummer des Patienten.
     * @param vorname Vorname des Patienten.
     * @param nachname Nachnae des Patienten.
     * @param nextPatient Der naechste Patient in der Warteschlange.
     * 
     * @throws IllegalArgumentException Wenn Patientennummer nicht vierstellig ist, ohne fuehrende Nullen.
     * @throws IllegalArgumentException Wenn der Vorname nicht aus Zeichen besteht.
     * @throws IllegalArgumentException Wenn der Nachname nicht aus Zeichen besteht.
     */
    public Patient(int patientenNr, String vorname, String nachname, Patient nextPatient){
        if(patientenNr > 9999 || patientenNr < 1000){
            throw new IllegalArgumentException(ERROR_PATIENTENNR_VIER_STELLEN);
        }
        if(vorname.strip().isEmpty()){
            throw new IllegalArgumentException(ERROR_VORNAME_LEER);
        }
        if(nachname.strip().isEmpty()){
            throw new IllegalArgumentException(ERROR_NACHNAME_LEER);
        }
        
        this.patientenNr = patientenNr;
        this.vorname = vorname;
        this.nachname = nachname;
        this.nextPatient = nextPatient;
    }
    
    /**
     * Konstruktor ohne nextPatient.
     */
    public Patient(int patientenNr, String vorname, String nachname){
        this(patientenNr, vorname, nachname, null);
    }
    
    private Patient(){}
    
    public final void setNextPatient(Patient nextPatient){
        this.nextPatient = nextPatient;
    }
    
    public final Patient getNextPatient(){
        return nextPatient;
    }
    
    public final int getPatientenNr(){
        return patientenNr;
    }
    
    public final String getVorname(){
        return vorname;
    }
    
    public final String getNachname(){
        return nachname;
    }
    
    @Override
    public final String toString(){
        return patientenNr + " " + vorname + " " + nachname;
    }
}
