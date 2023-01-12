package container.ueb08;


/**
 * Mittelwert Klasse um drei double Werte zu speichern und ausgeben zu koennen.
 * 
 * @author Yannick Gross / Tim Mueller
 * @version 29.12.2022 / 15:00Uhr
 */
public final class Mittelwert{
    //Attribute
    private     double  mittelwert;
    private     double  nahesterWert;
    private     double  entferntesterWert;
    
    /**
     * Konstruktor um 3 double Werte zu speichern, extra fuer ArrayFunctions.
     * 
     */
    public Mittelwert(double mittelwert, double nahesterWert, double entferntesterWert){
        this.mittelwert = mittelwert;
        this.nahesterWert = nahesterWert;
        this.entferntesterWert = entferntesterWert;
    }
    
    private Mittelwert(){}
    
    public final double getMittelwert(){
        return mittelwert;    
    }

    public final double getNahesterWert(){
        return nahesterWert;
    }
    
    public final double getEntferntesterWert(){
        return entferntesterWert;
    }
    
    @Override
    public final String toString(){
        return String.format("%f, %f, %f", mittelwert, nahesterWert, entferntesterWert);
    }
}
    
