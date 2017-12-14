package ch.labegg.lutebox.db;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.sql.ResultSet;

import ch.labegg.lutebox.model.Lute;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DerbySerializer {
	

	public static ObservableList<Lute> deserialize(ResultSet rs) {
		try {

			ObservableList<Lute> list = FXCollections.observableArrayList();
	      // loop through the result set
	      while (rs.next()) {
	         // fetch the serialized object to a byte array
	         byte[] st = (byte[])rs.getObject(1);
	             //   or  byte[] st = rs.getBytes(1);
	             //   or  Blob aBlob = rs.getBlob(1);
	             //       byte[] st = aBlob.getBytes(0, (int) aBlob.length());
	         
	         ByteArrayInputStream baip = new ByteArrayInputStream(st);
	         ObjectInputStream ois = new ObjectInputStream(baip);
	         
	         // re-create the object
	         Lute lute = (Lute)ois.readObject();
	         list.add(lute);
	      }
	      
	      rs.close();
	      return list;
			
		} catch(Exception e) {
			e.printStackTrace();
	    }
		return null;
	}

}
