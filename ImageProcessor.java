import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageProcessor {

    public static void main(String[] args) {
        // File paths for four images
        String[] imagePaths = {
                "image 1.png",
                "image 2.png",
                "image 3.png",
                "image 4.png"
        };

        // Process each image
        for (String path : imagePaths) {
            processImage(path);
        }
    }

    private static void processImage(String path) {
        try {
            // Read the image file
            File imageFile = new File(path);
            BufferedImage image = ImageIO.read(imageFile);

            // Get the image dimensions
            int width = image.getWidth();
            int height = image.getHeight();
            System.out.println("Image " + path + " dimensions: " + width + " x " + height);

            // Iterate over each pixel and print its red component value
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = image.getRGB(x, y);
                    int red = (rgb >> 16) & 0xFF;
                    System.out.print(red + " ");
                }
                System.out.println(); // Move to the next line after each row
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
