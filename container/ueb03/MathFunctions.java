package container.ueb03;

import java.lang.Math;

/**
 * Werkzeugmethoden fuer mathematische Operationen
 * 
 * @author Yannick Gross / Tim Mueller
 * @version 20.11.2022 / 21:00Uhr
 */
public class MathFunctions{
    public static final String ERROR_KEINE_ISBN = "Eingegebene Zahl keine ISBN\n";
    public static final String ERROR_GROESSER_NULL = "Eingegebene Zahl muss groesser 0 sein\n";
    public static final String ERROR_NICHT_NEGATIV_SEIN = "Eingegebene Zahl darf nicht negativ sein\n";
    private static final double ERROR = 0.00001;
    
    private MathFunctions(){
    }
    
    /**
     * Findet Teiler von Zahl heraus und berechnet deren Summe.
     * 
     * @param zahl Zahl von der die Teilersumme berechnet wird.
     * 
     * @return Teilersumme der uebergebenen Zahl.
     * 
     * @throws IllegalArgumentException Wenn <code>Zahl</code> negativ ist.
     */
    public static long berechneTeilersumme(long zahl){
        long teilerSumme = 0;
        
        if(zahl <= 0){
            throw new IllegalArgumentException(ERROR_GROESSER_NULL);
        }
        for(long i = 1; i * i <= zahl; i++){
            if(i * i == zahl){
                teilerSumme += i;
                break;
            }
            if(zahl % i == 0){
                teilerSumme += i + zahl / i;
            }
        }
        return teilerSumme;
    }

    /**
     * Berechnet Pruefziffer fuer ISBN.
     * 
     * @param isbn 9-stellige ISBN deren 10. Stelle, die Pruefziffer, berechnet werden soll.
     * 
     * @return ISBN Pruefziffer.
     * 
     * @throws IllegalArgumentException Wenn <code>isbn</code> negativ ist.
     * @throws IllegalArgumentException Wenn <code>isbn</code> nicht genau 9-stellig ist.
     */
    public static String berechneChecksummeIsbn(long isbn){
        long pruefziffer = 0;
        
        if(isbn < 0){
            throw new IllegalArgumentException(ERROR_NICHT_NEGATIV_SEIN);
        }
        if(isbn / 100000000 == 0 || isbn / 1000000000 > 0){
            throw new IllegalArgumentException(ERROR_KEINE_ISBN);
        }
        
        for(int i = 9; i > 0; i--){
            pruefziffer += (isbn %  10) * i;
            isbn /= 10;
        }
        pruefziffer %= 11;
        
        if(pruefziffer == 10){
            return "X";
        }
        
        return String.valueOf(pruefziffer);
        
    }
    
    /**
     * Berechnet Nullstellen einer quadratischen Gleichung ueber die pq-Formel,
     * beachtet keine Rundungsfehler beim Potenzieren und Wurzel ziehen.
     * Betrachtet NS die auf 10.000-tel genau nebeneinander liegen als doppelte NS.
     * 
     * @param p Variable p die bei Normalform als Faktor des zweiten Summanden steht.
     * @param q Variable q die bei Normalform als letzter Summand steht.
     * 
     * @return Art der Nullstellen und die dazugehoerigen Nullstellen.
     */
    public static String berechneNullstellen(double p, double q){
        double diskriminante = (p/2) * (p/2) - q;
        double ersteNullstelle = -(p/2) + Math.sqrt(diskriminante);
        double zweiteNullstelle = -(p/2) - Math.sqrt(diskriminante);
        
        
        if(diskriminante >= ERROR){
            return "Zwei Nullstellen: " + ersteNullstelle + " | " + zweiteNullstelle;
        }
        
        else if(diskriminante <= ERROR){
            return "Komplexe Nullstellen";
        }
        
        return "Doppelte Nullstelle: " + ersteNullstelle;
    }
}
