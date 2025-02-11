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

//TODO delete this and the corresponding controller --> use the same as add new (refactor the files) so there is no dublicate views
public class EditProductGUIController implements ModalControllerInterface<String> {
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
    private void cancelEditing() {
        cancel();
    }
    
    @FXML
    private void saveChanges() {
        Dialogs.showMessageDialog("Ei vielä osata tallentaa muutoksia");
    }
    
    private void cancel() {
        ModalController.closeStage(cancelBtn);
    }
}
