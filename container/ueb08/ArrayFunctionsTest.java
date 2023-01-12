package container.ueb08;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Die Test-Klasse fuer ArrayFunctions.
 *
 * @author  Yannick Gross / Tim Mueller
 * @version 29.12.2022 / 17:45Uhr
 */
public class ArrayFunctionsTest{
    private static double EPSILON = 0.000001;
    
    @Test
    public void testBerechneMittelwert_erwartet_mittelwert_6_nahWert_8_entWert_10(){
        final double [] tmp = {2.0,4.0,6.0,8.0,10.0};
        Mittelwert mittelwert = ArrayFunctions.berechneMittelwert(tmp);
        
        double erwarteterMittelwert         = 6.0;
        double erwarteterNahesterWert       = 6.0;
        double erwarteterEntferntesterWert  = 2.0;
        
        assertEquals(erwarteterMittelwert, mittelwert.getMittelwert(), EPSILON);
        assertEquals(erwarteterNahesterWert, mittelwert.getNahesterWert(), EPSILON);
        assertEquals(erwarteterEntferntesterWert, mittelwert.getEntferntesterWert(), EPSILON);
    }
    
    @Test
    public void testBerechneMittelwert_erwartet_mittelwert_0k5_nahWert_0_entWert_0(){
        final double [] tmp = {0.0,0.0,0.0,3.0,0.0,0.0};
        Mittelwert mittelwert = ArrayFunctions.berechneMittelwert(tmp);
        
        double erwarteterMittelwert         = 0.5;
        double erwarteterNahesterWert       = 0.0;
        double erwarteterEntferntesterWert  = 3.0;
        
        assertEquals(erwarteterMittelwert, mittelwert.getMittelwert(), EPSILON);
        assertEquals(erwarteterNahesterWert, mittelwert.getNahesterWert(), EPSILON);
        assertEquals(erwarteterEntferntesterWert, mittelwert.getEntferntesterWert(), EPSILON);
    }
    
    @Test
    public void testBerechneMittelwert_erwartet_mittelwert_7k0_nahWert_3_entWert_45(){
        final double [] tmp = {-5.0,3.0,-2.0,15.0,45.0,-14.0};
        Mittelwert mittelwert = ArrayFunctions.berechneMittelwert(tmp);
        
        double erwarteterMittelwert         = 7.0;
        double erwarteterNahesterWert       = 3.0;
        double erwarteterEntferntesterWert  = 45.0;
        
        assertEquals(erwarteterMittelwert, mittelwert.getMittelwert(), EPSILON);
        assertEquals(erwarteterNahesterWert, mittelwert.getNahesterWert(), EPSILON);
        assertEquals(erwarteterEntferntesterWert, mittelwert.getEntferntesterWert(), EPSILON);
    }
    
    @Test
    public void testBerechneMittelwert_erwartet_mittelwert_N14k0_nahWert_N24_entWert_N50(){
        final double [] tmp = {-50.0,7.0,-24.0,15.0,-45.0,13.0};
        Mittelwert mittelwert = ArrayFunctions.berechneMittelwert(tmp);
        
        double erwarteterMittelwert         = -14.0;
        double erwarteterNahesterWert       = -24.0;
        double erwarteterEntferntesterWert  = -50.0;
        
        assertEquals(erwarteterMittelwert, mittelwert.getMittelwert(), EPSILON);
        assertEquals(erwarteterNahesterWert, mittelwert.getNahesterWert(), EPSILON);
        assertEquals(erwarteterEntferntesterWert, mittelwert.getEntferntesterWert(), EPSILON);
    }
    
    @Test
    public void testBerechneMittelwert_erwartet_mittelwert_0_nahWert_0_entWert_0(){
        final double [] tmp = {0.0,0.0,0.0,0.0,0.0,0.0};
        Mittelwert mittelwert = ArrayFunctions.berechneMittelwert(tmp);
        
        double erwarteterMittelwert         = 0.0;
        double erwarteterNahesterWert       = 0.0;
        double erwarteterEntferntesterWert  = 0.0;
        
        assertEquals(erwarteterMittelwert, mittelwert.getMittelwert(), EPSILON);
        assertEquals(erwarteterNahesterWert, mittelwert.getNahesterWert(), EPSILON);
        assertEquals(erwarteterEntferntesterWert, mittelwert.getEntferntesterWert(), EPSILON);
    }
    
    @Test
    public void testBerechneMittelwert_erwartet_mittelwert_10k32216_nahWert_12k7_entWert_20k3(){
        final double [] tmp = {3.6,4.8,5.2,20.3,15.333,12.7};
        Mittelwert mittelwert = ArrayFunctions.berechneMittelwert(tmp);
        
        double erwarteterMittelwert         = 10.3221 + (2.0/30000.0);
        double erwarteterNahesterWert       = 12.7;
        double erwarteterEntferntesterWert  = 20.3;
        
        assertEquals(erwarteterMittelwert, mittelwert.getMittelwert(), EPSILON);
        assertEquals(erwarteterNahesterWert, mittelwert.getNahesterWert(), EPSILON);
        assertEquals(erwarteterEntferntesterWert, mittelwert.getEntferntesterWert(), EPSILON);
    }
    
