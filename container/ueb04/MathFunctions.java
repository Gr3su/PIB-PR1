package container.ueb04;

import java.lang.Math;

/**
 * Werkzeugmethoden fuer mathematische Operationen
 * 
 * @author Yannick Gross / Tim Mueller
 * @version 28.11.2022 / 19:00Uhr
 */
public class MathFunctions{
    public  static final String ERROR_KEINE_ISBN =           "Eingegebene ISBN muss 9-stellig und positiv sein\n";
    public  static final String ERROR_GROESSER_NULL =        "Eingegebene Zahl muss groesser 0 sein\n";
    public  static final String ERROR_NICHT_NEGATIV_SEIN =   "Eingegebene Zahl muss positiv sein\n";
    public  static final String ERROR_NICHT_NULL =           "Eingegebene Zahl darf nicht 0 sein";
    public  static final String ERROR_EINGABE_ZU_GROSS =     "Eingegebene Zahl zu gross zur Berechnung.\n";
    private static final double ERROR =                      0.00001;
    private static final long ISBN_MIN =                     100000000;
    private static final long ISBN_MAX =                     999999999;
    
    /**
     * Verhindert die Erstellung eines Objekts.
     */
    private MathFunctions(){
    }
    
    /**
     * Findet Teiler von Zahl heraus und berechnet deren Summe.
     * 
     * @param zahl Natürliche Zahl von der die Teilersumme berechnet wird.
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
        
        long i;
        for(i = 1; i * i <= zahl; i++){
            if(zahl % i == 0){
                teilerSumme += i + zahl / i;
            }
        }
        
        if(i * i == zahl){
            teilerSumme += i;
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
        
        if(isbn < ISBN_MIN || isbn > ISBN_MAX || isbn < 0){
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
        double pHalbe = p/2;
        double diskriminante = pHalbe * pHalbe - q;
        double wurzelAusDiskriminante = Math.sqrt(diskriminante);
        double ersteNullstelle = -pHalbe + wurzelAusDiskriminante;
        double zweiteNullstelle = -pHalbe - wurzelAusDiskriminante;
        
        
        if(diskriminante >= ERROR){
            return "Zwei Nullstellen: " + ersteNullstelle + " | " + zweiteNullstelle;
        }
        
        else if(diskriminante <= -ERROR){
            return "Komplexe Nullstellen";
        }
        
        return "Doppelte Nullstelle: " + ersteNullstelle;
    }
    
    /**
     * Ueberprueft ob Zahl als a^4+b^3+c^2 dargestellt werden kann.
     * 
     * @param zahl Zahl fuer die geprueft werden soll, muss groesser 3 sein.
     *
     * @return Wahrheitswert, ob Zahl mit gegebener Formel dargestellt werden kann.
     *
     * @throws IllegalArgumentException Wenn eingegebene <code>kleiner</code> 1 ist.
     */
    public static boolean istSummeVonPotenzen(long zahl){
        if(zahl < 1){
            throw new IllegalArgumentException(ERROR_GROESSER_NULL);
        }
        if(zahl < 3){
            return false;
        }
        double aHochVier;
        double bHochDrei;
        double c;
        for(int a = 1; a <= Math.pow(zahl,1.0/4.0); a++){
            aHochVier = a * a * a * a;
            
            for(int b = 1; b <= Math.cbrt(zahl); b++){
                bHochDrei = b * b * b;
                c = zahl - aHochVier - bHochDrei;
                
                if(Math.sqrt(c) % 1 == 0 && c != 0){

                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * Berechnet mit hilfe des Euklidischen Algorithmus den groessten gemeinsamen Teiler
     * zweier Zahlen.
     * 
     * @param zahl1 Die erste Zahl die man zur pruefung eines gemeinsamen Teilers eingibt.
     * @param zahl2 Die zweite Zahl die man zur pruefung eines gemeinsamen Teilers eingibt.      
     * @return Den groessten gemeinsamen Teiler aus zahl1 und zahl2.
     * 
     * @throws IllegalArgumentException Wenn <code>Zahl1</code> oder <code>Zahl2</code> kleiner 1 ist.
     */
    public static int berechneGgt(int zahl1, int zahl2){
        if(zahl1 <= 0 || zahl2 <= 0){
            throw new IllegalArgumentException(ERROR_GROESSER_NULL);
        }
        
        if(zahl2 > zahl1){
            int tmp = zahl2;
            zahl2 = zahl1;
            zahl1 = tmp;
        }
        
        int tmp;
        while(zahl2 != 0){
            tmp = zahl1;
            zahl1 = zahl2;
            zahl2 = tmp % zahl2;
        }
        
        return zahl1;
    }
    
    /**
     * Berechnet die Fakultaet einer Zahl.
     * 
     * @param zahl Die einzugebende Zahl von der die Fakultaet bestimmt wird.   
     * @return Die Fakultaet der eingegebenen Zahl.
     * 
     * @throws IllegalArgumentException Wenn <code>zahl</code> negativ ist.
     */
     
    public static long berechneFakultaet(int zahl){
        if(zahl < 0){
            throw new IllegalArgumentException(ERROR_NICHT_NEGATIV_SEIN);
        }
        if(zahl > 20){
            throw new IllegalArgumentException(ERROR_EINGABE_ZU_GROSS);    
        }
        
        long fakultaet = 1;
        for(int i = 0; i < zahl; i++){
            fakultaet *= (i + 1);
        }
        return fakultaet;
    }
    
    /**
     * Berechnet die Reihensumme eines Wertes x und einer ganzen Zahl anzahl (n).
     * 
     * @param anzahl Eine ganze Zahl fuer die anzahl der Summanden der Reihe.   
     * @param x Ein Wert x fuer die Berechnung der Reihensumme.
     * @return Die Reihensumme aus dem Wert x und der ganzen Zahl anzahl (n).
     * 
     * @throws IllegalArgumentException Wenn <code>anzahl</code> kleiner 1 ist.
     * @throws IllegalArgumentException Wenn <code>x</code> 0 ist.
     */
    
    public static double berechneReihensumme(int anzahl, double x){
        if(anzahl <= 0){
            throw new IllegalArgumentException(ERROR_GROESSER_NULL);
        }
        if(x == 0){
            throw new IllegalArgumentException(ERROR_NICHT_NULL);    
        }
        
        double zaehler = x-1.0;
        double nenner = x;
        double reihenSumme = zaehler / nenner;
        for(int i = 2; i <= anzahl; i++){ 
            zaehler *= (x-1.0);
            nenner = (nenner / (i-1.0)) * x;
            reihenSumme += zaehler / nenner;
        }
        
        if(Double.isNaN(reihenSumme)){
            throw new IllegalArgumentException(ERROR_EINGABE_ZU_GROSS);
        }
        
        return reihenSumme;
    }

    /**
     * Berechnet die Reihensumme eines Wertes x und einer ganzen Zahl anzahl (n) REKURSIV.
     * 
     * @param anzahl Eine ganze Zahl fuer die anzahl der Summanden der Reihe.   
     * @param x Ein Wert x fuer die Berechnung der Reihensumme.
     * @return Die Reihensumme aus dem Wert x und der ganzen Zahl anzahl (n).
     * 
     * @throws IllegalArgumentException Wenn <code>anzahl</code> kleiner 1 ist.
     * @throws IllegalArgumentException Wenn <code>x</code> 0 ist.
     */
    public static double berechneReihensummeRekursiv(int anzahl, double x){
        if(anzahl <= 0){
            throw new IllegalArgumentException(ERROR_GROESSER_NULL);
        }
        if(x == 0){
            throw new IllegalArgumentException(ERROR_NICHT_NULL);    
        }
        if(anzahl == 1){
            return (x-1)/x;
        }
        double tmp = Math.pow(x-1,anzahl)/(anzahl * Math.pow(x,anzahl)) + berechneReihensumme(anzahl - 1, x);
        
        if(Double.isNaN(tmp)){
            throw new IllegalArgumentException(ERROR_EINGABE_ZU_GROSS);
        }
        
        return tmp;
    }  
}