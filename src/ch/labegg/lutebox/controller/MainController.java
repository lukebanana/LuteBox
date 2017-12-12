package ch.labegg.lutebox.controller;

import ch.labegg.lutebox.controller.api.DataController;
import ch.labegg.lutebox.model.Lute;
import ch.labegg.lutebox.model.MainModel;
import ch.labegg.lutebox.model.api.DataModel;
import ch.labegg.lutebox.views.MainWindow;
import javafx.event.ActionEvent;

public class MainController implements DataController {

	private MainWindow view = null;
	private DataModel model = null;
	  
	public MainController(){
		DataModel modeltest = new MainModel();
        this.view = new MainWindow();
        
        initModel(modeltest);
        
       
        this.view.getList().setItems(this.model.getList());;
        
        
        this.view.showView();
    }
	
	
  
	public static void main(String[] args)	{
		DataController controller = new MainController();
		
    }
	
	 public void initModel(DataModel model) {
	        // ensure model is only set once:
	        if (this.model != null) {
	            throw new IllegalStateException("Model can only be initialized once");
	        }

	        this.model = model;
	        
	        
	        System.out.println(this.model.getList());
	        //listView.setItems();
	        
	        /*

	        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> 
	            model.setCurrentPerson(newSelection));

	        model.currentPersonProperty().addListener((obs, oldPerson, newPerson) -> {
	            if (newPerson == null) {
	                listView.getSelectionModel().clearSelection();
	            } else {
	                listView.getSelectionModel().select(newPerson);
	            }
	        });
	        
	        */
	    }
	  
	private void handleButtonAction(ActionEvent event) {
	
		// Button was clicked, do something...
		    
	}
}
