package container.ueb07;
import java.util.Scanner;

/**
 * Beschreiben Sie hier die Klasse LinkFilter.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class LinkFilter{
    
    private LinkFilter(){}
    
    public static String linkFilter(){
        final   String          LINK_INDIKATOR = "href";
        final   Scanner         scanner         = new Scanner(System.in);
        
                int             filterStart     = -1;
                int             filterEnde      = -1;
                StringBuilder   ausgabe         = new StringBuilder();
                String          link            = "";
                String          hypertext       = "";
                String          aktuelleZeile   = "";
        
        while(scanner.hasNextLine()){
            
            aktuelleZeile = scanner.nextLine();
            filterStart = aktuelleZeile.indexOf(LINK_INDIKATOR);
            
            if(filterStart != -1){
                
                //Moegliche Leerzeichen zwischen "href" und Link-Start ueberspringen
                filterStart = aktuelleZeile.indexOf("\"", filterStart) + 1;
                
                filterEnde = aktuelleZeile.indexOf("\"", filterStart);
                link = aktuelleZeile.substring(filterStart, filterEnde).strip();
                
                //Moegliche Leerzeichen zwischen Link-Ende und Hypertext ueberspringen
                filterEnde = aktuelleZeile.indexOf("\"", filterStart);
                
                filterStart = aktuelleZeile.indexOf(">", filterEnde) + 1;
                filterEnde = aktuelleZeile.indexOf("<", filterStart);
                hypertext = aktuelleZeile.substring(filterStart, filterEnde).strip();
                
                ausgabe.append(hypertext + "\t\t\t" + link + "\t\t" + link.length());
                
            }
        }
        
        return ausgabe.toString();
    }
    
    public static void main(String [] args){
        System.out.println(LinkFilter.linkFilter());
    }
}
