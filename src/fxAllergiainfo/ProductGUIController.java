package fxAllergiainfo;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.ModalController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox; //TODO add combobox and arguments
import javafx.scene.control.TextField;

/**
 * @author Viivi
 * @version 8.2.2025
 *
 */
public class ProductGUIController implements ModalControllerInterface<String>{
    @FXML private Button cancelBtn;
    @FXML private TextField textProductName;
    //@FXML private ComboBox<?> restaurantCB;

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
    private void cancelAddingNewProduct() {
       cancel();
    }
    
    @FXML
	private void handleAddNewProduct() {
        Dialogs.showMessageDialog("Ei vielä osata lisätä uutta");
    }
    
    private void cancel() {
        ModalController.closeStage(cancelBtn);
    }
}
