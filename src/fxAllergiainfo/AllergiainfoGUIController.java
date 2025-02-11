package fxAllergiainfo;

import fi.jyu.mit.fxgui.ModalController;
import javafx.fxml.FXML;
//import javafx.scene.control.ComboBox; //TODO add combobox and it's arguments
import javafx.scene.control.TextField;
/**
 * @author Viivi
 * @version 8.2.2025
 *
 */
public class AllergiainfoGUIController {
    @FXML private TextField searchField;
    //@FXML private ComboBox<?> searchTermCB; //TODO add arguments to CB
	
	@FXML
	private void openHandleProducts() {
	    ModalController.showModal(AllergiainfoGUIController.class.getResource("ProductManagementGUIView.fxml"), "Tuotteiden hallinta", null, "");
	}
}
