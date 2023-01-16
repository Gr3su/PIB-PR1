

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Die Test-Klasse fuer Artikel.
 *
 * @author  Tim Mueller / Yannick Gross
 * @version 12.01.2023 / 20:00
 */
public class ArtikelTest{
    
    @Test
    public void testKonstruktor_mit_artikelNummer_dreistellig_erwartet_IA(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Artikel(999, "Baum");
        });
    }
    
    @Test
    public void testKonstruktor_mit_artikelNummer_fuenfstellig_erwartet_IA(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Artikel(99999, "Baum");
        });
    }
    
    @Test
    public void testKonstruktor_mit_artikelNummer_1234_erwartet_1234(){
        Artikel tmp = new Artikel(1234, "Baum");
        int erwartet = 1234;
        int real = tmp.getArtikelNr();
        
        assertEquals(erwartet, real);
    }
    
    @Test
    public void testKonstruktor_mit_artikelNummer_3744_erwartet_3744(){
        Artikel tmp = new Artikel(3744, "Baum");
        int erwartet = 3744;
        int real = tmp.getArtikelNr();
        
        assertEquals(erwartet, real);
    }
    
    @Test
    public void testKonstruktor_mit_art_leer_erwartet_IA(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Artikel(1234, "");
        });
    }
    
    @Test
    public void testKonstruktor_mit_art_leerzeichen_erwartet_IA(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Artikel(1234, "     ");
        });
    }
    
    @Test
    public void testKonstruktor_mit_art_null_erwartet_NP(){
        String art = null;
        
        assertThrows(NullPointerException.class, () -> {
            new Artikel(1234, art);
        });
    }
    
    @Test
    public void testKonstruktor_mit_art_Baum_erwartet_Baum(){
        String art = "Baum";
        Artikel tmp = new Artikel(1234, art);
        String real = tmp.getArt();
        
        assertEquals(art, real);
    }
    
    @Test
    public void testKonstruktor_mit_art_Auto_erwartet_Auto(){
        String art = "Auto";
        Artikel tmp = new Artikel(1234, art);
        String real = tmp.getArt();
        
        assertEquals(art, real);
    }
}
