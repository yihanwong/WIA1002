/*
BY XUE TING ^^
*/

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 *
 * @author User
 */
public class ImageToRGB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            // Read the image file
            File imageFile = new File("C:\\Users\\User\\Downloads\\image 1.png");
            BufferedImage image = ImageIO.read(imageFile);

            // Get the image dimensions
            int width = image.getWidth();
            int height = image.getHeight();
            System.out.println(width);
            System.out.println(height);
            // Iterate over each pixel and print its RGB values
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = image.getRGB(x, y);
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;

                    // Print the RGB values
                    System.out.print(red +" ");
                }
                System.out.println(); // Move to the next line after each row
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
