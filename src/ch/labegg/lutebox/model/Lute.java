package ch.labegg.lutebox.model;

import java.io.File;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javafx.scene.image.Image;

@Entity
public class Lute implements Serializable  {
	@Id @GeneratedValue
    private long id;
	   
	private String filePath = "";
	private String name = "";
	private short year = 0;
	private String referenceNr = "";
	
	private int gridSize = 12;
	private int points[][] = new int[gridSize][2];
	
	public Lute(String name) {
		this.name = name;
	}
	
	public Lute(String name, String referenceNr) {
		this.name = name;
		this.referenceNr = referenceNr;
	}
	
	public Lute(String name, String referenceNr, short year) {
		this.name = name;
		this.referenceNr = referenceNr;
		this.year = year;
	}
	
	public Lute(String name, String referenceNr, short year, String filePath) {
		this.name = name;
		this.year = year;
		this.referenceNr = referenceNr;
		this.filePath = filePath;
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

	public String getFilePath() {
		return filePath;
	}
	
	public Image getImage() {
		if (this.filePath != "") {
			File file = new File(this.filePath);
			return new Image(file.toURI().toString());
		} else {
			return null;
		}
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath; 
	}

	public void setYear(short year) {
		this.year = year; 
	}

	
}
