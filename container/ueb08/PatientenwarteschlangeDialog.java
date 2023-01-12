package container.ueb08;

/**
 * Vorgegebener Dialog fuer Patientenwarteschlange.
 * 
 * @author Yannick Gross / Tim Mueller
 * @version 23.12.2022 / 17:30Uhr
 */
public class PatientenwarteschlangeDialog {
    private PatientenwarteschlangeDialog(){}
    
    public static void main(String[] args) {
        try {
            // Warteschlange fuer maximal 10 Patienten anlegen
            PatientenWarteschlange patientenwarteschlange = new 
                PatientenWarteschlange(10);
            patientenwarteschlange.neuerPatient(4711, "Tom", "Ate");
            // Neue Patienten anhaengen
            patientenwarteschlange.neuerPatient(1234, "Elle", "Fant");
            patientenwarteschlange.neuerPatient(1111, "Moni", "Tor");
            patientenwarteschlange.neuerPatient(2222, "Phil", "Harmonie");
            System.out.println(patientenwarteschlange); // Warteschlange ausgeben
            Patient entfernterPatient = patientenwarteschlange.entfernePatient(1234);
            // Patient entfernen
            System.out.println("Patient geloescht: " + entfernterPatient);
            // naechsten Patient herausholen
            Patient naechsterPatient = patientenwarteschlange.derNaechsteBitte(); 
            System.out.println("Naechster Patient: " + naechsterPatient);
            System.out.println(patientenwarteschlange);
        } catch (RuntimeException e) {
            System.out.println(e);
            e.printStackTrace(System.out);
            }
    }
}