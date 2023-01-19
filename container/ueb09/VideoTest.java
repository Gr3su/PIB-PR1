import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Die Test-Klasse fuer Video.
 *
 * @author  Tim Mueller / Yannick Gross
 * @version 15.01.2023 / 20:00
 */
public class VideoTest{
    
    @Test
    public void testKonstruktor_mit_titel_leer_erwartet_IA(){
        assertThrows(IllegalArgumentException.class, () -> {
            Video video = new Video(1234, 0, 0.0, "", 10, 2000);    
        });
        
    }
    
    @Test
    public void testKonstruktor_mit_spieldauer_null_erwartet_IA(){
        assertThrows(IllegalArgumentException.class, () -> {
            Video video = new Video(1234, 0, 0.0, "Shreck", 0, 2000);    
        });
        
    }
    
    @Test
    public void testKonstruktor_mit_jahr_1899_erwartet_IA(){
        assertThrows(IllegalArgumentException.class, () -> {
            Video video = new Video(1234, 0, 0.0, "Shreck", 1, 1899);    
        });
        
    }
    @Test
    public void testKonstruktor_mit_jahr_2023_erwartet_IA(){
        assertThrows(IllegalArgumentException.class, () -> {
            Video video = new Video(1234, 0, 0.0, "Shreck", 1, 2023);    
        });
        
    }
    
    @Test
    public void testKonstruktor_mit_Shreck_1_erwartet_korrekt(){
        Video video = new Video(1234, 0, 0.0, "Shreck", 1, 2000);    
        final String erwarteterTitel = "Shreck";
        final int erwarteteSpieldauer = 1;
        final int erwartetesJahr = 2000;
        
        assertEquals(erwarteterTitel, video.getTitel());
        assertEquals(erwarteteSpieldauer, video.getSpieldauer());
        assertEquals(erwartetesJahr, video.getJahr());
    }
    
}