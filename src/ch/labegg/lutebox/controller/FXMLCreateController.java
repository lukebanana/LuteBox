package ch.labegg.lutebox.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ch.labegg.lutebox.model.Lute;
import ch.labegg.lutebox.model.MainModel;
import ch.labegg.lutebox.model.api.DataModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLCreateController implements Initializable {

	@FXML private BorderPane borderPane;

	@FXML private TextField textInputName;
	@FXML private TextField textInputRefNr;
	@FXML private TextField textInputYear;
    
	private DataModel model = null;
	private ObservableList<Lute> list = null;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		model = new MainModel();
		
	}	
	


	@FXML
	public void handleAddButtonAction(ActionEvent event) {
		Lute l = new Lute("Tets");
		list.add(l);
		model.addItem(l);
    }
	
	@FXML
	private void handleSaveAction(final ActionEvent event) {
		 System.out.println("Saved");   
	}
	
}
