package ch.labegg.lutebox.db;

import javax.persistence.*;

import ch.labegg.lutebox.db.api.LBDatabaseHandler;
import ch.labegg.lutebox.model.Lute;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.util.*;

public class ObjectDB implements LBDatabaseHandler{
	EntityManagerFactory emf = null;
	EntityManager em = null;
	
	public ObjectDB() {
		  emf = Persistence.createEntityManagerFactory("$objectdb/db/lutebox.odb");
		   em = emf.createEntityManager();
		    
	}
	
    public static void main(String[] args) {
		// Open a database connection
	    // (create a new database if it doesn't exist yet):
    		ObjectDB db = new ObjectDB();
    	
    	
	    
	    
	    // Find the number of Point objects in the database:
	    Query q1 = em.createQuery("SELECT COUNT(l) FROM Lute l");
	    System.out.println("Total Points: " + q1.getSingleResult());
	
	
	
	    db.closeConnection();
	}

	@Override
	public boolean createTable(String tableName) {
		emf = Persistence.createEntityManagerFactory("$objectdb/db/lutebox.odb");
		return true;
	}

	@Override
	public boolean insertData(ObservableList<Lute> list) {
		// Open a database connection
	    // (create a new database if it doesn't exist yet):
	    emf = Persistence.createEntityManagerFactory("$objectdb/db/lutebox.odb");
	    em = emf.createEntityManager();
	    
	    // Store 1000 Point objects in the database:
	    em.getTransaction().begin();
	    
	    for (int i = 0; i < 1000; i++) {
	        Lute l = new Lute("TestLute"+i);
	        em.persist(l);
	    }
	    em.getTransaction().commit();
	    return true;
	}
	

	@Override
	public void closeConnection() {
	    // Close the database connection:
	    em.close();
	    emf.close();
	}

	@Override
	public ObservableList<Lute> getAllEntries() {

	    // Retrieve all the Point objects from the database:
	    TypedQuery<Lute> query = em.createQuery("SELECT l FROM Lute l", Lute.class);
	    ObservableList<Lute> results = (ObservableList<Lute>) query.getResultList();
	    for (Lute l : results) {
	        System.out.println(l.getName());
	    }
		return results;
	}
}
