package ch.labegg.lutebox.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Lute implements Serializable  {
   @Id @GeneratedValue
    private long id;
	   
	private String name;
	private String referenceNr;
	
	public Lute(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getReferenceNr() {
		return referenceNr;
	}
	   
	
	
}
