package ch.labegg.lutebox.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ch.labegg.lutebox.model.Lute;
import ch.labegg.lutebox.views.AlertBox;

public class FieldValidator {

	public static boolean validateImage(BufferedImage bufferedImage, File file) {
		boolean success = true;
		
		if (bufferedImage != null) {
			try {
				boolean folderCreated = true;

				File folder = new File("res/images/");

				if (!folder.exists()) {
					folderCreated = folder.mkdirs();
				}

				if (folderCreated) {
					String filePath = "res/images/" + file.getName();
					File outputfile = new File(filePath);

					if (ImageIO.write(bufferedImage, "png", outputfile)) {
				
						//AlertBox.display("Success", "Image successfully saved!", 300, 200);							
					} else {
						AlertBox.display("Error", "Error: Image not saved!", 300, 200);
						success = false;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				success = false;
			} catch (SecurityException se) {
				se.printStackTrace();
				success = false;
			}
		}else {
			// Kein Bild gespeichert
			//AlertBox.display("Error", "Error: Image not saved! bufferedImage == null", 300, 200);
		}
		
		return success;
		
	}
}
