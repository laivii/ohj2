package fxAllergiainfo;

import java.io.PrintStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import allergiainfo.Allergeeni;
import allergiainfo.Allergiainfo;
import allergiainfo.Ravintola;
import allergiainfo.SailoException;
import allergiainfo.Tuote;
import allergiainfo.TuoteAllergeeni;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Insets;



/**
 * @author Viivi
 * @version 24.4.2025
 * 
 * Hoitaa kaiken päänäytölle tulevan sekä tiedon pyytämisen
 */
public class AllergiainfoGUIController implements Initializable {
    
    @FXML private TextField  searchField;
    @FXML private Button     searchButton;
    @FXML private ScrollPane panelTuotteet;
    @FXML private VBox       tuotteetVBox;
    @FXML private CheckBox   cbGluteeni;
    @FXML private CheckBox   cbMaito;
    @FXML private CheckBox   cbLaktoosi;
    @FXML private CheckBox   cbMuna;
    @FXML private CheckBox   cbSoija;
    @FXML private CheckBox   cbSeesami;
    @FXML private CheckBox   cbSinappi;
    @FXML private CheckBox   cbSelleri;
    @FXML private CheckBox   cbKala;
    @FXML private CheckBox   cbAyriaiset;
    @FXML private CheckBox   cbPahkinat;
    @FXML private CheckBox   cbMaapahkina;
    @FXML private CheckBox   cbNilviaiset;
    @FXML private CheckBox   cbLupiini;
    @FXML private CheckBox   cbSulfiitit;
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    //  Asetetaan allergeeni "suodattajat" listaan kun sovellus avataan
        allergiaCheckBoxes = Arrays.asList(
                cbGluteeni, cbMaito, cbLaktoosi, cbMuna, cbSoija, cbSeesami, cbSinappi,
                cbSelleri, cbKala, cbAyriaiset, cbPahkinat, cbMaapahkina,
                cbNilviaiset, cbLupiini, cbSulfiitit
        );   
        
    //  
       searchField.textProperty().addListener(( observable, oldValue, newValue ) -> {
           suodataTuotteet();
       }); 
        
    }
	
	
	@FXML
	private void lisaaTuote() {
	    Allergiainfo palautus = ModalController.<Allergiainfo>showModal( AllergiainfoGUIController.class.getResource("ProductGUIView.fxml"),"Lisää uusi tuote", null, this.allergiainfo);
	    
	    if( palautus != null ) hae();
	}
	
	
	@FXML
	private void suodata() {
	    suodataTuotteet();
	}
	
	
	@FXML
	private void tyhjennaAllergiat() {
	    tyhjenna();
	}
    
    
