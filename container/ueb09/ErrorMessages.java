
/**
 * Ansammlung der Fehlermeldungen.
 * 
 * @author  Tim Mueller / Yannick Gross
 * @version 16.01.2023 / 16:00Uhr
 */
public enum ErrorMessages{
    ERROR_BUCH_TITEL_LEER("#ERR# Der Titel muss mindestens aus einem Zeichen bestehen.\n"),
    ERROR_BUCH_AUTOR_LEER("#ERR# Der Autor muss mindestens aus einem Zeichen bestehen.\n"),
    ERROR_BUCH_VERLAG_LEER("#ERR# Der Verlag muss mindestens aus einem Zeichen bestehen.\n"),
    
    ERROR_VIDEO_TITEL_LEER("#ERR# Der Titel muss mindestens ein Zeichen enthalten.\n"),
    ERROR_VIDEO_SPIELDAUER_KLEINER_EINS("#ERR# Die Spieldauer muss groesser 0 sein.\n"),
    ERROR_VIDEO_ERSCHEINUNGSJAHR_NICHT_MOEGLICH("#ERR# Das Erscheinungsjahr muss zwischen 1899 und 2023 liegen.\n"),
    
    ERROR_CD_INTERPRET_LEER("#ERR# Der Interpret muss mindestens ein Zeichen enthalten.\n"),
    ERROR_CD_TITEL_LEER("#ERR# Der Titel muss mindestens ein Zeichen enthalten.\n"),
    ERROR_CD_ANZAHLTITEL_KLEINER_EINS("#ERR# Die Anzahl der Titel muss eine natürliche Zahl sein.\n"),
    
    ERROR_ARTIKEL_ARTIKELNUMMER_VIERSTELLIG("#ERR# Die Artikelnummer muss vierstellig und positiv sein\n"),
    ERROR_ARTIKEL_ARTIKELART_ZEICHENKETTE("#ERR# Die Artikelart muss eine Zeichenkette sein\n"),
    ERROR_ARTIKEL_BESTAND_POSITIV("#ERR# Der Bestand muss positiv sein\n"),
    ERROR_ARTIKEL_BUCHUNG_POSITIV("#ERR# Die Buchungsmenge muss positiv sein\n"),
    ERROR_ARTIKEL_PREIS_POSITIV("#ERR# Der Preis muss positiv sein\n"),
    ERROR_ARTIKEL_PROZENT_UNTER_MINUS_100("#ERR# Der Preis kann maximal um 100% reduziert werden\n"),
    
    ERROR_LAGER_LAGERGROESSE_MUSS_GROESSER("#ERR# Die Groesse des Lagers muss mindestens 1 betragen.\n"),
    ERROR_LAGER_KEIN_OBJEKT("#ERR# Es wurde kein gueltiges Objekt angegeben.\n"),
    ERROR_LAGER_LAGER_VOLL("#ERR# Artikel konnte nicht angelegt werden, weil das Lager voll ist.\n"),
    ERROR_LAGER_ARTIKEL_NICHT_GEFUNDEN("#ERR# Der gewuenschte Artikel wurde nicht im Lager gefunden.\n"),
    ERROR_LAGER_INDEX_FALSCH("#ERR# Der angegebene Index ist falsch. Er muss liegen, zwischen 0 und "),
    ERROR_LAGER_ARTIKEL_EXISTIERT("#ERR# Der eingegebene Artikel existiert bereits.\n"),
    
    ERROR_LAGERDIALOG_OPTION_NICHT_GEFUNDEN("#ERR# Option wurde nicht gefunden\n"),
    ERROR_LAGERDIALOG_OPTION_NUMMER_BIS_2("#ERR# Es koennen nur folgende Option-Nummern angenommen werden: 1-2"),
    ERROR_LAGERDIALOG_OPTION_NUMMER_BIS_3("#ERR# Es koennen nur folgende Option-Nummern angenommen werden: 1-3"),
    ERROR_LAGERDIALOG_OPTION_NUMMER_BIS_4("#ERR# Es koennen nur folgende Option-Nummern angenommen werden: 1-4"),
    ERROR_LAGERDIALOG_OPTION_NUMMER_BIS_11("#ERR# Es koennen nur folgende Option-Nummern angenommen werden: 1-11"),
    ERROR_LAGERDIALOG_INDEX_NICHT_BELEGT("#ERR# Gewuenschter Index ist nicht belegt.\n"),
    ERROR_LAGERDIALOG_FALSCHER_DATENTYP("#ERR# Falscher Datentyp wurde eingegeben.\n");
    
    private String message;
    
    private ErrorMessages(String message){
        this.message = message;
    }
    
    public String getMessage(){
        return message;
    }
}
