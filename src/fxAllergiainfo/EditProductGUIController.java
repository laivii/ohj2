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
import allergiainfo.TuoteAllergeeni;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Viivi
 * @version 23.4.2025
 */

public class EditProductGUIController implements ModalControllerInterface<Allergiainfo>, Initializable {
    @FXML private Button cancelBtn;
    @FXML private Button saveBtn;
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
        
        Stage stage = (Stage) saveBtn.getScene().getWindow();
        stage.setOnCloseRequest(event -> {
            if ( onkoTallentamatta() ) {
                
                Boolean vastaus = ModalController.<Boolean>showModal(ProductGUIController.class.getResource("WarningUnsavedGUIView.fxml"), "Varoitus", null, true);
                
                if ( vastaus ) {
                    tallenna();
                }
                
                ModalController.closeStage( saveBtn );
            }
        });
        
    }

    
    @Override
    public void setDefault( Allergiainfo a ) {
        this.allergiainfo = a;
        this.tuote = this.allergiainfo.haeMuokattava();
        
        naytaTiedot();
    }
    
    
    @FXML
    private void cancelEditing() {
        peruuta();
    }
    
    
    @FXML
    private void saveChanges() {
        tallenna();
    }
    
//---------------------------------------------------------------------------------------------------------------------------------------------
// Tästä eteenpäin ei käyttöliittymään suoraan liittyvää koodia 
    
    private Allergiainfo palautus = null;
    
    private Allergiainfo allergiainfo;
    private Tuote tuote = null;
    private Ravintola ravintola;
    private List<CheckBox>  allergeeniCheckBoxes;
    private List<Ravintola> ravintolat = new ArrayList<>();
    private List<TuoteAllergeeni> allergeenit = new ArrayList<>();
    
    
    /**
     * Peruutetaan muokkaus
     */
    private void peruuta() {
        
        if( onkoTallentamatta() ) {
            Boolean jatko = ModalController.<Boolean>showModal( ProductGUIController.class.getResource("WarningUnsavedGUIView.fxml"), "Varoitus", null, true );
            System.out.println("palautti: " + jatko );
            
            if( jatko ) {
                tallenna();
            };
        }
        
        ModalController.closeStage(cancelBtn);
    }
    
    
    /**
     * Tallennetaan muutokset
     */
    private void tallenna() {
        
        if( !onkoTallentamatta() ) ModalController.closeStage( saveBtn );
        
        String nimiMuokattu = textProductName.getText();
        Ravintola ravintolaMuokattu = restaurantCB.getValue();
        
        List<Integer> allergeenitMuokattu = allergeeniCheckBoxes.stream()
                                            .filter( CheckBox::isSelected)
                                            .map( cb -> Integer.parseInt( cb.getUserData().toString()))
                                            .toList();
        
        if( !nimiMuokattu.equalsIgnoreCase( this.tuote.haeNimi())) {
            this.tuote.asetaNimi( nimiMuokattu );
        }
        
        if( this.tuote.haeRavintolaId() != ravintolaMuokattu.haeId() ) {
            this.tuote.asetaRavintola( ravintolaMuokattu.haeId() );
        }
        
    //  Etsitään lisättävät allergeenit ja lisätään tietorakenteeseen
        for (Integer allergeeni : allergeenitMuokattu) {
            boolean olemassa = this.allergeenit.stream()
                               .anyMatch(ta -> ta.haeAllergeeniID() == allergeeni);
            if (!olemassa) {
                this.allergiainfo.lisaa(tuote.haeId(), allergeeni);
            }
        }
    
    //  Etsitään poistettavat allergeenit ja poistetaan tietorakenteesta
        for (TuoteAllergeeni ta : this.allergeenit) {
            if (allergeenitMuokattu.stream().noneMatch(id -> id == ta.haeAllergeeniID())) {
                this.allergiainfo.poistaTuoteAllergeeni(ta);
            }
        }
        
        this.allergiainfo.asetaMuokattava( null );
        
        try {
            this.allergiainfo.tallenna();
        } catch (SailoException e) {
            System.err.println( e.getMessage() );
        }
        
        ModalController.closeStage( saveBtn );
        palautus = this.allergiainfo;
    }
    
    
    /**
     * Tarkistetaan onko muutoksia
     * @return totuus arvo onko muutoksia ( true | false )
     */
    private boolean onkoTallentamatta() {
        
        if( !textProductName.getText().equalsIgnoreCase( tuote.haeNimi())) return true;
        
        if( restaurantCB.getValue() == null || !restaurantCB.getValue().equals( this.ravintola )) return true;
        
        List<Integer> allergeenitNyt = allergeeniCheckBoxes.stream()
                                       .filter(CheckBox::isSelected)
                                       .map(cb -> Integer.parseInt(cb.getUserData().toString()))
                                       .toList();
        
        List<Integer> allergeenitAlussa = allergeenit.stream()
                                          .map( TuoteAllergeeni::haeAllergeeniID )
                                          .toList();
        
        if( !allergeenitNyt.containsAll( allergeenitAlussa ) || !allergeenitAlussa.containsAll( allergeenitNyt )) return true;
        
        return false;
    }
    
    
    /**
     * Asetetaan muokattavan tuotteen tiedot modaaliin
     */
    private void naytaTiedot() {
        
    //  Asetetaan tuotteen nimi tekstikenttään
        textProductName.setText( this.tuote.haeNimi());
        
        
    //  Haetaan + alustetaan ravintolat ja asetetaan ne ComboBoxiin
        for( int i = 0; i < this.allergiainfo.haeRavintoloita(); i++ ) {
            Ravintola r = this.allergiainfo.annaRavintola( i );
            this.ravintolat.add( r );
        }
        
        restaurantCB.setItems( FXCollections.observableList( this.ravintolat ));
        
        
    //  Haetaan ja asetetaan nykyinen ravintola ComboBoxin arvoksi
       
        try {
            this.ravintola = this.allergiainfo.haeRavintolaIdlla( this.tuote.haeRavintolaId() );
        } catch (SailoException e) {
            System.err.println( e.getMessage());
        }
        
        restaurantCB.setValue( this.ravintola );
       
        
    //  Haetaan ja asetetaan allergeenit (CheckBox arvot)
        this.allergeenit = this.allergiainfo.haeTuotteenAllergeenit( tuote.haeId());
        
        for( int i = 0; i < allergeeniCheckBoxes.size(); i++ ) {
            int allergeeniId = i;
            
            for( TuoteAllergeeni ta: this.allergeenit ) {
                if( ta.haeAllergeeniID() == allergeeniId ) {
                    allergeeniCheckBoxes.get( i ).setSelected( true );
                    break;
                }
            }
        }
    }
}
