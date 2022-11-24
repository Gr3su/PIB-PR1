package container.ueb04;


/**
 * Beschreiben Sie hier die Klasse test.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class test{
    public static void main(String [] args, long a){
        long sum = 1;
        for(long i = 1; i <= a; i++){
            sum *= i;
        }
        System.out.println(sum);
    }
}
