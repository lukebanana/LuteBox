package ch.labegg.lutebox.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ch.labegg.lutebox.config.LBConfig;
import ch.labegg.lutebox.model.Lute;
import ch.labegg.lutebox.model.MainModel;
import ch.labegg.lutebox.model.api.DataModel;
import ch.labegg.lutebox.views.ConfirmBox;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class FXMLMainController extends Application implements Initializable {

	@FXML private ScrollPane scrollPane = new ScrollPane();
	@FXML private BorderPane borderPane;

	@FXML private Menu menuFile;
	@FXML private Menu menuEdit;
	@FXML private Menu menuView;
	@FXML private MenuBar menuBar;
	
	@FXML private VBox leftBox;
	@FXML private TextField searchFilter;
	
	@FXML private VBox listlayout;
	@FXML private TableView<Lute> tableView;
	@FXML private TableColumn<Lute, Image> colHasImg;
    @FXML private TableColumn<Lute, String> colRefNr;
    @FXML private TableColumn<Lute, String> colName;
    @FXML private TableColumn<Lute, Short> colYear;
    
	@FXML private VBox bottomlayout;
		
	private DataModel model = new MainModel();
	private ObservableList<Lute> list = null;

	private FXMLLoader loader = new FXMLLoader();
	private Stage window;

	
	public static void main(String[] args) {
		System.setProperty("com.apple.mrj.application.apple.menu.about.name", "LuteBox");
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		launch(args);
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
 		window.centerOnScreen();
 		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

		window.setTitle("LuteBox");

	    	window.setOnCloseRequest(e -> {
			e.consume(); // Stops java from closing Program
			closeProgram(primaryStage);
		});
	
	    try {	    		
		    	loader.setResources(model.getRessourceBundle());
		    	loader.setLocation(getClass().getResource("/ch/labegg/lutebox/views/MainWindow.fxml"));
		    	loader.setRoot(scrollPane);
		    loader.load();
		 
		    	Scene scene = new Scene(scrollPane, screenBounds.getWidth(), screenBounds.getHeight());	
		    	window.setScene(scene);		
			
		    	window.show();
		
        } catch (IOException e) {
        		e.printStackTrace();
        }
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		leftBox.setPrefWidth( Screen.getPrimary().getBounds().getWidth() / 100 * LBConfig.LEFT_MENU_WIDTH_PERCENT );
		leftBox.setMaxWidth( Screen.getPrimary().getBounds().getWidth() / 100 * LBConfig.LEFT_MENU_WIDTH_PERCENT );
		leftBox.setPadding(LBConfig.GLOBAL_BOX_PADDING_INSET);
		
		// Data Mapping
		colHasImg.setCellValueFactory(new PropertyValueFactory<>("filePath"));
		colRefNr.setCellValueFactory(new PropertyValueFactory<>("referenceNr"));
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
		
		list = this.model.getList();
        
		tableView.setItems(list);
        
		listlayout.setPrefHeight( Screen.getPrimary().getBounds().getHeight() / 100 * LBConfig.LIST_WINDOW_HEIGHT_PERCENT);
		listlayout.setMaxHeight( Screen.getPrimary().getBounds().getHeight() / 100 * LBConfig.LIST_WINDOW_HEIGHT_PERCENT );
		listlayout.setVgrow(tableView, Priority.ALWAYS);
	

		bottomlayout.setPrefHeight( Screen.getPrimary().getBounds().getHeight() / 100 * LBConfig.BOTTOM_WINDOW_HEIGHT_PERCENT);
		bottomlayout.setMaxHeight( Screen.getPrimary().getBounds().getHeight() / 100 * LBConfig.BOTTOM_WINDOW_HEIGHT_PERCENT );
		bottomlayout.setPadding(LBConfig.GLOBAL_BOX_PADDING_INSET);
	}	
	
	
	@FXML
	public void handleAddButtonAction(ActionEvent event) {
        FXMLCreateController ccontroller = new FXMLCreateController(model, list);
    }
	
	@FXML
	public void handleViewImageAction(ActionEvent event) {
		FXMLImageViewWindow imgcontroller = new FXMLImageViewWindow(model, tableView.getSelectionModel().getSelectedItem());
    }
	

	@FXML
	public void handleDeleteButtonAction(ActionEvent event) {
		boolean result = ConfirmBox.display("Eintrag löschen", "Are you sure you want to delete the selected items?", 380, 120);
		
		if(result) {
			ObservableList<Lute> selectedItems = tableView.getSelectionModel().getSelectedItems();
			for(Lute item : selectedItems) {
				tableView.getItems().remove(item);
				model.removeItem(item);
			}
		}	
    }
		
	@FXML
	private void handleCloseAction(final ActionEvent event) {
		closeProgram((Stage) borderPane.getScene().getWindow());
	}

	private void closeProgram(Stage stage) {
		boolean result = ConfirmBox.display("Before you go...", "Are you sure you want to exit?", 250, 120);
				
		if(result) {
			stage.close();
		}
	}
	
	
	@FXML
	private void searchRecord(KeyEvent ke) {
		 FilteredList<Lute> filterData = new FilteredList<>(list, p -> true);
		 
		 searchFilter.textProperty().addListener((observable, oldValue, newValue) -> {
			 filterData.setPredicate(lute ->{

				 if(newValue == null || newValue.isEmpty()){
					 return true;
				 }
				 
				 String typedText = newValue.toLowerCase();
				 if(lute.getName().toLowerCase().indexOf(typedText) != -1){
					 return true;
				 }
				 
				 if(lute.getReferenceNr().toLowerCase().indexOf(typedText) != -1){
					 return true;
				 }
				 
				 if(Short.toString(lute.getYear()).toLowerCase().indexOf(typedText) != -1){
					 return true;
				 }
				 
				 return false;
			 });
		 });
		 
		 SortedList<Lute> sortedList = new SortedList<>(filterData);
		 sortedList.comparatorProperty().bind(tableView.comparatorProperty());
		 tableView.setItems(sortedList);
	}
	
	@FXML
	private void resetSearch(final ActionEvent event) {
		searchFilter.setText("");
		tableView.setItems(list);
	}
	

	@FXML
	public void handleAboutAction(ActionEvent event) {
		new FXMLAboutWindow(model);
    }

}
