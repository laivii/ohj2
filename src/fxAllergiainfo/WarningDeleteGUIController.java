package fxAllergiainfo;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * @author Viivi
 * @version 10.2.2025
 *
 */
public class WarningDeleteGUIController implements ModalControllerInterface<String> {
    @FXML private Button cancelBtn;

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
    private void cancelDelete() {
        ModalController.closeStage(cancelBtn);
    }
	
    @FXML
    private void deleteProduct() {
        delete();
    }
    
    private void delete() {
        Dialogs.showMessageDialog("Ei osata vielä poistaa tuotetta");
    }
}
