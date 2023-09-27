package Gava;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageLibrary {
    /**
     * create the image library from the Game.initImageLibrary(folderpath) function
     * if not called, no image will be loaded.
     */

    private Map<String,BufferedImage> images;

    public ImageLibrary(String folderPath){
        images = new HashMap<String,BufferedImage>();
        LoadImagesFromFolder(folderPath);
    }

    public BufferedImage getImage(String name){
        return images.get(name);
    }

    public void LoadImagesFromFolder(String folderPath){
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        System.out.println("Loading images from : "+listOfFiles.length);

        assert listOfFiles != null;
        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println("Loading image : "+file.getName());
                try {
                    images.put(file.getName(),ImageIO.read(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
