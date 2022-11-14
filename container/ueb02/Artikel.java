
/**
 * 
 * @author Tim Mueller / Yannick Gross
 * @version 12.11.2022 / 23:14
 */
public class Artikel{
   //Fehlermeldungen
   private static final String ARTIKELNUMMER = "Die Artikelnummer muss vierstellig und positiv sein";
   private static final String ARTIKELART = "Die Artikelart muss eine Zeichenkette sein";
   private static final String BESTAND = "Der Bestand darf nicht negativ sein";
   private static final String BUCHUNG = "Die Buchungsmenge darf nicht negativ sein";
   
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
           throw new IllegalArgumentException(ARTIKELNUMMER);
       }
       
       if(artikelNr > 9999){
           throw new IllegalArgumentException(ARTIKELNUMMER);
        }
       
       if(art.isBlank() || art == null){
           throw new IllegalArgumentException(ARTIKELART);
       }
        
       if(bestand < 0){
           throw new IllegalArgumentException(BESTAND);
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
           throw new IllegalArgumentException(BUCHUNG);
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
           throw new IllegalArgumentException(BUCHUNG);
       }
       
       if(bestand - menge < 0){
           throw new IllegalArgumentException(BESTAND); 
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
    * Überschreibt den Bestand des Artikels mit dem übergebenem Wert.
    * 
    * @param bestand Neuer Bestand des Artikels.
    * 
    * @throws IllegalArgumentException Wenn bestand kleiner als 0 gesetzt wird.
    */
   public void setBestand(int bestand){
       if(bestand < 0){
           throw new IllegalArgumentException(BESTAND);
       }
       this.bestand = bestand;
   }
   
   /**
    * Überschreibt die Art des Artikels mit dem übergebenem Wert.
    * 
    * @param art Neue Art des Artikels.
    * 
    * @throws IllegalArgumentException Wenn art keine Zeichenkette.
    */
   public void setArt(String art){
       if(art.trim().isEmpty() == true || art == null){
           throw new IllegalArgumentException(ARTIKELART); 
       }
       
       this.art = art;     
   }
   
   /**
    * Damit Artikelnummern kleiner als 1000 als int gespeichert werden können
    * und trotzdem als vierstellige Zahl ausgegeben werden, bereitet diese Methode
    * einen String auf. Artikelnummern mit weniger als vier Stellen, werden dann mit
    * fuehrenden 0 aufgefuellt.
    * 
    * @return Aufbereitete Artikelnummer.
    */
   public String artikelNrAusgabe(){
      String artikelNrAusgabe = String.valueOf(artikelNr);
      
      while(artikelNrAusgabe.length() < 4) {
          artikelNrAusgabe = ("0" + artikelNrAusgabe);  
      } 
      
      return artikelNrAusgabe; 
    }
   
   /**
    * Bereitet eine Zeichenkette auf, die alle Attribute des Artikels beinhaltet.
    * 
    * @return Zeichenkette mit den Objekt-Attributen, getrennt durch Semikolon.
    */
   public String toString(){
       return("Artikelnummer: " + artikelNrAusgabe() + "; Artikelart " + art + "; Bestand "
       + bestand);
   }
   }
