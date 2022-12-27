package container.ueb07;

import java.util.Scanner;

/**
 * Beschreiben Sie hier die Klasse LinkFilter.
 * 
 * @author Tim Muelle / Yannick Gross
 * @version 16.12.2022 / 13:30Uhr
 */
public class LinkFilter{
    
    /**
     * Privater Konstruktor, wegen Werkzeugmethode die keine Objekte benoetigt.
     */
    private LinkFilter(){}
    
    /**
     * Link-Filter Methode
     * Liesst HTML-Code ein und sucht nach Hypertext Links.
     * 
     * @return Liste mit folgendem Format -> Hypertext: Link, Zeichenanzahl von Link | Letzte Zeile
     * beinhaltet Anzahl der Links und Anzahl der gescannten Zeilen
     */
    public static String linkFilter(){
        final   String          LINK_INDIKATOR  = "<a";
        final   Scanner         scanner         = new Scanner(System.in);
        final   StringBuilder   ausgabe         = new StringBuilder();
        final   char            LINK_START_ENDE = '"';
        final   char            NAME_START      = '>';
        final   char            NAME_ENDE       = '<';

                int             filterStart     = -1;
                int             filterEnde      = -1;
                String          link            = "";
                String          hypertext       = "";
                String          aktuelleZeile   = "";
        
        while(scanner.hasNextLine()){
            
            aktuelleZeile = scanner.nextLine();
            filterStart = aktuelleZeile.indexOf(LINK_INDIKATOR);
            
            if(filterStart != -1){
                
                /* Von a-Tag Anfang zu Link Anfang springen.
                 * Dann Link Ende finden.
                 */
                filterStart = aktuelleZeile.indexOf(LINK_START_ENDE, filterStart) + 1;
                filterEnde = aktuelleZeile.indexOf(LINK_START_ENDE, filterStart);

                link = cutContent(filterStart, filterEnde, aktuelleZeile);
                
                /* Von Link Ende zu Hypertext Anfang springen.
                 * Dann Hypertext Ende finden.
                 */
                filterStart = aktuelleZeile.indexOf(NAME_START, filterEnde) + 1;
                filterEnde = aktuelleZeile.indexOf(NAME_ENDE, filterStart);

                hypertext = cutContent(filterStart, filterEnde, aktuelleZeile);
                
                String tmp = String.format("%-30s%-40s%5d Zeichen", hypertext, link, link.length());

                ausgabe.append(tmp + "\n");
                
            }
        }
        
        return ausgabe.toString();
    }
    
    /**
     * Aus einzeiligem String wird ein Teil rausgeschnitten, fuehrende und folgende Leerezeichen
     * werden geloescht.
     * 
     * @param anfang Anfang des rauszuschneidenden Strings.
     * @param ende Ende + 1 Stelle des rauszuschneidenden Strings.
     * @param zeile String aus dem ausgeschnitten wird.
     * 
     * @return Gewuenschter Substring.
     */
    public static String cutContent(int anfang, int ende, String zeile){
        zeile = zeile.substring(anfang, ende);
        zeile = zeile.strip();
        return zeile;
    }
    
    public static void main(String [] args){
        System.out.println(linkFilter());
    }
}
