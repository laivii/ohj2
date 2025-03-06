package fxAllergiainfo;

import java.net.URL;
import java.io.PrintStream;
import java.util.ResourceBundle;

import allergiainfo.Allergiainfo;
import allergiainfo.SailoException;
import allergiainfo.Tuote;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
//import javafx.scene.control.ComboBox; //TODO add combobox and it's arguments
import javafx.scene.control.TextField;
import javafx.scene.text.Font;



/**
 * @author Viivi
 * @version 6.3.2025
 */
public class AllergiainfoGUIController implements Initializable {
    
    @FXML private TextField searchField;
    @FXML private ScrollPane panelTuotteet;
    //@FXML private ComboBox<?> searchTermCB; //TODO add arguments to CB
  	
    
	@FXML
	private void avaaTuotteidenhallinta() {
	    ModalController.showModal(AllergiainfoGUIController.class.getResource("ProductManagementGUIView.fxml"), "Tuotteiden hallinta", null, "");
	}
	
	
	@FXML
	private void lisaaUusiTuote() {
	    lisaaTuote();
	}
	
	
	@FXML
	private void poistaViimeinenTuote() {
	    poistaViimeinen();
	}
	
	@Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }
    
    
//============================================================================================================
// Tästä eteenpäin ei käyttöliittymään suoraan liittyvää koodia
	
	private Allergiainfo allergiainfo;
	private TextArea areaTuotteet = new TextArea();
	
	
	/**
	 * Asetetaan käytettävä allergiainfo
	 * @param allergiainfo jota käytetään tässä käyttöliittymässä
	 */
	public void  setAllergiainfo(Allergiainfo allergiainfo) {
	    this.allergiainfo = allergiainfo;
	}
	
	
	/**
	 * Alustetaan tekstialue
	 */
	private void alusta() {
	    panelTuotteet.setContent(areaTuotteet);
	    areaTuotteet.setFont(new Font("Courier New", 12));
	    areaTuotteet.setEditable(false);
	    panelTuotteet.setFitToHeight(true);
	}
	
	
	/**
	 * Luodaan uusi alustettu tuote
	 */
	private void lisaaTuote() {	    
	    Tuote tuote = new Tuote();
	    tuote.rekisteroi(); //TODO muuta kun muokataan toimivaksi --> rekisteröinti vasta myöhemmin
	    tuote.taytaTuoteTiedoilla();
	    
	    try {
            allergiainfo.lisaaTuote(tuote);
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
            return;
        }
	    
	    areaTuotteet.clear();
	    tulostaTuotteet(areaTuotteet);
	}
	
	
	/**
	 * Poistetaan tuotelistan ensimmäinen alkio
	 */
	private void poistaViimeinen() {
	    if( allergiainfo.haeTuotteita() <= 0 ) return;
	    
	    allergiainfo.poistaViimeinenTuote();
	        
	    areaTuotteet.clear();
	    tulostaTuotteet(areaTuotteet);
	}
	
	
	/**
	 * Tulostetaan tuotteen tiedot
	 * @param os tietovirta johon tulostetaan
	 * @param tuote joka halutaan tulostaa
	 */
	private void tulostaTuote( PrintStream os, final Tuote tuote ) {
	    os.println("------------------------------");
	    tuote.tulosta(os);
	    os.println("------------------------------");
	}
	
	
	/**
	 * Tulostaa listassa olevat tuotteet tekstialueeseen
	 * @param text alue johon tulostetaan
	 */
	private void tulostaTuotteet(TextArea text) {	    
	    try( PrintStream os = TextAreaOutputStream.getTextPrintStream(text)) {
	        for( int i = 0; i < allergiainfo.haeTuotteita(); i++ ) {
	            Tuote tuote = allergiainfo.annaTuote( i );
	            tulostaTuote(os, tuote);
	        }
	    }
	}
}
