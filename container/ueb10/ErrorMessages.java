
/**
 * Beinhaltet alle Fehlermeldungen.
 * 
 * @author Tim Mueller / Yannick Gross
 * @version 19.01.2023 / 11:00Uhr
 */
public enum ErrorMessages{
    PERSON_VORNAME_LEER("#ERR#\nDer Vorname darf nicht leer sein.\n#ERR#\n"),
    PERSON_NACHNAME_LEER("#ERR#\nDer Nachname darf nicht leer sein\n#ERR#\n"),
    NAMEN_KEINE_SONDERZEICHEN("#ERR#\nNamen duerfen nur aus Buchstaben bestehen!\n#ERR#"),
    QUEUE_ZU_KLEIN("#ERR#\nWarteschlangengroesse muss mindestens 1 betragen.\n#ERR#\n"),
    QUEUE_VOLL("#ERR#\nDie Warteschlange ist bereits voll.\n#ERR#\n"),
    KEIN_PERSONENOBJEKT("#ERR#\nDer uebergebene Datentyp ist keine Person.\n#ERR#\n"),
    KEIN_STRINGOBJEKT("#ERR#\nDer uebergebene Datentyp ist kein String.\n#ERR#\n"),
    KEIN_BYTE("#ERR#\nDer eingegebene Datentyp ist kein Byte.\n#ERR#\n"),
    OBJEKT_IST_NULL("#ERR#\nUebergebener Datentyp darf nicht null sein.\n#ERR#"),
    QUEUE_LEER("#ERR#\nWarteschlange ist leer.\n#ERR#"),
    MENUE_AUSWAHL_BIS6("#ERR#\nEs sind nur Menue Nummern von 1-6 moeglich.\n#ERR#"),
    MENUE_AUSWAHL_BIS2("#ERR#\nEs sind nur Menue Nummern von 1-2 moeglich.\n#ERR#"),
    MAX_QUEUES_ERREICHT("#ERR#\nDie maximale Anzahl an zu erstellenden Queues wurde erreicht.\n#ERR#"),
    AUSWAHL_AUSSERHALB_WERTEBREICH("#ERR#\nDie gewuenschte Auswahl liegt ausserhalb des Wertebereichs.\n#ERR#"),
    UNMOEGLICH_ZU_ERREICHEN("#ERR#\nKeine Ahnung wie Du hier gelandet bist.\n#ERR#");
    
    
    private String message;
    
    private ErrorMessages(String message){
        this.message = message;
    }
    
    public String getMessage(){
        return message;
    }
    
}
