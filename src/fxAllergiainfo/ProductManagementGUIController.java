package fxAllergiainfo;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * @author Viivi
 * @version 10.2.2025
 *
 */
public class ProductManagementGUIController implements ModalControllerInterface<Object> {
    @FXML private TextField textSearchTerm;

    @Override
    public Object getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
    }

    @Override
    public void setDefault(Object oletus) {
        // TODO Auto-generated method stub
    }
    
    @FXML
    private void openAddNewProductView() {
        ModalController.showModal(ProductManagementGUIController.class.getResource("AddProductGUIView.fxml"), "Lisää uusi", null, "");
    }
    
    @FXML
    private void openEditProductView() {
        //Miten haetaan tuote id?
        ModalController.showModal(ProductManagementGUIController.class.getResource("EditProductGUIView.fxml"), "Muokkaa", null, "");
    }
    
    @FXML
    private void deleteProduct() {
        //TODO hae tuote id
        ModalController.showModal(ProductManagementGUIController.class.getResource("WarningDeleteGUIView.fxml"), "Poista tuote", null, "");
    }
    
    
}