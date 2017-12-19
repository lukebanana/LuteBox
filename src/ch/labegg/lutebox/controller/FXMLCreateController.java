package ch.labegg.lutebox.controller;

import java.net.URL;
import java.util.ResourceBundle;

import ch.labegg.lutebox.config.LBConfig;
import ch.labegg.lutebox.model.Lute;
import ch.labegg.lutebox.model.api.DataModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class FXMLCreateController implements Initializable {

	@FXML private BorderPane borderPane;

	@FXML private Label heading;

	@FXML private TextField textInputName;
	@FXML private TextField textInputRefNr;
	@FXML private TextField textInputYear;
	@FXML private GridPane formGrid;
    
	private DataModel model = null;
	private ObservableList<Lute> list = null;

	public FXMLCreateController(DataModel model, ObservableList<Lute> list) {
		this.model = model;
		this.list = list;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		borderPane.setPadding(LBConfig.GLOBAL_BOX_PADDING_INSET);
	}	
	
	@FXML
	public void handleAddButtonAction(ActionEvent event) {
		String name = textInputName.getText().trim();
		
		if(name.equals(null) || name.length() != 0) {
			Lute l = new Lute(name, textInputRefNr.getText().trim(), Short.parseShort(textInputYear.getText().trim()));
			list.add(l);
			model.addItem(l);
		}
		
    }
	
	@FXML
	private void handleSaveAction(final ActionEvent event) {
		 System.out.println("Saved");   
	}
	
}
