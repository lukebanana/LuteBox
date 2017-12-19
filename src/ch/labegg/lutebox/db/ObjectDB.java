package ch.labegg.lutebox.db;

import javax.persistence.*;

import ch.labegg.lutebox.db.api.LBDatabaseHandler;
import ch.labegg.lutebox.model.Lute;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.util.*;

public class ObjectDB implements LBDatabaseHandler{
	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	
	public ObjectDB() {
		emf = Persistence.createEntityManagerFactory("$objectdb/db/lutebox.odb");
		em = emf.createEntityManager();
	}
	
	public boolean createDB(String dbName) {
		emf = Persistence.createEntityManagerFactory("$objectdb/db/"+dbName+".odb");
		return true;
	}

	@Override
	public boolean insertData() {
	    
	    em.getTransaction().begin();
	    
	    // Store 200 Test objects in the database:
	    for (short i = 0; i < 200; i++) {
	        Lute l = new Lute("TestLute"+i, "Ref"+i, (short)(1800+i));
	        em.persist(l);
	    }
	    em.getTransaction().commit();
	    return true;
	}
	
	@Override
	public void insertSingle(Lute lute) {
		em.getTransaction().begin();
	    em.persist(lute);
	    em.getTransaction().commit();
	}
	

	@Override
	public void closeConnection() {
	    // Close the database connection:
	    em.close();
	    emf.close();
	}

	@Override
	public List<Lute> getAllEntries() {
	    // Retrieve all the Lute objects from the database
	    TypedQuery<Lute> query = em.createQuery("SELECT l FROM Lute l", Lute.class);
	    List<Lute> results = query.getResultList();
		return results;
	}

	/**
     * Delete lute entity.
     *
     * @param lute the object to be deleted.
     */
	@Override
	public void delete(Lute lute) {
		em.getTransaction().begin();
		em.remove(lute);
		em.getTransaction().commit();
	}
	
	/**
     * Update lute entity.
     *
     * @param lute the object to be updated.
     */
	@Override
	public void update(Lute lute) {
		em.getTransaction().begin();
		em.merge(lute);
		em.getTransaction().commit();
	}
}
