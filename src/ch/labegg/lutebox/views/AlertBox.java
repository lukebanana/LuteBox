package ch.labegg.lutebox.views;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.*;

public class AlertBox {

	
	public static void display(String title, String message, int width, int height) {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL); // Block user from clicking away
		window.setTitle(title);
		window.setMinWidth(250);
		
		Label label = new Label(message);
		Button closeBtn = new Button("Close");
		closeBtn.setOnAction(e -> window.close());
		

		VBox layout = new VBox(20); 	// 20px spacing
		
		layout.getChildren().addAll(label, closeBtn);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout, width, height);
		layout.getStyleClass().add("pane");
		scene.getStylesheets().add("/ch/labegg/lutebox/views/styles/global.css");
		
		window.setScene(scene);
		window.showAndWait();
	}
}
