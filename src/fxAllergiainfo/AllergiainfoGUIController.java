package fxAllergiainfo;

import java.net.URL;
import java.io.PrintStream;
import java.util.List;
import java.util.ResourceBundle;

import allergiainfo.Allergeeni;
import allergiainfo.Allergiainfo;
import allergiainfo.Ravintola;
import allergiainfo.SailoException;
import allergiainfo.Tuote;
import allergiainfo.TuoteAllergeeni;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
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
 * @version 11.3.2025
 */
public class AllergiainfoGUIController implements Initializable {
    
    @FXML private TextField searchField;
    @FXML private ScrollPane panelTuote;
    @FXML private ListChooser<Tuote> chooserTuotteet;
    //@FXML private ComboBox<?> searchTermCB; //TODO add arguments to CB
  	
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }
    
    
	@FXML
	private void avaaTuotteidenhallinta() {
	    ModalController.showModal(AllergiainfoGUIController.class.getResource("ProductManagementGUIView.fxml"), "Tuotteiden hallinta", null, "");
	}
	
	
	@FXML
	private void lisaaUusiTuote() {
	    lisaaTuote();
	}
	
	
	@FXML
	private void lisaaAllergeeniValitulle() {
	    lisaaTuoteAllergeeni();
	}
    
    
//============================================================================================================
// Tästä eteenpäin ei käyttöliittymään suoraan liittyvää koodia


    private Allergiainfo allergiainfo;
	private TextArea areaTuote = new TextArea();
	private Tuote tuoteKohdalla;
	
	
	
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
	    panelTuote.setContent(areaTuote);
	    areaTuote.setFont(new Font("Courier New", 12));
	    areaTuote.setEditable(false);
	    panelTuote.setFitToHeight(true);
	    
	    chooserTuotteet.clear();
	    chooserTuotteet.addSelectionListener( e -> naytaTuote());
	}
	
	
	/**
	 * Näyttää listasta valitun tuotteen tiedot, tilapäisesti yhteen isoon edit-kenttään
	 */
	private void naytaTuote() {
	    tuoteKohdalla = chooserTuotteet.getSelectedObject();
	    
	    if( tuoteKohdalla == null ) return;
	    
	    areaTuote.setText("");
	    try( PrintStream os = TextAreaOutputStream.getTextPrintStream(areaTuote)){
	        tulosta(os, tuoteKohdalla);
	    }
	}
	
	/**
	 * Hakee tuotteiden tiedot listaan
	 * @param tuoteID haettavan tuotteen id
	 */
	private void hae( int tuoteID ) {
	    chooserTuotteet.clear();
	    
	    int index = 0;
	    for( int i = 0; i < this.allergiainfo.haeTuotteita(); i++ ) {
	        Tuote tuote = this.allergiainfo.annaTuote( i );
	        if( tuote.haeId() == tuoteID ) index = i;
	        chooserTuotteet.add( tuote.haeNimi(), tuote);
	    }
	    
	    chooserTuotteet.setSelectedIndex( index );
	}
	
	
	/**
	 * Luodaan uusi alustettu tuote
	 */
	private void lisaaTuote() {	    
	    Tuote tuote = new Tuote();
	    tuote.rekisteroi(); //TODO muuta kun muokataan toimivaksi --> rekisteröinti vasta myöhemmin
	    tuote.taytaTuoteTiedoilla();
	    
	    try {
            allergiainfo.lisaa(tuote);
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
            return;
        }
	    
	    hae(tuote.haeId());
	}
	

	/**
	 * Lisätään valitulle tuotteelle allergeeni
	 */
    private void lisaaTuoteAllergeeni() {
        /*
         * allergeeneja 15 eri, joten demossa voidaan valita random 1-15
         * TODO katso ettei allergeeni ole jo yhdistetty tuotteeseen
         */
        int allergeeniID = (int)(Math.random()* 15);
        int tuoteID = tuoteKohdalla.haeId();
        
        allergiainfo.lisaa(tuoteID, allergeeniID);
        
        naytaTuote();
    }
    
    
    /**
     * Tulostaa tuotteen tiedot (nimi ja allergeenit)
     * @param os tietovirta johon tulostetaan
     * @param tuote tulostettava tuote
     */
    public void tulosta(PrintStream os, final Tuote tuote) {
        Ravintola r = null;
        try {
            r = allergiainfo.haeRavintolaIdlla(tuote.haeRavintolaId());
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
 
        os.println("----------------------------------------------");
        
        tuote.tulosta(os);
        if( r != null)
            r.tulosta(os);
        
        os.println("----------------------------------------------");
        os.println("Allergeenit:\n");
        
        List<TuoteAllergeeni> tat = allergiainfo.haeTuotteenAllergeenit(tuote.haeId());   
        for (TuoteAllergeeni ta: tat) {
            ta.tulosta(os);
            Allergeeni a = null;
            try {
                a = allergiainfo.haeAllergeeniIdlla(ta.haeAllergeeniID());
            } catch (SailoException e) {
                System.err.println(e.getMessage());
            }
            
            if( a != null )
                a.tulosta(os);
                os.println();
        }
        os.println("----------------------------------------------");
   }

}
