
/**
 * Abarbeitung der auftretenden Fehler.
 * 
 * @author Tim Mueller / Yannick Gross
 * @version 16.01.2023 / 20:00
 */
public final class FehlerPruefung{
    private static final int ARTIKELNUMMER_OBERGRENZE = 9999;
    private static final int ARTIKELNUMMER_UNTERGRENZE = 1000;
    private static final int ARTIKEL_BESTAND_POSITIV = 0;
    private static final int VIDEO_ERSCHEINUNGSJAHR_OBERGRENZE = 2022;
    private static final int VIDEO_ERSCHEINUNGSJAHR_UNTERGRENZE = 1900;
    private static final int ANZAHL_MINDESTENS_1 = 1;
    private static final int OPTION_OBERGRENZE_2 = 2;
    private static final int OPTION_OBERGRENZE_3 = 3;
    private static final int OPTION_OBERGRENZE_4 = 4;
    private static final int OPTION_OBERGRENZE_12 = 12;
    
    private FehlerPruefung(){};
    
    /**
     * Kann einen Integer pruefen, ob er bei der jeweiligen Error enum
     * die gewuenschte Bedingung gegeben ist.
     * 
     * @param error Instanz von ErrorMessages enum.
     * @param pruefwert Zu pruefender Wert
     * 
     * @throws IllegalArgumentException Wenn die geforderte Bedingung gegeben ist.
     */
    public static final void fehlerPruefung(ErrorMessages error, int pruefwert){
        switch(error){
            case ERROR_VIDEO_SPIELDAUER_KLEINER_EINS:
            case ERROR_CD_ANZAHLTITEL_KLEINER_EINS:
            case ERROR_LAGER_LAGERGROESSE_MUSS_GROESSER:
                if(pruefwert < ANZAHL_MINDESTENS_1){
                    werfeIA(error.getMessage());
                }
                break;
            
            case ERROR_ARTIKEL_BESTAND_POSITIV:
            case ERROR_ARTIKEL_BUCHUNG_POSITIV:
                if(pruefwert < ARTIKEL_BESTAND_POSITIV){
                    werfeIA(error.getMessage());  
                }
                break;
                
            case ERROR_ARTIKEL_ARTIKELNUMMER_VIERSTELLIG:
                if( pruefwert > ARTIKELNUMMER_OBERGRENZE ||
                    pruefwert < ARTIKELNUMMER_UNTERGRENZE){
                    werfeIA(error.getMessage());
                }
                break;
                
            case ERROR_VIDEO_ERSCHEINUNGSJAHR_NICHT_MOEGLICH:
                if( pruefwert < VIDEO_ERSCHEINUNGSJAHR_UNTERGRENZE ||
                    pruefwert > VIDEO_ERSCHEINUNGSJAHR_OBERGRENZE){
                    werfeIA(error.getMessage());
                }
                break;
                
            case ERROR_LAGERDIALOG_OPTION_NUMMER_BIS_2:
                if( pruefwert < ANZAHL_MINDESTENS_1 ||
                    pruefwert > OPTION_OBERGRENZE_2){
                    werfeIA(error.getMessage());
                }
                break;
            
            case ERROR_LAGERDIALOG_OPTION_NUMMER_BIS_3:
                if( pruefwert < ANZAHL_MINDESTENS_1 ||
                    pruefwert > OPTION_OBERGRENZE_3){
                    werfeIA(error.getMessage());
                }
                break;
                
            case ERROR_LAGERDIALOG_OPTION_NUMMER_BIS_4:
                if( pruefwert < ANZAHL_MINDESTENS_1 ||
                    pruefwert > OPTION_OBERGRENZE_4){
                    werfeIA(error.getMessage());
                }
                break;
            
            case ERROR_LAGERDIALOG_OPTION_NUMMER_BIS_11:
                if( pruefwert < ANZAHL_MINDESTENS_1 ||
                    pruefwert > OPTION_OBERGRENZE_12){
                    werfeIA(error.getMessage());
                }
                break;
        }   
    }
    
