package container.ueb06;



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Die Test-Klasse ArtikelTest.
 *
 * @author  Yannick Gross / Tim Mueller
 * @version 11.12.2022 / 00:35Uhr
 */
public class ArtikelTest{
    
    @Test
    public void testKonstruktor_mit_artikelNr_1000_erwartet_korrekt(){
        Artikel artikel = new Artikel(1000,"auto");
        assertEquals(1000,artikel.getArtikelNr());
    }
    
    @Test
    public void testKonstruktor_mit_artikelNr_9999_erwartet_korrekt(){
        Artikel artikel = new Artikel(9999,"auto");
        assertEquals(9999,artikel.getArtikelNr());
    }
    
    @Test
    public void testKonstruktor_mit_artikelNr_999_erwartet_IllegalAE(){
        assertThrows(IllegalArgumentException.class, () -> {
          new Artikel(999,"auto");  
        });
    }
    
    @Test
    public void testKonstruktor_mit_artikelNr_10000_erwartet_IllegalAE(){
        assertThrows(IllegalArgumentException.class, () -> {
          new Artikel(10000,"auto");  
        });
    }
    
    @Test
    public void testKonsturktor_mit_bestand_Negativ1_erwartet_IllegalAE(){
        assertThrows(IllegalArgumentException.class, () -> {
          new Artikel(1000,"auto",-1);  
        });
    }
    
    @Test
    public void testKonsturktor_mit_preis_Negativ1_erwartet_IllegalAE(){
        assertThrows(IllegalArgumentException.class, () -> {
          new Artikel(1000,"auto",-1.0);  
        });
    }
    
    @Test
    public void testBucheAbgang_mit_0_erwartet_gleicherBestand(){
        Artikel artikel = new Artikel(1000,"auto",4);
        final int erwarteterBestand = 4;
        
        artikel.bucheAbgang(0);
        
        assertEquals(erwarteterBestand, artikel.getBestand());
    }
    
    @Test
    public void testBucheAbgang_mit_1_erwartet_BestandWeniger(){
        Artikel artikel = new Artikel(1000,"auto",4);
        final int erwarteterBestand = 3;
        
        artikel.bucheAbgang(1);
        
        assertEquals(erwarteterBestand, artikel.getBestand());
    }
    
    @Test
    public void testBucheAbgang_mit_Negativ1_erwartet_IllegalAE(){
        Artikel artikel = new Artikel(1000,"auto",4);
        
        assertThrows(IllegalArgumentException.class, () -> {
           artikel.bucheAbgang(-1); 
        });
    }
    
    @Test
    public void testBucheAbgang_mit_mehrAlsBestand_erwartet_IllegalAE(){
        Artikel artikel = new Artikel(1000,"auto",4);
        
        assertThrows(IllegalArgumentException.class, () -> {
           artikel.bucheAbgang(5); 
        });   
    }
    
    @Test
    public void testBucheZugang_mit_0_erwartet_gleicherBestand(){
        Artikel artikel = new Artikel(1000,"auto",4);
        final int erwarteterBestand = 4;
        
        artikel.bucheZugang(0);
        
        assertEquals(erwarteterBestand, artikel.getBestand());
    }
    
    @Test
    public void testBucheZugang_mit_1_erwartet_BestandWeniger(){
        Artikel artikel = new Artikel(1000,"auto",4);
        final int erwarteterBestand = 5;
        
        artikel.bucheZugang(1);
        
        assertEquals(erwarteterBestand, artikel.getBestand());
    }
    
    @Test
    public void testBucheZugang_mit_Negativ1_erwartet_IllegalAE(){
        Artikel artikel = new Artikel(1000,"auto",4);
        
        assertThrows(IllegalArgumentException.class, () -> {
           artikel.bucheZugang(-1); 
        });
    }
    
    @Test
    public void testAenderePreis_mit_Negativ50_erwartet_halberPreis(){
        Artikel artikel = new Artikel(1000,"auto",10.0);
        final double erwarteterPreis = 5.0;
        
        artikel.aenderePreis(-50.0);
        
        assertEquals(erwarteterPreis, artikel.getPreis());
    }
    
    @Test
    public void testAenderePreis_mit_0_erwartet_gleicherPreis(){
        Artikel artikel = new Artikel(1000,"auto",10.0);
        final double erwarteterPreis = 10.0;
        
        artikel.aenderePreis(0.0);
        
        assertEquals(erwarteterPreis, artikel.getPreis());
    }
}
