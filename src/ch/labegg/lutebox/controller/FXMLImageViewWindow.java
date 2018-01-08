package ch.labegg.lutebox.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import ch.labegg.lutebox.config.LBConfig;
import ch.labegg.lutebox.model.FileHelper;
import ch.labegg.lutebox.model.Lute;
import ch.labegg.lutebox.model.api.DataModel;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class FXMLImageViewWindow implements Initializable  {
	@FXML private ScrollPane scrollPane;
	@FXML private BorderPane borderPane;
	@FXML private ImageView imageView;
	
	private FXMLLoader loader = new FXMLLoader();
	
	private DataModel model = null;
	private Lute currentItem = null;
	private Image currentImage = null;
    final DoubleProperty zoomProperty = new SimpleDoubleProperty(300);
    FileChooser fileChooser = null;
    
	public FXMLImageViewWindow(DataModel model, Lute currentItem) {
		this.model = model;
		this.currentItem = currentItem;
				
		try {
			loader.setResources(model.getRessourceBundle());
		 	loader.setLocation(getClass().getResource("/ch/labegg/lutebox/views/ImageView.fxml"));
		
			
	        // Set it in the FXMLLoader
	        loader.setController(this);
			
			Parent root = (Parent)loader.load();
		
			Stage stageViewImg = new Stage();
			Scene scene = new Scene(root, Screen.getPrimary().getBounds().getWidth() / 100 * LBConfig.IMAGE_WINDOW_WIDTH_PERCENT, Screen.getPrimary().getBounds().getHeight() / 100 * LBConfig.IMAGE_WINDOW_HEIGHT_PERCENT);
			stageViewImg.setScene(scene);	
			stageViewImg.centerOnScreen();
			stageViewImg.show();
			
			borderPane.setPrefWidth(scene.getWidth());
			borderPane.setPrefHeight(scene.getHeight());
			imageView.setFitWidth(scene.getWidth());
		    imageView.setFitHeight(scene.getHeight());
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		zoomProperty.addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable arg0) {
                imageView.setFitWidth(zoomProperty.get() * 4);
                imageView.setFitHeight(zoomProperty.get() * 3);
            }
        });
		
	    scrollPane.addEventFilter(ScrollEvent.ANY, new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                if (event.getDeltaY() > 0) {
                    zoomProperty.set(zoomProperty.get() * 1.1);
                } else if (event.getDeltaY() < 0) {
                    zoomProperty.set(zoomProperty.get() / 1.1);
                }
            }
        });
		
		//borderPane.setPadding(LBConfig.GLOBAL_BOX_PADDING_INSET);	
	    
	    
	    EventHandler<MouseEvent> imageHoverHandler = new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {		
		    	 	imageView.setCursor(Cursor.CROSSHAIR); //Change cursor to hand
		     }
		};
		
		imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, imageHoverHandler);
		
				
		if(currentItem != null) {
			if(currentItem.getFilePath() != "") {
				// simple displays ImageView the image as is
				currentImage = currentItem.getImage();
				imageView.setImage(currentImage);		   
			   
			}else {
				showNoImgMessage();
			}
		}else {
			showNoImgMessage();
		}
	}

	private void showNoImgMessage() {
		borderPane.setCenter(new Label("Kein Bild vorhanden."));
	}		
	
	@FXML
	private void handleDownloadImageAction(ActionEvent event) {
		System.out.println("Downloaded");   
		fileChooser = new FileChooser();
		 
		//Set extension filter
        fileChooser.getExtensionFilters().addAll(
        		new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG"), 
        		new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG")
        );
         
        //Show save file dialog
        File file = fileChooser.showSaveDialog(null);
        
        if(file != null){
        		FileHelper.saveImageToFile(file, currentImage);
        }
		 
	}
	
 }