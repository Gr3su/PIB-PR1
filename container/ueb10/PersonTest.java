

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Die Test-Klasse PersonTest.
 *
 * @author  Tim Mueller / Yannick Gross
 * @version 23.01.2023 / 18:00
 */
public class PersonTest
{
    @Test
        public void testKonstruktor_Person_mit_Vorname_Leer_erwartet_IA(){
            assertThrows(IllegalArgumentException.class, () -> {
                new Person("", "Mueller");
            });
    }
    
    @Test
        public void testKonstruktor_Person_mit_Nachname_Leer_erwartet_IA(){
            assertThrows(IllegalArgumentException.class, () -> {
                new Person("Tim", "");
            });
    }
    @Test
        public void testMethode_setVorname_Leer_erwartet_IA(){
            Person person = new Person("Tim", "Mueller");
            assertThrows(IllegalArgumentException.class, () -> {
            person.setVorname("");
            });
    }
    @Test
        public void testMethode_setNachname_Leer_erwartet_IA(){
            Person person = new Person("Tim", "Mueller");
            assertThrows(IllegalArgumentException.class, () -> {
            person.setNachname("");
            });
    }
    @Test
        public void testKonstruktor_mit_Vorname_Yannick_erwartet_Yannick(){
            Person person = new Person("Yannick", "Gross");
            assertEquals("Yannick",person.getVorname());
          
    }
    @Test
        public void testKonstruktor_mit_Nachname_Gross_erwartet_Gross(){
            Person person = new Person("Yannick", "Gross");
            assertEquals("Gross",person.getNachname());
          
    }
   
    
}
