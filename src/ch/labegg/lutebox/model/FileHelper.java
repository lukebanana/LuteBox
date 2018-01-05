package ch.labegg.lutebox.model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class FileHelper {
	
	public static void saveImageToFile(File file, Image image) {
	    try {
	    		ImageIO.write(SwingFXUtils.fromFXImage(image, null), FileHelper.getFileExtension(file), file);
	    } catch (IOException e) {
	    		e.printStackTrace();
	    }
	}
	
	private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
}
