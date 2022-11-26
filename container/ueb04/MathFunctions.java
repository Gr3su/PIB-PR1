package container.ueb04;

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
    
    public static boolean istSummeVonPotenzen(long zahl){
        boolean ergebnis = false;
        int a = 1;
        int b = 1;
        int c = 1;
        int d = 9;
        
        //zahl < Math.pow(a, 4) + Math.pow(b2, 3) + Math.pow(c2, 2)
        
        for (int a1 = 1; a1 < d || ergebnis == false ; a1++){ 
            a++;
            for(int b1 = 1; b1 < d || ergebnis == false; b1++){
                b++;
                for(int c1 = 1; c1 < d || ergebnis == false ; c1++){
                    c++;
                    if (zahl == Math.pow(a, 4) + Math.pow(b, 3) + Math.pow(c, 2)){
                        ergebnis = true;    
                    }
                }
            }
        }
        
        if (zahl == Math.pow(a, 4) + Math.pow(b, 3) + Math.pow(c, 2)){
            ergebnis = true;    
        }
        return ergebnis;  
    } 
    
    /**
     * Berechnet mit hilfe des Euklidischen Algorithmus den größten gemeinsamen Teiler
     * zweier Zahlen 
     * 
     * @param zahl1 Die erste Zahl die man zur prüfung eines gemeinsamen Teilers eingibt
     * @param zahl2 Die zweite Zahl die man zur prüfung eines gemeinsamen Teilers eingibt       
     * @return Den groeßten gemeinsamen Teiler aus zahl1 und zahl2
     * 
     * @throws IllegalArgumentException Wenn <code>Zahl1</code> oder <code>Zahl2</code> negativ ist.
     */
    public static int berechneGgt(int zahl1, int zahl2){
        if(zahl1 <= 0 || zahl1 <= 0){
            throw new IllegalArgumentException(ERROR_GROESSER_NULL);
        }
        while (zahl2 != 0) {
             if (zahl1 > zahl2) {
                 zahl1 = zahl1 - zahl2;
                 }
             else {
                 zahl2 = zahl2 - zahl1;
                 }
        }    
        return zahl1;
    }
    
    /**
     * Berechnet die Fakultät einer Zahl
     * 
     * @param zahl Die einzugebende Zahl aus der die Fahultät bestimmt wird    
     * @return die Fakultät der eingegebenen Zahl
     * 
     * @throws IllegalArgumentException Wenn <code>zahl</code>  negativ ist.
     */
    
    public static long berechneFakultät(int zahl){
        if(zahl <= 0){
            throw new IllegalArgumentException(ERROR_GROESSER_NULL);
        }
        long fakultaet = 1;
        for(int i = 1; i <= zahl; i++){
            fakultaet *= i;
        }
        return fakultaet;
    }
    
    /**
     * Berechnet die Reihensumme eines Wertes x und einer ganzen Zahl anzahl (n)
     * 
     * @param anzahl eine ganze Zahl für die anzahl der Reihen der Reihensumme    
     * @param x ein Wert x für die berechnung der Reihensumme
     * @return die Reihensumme aus dem Wert x und der ganzen Zahl anzahl (n)
     * 
     * @throws IllegalArgumentException Wenn <code>anzahl</code>  negativ ist.
     */
    
    public static double berechneReihensumme(int anzahl, double x){
        if(anzahl <= 0){
            throw new IllegalArgumentException(ERROR_GROESSER_NULL);
        }
        double reihenSumme = 0;
        for(int i = 1; i <= anzahl; i++){ 
            reihenSumme += Math.pow((x - 1),i) / (i * Math.pow(x,i));  
        }
        return reihenSumme;
    }
}
