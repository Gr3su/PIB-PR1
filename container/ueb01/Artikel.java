package ueb01

/**
 * 
 * @author Tim Mueller / Yannick Gross
 * @version 11.11.2022 / 17:28
 */
public class Artikel{
   //Attribute für die Klasse Artikel
   private int artikelNr;
   private String art;
   private int bestand;

   /**
    * Konstruktor für die Klasse Artikel mit allen Attributen.
    * 
    * @param artikelNr Artikelnummer des Artikels.
    * @param art Art des Artikels.
    * @param bestand Aktueller Bestand / Anzahl des Artikels.
    * 
    * @throws IllegalArgumentException Wenn artikelNr kleiner als 1 ist.
    * @throws IllegalArgumentException Wenn art nicht eingegeben wurde.
    * @throws IllegalArgumentException Wenn bestand kleiner als 0 ist.
    */
   public Artikel(int artikelNr, String art, int bestand) {
       
       if(artikelNr < 0){
           throw new IllegalArgumentException("Die Artikelnummer darf nicht kleiner als 1 sein");
       }
       
       if(String.valueOf(artikelNr).length() != 4){
           throw new IllegalArgumentException("Die Artikelnummer muss vierstellig sein");
        }
       
       if(art.isBlank()){
           throw new IllegalArgumentException("Die Artikelart muss eine Zeichenkette sein");
       }
        
       if(bestand < 0){
           throw new IllegalArgumentException("Der bestand darf nicht auf einem Wert unter 0 liegen");
       }
       
       this.artikelNr = artikelNr;
       this.art = art;
       this.bestand = bestand;
   }
   
   /**
    * Konstruktor für die Klasse Artikel mit den Attributen artikelNr und art.
    * 
    * @param artikelNr Artikelnummer des Artikels.
    * @param art Art des Artikels
    */
   public Artikel(int artikelNr, String art){
       this(artikelNr, art, 0);
   }
   
   /* Kein Standardkonstruktor wie Artikel(), da ein Artikel
    * ohne Artikelnummer oder Art Sinn macht. Wenn z.B. die Artikelnummer
    * auf "null" oder 000 automatisch gesetzt wird, gibt es mehrere
    * Artikel mit der selben Artikelnummer => Mehrdeutigkeit.
    */
   
   /**
    * Erhöht den Bestand um die übergebene Menge.
    * 
    * @param menge Betrag um den Bestand erhöht wird.
    * 
    * @throws IllegalArgumentException Wenn Buchung eine negative Zahl ist.
    */
   public void bucheZugang(int menge){
       if(menge < 0){
           throw new IllegalArgumentException("Die Zubuchung darf keine negative Zahl sein");
       }   
       bestand += menge; 
   }

   /**
    * Verringert den Bestand um die übergebene Menge.
    * 
    * @param menge Betrag um den der Bestand verringert wird.
    * 
    * @throws IllegalArgumentException Wenn menge eine negative Zahl ist.
    * @throws IllegalArgumentException Wenn bestand unter 0 fallen sollte.
    */
   public void bucheAbgang(int menge){
       if(menge < 0){
           throw new IllegalArgumentException("Die Abbuchung darf keine negative Zahl sein");
       }
       
       if(bestand - menge < 0){
           throw new IllegalArgumentException("Der bestand darf nach der Abbuchen nicht kleiner sein als 0"); 
       }
       bestand -= menge;
   }

   /**
    * Gibt die Artikelnummer des Artikels zurueck.
    * 
    * @return Artikelnummer des Artikels.
    */
   public int getArtikelNr(){
       return artikelNr;
   }

   /**
    * Gibt die Art des Artikels zurueck.
    * 
    * @return Artikelart.
    */
   public String getArt(){
       return art;
   }

   /**
    * Gibt den Bestand des Artikels zurueck.
    * 
    * @return Bestan des Artikels.
    */
   public int getBestand(){
       return bestand;
   }

   /**
    * Überschreibt den Bestand des Artikels mit dem übergebenem Wert.
    * 
    * @param bestand Neuer Bestand des Artikels.
    * 
    * @throws IllegalArgumentException Wenn bestand kleiner als 0 gesetzt wird.
    */
   public void setBestand(int bestand){
       if(bestand < 0){
           throw new IllegalArgumentException("Der Bestand darf nicht unter den Wert 0 liegen");
       }
       this.bestand = bestand;
   }

   /**
    * Bereitet eine Zeichenkette auf, die alle Attribute des Artikels beinhaltet.
    * 
    * @return Zeichenkette mit den Objekt-Attributen, getrennt durch Semikolon.
    */
   public String toString(){
       return("Artikelnummer: " + artikelNr + "; Artikelart " + art + "; Bestand "
       + bestand);
   }
   }
