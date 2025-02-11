package fxAllergiainfo;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @author Viivi
 * @version 11.2.2025
 *
 */
public class WarningUnsavedGUIController implements ModalControllerInterface<String> {
    @FXML private Button exitBtn;

    @Override
    public String getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(String oletus) {
        // TODO Auto-generated method stub
        
    }
    
    @FXML
    private void saveChanges() {
        Dialogs.showMessageDialog("Ei vielä osata tallentaa");
    }
    
    @FXML
    private void exitWithoutSaving() {
        exit();
    }
    
    private void exit() {
        ModalController.closeStage(exitBtn);
    }
	
}
