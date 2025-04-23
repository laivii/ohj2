package fxAllergiainfo;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;

import allergiainfo.Allergiainfo;
import allergiainfo.SailoException;


/**
 * @author Viivi
 * @version 10.3.2025
 * @version 18.4.2025
 * @version 23.4.2025
 */
public class AllergiainfoMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			final FXMLLoader ldr = new FXMLLoader(getClass().getResource("AllergiainfoGUIView.fxml"));
			final Pane root = (Pane)ldr.load();
			final AllergiainfoGUIController allergiainfoCtrl = (AllergiainfoGUIController)ldr.getController();
			
			
			final Scene scene = new Scene(root,800,500);
			scene.getStylesheets().add(getClass().getResource("allergiainfo.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Allergiainfo");
			
			Allergiainfo allergiainfo = new Allergiainfo();
			allergiainfoCtrl.setAllergiainfo(allergiainfo);
			
		//  Alustetaan ravintolat (toistaiseksi vain tietyt vaihtoehdot)
	        allergiainfo.alustaRavintolat();
			
			primaryStage.show();
			
            allergiainfoCtrl.lueTiedostosta();
            
        //  Tallennetaan ennen sulkemista
            primaryStage.setOnCloseRequest( event -> {
                try {
                    allergiainfo.tallenna();
                } catch (SailoException e) {
                    System.err.println( e.getMessage() );
                }
            });

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