//============================================================================================================
// Tästä eteenpäin ei käyttöliittymään suoraan liittyvää koodia


    private Allergiainfo allergiainfo;
    private List<CheckBox> allergiaCheckBoxes;
	
	
	/**
	 * Alustetaan allergiainfo lukemalla tiedot tiedostosta
	 * @return null jos lukeminen onnistuu, muuten virhe tekstinä (popup)
	 */
	protected String lueTiedostosta() {
	    try {
            allergiainfo.lueTiedostosta();
            hae();
            return null;
        } catch (SailoException e) {
            hae();
            String virhe = e.getMessage();
           
            if( virhe != null ) Dialogs.showMessageDialog( virhe );
            return virhe;
        }
	}
	
	
    /**
     * Tietojen tallennus
     * @return null jos onnistuu, muuten virhe tekstinä
     */
	public String tallenna() {
	    try {
            allergiainfo.tallenna();
            return null;
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Tallennuksessa virhe! " + e.getMessage());
            return e.getMessage();
        }
	}
	
	
	/**
	 * Suodattaa tuotteet nimen ja allergeenien (accordion checkbox) perusteella
	 * Tyhjentää tuotelistan (UI) ja laittaa tilalle suodatetut tulokset
	 * Ei vaikuta varsinaiseen listaan/taulukkoon vain käyttöliittymän listaukseen
	 */
	private void suodataTuotteet() {
	    String hakuehto = searchField.getText().toLowerCase();
	    List<Integer> allergiat = allergiaCheckBoxes.stream()
	                               .filter(CheckBox::isSelected)
	                               .map(cb -> Integer.parseInt(cb.getUserData().toString()))
	                               .toList();

	    tuotteetVBox.getChildren().clear();

	    for (int i = 0; i < this.allergiainfo.haeTuotteita(); i++) {
	        Tuote tuote = this.allergiainfo.annaTuote(i);

	    //  Suodata nimen perusteella
	        if (!hakuehto.isBlank() && !tuote.haeNimi().toLowerCase().contains(hakuehto)) {
	            continue;
	        }

	    //  Suodata allergeenien perusteella
	        List<TuoteAllergeeni> tuotteenAllergeenit = this.allergiainfo.haeTuotteenAllergeenit(tuote.haeId());
	        boolean sisaltaaAllergeenin = tuotteenAllergeenit.stream()
	                                      .anyMatch(ta -> allergiat.contains(ta.haeAllergeeniID()));

	        if (sisaltaaAllergeenin) {
	            continue;
	        }

	        tuotteetVBox.getChildren().add(luoTuoteKomponentti(tuote));
	    }
	}
	
	
	/**
	 * Asetetaan käytettävä allergiainfo
	 * @param allergiainfo jota käytetään tässä käyttöliittymässä
	 */
	public void  setAllergiainfo(Allergiainfo allergiainfo) {
	    this.allergiainfo = allergiainfo;
	}
	
	
	/**
	 * Hakee tuotteiden tiedot
	 */
	private void hae() {	    
	    tuotteetVBox.getChildren().clear();
	    
	    for( int i = 0; i < this.allergiainfo.haeTuotteita(); i++ ) {
	        Tuote tuote = this.allergiainfo.annaTuote( i );
	        tuotteetVBox.getChildren().add( luoTuoteKomponentti( tuote ) );
	    }
	}
	
	
	/**
	 * Luodaan tuote komponentti
	 * @param t tuote jolle tahdotaan luoda komponentti (Eli siis ruudussa näkyvä "kortti")
	 * @return palauttaa komponentin, joka sisältää tuotteen tiedot
	 */
    private HBox luoTuoteKomponentti( Tuote t ) {
        
        Tuote tuote = t;
        Ravintola ravintola = this.allergiainfo.annaRavintola( tuote.haeRavintolaId() - 1 );
        List<TuoteAllergeeni> tuoteAllergeenit = this.allergiainfo.haeTuotteenAllergeenit( tuote.haeId() );
        
        HBox tuoteBox = new HBox();
        tuoteBox.setSpacing( 10 );
        
        VBox tuotteenTiedot = new VBox();        
        Label nimiJaRavintola = new Label( tuote.haeNimi() + " | " + ravintola.haeNimi() );
        nimiJaRavintola.setFont( Font.font( "Arial", FontWeight.BOLD, 18) );
        
        
        String allergeenit = "Sisältää: \n";
        
        if( tuoteAllergeenit.size() > 0 ) {
            for (int i = 0; i < tuoteAllergeenit.size(); i++) {
                TuoteAllergeeni ta = tuoteAllergeenit.get(i);
                int allergeeniId = ta.haeAllergeeniID();

                Allergeeni allergeeni = null;

                try {
                    allergeeni = this.allergiainfo.haeAllergeeniIdlla(allergeeniId);
                } catch (SailoException e) {
                    System.err.println(e.getMessage());
                }

                if (allergeeni != null) {
                    String nimi = allergeeni.haeNimi();

                    if (i < tuoteAllergeenit.size() - 1) {
                        allergeenit += nimi + ", ";
                    } else {
                        allergeenit += nimi;
                    }
                }
            }
        } else {
            allergeenit = "Tuote ei sisällä allergeeneja!";
        }
        
        Label tuotteenAllergeenit = new Label( allergeenit );
        tuotteenAllergeenit.setFont( new Font( 16 ));
        tuotteenAllergeenit.setWrapText(true);
        tuotteenAllergeenit.setPrefWidth(400);
        tuotteenAllergeenit.setMaxWidth(400);
        
        tuotteenTiedot.getChildren().addAll( nimiJaRavintola , tuotteenAllergeenit );
        VBox.setMargin( tuotteenAllergeenit, new Insets( 0,0,10,0 ));
        
        HBox napit = new HBox();
        napit.setSpacing(15);
        
        Button muokkaa = new Button( "M" );
        Button poista  = new Button( "P" );
        
        muokkaa.setOnAction( e -> muokkaaTuotetta( t ) );
        poista.setOnAction( e -> poistaTuote( tuote.haeId() ));
        
        napit.getChildren().addAll( muokkaa, poista );
        
        tuoteBox.getChildren().addAll( tuotteenTiedot, napit );
        
        return tuoteBox;
    }
    
    
    /**
     * Avataan tuotteen muokkaus näkymä
     * @param t muokattava tuote
     */
    private void muokkaaTuotetta( Tuote t ) {
        
    //  Asetetaan muokattava tuote "säilöön"
        this.allergiainfo.asetaMuokattava( t );
        
        Allergiainfo palautus = ModalController.<Allergiainfo>showModal( AllergiainfoGUIController.class.getResource("EditProductGUIView.fxml"), "Muokkaa", null, this.allergiainfo );
        
        if( palautus != null ) hae();
    }
	
	
	/**
	 * Poistetaan tuote ja sen allergeenit
	 * Haetaan tuotteet uudestaan
	 * @param tuoteID poistettavan tuotteen id
	 */
	private void poistaTuote( int tuoteID ) {
	    Tuote tuote = null;
	    try {
            tuote = this.allergiainfo.haeTuoteIdlla( tuoteID );
        } catch (SailoException e) {
            System.err.println( e.getMessage() );
        }
	    
	    if( tuote == null ) return;
	    
	    Tuote palautus = ModalController.<Tuote>showModal( AllergiainfoGUIController.class.getResource("WarningDeleteGUIView.fxml"), "Varoitus", null, tuote);
	    
	    if( palautus == null ) return;
	    
	    this.allergiainfo.poistaTiettyTuote( tuoteID );
	    List<TuoteAllergeeni> allergeenit = this.allergiainfo.haeTuotteenAllergeenit( tuoteID );
	    
	    for( TuoteAllergeeni ta: allergeenit ) {
	        this.allergiainfo.poistaTuoteAllergeeni( ta );
	    }
	    
	    hae();
	}
	
	
	/**
	 * Tyhjennetään allergia CheckBoxien valinnat
	 * eli poistaa kaikki valinnat
	 * 
	 * Hakee tuotteet ja suodattaa uudelleen siltä varalta, että käyttäjällä on hakuehto (tekstikentässä)
	 */
	private void tyhjenna() {
	    this.allergiaCheckBoxes.stream()
	    .filter(CheckBox::isSelected)
	    .forEach(cb -> cb.setSelected(false));
	    
	    hae();
	    
	//  Jos hakukentässä hakuehdot suodatetaan uudestaan, mutta ilman allergioita
	    suodata();
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