    @Test
    public void testBerechneMittelwert_erwartet_mittelwert_6_nahWert_2_entWert_1(){
        final double [] tmp = {1.0,2.0,3.0,4.0,5.0};
        Mittelwert mittelwert = ArrayFunctions.berechneMittelwert(tmp);
        
        double erwarteterMittelwert         = 3.0;
        double erwarteterNahesterWert       = 3.0;
        double erwarteterEntferntesterWert  = 1.0;
        
        assertEquals(erwarteterMittelwert, mittelwert.getMittelwert(), EPSILON);
        assertEquals(erwarteterNahesterWert, mittelwert.getNahesterWert(), EPSILON);
        assertEquals(erwarteterEntferntesterWert, mittelwert.getEntferntesterWert(), EPSILON);
    }
    
    //#######################################################################################
    
    @Test
    public void testStringsAuswerten_mit_nur_Grossbuchstaben_erwartet_1(){
        final String [] tmp = {"HALLO","SAARLAND.DE","OLBERTZ@HTW-SAARLAND.DE"};
        int erwartetesErgebnis = 1;
        int erhaltenesErgebnis = ArrayFunctions.stringsAuswerten(tmp);
        
        assertEquals(erwartetesErgebnis, erhaltenesErgebnis);
    }
    
    @Test
    public void testStringsAuswerten_mit_nur_Kleinbuchstaben_erwartet_1(){
        final String [] tmp = {"hallo","saarland.de","olbertz@htw-saarland.de"};
        int erwartetesErgebnis = 1;
        int erhaltenesErgebnis = ArrayFunctions.stringsAuswerten(tmp);
        
        assertEquals(erwartetesErgebnis, erhaltenesErgebnis);
    }
    
    @Test
    public void testStringsAuswerten_mit_gemischten_Buchstaben_erwartet_0(){
        final String [] tmp = {"UNIversitaet","SAarLAnD","olbertz@HTW-saarland.de","ichBINcoolDUnurVIELLEICHT"};
        int erwartetesErgebnis = 0;
        int erhaltenesErgebnis = ArrayFunctions.stringsAuswerten(tmp);
        
        assertEquals(erwartetesErgebnis, erhaltenesErgebnis);
    }
    
    @Test
    public void testStringsAuswerten_mit_2Strings_Grossbuchstaben_2Strings_Kleinbuchstaben_erwartet_3(){
        final String [] tmp = {"UNIVERSITAET","SAARLAND","olbertz@htw-saarland.de","ichbincooldunurvielleicht"};
        int erwartetesErgebnis = 3;
        int erhaltenesErgebnis = ArrayFunctions.stringsAuswerten(tmp);
        
        assertEquals(erwartetesErgebnis, erhaltenesErgebnis);
    }
    
    @Test
    public void testStringsAuswerten_mit_2Strings_Grossbuchstaben_2Strings_Kleinbuchstaben_2Strings_gemischt_erwartet_3(){
        final String [] tmp = {"UNIVERSITAET","SAARLAND","olbertz@htw-saarland.de","ichbincooldunurvielleicht",
                                "OhABlaBLa","Netflix, AmzonPrime, Disney+, Maxdome"};
        int erwartetesErgebnis = 3;
        int erhaltenesErgebnis = ArrayFunctions.stringsAuswerten(tmp);
        
        assertEquals(erwartetesErgebnis, erhaltenesErgebnis);
    }
    
    @Test
    public void testStringsAuswerten_mit_0Strings_erwartet_0(){
        final String [] tmp = {};
        int erwartetesErgebnis = 0;
        int erhaltenesErgebnis = ArrayFunctions.stringsAuswerten(tmp);
        
        assertEquals(erwartetesErgebnis, erhaltenesErgebnis);
    }
    
    @Test
    public void testStringsAuswerten_mit_4_leeren_Strings_erwartet_0(){
        final String [] tmp = {"","","",""};
        int erwartetesErgebnis = 0;
        int erhaltenesErgebnis = ArrayFunctions.stringsAuswerten(tmp);
        
        assertEquals(erwartetesErgebnis, erhaltenesErgebnis);
    }
    
    @Test
    public void testStringsAuswerten_mit_4_Strings_nur_Leerzeichen_erwartet_0(){
        final String [] tmp = {"   ","   ","      "," "};
        int erwartetesErgebnis = 0;
        int erhaltenesErgebnis = ArrayFunctions.stringsAuswerten(tmp);
        
        assertEquals(erwartetesErgebnis, erhaltenesErgebnis);
    }
    
    @Test
    public void testStringsAuswerten_mit_4_Strings_mit_Sonderzeichen_erwartet_0(){
        final String [] tmp = {"HeyWasGeht","IchMagTest","BlaBla+BloBlo=BlaBlaBloBlo","-:+#.:$%6//(354"};
        int erwartetesErgebnis = 0;
        int erhaltenesErgebnis = ArrayFunctions.stringsAuswerten(tmp);
        
        assertEquals(erwartetesErgebnis, erhaltenesErgebnis);
    }
    
    @Test
    public void testStringsAuswerten_mit_4_Strings_mit_Zahlen_erwartet_0(){
        final String [] tmp = {"564567","5673","0","1"};
        int erwartetesErgebnis = 0;
        int erhaltenesErgebnis = ArrayFunctions.stringsAuswerten(tmp);
        
        assertEquals(erwartetesErgebnis, erhaltenesErgebnis);
    }
}
