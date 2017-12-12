package ch.labegg.lutebox.views;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginWindow extends Application {

	private Stage stage = null;
	private Scene scene = null;

	
	public static void main(String[] args) {
		launch(args);
	}
	

	@Override
	public void start(Stage initialStage) throws Exception {
		stage = initialStage;
		stage.setTitle("Login");
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(16, 16, 16, 16));
		grid.setVgap(8);
		grid.setHgap(8);
		
		// Name Label
		Label nameLabel = new Label("Username");
		GridPane.setConstraints(nameLabel, 0, 0);
		
		// Name Input
		TextField nameInput = new TextField();
		nameInput.setPromptText("Username");
		GridPane.setConstraints(nameInput, 1, 0);
		
		// PW Label
		Label pwLabel = new Label("Password");
		GridPane.setConstraints(pwLabel, 0, 1);
			
		// PW Input
		TextField pwInput = new TextField();
		pwInput.setPromptText("Password");
		GridPane.setConstraints(pwInput, 1, 1);
		
		Button btn = new Button("Login");
		GridPane.setConstraints(btn, 1, 2);
		
		grid.getChildren().addAll(nameLabel, nameInput, pwLabel, pwInput, btn);
		
		Scene scene = new Scene(grid, 300, 220);
		stage.setScene(scene);
		stage.show();
	}

}
