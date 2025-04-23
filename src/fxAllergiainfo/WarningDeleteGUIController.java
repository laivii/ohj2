package fxAllergiainfo;

import allergiainfo.Tuote;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * @author Viivi
 * @version 10.2.2025
 * @version 23.4.2025
 */
public class WarningDeleteGUIController implements ModalControllerInterface<Tuote> {
    
    @FXML private Button cancelBtn;
    @FXML private Button deleteBtn;
    @FXML private Text   textVaroitus;
   
    
    @Override
    public Tuote getResult() {
        return palautus;
    }

    
    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    
    @Override
    public void setDefault(Tuote oletus) {
        this.tuote = oletus;
        this.tuotteenNimi = oletus.haeNimi();
        
        String varoitus = "Olet poistamassa tuotteen: " + this.tuotteenNimi + "\nHaluatko varmasti poistaa valitun tuotteen?";
        textVaroitus.setText( varoitus );
    }
    
    
    @FXML
    private void cancel() {
        ModalController.closeStage( cancelBtn );
    }
    
    
    @FXML
    private void delete() {
        this.palautus = tuote;
        ModalController.closeStage( deleteBtn );
    }
    
    private Tuote palautus = null;
    private Tuote tuote;
    private String tuotteenNimi;

}
