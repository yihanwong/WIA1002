/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ds_assignment;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;



public class DS_ASSIGNMENT {

    /**
     * @param args the command line arguments
     */
    
   
    private static final int EMPTY = 0;
    private static final int OBSTACLE = 1;
    private static final int STATION = 2;
    private static final int DESTINATION = 3;

    private static int[][] map;
    private static int width;
    private static int height;
    private static boolean[][] visited;
    
    public static void main(String[] args) {
       
        
          String[] imagePaths = {
            "image 1.png",
            "image 2.png",
            "image 3.png",
            "image 4.png"
        };

        for (String path : imagePaths) {
            processImage(path);
        }
    }

    private static void processImage(String path) {
        try {
            File imageFile = new File(path);
            BufferedImage image = ImageIO.read(imageFile);

            width = image.getWidth();
            height = image.getHeight();
            System.out.println();
            System.out.println("Image " + path + " dimensions: " + width + " x " + height);

            map = new int[height][width];
            visited = new boolean[height][width];

            // Convert image pixels to map values
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = image.getRGB(x, y);
                    int red = (rgb >> 16) & 0xFF;
                    switch (red) {
                        case 0:
                            map[y][x] = EMPTY;
                            break;
                        case 64:
                            map[y][x] = OBSTACLE;
                            break;
                        case 128:
                            map[y][x] = STATION;
                            break;
                        case 192:
                            map[y][x] = DESTINATION;
                            break;
                        default:
                            // Do nothing
                            break;
                    }
                }
            }

            int numPaths = calculatePaths(0, 0, 0);
            System.out.println("Number of possible paths: " + numPaths);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int calculatePaths(int x, int y, int numStations) {
        if (x < 0 || x >= width || y < 0 || y >= height || map[y][x] == OBSTACLE) {
            return 0;
        }

        if (visited[y][x]) {
            return 0;
        }

        if (map[y][x] == DESTINATION && numStations == 3) {
            return 1;
        }

        if (map[y][x] == STATION) {
            numStations++;
        }

        visited[y][x] = true;

        int paths = calculatePaths(x - 1, y, numStations) +  // Left
                calculatePaths(x + 1, y, numStations) +     // Right
                calculatePaths(x, y - 1, numStations) +     // Up
                calculatePaths(x, y + 1, numStations);      // Down

        visited[y][x] = false;

        return paths;
    }
}

 
    
    

