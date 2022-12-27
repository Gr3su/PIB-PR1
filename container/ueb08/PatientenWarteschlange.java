package container.ueb08;


/**
 * Beschreiben Sie hier die Klasse PatientenWarteschlange.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class PatientenWarteschlange{
    private static final String ERROR_WARTESCHLANGE_VOLL = "Warteschlange ist voll.\n";
    private static final String ERROR_WARTESCHLANGE_LEER = "Warteschlange ist leer.\n";
    
    private Patient anfang;
    private Patient ende;
    private int maxPatienten;
    private int anzahlPatienten;
    
    public PatientenWarteschlange(int maxPatienten){
        this.maxPatienten = maxPatienten;
        this.anzahlPatienten = 0;
        this.anfang = null;
        this.ende = null;
    }
    
    public void neuerPatient(int patientenNr, String vorname, String nachname){
        if(ende == null){
            anfang = new Patient(patientenNr, vorname, nachname);
            ende = anfang;
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
    
    public Patient derNaechsteBitte(){
        if(anfang == null){
            throw new IllegalArgumentException();
        }
        
        Patient tmp = anfang;
        anfang = tmp.getNextPatient();
        tmp.setNextPatient(null);
        anzahlPatienten--;
        
        return tmp;
    }
    
    public Patient entfernePatient(int patientenNr){
        Patient tmp = anfang;
        Patient rueckgabe;
        
        while(tmp.getNextPatient().getPatientenNr() != patientenNr){
            tmp = tmp.getNextPatient();
        }
        
        rueckgabe = tmp.getNextPatient();
        if(tmp.getNextPatient().getNextPatient() == null){
            tmp.setNextPatient(null);
            return rueckgabe;
        }
        
        tmp.setNextPatient(tmp.getNextPatient().getNextPatient());
        anzahlPatienten--;
        
        return rueckgabe;
    }
    
    @Override
    public String toString(){
        StringBuilder ausgabe = new StringBuilder();
        ausgabe.append(String.format("%-15s%15s  %-15s\n", "Patientennummer","Vorname","Nachname"));
        
        Patient tmp = anfang;
        while(tmp != null){
            ausgabe.append(String.format("%1$15s%2$15s, %3$-15s\n",tmp.toString().split("( )+")));
            tmp = tmp.getNextPatient();
        }
        
        return ausgabe.toString();
        
    }
}
