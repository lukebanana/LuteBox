package ch.labegg.lutebox.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class ImageUploadTest extends Application {
    
    ImageView imageView;
    
    @Override
    public void start(Stage primaryStage) {
        
        Button btnLoad = new Button("Load");
        
        btnLoad.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			            
	        //Set extension filter
	        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
	        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
	        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
	         
	        //Show open file dialog
	        File file = fileChooser.showOpenDialog(null);
	                  
	        try {
	            BufferedImage bufferedImage = ImageIO.read(file);
	            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
	            imageView.setFitHeight(120);
	            imageView.setFitWidth(120);
	            imageView.setPreserveRatio(true);
	            imageView.setImage(image);
	            String filePath = "res/images/"+file.getName();
	            File outputfile = new File(filePath);
	            
	            if(ImageIO.write(bufferedImage, "png", outputfile)) {
	            		System.out.println("Image successfully saved");
	            }else {
	            		System.out.println("Error: Image not saved!");
	            }
	        } catch (IOException e) {
	        		e.printStackTrace();
	        }
        });
        
        imageView = new ImageView();        
        
        VBox rootBox = new VBox();
        rootBox.getChildren().addAll(btnLoad, imageView);
        
        Scene scene = new Scene(rootBox, 300, 300);
        
        primaryStage.setTitle("Image Upload Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
   
}