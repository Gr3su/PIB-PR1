

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Die Test-Klasse fuer CD.
 *
 * @author  Tim Mueller / Yannick Gross
 * @version 15.01.2023 / 20:00
 */
public class CDTest{
    
    @Test
    public void testKonstruktor_mit_interpret_leer_erwartet_IA(){
        assertThrows(IllegalArgumentException.class, () -> {
            CD cd = new CD(1234, 0, 0.0, "", "Lemon Tree", 1);    
        });
        
    }
    
    @Test
    public void testKonstruktor_mit_titel_leer_erwartet_IA(){
        assertThrows(IllegalArgumentException.class, () -> {
            CD cd = new CD(1234, 0, 0.0, "Xavier", "", 1);    
        });
        
    }
    
    @Test
    public void testKonstruktor_mit_anzahlTitel_0_erwartet_IA(){
        assertThrows(IllegalArgumentException.class, () -> {
            CD cd = new CD(1234, 0, 0.0, "Xavier", "Lemon Tree", 0);    
        });
        
    }
    
    @Test
    public void testKonstruktor_mit_Xavier_LemonTree_1_erwartet_korrekt(){
        CD cd = new CD(1234, 0, 0.0, "Xavier", "Lemon Tree", 1);    
        final String erwarteterInterpret = "Xavier";
        final String erwarteterTitel = "Lemon Tree";
        final int erwarteteAnzahlTitel = 1;
        
        assertEquals(erwarteterInterpret, cd.getInterpret());
        assertEquals(erwarteterTitel, cd.getTitel());
        assertEquals(erwarteteAnzahlTitel, cd.getAnzahlTitel());
    }
    
}
