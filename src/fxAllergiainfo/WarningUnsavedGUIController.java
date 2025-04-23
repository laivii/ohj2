package fxAllergiainfo;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @author Viivi
 * @version 23.4.2025
 */
public class WarningUnsavedGUIController implements ModalControllerInterface<Boolean> {
    
    @FXML private Button exitBtn;
    @FXML private Button saveBtn;

    @Override
    public Boolean getResult() {
        return palautus;
    }

    
    @Override
    public void handleShown() {
        //
    }


    @Override
    public void setDefault(Boolean oletus) {
        this.palautus = oletus;
    }
    
    
    @FXML
    private void saveChanges() {
        this.palautus = true;
        exit();
    }
    
    
    @FXML
    private void exitWithoutSaving() {
        this.palautus = false;
        exit();
    }

//-------------------------------------------------------------------------------------------------------------------------------------------
    
    private Boolean palautus;
    
    private void exit() {
        ModalController.closeStage(exitBtn);
    }
}
