

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Die Test-Klasse PersonQueueTest.
 *
 * @author  Tim Mueller / Yannick Gross
 * @version 23.01.2023 / 18:00
 */
public class PersonQueueTest
{
    @Test
        public void testKonstruktor_PersonQueue_mit_QueueGroesse_0_erwartet_IA(){
            assertThrows(IllegalArgumentException.class, () -> {
                new PersonQueue(0);
            });
        }
    @Test
        public void testKonstruktor_PersonQueue_mit_QueueGroesse_Minus1_erwartet_IA(){
            assertThrows(IllegalArgumentException.class, () -> {
                new PersonQueue(-1);
            });
        }
    @Test
        public void testMethode_PersonQueue_addLast_mit_String_erwartet_IA(){
            Object obj = "String";
            Queue warteschlange = new PersonQueue(1);
            assertThrows(IllegalArgumentException.class, () -> {
                warteschlange.addLast(obj);  
            });
        }
    @Test
        public void testMethode_PersonQueue_addLast_mit_Null_erwartet_IA(){
            Object obj = null;
            Queue warteschlange = new PersonQueue(1);
            assertThrows(IllegalArgumentException.class, () -> {
                warteschlange.addLast(obj);  
            });
        }
    @Test
        public void testMethode_PersonQueue_addLast_Queue_voll_erwartet_IA(){
            Person person  = new Person("Max","Mustermann");
            Person person2 = new Person("Test","123");
            Queue warteschlange = new PersonQueue(1);
            warteschlange.addLast(person);
            assertThrows(IllegalArgumentException.class, () -> {
                warteschlange.addLast(person2);  
            });
        }
    @Test
        public void testMethode_PersonQueue_removeFirst_Queue_leer_erwartet_IA(){
            Queue warteschlange = new PersonQueue(1);
            assertThrows(IllegalArgumentException.class, () -> {
                warteschlange.removeFirst();  
            });
        }
    @Test
        public void testKonstruktor_capacity_1_erwartet_1(){
            Queue warteschlange = new PersonQueue(1);
            
            assertEquals(1, warteschlange.getCapacity());
        }
    @Test
        public void testKonstruktor_get_0_erwartet_person(){
            Person person  = new Person("Max","Mustermann");
            Queue warteschlange = new PersonQueue(1);
            warteschlange.addLast(person);
            
            assertEquals(person, warteschlange.get(0));
        }
    @Test
        public void testKonstruktor_get_1_erwartet_person2(){
            Person person   = new Person("Max","Mustermann");
            Person person2  = new Person("Wolfgang","Amadeus");
            Queue warteschlange = new PersonQueue(2);
            warteschlange.addLast(person);
            warteschlange.addLast(person2);
            
            assertEquals(person2, warteschlange.get(1));
        }
}