    /**
     * Kann zwei Integer pruefen, ob sie bei der jeweiligen Error enum
     * die gewuenschte Bedingung erfuellen.
     * 
     * @param error Instanz von ErrorMessages enum.
     * @param pruefwert1 Zu pruefender Wert.
     * @param pruefwert2 Vergleichswert.
     * 
     * @throws IllegalArgumentException Wenn die geforderte Bedingung gegeben ist.
     */
    public static final void fehlerPruefung(ErrorMessages error, int pruefwert1, int pruefwert2){
        switch(error){
            case ERROR_LAGER_LAGER_VOLL:
                if(pruefwert1 == pruefwert2){
                    werfeIA(error.getMessage());
                }
                break;
            
            case ERROR_LAGER_INDEX_FALSCH:
                if(pruefwert1 < 0 || pruefwert1 > pruefwert2 - 1){
                    werfeIA(error.getMessage());
                }
                break;
        }
    }
    
    /**
     * Kann einen Double pruefen, ob er bei der jeweiligen Error enum
     * die gewuenschte Bedingung erfuellt.
     * 
     * @param error Instanz von ErrorMessages enum.
     * @param pruefwert Zu pruefender Wert.
     * 
     * @throws IllegalArgumentException Wenn die geforderte Bedingung gegeben ist.
     */
    public static final void fehlerPruefung(ErrorMessages error, double pruefwert){
        switch(error){
            case ERROR_ARTIKEL_PROZENT_UNTER_MINUS_100:
                if(pruefwert < -100.0){
                    werfeIA(error.getMessage());
                }
                break;
                
            case ERROR_ARTIKEL_PREIS_POSITIV:
                if(pruefwert < 0.0){
                    werfeIA(error.getMessage());
                }
                break;
    
        }
    }
    
    /**
     * Kann einen String pruefen, ob er bei der jeweiligen Error enum
     * die gewuenschte Bedingung erfuellt, nicht leer zu sein.
     * 
     * @param error Instanz von ErrorMessages enum.
     * @param pruefwert Zu pruefender Wert.
     * 
     * @throws IllegalArgumentException Wenn die geforderte Bedingung gegeben ist.
     */
    public static final void fehlerPruefung(ErrorMessages error, String pruefwert){
        switch(error){
            case ERROR_BUCH_TITEL_LEER:
            case ERROR_BUCH_AUTOR_LEER:
            case ERROR_BUCH_VERLAG_LEER:
            case ERROR_CD_TITEL_LEER:
            case ERROR_CD_INTERPRET_LEER:
            case ERROR_VIDEO_TITEL_LEER:
            case ERROR_ARTIKEL_ARTIKELART_ZEICHENKETTE:
                if(pruefwert == null || pruefwert.isBlank()){
                    werfeIA(error.getMessage());
                }
                break;
        }
    }
    
    /**
     * Kann einen Boolean pruefen, ob er bei der jeweiligen Error enum
     * die gewuenschte Bedingung erfuellt, true zu sein.
     * 
     * @param error Instanz von ErrorMessages enum.
     * @param pruefwert Zu pruefender Wert
     * 
     * @throws IllegalArgumentException Wenn die geforderte Bedingung gegeben ist.
     */
    public static final void fehlerPruefung(ErrorMessages error, boolean pruefwert){
        switch(error){
            case ERROR_LAGER_ARTIKEL_EXISTIERT:
                if(pruefwert == true){
                    werfeIA(error.getMessage());
                }
                break;
        }
    }
    
    /**
     * Kann einen Artikel pruefen, ob er bei der jeweiligen Error enum
     * die gewuenschte Bedingung erfuellt, gleich null zu sein.
     * 
     * @param error Instanz von ErrorMessages enum.
     * @param pruefwert Zu pruefender Wert
     * 
     * @throws IllegalArgumentException Wenn die geforderte Bedingung gegeben ist.
     */
    public static final void fehlerPruefung(ErrorMessages error, Artikel pruefwert){
        switch(error){
            case ERROR_LAGER_KEIN_OBJEKT:
                if(pruefwert == null){
                    werfeIA(error.getMessage());
                }
        }
    }
    
    /**
     * Lediglich da um Schreibarbeit zu sparen,
     * wirft eine IAException mit der uebergebenen message.
     * 
     * @param message Error Nachricht.
     */
    private static final void werfeIA(String message){
        throw new IllegalArgumentException(message);
    }
}
