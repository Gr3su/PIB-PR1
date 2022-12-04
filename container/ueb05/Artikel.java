package container.ueb05;
/**
 * 
 * @author Tim Mueller / Yannick Gross
 * @version 03.12.2022 / 21:10
 */

public class Artikel{
   //Fehlermeldungen
   private static final String ERROR_ARTIKELNUMMER_VIERSTELLIG =    "Die Artikelnummer muss vierstellig und positiv sein";
   private static final String ERROR_ARTIKELART_ZEICHENKETTE =      "Die Artikelart muss eine Zeichenkette sein";
   private static final String ERROR_BESTAND_POSITIV =              "Der Bestand muss positiv sein";
   private static final String ERROR_BUCHUNG_POSITIV =              "Die Buchungsmenge muss positiv sein";
   private static final String ERROR_PREIS_POSITIV =                "Der Preis muss positiv sein";
   private static final int ARTIKELNR_OBERGRENZE =                  9999;
   private static final int ARTIKELNR_UNTERGRENZE =                 999;
   
   //Attribute fuer die Klasse Artikel
   private String art;
   private int artikelNr;
   private int bestand;
   private double preis;
   /**
    * Konstruktor fuer die Klasse Artikel mit allen Attributen.
    * 
    * @param artikelNr Artikelnummer des Artikels.
    * @param art Art des Artikels.
    * @param bestand Aktueller Bestand / Anzahl des Artikels.
    * @param preis Preis des Artikels
    * 
    * @throws IllegalArgumentException Wenn artikelNr kleiner als 1 ist.
    * @throws IllegalArgumentException Wenn art nicht eingegeben wurde.
    * @throws IllegalArgumentException Wenn bestand kleiner als 0 ist.
    */
   public Artikel(int artikelNr, String art, int bestand, double preis) {
       if(artikelNr > ARTIKELNR_OBERGRENZE || artikelNr <= ARTIKELNR_UNTERGRENZE || artikelNr < 0){
           throw new IllegalArgumentException(ERROR_ARTIKELNUMMER_VIERSTELLIG);
        }
       
       if(art.isBlank() || art == null){
           throw new IllegalArgumentException(ERROR_ARTIKELART_ZEICHENKETTE);
       }
       
       this.artikelNr = artikelNr;
       setArt(art);
       setBestand(bestand);
       setPreis(preis);
   }
   
   /**
    * Konstruktor fuer die Klasse Artikel mit den Attributen artikelNr, art und Preis.
    * 
    * @param artikelNr Artikelnummer des Artikels.
    * @param art Art des Artikels.
    * @param preis Preis des Artikels.
    */
   public Artikel(int artikelNr, String art, double preis){
       this(artikelNr, art, 0, preis);
   }
   
   /**
    * Konstruktor fuer die Klasse Artikel mit den Attributen artikelNr und art.
    * 
    * @param artikelNr Artikelnummer des Artikels.
    * @param art Art des Artikels
    */
   public Artikel(int artikelNr, String art){
       this(artikelNr, art, 0, 0.0);
   }
   
   /* Kein Standardkonstruktor wie Artikel(), da ein Artikel
    * ohne Artikelnummer oder Art Sinn macht. Wenn z.B. die Artikelnummer
    * auf "null" oder 000 automatisch gesetzt wird, gibt es mehrere
    * Artikel mit der selben Artikelnummer => Mehrdeutigkeit.
    */
   
   /**
    * Erhoeht den Bestand um die Uebergebene Menge.
    * 
    * @param menge Betrag um den Bestand erhoeht wird.
    * 
    * @throws IllegalArgumentException Wenn Buchung eine negative Zahl ist.
    */
   public void bucheZugang(int menge){
       if(menge < 0){
           throw new IllegalArgumentException(ERROR_BUCHUNG_POSITIV);
       }   
       bestand += menge; 
   }

   /**
    * Verringert den Bestand um die uebergebene Menge.
    * 
    * @param menge Betrag um den der Bestand verringert wird.
    * 
    * @throws IllegalArgumentException Wenn menge eine negative Zahl ist.
    * @throws IllegalArgumentException Wenn bestand unter 0 fallen sollte.
    */
   public void bucheAbgang(int menge){
       if(menge < 0){
           throw new IllegalArgumentException(ERROR_BUCHUNG_POSITIV);
       }
       
       if(bestand - menge < 0){
           throw new IllegalArgumentException(ERROR_BESTAND_POSITIV); 
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
    * @return Bestand des Artikels.
    */
   public int getBestand(){
       return bestand;
   }
   
   /**
    * Gibt den Preis des Artikels zurueck.
    * 
    * @return Preis des Artikels.
    */
   public double getPreis(){
       return preis;
   }

   /**
    * Ueberschreibt den Bestand des Artikels mit dem uebergebenen Wert.
    * 
    * @param bestand Neuer Bestand des Artikels.
    * 
    * @throws IllegalArgumentException Wenn bestand kleiner als 0 gesetzt wird.
    */
   public void setBestand(int bestand){
       if(bestand < 0){
           throw new IllegalArgumentException(ERROR_BESTAND_POSITIV);
       }
       this.bestand = bestand;
   }
   
   /**
    * Ueberschreibt die Art des Artikels mit dem uebergebenem Wert.
    * 
    * @param art Neue Art des Artikels.
    * 
    * @throws IllegalArgumentException Wenn art keine Zeichenkette.
    */
   public void setArt(String art){
       if(art.trim().isEmpty() == true || art == null){
           throw new IllegalArgumentException(ERROR_ARTIKELART_ZEICHENKETTE); 
       }
       
       this.art = art;     
   }
   
   /**
    * Ueberschreibt des Preis des Artikels.
    * 
    * @praram preis Neuer Preis des Artikels.
    * 
    * @throws IllegalArgumentException Wenn Preis negativ ist.
    */
   public void setPreis(double preis){
       if(preis < 0){
           throw new IllegalArgumentException(ERROR_PREIS_POSITIV);
       }
       this.preis = preis;
   }
   
   /**
    * Bereitet eine Zeichenkette auf, die alle Attribute des Artikels beinhaltet.
    * 
    * @return Zeichenkette mit den Objekt-Attributen, getrennt durch Semikolon.
    */
   @Override
   public String toString(){
       return("Artikelnummer: " + artikelNr + "; Artikelart: " + art + "; Preis: " + preis +
       "; Bestand: " + bestand);
   }
   }
