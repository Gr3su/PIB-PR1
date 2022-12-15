package container.ueb06;



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Die Test-Klasse fuer die Klasse Lager.
 *
 * @author  Yannick Gross / Tim Mueller
 * @version 11.12.2022 / 00:35Uhr
 */
public class LagerTest{
    private static Artikel artikelBestand0;
    private static Artikel artikelBestand3;
    private static Artikel artikelPreis10;
    
    @BeforeEach
    public void setup(){
        artikelBestand0 = new Artikel(1000,"auto",0);
        artikelBestand3 = new Artikel(1001,"lkw",3);
        artikelPreis10 = new Artikel(1002,"motorrad",10.0);
    }
    
    @Test
    public void testKonstruktor_mit_lagergroesse_5_erwartet_korrekt(){
        Lager lager = new Lager(5);
        final int erwarteteGroesse = 5;
        
        assertEquals(erwarteteGroesse,lager.getLagerGroesse());
    }
    
    @Test
    public void testKonstruktor_mit_lagergroesse_1_erwartet_korrekt(){
        Lager lager = new Lager(1);
        final int erwarteteGroesse = 1;
        
        assertEquals(erwarteteGroesse, lager.getLagerGroesse());
    }
    
    @Test
    public void testKonstruktor_mit_lagergroesse_0_erwartet_IllegalAE(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Lager(0);
        });
    }
    
    @Test
    public void testKonstruktor_mit_lagergroesse_Negativ1_erwartet_IllegalAE(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Lager(-1);
        });
    }
    
    @Test
    public void testStandardKonstruktor_erwartet_lagergroesse_10(){
        Lager lager = new Lager();
        final int erwarteteGroesse = 10;
        
        assertEquals(erwarteteGroesse, lager.getLagerGroesse());
    }
    
    @Test
    public void tesLegeArtikelAn_mit_zweiUnterschiedlichenArtikeln_erwartet_bestand_2(){
        Lager lager = new Lager();
        lager.legeAnArtikel(artikelBestand0);
        lager.legeAnArtikel(artikelBestand3);
        final int erwarteterLagerbestand = 2;
        
        assertEquals(erwarteterLagerbestand, lager.getArtikelAnzahl());
    }
    
    @Test
    public void testLegeArtikelAn_mit_zweiUnterschiedlichenArtikeln_erwartet_korrekt(){
        Lager lager = new Lager(4);
        lager.legeAnArtikel(artikelBestand0);
        
        assertEquals(artikelBestand0, lager.getArtikel(0));
        
        lager.legeAnArtikel(artikelBestand3);
        
        assertEquals(artikelBestand3, lager.getArtikel(1));
        
        assertNotEquals(artikelBestand0,lager.getArtikel(2));
        assertNotEquals(artikelBestand3,lager.getArtikel(2));
        assertNotEquals(artikelBestand0,lager.getArtikel(3));
        assertNotEquals(artikelBestand3,lager.getArtikel(3));
    }
    
    @Test
    public void testLegeArtikelAn_mit_zweiGleichenArtikel_erwartet_IllegalAE(){
        Lager lager = new Lager();
        lager.legeAnArtikel(artikelBestand0);
        
        assertThrows(IllegalArgumentException.class, () -> {
            lager.legeAnArtikel(artikelBestand0);
        });
    }
    
    @Test
    public void testLegeArtikelAn_mit_einemArtikel_vollesLager_erwartet_IllegalAE(){
        Lager lager = new Lager(2);
        lager.legeAnArtikel(artikelBestand0);
        lager.legeAnArtikel(artikelBestand3);
        
        assertThrows(IllegalArgumentException.class, () -> {
            lager.legeAnArtikel(artikelPreis10);
        });
    }
    
    @Test
    public void testEntferneArtikel_mit_einemArtikel_erwartet_bestand_1_weniger(){
        Lager lager = new Lager(4);
        lager.legeAnArtikel(artikelBestand0);
        lager.legeAnArtikel(artikelBestand3);
        lager.legeAnArtikel(artikelPreis10);
        final int erwarteteAnzahl = 2;
        
        lager.entferneArtikel(1002);
        
        assertEquals(erwarteteAnzahl, lager.getArtikelAnzahl());
    }
    
    @Test
    public void testEntferneArtikel_mit_einemArtikel_erwartet_korrekt(){
        Lager lager = new Lager(4);
        lager.legeAnArtikel(artikelBestand0);
        lager.legeAnArtikel(artikelBestand3);
        lager.legeAnArtikel(artikelPreis10);
        
        lager.entferneArtikel(1002);
        
        assertThrows(IllegalArgumentException.class, () -> {
            lager.getIndexArtikel(1002);
        });
    }
    
    @Test
    public void testBucheZugang_mit_0_erwartet_gleicherBestand(){
        Lager lager = new Lager();
        lager.legeAnArtikel(artikelBestand3);
        final int erwarteterBestand = 3;
        
        lager.bucheZugang(1001,0);
        final int tatsaechlicherBestand = lager.getArtikel(0).getBestand();
        
        assertEquals(erwarteterBestand, tatsaechlicherBestand);
    }
    
    @Test
    public void testBucheZugang_mit_Negativ1_erwartet_IllegalAE(){
        Lager lager = new Lager();
        lager.legeAnArtikel(artikelBestand3);
        
        assertThrows(IllegalArgumentException.class, () -> {
           lager.bucheZugang(1001, -1); 
        });
        
    }
    
    @Test
    public void testBucheZugang_mit_falscherArtikelNr_erwartet_IllegalAE(){
        Lager lager = new Lager();
        lager.legeAnArtikel(artikelBestand3);
        
        assertThrows(IllegalArgumentException.class, () -> {
           lager.bucheZugang(4589, 3); 
        });
        
    }
    
    @Test
    public void testAenderePreisEinesArtikels_mit_100_erwartet_doppelterPreis(){
        Lager lager = new Lager();
        lager.legeAnArtikel(artikelPreis10);
        final double erwarteterPreis = 20.0;
        
        lager.aenderePreisEinesArtikels(1002,100.0);
        final double tatsaechlicherPreis = lager.getArtikel(0).getPreis();
        
        assertEquals(erwarteterPreis, tatsaechlicherPreis);
    }
    
    @Test
    public void testAenderePreisEinesArtikels_mit_Negativ50_erwartet_halberPreis(){
        Lager lager = new Lager();
        lager.legeAnArtikel(artikelPreis10);
        final double erwarteterPreis = 5.0;
        
        lager.aenderePreisEinesArtikels(1002,-50.0);
        final double tatsaechlicherPreis = lager.getArtikel(0).getPreis();
        
        assertEquals(erwarteterPreis, tatsaechlicherPreis);
    }
    
    @Test
    public void testAenderePreisEinesArtikels_mit_0_erwartet_gleicherPreis(){
        Lager lager = new Lager();
        lager.legeAnArtikel(artikelPreis10);
        final double erwarteterPreis = 10.0;
        
        lager.aenderePreisEinesArtikels(1002,0.0);
        final double tatsaechlicherPreis = lager.getArtikel(0).getPreis();
        
        assertEquals(erwarteterPreis, tatsaechlicherPreis);
    }
    
    @Test
    public void testAenderePreisEinesArtikel_mit_Negativ200_erwartet_IllegalAE(){
        Lager lager = new Lager();
        lager.legeAnArtikel(artikelPreis10);
        
        assertThrows(IllegalArgumentException.class, () -> {
            lager.aenderePreisEinesArtikels(1002,-200.0);
        });
    }
}
