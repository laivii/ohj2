package allergiainfo;

/**
 * Poikkeusluokka tietorakenteesta aiheutuville poikkeuksille
 * @author Viivi
 * @version 21.2.2025
 *
 */
public class SailoException extends Exception {
    private static final long serialVersionUID = 1L;
    
    /**
     * Poikkeukden muodostaja jolle tuodaan poikkeuksessa
     * käytettävä viesti
     * @param viesti Poikkeuksen viesti
     */
    public SailoException(String viesti) {
        super(viesti);
    }
}
