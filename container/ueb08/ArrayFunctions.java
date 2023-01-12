package container.ueb08;


/**
 * Hilfsmethoden fuer Mittelwertberechnung und die Auswertung von Strings.
 * 
 * @author Yannick Gross / Tim Mueller
 * @version 29.12.2022 / 18:00Uhr
 */
public final class ArrayFunctions{
    
    private ArrayFunctions(){}
    
    /**
     * Funktion um aus gegebenem Array mit double Werten das arithmetische Mittel zu bestimmen,
     * den Wert der diesem am naehesten ist und am weitesten entfernt.
     * 
     * @param messwerte Array mit double Werten.
     * 
     * @return Instanz von Klasse Mittelwert, in der die 3 Werte gespeichert werden.
     */
    public static final Mittelwert berechneMittelwert(double [] messwerte){
        final   double  EPSILON             = 0.00001;
                double  mittelwert          = 0.0;
                double  abstandNah          = 0.0;
                double  abstandWeit         = 0.0;
                double  abstand             = 0.0;            
        final   int     arrayLaenge         = messwerte.length;
                int     indexNah            = 0;
                int     indexWeit           = 0;
        
        for(int i = 0; i < arrayLaenge; i++){
            mittelwert += messwerte[i];
            
            if(messwerte[i] > 0.0){
                abstandNah = messwerte[i];
            }
        }
        mittelwert /= arrayLaenge;
        
        for(int i = 0; i < arrayLaenge; i++){
            abstand = abstandZwischenZwei(messwerte[i], mittelwert); 
            
            if(abstand < abstandNah){
                abstandNah = abstand;
                indexNah = i;
            }
            if(abstand > abstandWeit){
                abstandWeit = abstand;
                indexWeit = i;
            }
        }
        
        return new Mittelwert(mittelwert, messwerte[indexNah], messwerte[indexWeit]);
    }
    
    /**
     * Funktion die in gegebenem Array zaehlt wie viele Strings ausschliesslich aus Gross- oder
     * Kleinbuchstaben bestehen.
     * 
     * @param strings Array mit Strings.
     * 
     * @return Anzahl der gesuchten Strings.
     */
    public static final int stringsAuswerten(String [] strings){
                int     ergebnis    = 0;
        final   String  REGEX       = "^[A-Z]+$|^[a-z]+$";
        for(int i = 0; i < strings.length; i++){
            if(strings[i].matches(REGEX)){
                 ergebnis++;   
            }
        }
        
        return ergebnis;
    }
    
    private static final double abstandZwischenZwei(double a, double b){
        if(b > a){
            return b - a;
        }
        return a - b;
    }
}

