

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Die Test-Klasse StringQueueTest.
 *
 * @author  Tim Mueller / Yannick Gross
 * @version 23.01.2023 / 18:00
 */
public class StringQueueTest
{
    @Test
    public void testKonstruktor_StringQueue_mit_QueueGroesse_0_erwartet_IA(){
        assertThrows(IllegalArgumentException.class, () -> {
            new StringQueue(0);
        });
    }
    @Test
        public void testKonstruktor_StringQueue_mit_QueueGroesse_Minus1_erwartet_IA(){
            assertThrows(IllegalArgumentException.class, () -> {
                new StringQueue(-1);
            });
        }
    @Test
        public void testMethode_StringQueue_addLast_mit_Person_erwartet_IA(){
            Person person = new Person("Yannick","Groß");
            Object obj = person;
            Queue warteschlange = new StringQueue(1);
            assertThrows(IllegalArgumentException.class, () -> {
                warteschlange.addLast(obj);  
            });
        }
    @Test
        public void testMethode_StringQueue_addLast_mit_Null_erwartet_IA(){
            Object obj = null;
            Queue warteschlange = new StringQueue(1);
            assertThrows(IllegalArgumentException.class, () -> {
                warteschlange.addLast(obj);  
            });
        }
    @Test
        public void testMethode_StringQueue_addLast_Queue_voll_erwartet_IA(){
            Object obj1  = "Eins";
            Object obj2  = "Zwei";
            Queue warteschlange = new StringQueue(1);
            warteschlange.addLast(obj1);
            assertThrows(IllegalArgumentException.class, () -> {
                warteschlange.addLast(obj2);  
            });
        }
    @Test
        public void testMethode_StringQueue_removeFirst_Queue_leer_erwartet_IA(){
            Queue warteschlange = new StringQueue(1);
            assertThrows(IllegalArgumentException.class, () -> {
                warteschlange.removeFirst();  
            });
        }
    @Test
        public void testKonstruktor_capacity_1_erwartet_1(){
            Queue warteschlange = new StringQueue(1);
            
            assertEquals(1, warteschlange.getCapacity());
        }
    @Test
        public void testKonstruktor_get_0_erwartet_obj1(){
            Object obj1  = "Eins";
            Queue warteschlange = new StringQueue(1);
            warteschlange.addLast(obj1);
            
            assertEquals(obj1, warteschlange.get(0));
        }
    @Test
        public void testKonstruktor_get_1_erwartet_obj2(){
            Object obj1  = "Eins";
            Object obj2  = "Zwei";
            Queue warteschlange = new StringQueue(2);
            warteschlange.addLast(obj1);
            warteschlange.addLast(obj2);
            
            assertEquals(obj2, warteschlange.get(1));
        }
}
