
package fxAllergiainfo;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * @author Viivi
 * @version 11.2.2025
 *
 */
public class AddRestaurantGUIController implements ModalControllerInterface<String> {
    @FXML private TextField textRestaurantName;
    @FXML private Button cancelBtn;
    @FXML private Button addRestaurantBtn;

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
    private void cancelAddNewRestaurant() {
        cancel();
    }
    
    @FXML
    private void addNewRestaurant() {
        Dialogs.showMessageDialog("Ei vielä osata lisätä uutta ravintolaa");
    }
	
    private void cancel() {
        ModalController.closeStage(cancelBtn);
    }
}
