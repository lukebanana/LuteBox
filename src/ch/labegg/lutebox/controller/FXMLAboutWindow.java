package ch.labegg.lutebox.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ch.labegg.lutebox.config.LBConfig;
import ch.labegg.lutebox.model.Lute;
import ch.labegg.lutebox.model.api.DataModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FXMLAboutWindow implements Initializable  {
	@FXML private ScrollPane scrollPane;
	@FXML private BorderPane borderPane;
	
	private FXMLLoader loader = new FXMLLoader();
     
	public FXMLAboutWindow(DataModel model) {
		
		try {

			loader.setResources(model.getRessourceBundle());
		 	loader.setLocation(getClass().getResource("/ch/labegg/lutebox/views/AboutView.fxml"));
		
			
	        // Set it in the FXMLLoader
	        loader.setController(this);
			
			Parent root = (Parent)loader.load();
		
			Stage stageViewImg = new Stage();
			stageViewImg.setScene(new Scene(root, 800, 600));	
			stageViewImg.centerOnScreen();
			stageViewImg.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		borderPane.setPadding(LBConfig.GLOBAL_BOX_PADDING_INSET);	
		

	 }
	
 }