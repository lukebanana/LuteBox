package ch.labegg.lutebox.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Lute implements Serializable  {
	@Id @GeneratedValue
    private long id;
	   
	private String name = "";
	private short year = 0;
	private String referenceNr = "";
	
	private int gridSize = 12;
	private int points[][] = new int[gridSize][2];
	
	public Lute(String name) {
		this.name = name;
	}
	
	public Lute(String name, short year) {
		this.name = name;
		this.year = year;
	}
	
	public Lute(String name, short year, String referenceNr) {
		this.name = name;
		this.year = year;
		this.referenceNr = referenceNr;
	}
	
	public String getName() {
		return name;
	}

	public short getYear() {
		return year;
	}

	public String getReferenceNr() {
		return referenceNr;
	}

	public int[][] getMeasurements() {
		return points;
	}
	   
	
	
}
