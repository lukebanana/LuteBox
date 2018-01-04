package ch.labegg.lutebox.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import ch.labegg.lutebox.config.LBConfig;
import ch.labegg.lutebox.model.Lute;
import ch.labegg.lutebox.model.api.DataModel;
import ch.labegg.lutebox.views.AlertBox;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLCreateController implements Initializable {

	@FXML private ScrollPane scrollPane;
	@FXML private BorderPane borderPane;

	@FXML private Label heading;

	@FXML private TextField textInputName;
	@FXML private TextField textInputRefNr;
	@FXML private TextField textInputYear;
	@FXML private Button uploadImage;
	@FXML private Button saveLute;
	@FXML private GridPane formGrid;
	@FXML private ImageView imageView;
	private File file = null;
	private BufferedImage bufferedImage = null;
    
	private Stage stage;
	private DataModel model = null;
	private ObservableList<Lute> list = null;
	
	private FXMLLoader loader = new FXMLLoader();
	
	public FXMLCreateController(DataModel model, ObservableList<Lute> list) {
		this.model = model;
		this.list = list;
		
		try {
			
			loader.setResources(model.getRessourceBundle());
			loader.setLocation(getClass().getResource("/ch/labegg/lutebox/views/CreateItemView.fxml"));
			
	        // Set it in the FXMLLoader
	        loader.setController(this);
		 	
	      	Parent root = (Parent)loader.load();
	     
			stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL); // Block user from clicking away
			stage.setScene(new Scene(root, 800, 600));	
			stage.centerOnScreen();
			stage.show();
		
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		borderPane.setPadding(LBConfig.GLOBAL_BOX_PADDING_INSET);
		
		// Numeric 4 digit values only for year
		textInputYear.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        if (!newValue.matches("\\d")) {
		        		if(!oldValue.matches("\\d{4}")) {
		        			textInputYear.setText(newValue.replaceAll("[^\\d]", ""));
		        		}else {
		        			textInputYear.setText(oldValue);
		        		}
		        }
		    }
		});
	}	
	
	@FXML
	public void handleAddButtonAction(ActionEvent event) {
		String name = textInputName.getText().trim();
		boolean validateSuccessful = false;
		
		if (name != null) {
			if(name.length() != 0) {
				String yearString = textInputYear.getText().trim();
				
				Lute l = new Lute(
						name, 
						textInputRefNr.getText().trim()
				);
	
				if(yearString != "") {
					short year = Short.parseShort(yearString);
					l.setYear(year);
				}
				
				validateSuccessful = FieldValidator.validateImage(bufferedImage, file);	
				
				if(validateSuccessful) {
					list.add(l);
					model.addItem(l);
					if(file != null) {
						l.setFilePath("res/images/" + file.getName());
					}
					stage.close();
				}
			}
		}else {
			validateSuccessful = false;
		}
    }
	
	@FXML
	public void handleUploaduttonAction(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
        
        //Set extension filter
        fileChooser.getExtensionFilters().addAll(
        		new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG"), 
        		new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG")
        );
         
        //Show open file dialog
        file = fileChooser.showOpenDialog(null);
                  
        
        if(file != null){
	        try {
	            bufferedImage = ImageIO.read(file);
	            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
	           
	            imageView.setFitWidth(210);
	            imageView.setImage(image);
	        } catch (IOException e) {
	        		e.printStackTrace();
	        }
        }
    }
	
	@FXML
	private void handleSaveAction(final ActionEvent event) {
		 System.out.println("Saved");   
	}
	
}
