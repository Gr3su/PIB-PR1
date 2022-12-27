package container.ueb08;


/**
 * Beschreiben Sie hier die Klasse Patient.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Patient{
    private int patientenNr;
    private String vorname;
    private String nachname;
    private Patient nextPatient;
    
    public Patient(int patientenNr, String vorname, String nachname, Patient nextPatient){
        this.patientenNr = patientenNr;
        this.vorname = vorname;
        this.nachname = nachname;
        this.nextPatient = nextPatient;
    }
    
    public Patient(int patientenNr, String vorname, String nachname){
        this(patientenNr, vorname, nachname, null);
    }
    
    public void setNextPatient(Patient nextPatient){
        this.nextPatient = nextPatient;
    }
    
    public Patient getNextPatient(){
        return nextPatient;
    }
    
    public int getPatientenNr(){
        return patientenNr;
    }
    
    public String getVorname(){
        return vorname;
    }
    
    public String getNachname(){
        return nachname;
    }
    
    @Override
    public String toString(){
        return patientenNr + " " + vorname + " " + nachname;
    }
}
