package ch.labegg.lutebox.gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ch.labegg.lutebox.config.LBConfig;
import javafx.geometry.*;

public class ConfirmBox {

	private static boolean answer = false;
	
	public static boolean display(String title, String message, int width, int height) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL); // Block user from clicking away
		window.setTitle(title);
		window.setMinWidth(250);
		
		Label label = new Label(message);
		Button btn = new Button("Yes");
		Button btn2 = new Button("No");
		
		btn.setOnAction(e -> {
			answer = true;
			window.close();
		});
		
		
		btn2.setOnAction(e -> {
			answer = false;
			window.close();
		});
		
		HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setAlignment(Pos.CENTER);
		
	    hbox.getChildren().addAll(btn, btn2);

		VBox layout = new VBox(20); 	// 20px spacing
		layout.setStyle("-fx-background-color: "+LBConfig.PRIMARY_SKIN_COLOR+";");
		layout.getChildren().addAll(label, hbox);
		layout.setAlignment(Pos.CENTER);
	
		
		
		Scene scene = new Scene(layout, width, height);
		
		window.setScene(scene);
		window.showAndWait();
		
		
		return answer;
	}
}
