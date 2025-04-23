package fxAllergiainfo;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import allergiainfo.Allergiainfo;
import allergiainfo.Ravintola;
import allergiainfo.SailoException;
import allergiainfo.Tuote;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * @author Viivi
 * @version 23.4.2025
 */
public class ProductGUIController implements ModalControllerInterface<Allergiainfo>, Initializable{
    
    @FXML private Button cancelBtn;
    @FXML private Button AddNewBtn;
    @FXML private TextField textProductName;
    @FXML private ComboBox<Ravintola> restaurantCB;
    
    @FXML private CheckBox gluteeni;
    @FXML private CheckBox maitotuotteet;
    @FXML private CheckBox laktoosi;
    @FXML private CheckBox kananmuna;
    @FXML private CheckBox soija;
    @FXML private CheckBox seesami;
    @FXML private CheckBox sinappi;
    @FXML private CheckBox selleri;
    @FXML private CheckBox kala;
    @FXML private CheckBox ayriaiset;
    @FXML private CheckBox pahkinat;
    @FXML private CheckBox maapahkinat;
    @FXML private CheckBox nilviaiset;
    @FXML private CheckBox lupiini;
    @FXML private CheckBox sulfiitit;
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    //  Asetetaan allergeeni checkboxit listaan kun sovellus avataan
        allergeeniCheckBoxes = Arrays.asList(
                gluteeni, maitotuotteet, laktoosi, kananmuna, soija,
                seesami, sinappi, selleri, kala, ayriaiset, pahkinat,
                maapahkinat, nilviaiset, lupiini, sulfiitit
        );
    }
    
    
    @Override
    public Allergiainfo getResult() {
        return palautus;
    }


    @Override
    public void handleShown() {
        // TODO Auto-generated method stub 
    }


    @Override
    public void setDefault(Allergiainfo arg0) {
        this.allergiainfo = arg0;
        
        for( int i = 0; i < this.allergiainfo.haeRavintoloita(); i++ ) {
            Ravintola ravintola = this.allergiainfo.annaRavintola( i );
            this.ravintolat.add( ravintola );
        }
        
        restaurantCB.setItems( FXCollections.observableList( this.ravintolat ));
    }
    
    
    @FXML
    private void cancelAddingNewProduct() {
        peruuta();
    }
    
    
    @FXML
	private void addNewProduct() {
        lisaaUusi();
    }


//---------------------------------------------------------------------------------------------------------------------------------------------
// Tästä eteenpäin ei käyttöliittymään suoraan liittyvää koodia
    
    private Allergiainfo palautus = null;
    
    private Allergiainfo    allergiainfo;
    private List<CheckBox>  allergeeniCheckBoxes;
    private List<Ravintola> ravintolat = new ArrayList<>();
    
    
    /**
     * Perutaan uuden lisäys
     * Sulkee modaalin
     */
    private void peruuta() {
        ModalController.closeStage( cancelBtn );
    }
    
    
    /**
     * Lisätään tuote tuotteistoon
     * Lisätään tuotteelle valitut allergeenit
     */
    private void lisaaUusi() { 
    //  Alustetaan laatikoiden tyylit
        textProductName.setStyle("");
        restaurantCB.setStyle("");
        
    //  Haetaan luotavan tuotteen tiedot
        String nimi = textProductName.getText().trim();
        Ravintola ravintola = restaurantCB.getValue();
        
        
    //  Tarkistetaan, että tuotteella on nimi ja sille on valittu ravintola
        boolean hasError = false;
        String virheViesti = "";
        
        if( nimi.isEmpty() ) {
            textProductName.setStyle("-fx-border-color: red;");
            virheViesti += "Tuotteelta puuttuu nimi!\n";
            hasError = true;
        }
        
        if( restaurantCB.getValue() == null ) {
            restaurantCB.setStyle("-fx-border-color: red;");
            virheViesti += "Valitse ravintola!";
            hasError = true;
        }

        if( hasError ) {
            Dialogs.showMessageDialog("Virheitä lomakkeella:\n" + virheViesti, dlg -> {
                dlg.getDialogPane().setPrefWidth(250);
                dlg.getDialogPane().setPrefHeight(150);
            });
            
            return;
        }
        
    //  Jos virheitä ei löydy jatketaan eteenpäin
        int ravintolaNro = ravintola.haeId();
        List<Integer> allergeenit = allergeeniCheckBoxes.stream()
                                    .filter( CheckBox::isSelected )
                                    .map( cb -> Integer.parseInt( cb.getUserData().toString()))
                                    .toList();
        
        
    //  Luodaan tuote objekti
        Tuote tuote = new Tuote();
        
        tuote.asetaNimi( nimi );
        tuote.asetaRavintola( ravintolaNro );
        tuote.rekisteroi();
        
    //  Lisätään tuote taulukkoon
        int tuoteNro = tuote.haeId();
        String virhe = "";
        
        try {
            this.allergiainfo.lisaa( tuote );
        } catch (SailoException e) {
            virhe = e.getMessage();
            Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + virhe );
            return;
        }
        
    //  Lisätään TuoteAllergeenit     
        if( virhe == "" ) {
            for( int allergeeni : allergeenit ) {
                this.allergiainfo.lisaa( tuoteNro, allergeeni );
            }
        }
        
        try {
            allergiainfo.tallennaTuotteet();
            allergiainfo.tallennaTuoteAllergeenit();
        } catch (SailoException e) {
           System.err.println( e.getMessage() );
        }
        
        palautus = this.allergiainfo;
        ModalController.closeStage( AddNewBtn );
    }
}
