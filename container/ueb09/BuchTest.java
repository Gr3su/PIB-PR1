import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Die Test-Klasse fuer Buch.
 *
 * @author  Tim Mueller / Yannick Gross
 * @version 15.01.2023 / 20:00
 */
public class BuchTest{
    
    @Test
    public void testKonstruktor_mit_titel_leer_erwartet_IA(){
        assertThrows(IllegalArgumentException.class, () -> {
            Buch buch = new Buch(1234, 0, 0.0, "", "Tolkien", "Klett-Cotta");    
        });
        
    }
    
    @Test
    public void testKonstruktor_mit_autor_leer_erwartet_IA(){
        assertThrows(IllegalArgumentException.class, () -> {
            Buch buch = new Buch(1234, 0, 0.0, "Herr Der Ringe", "", "Klett-Cotta");    
        });
        
    }
    
    @Test
    public void testKonstruktor_mit_verlag_leer_erwartet_IA(){
        assertThrows(IllegalArgumentException.class, () -> {
            Buch buch = new Buch(1234, 0, 0.0, "Herr Der Ringe", "Tolkien", "");    
        });
        
    }
    
    @Test
    public void testKonstruktor_mit_Herr_Der_Ringe_1_erwartet_korrekt(){
        Buch buch = new Buch(1234, 0, 0.0, "Herr Der Ringe", "Tolkien", "Klett-Cotta");    
        final String erwarteterTitel = "Herr Der Ringe";
        final String erwarteterAutor = "Tolkien";
        final String erwarteterVerlag = "Klett-Cotta";
        
        assertEquals(erwarteterTitel, buch.getTitel());
        assertEquals(erwarteterAutor, buch.getAutor());
        assertEquals(erwarteterVerlag, buch.getVerlag());
    }
}    