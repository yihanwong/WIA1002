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

    private static int[][] map1;
    private static int[][] map2;
    private static int[][] map3;
    private static int[][] map4;
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

        printMaps();
    }

    private static void processImage(String path) {
        try {
            File imageFile = new File(path);
            BufferedImage image = ImageIO.read(imageFile);

            width = image.getWidth();
            height = image.getHeight();
            System.out.println();
            System.out.println("Image " + path + " dimensions: " + width + " x " + height);

            int[][] map = new int[height][width];

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

            if (path.equals("image 1.png")) {
                map1 = map;
            } else if (path.equals("image 2.png")) {
                map2 = map;
            } else if (path.equals("image 3.png")) {
                map3 = map;
            } else if (path.equals("image 4.png")) {
                map4 = map;
            }

            visited = new boolean[height][width]; // Initialize visited array

            int numPaths = calculatePaths(map, 0, 0, 0);
            System.out.println("Number of possible paths: " + numPaths);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int calculatePaths(int[][] map, int x, int y, int numStations) {
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

        int paths = calculatePaths(map, x - 1, y, numStations) +  // Left
                calculatePaths(map, x + 1, y, numStations) +     // Right
                calculatePaths(map, x, y - 1, numStations) +     // Up
                calculatePaths(map, x, y + 1, numStations);      // Down

        visited[y][x] = false;

        return paths;
    }

    private static void printMaps() {
    // Print map 1 and map 2 side by side
    System.out.println("Map 1               Map 2");
    for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
            System.out.print(map1[y][x] + " ");
        }
        System.out.print("    ");
        for (int x = 0; x < width; x++) {
            System.out.print(map2[y][x] + " ");
        }
        System.out.println();
    }

    // Print map 3 and map 4 side by side
    System.out.println("Map 3               Map 4");
    for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
            System.out.print(map3[y][x] + " ");
        }
        System.out.print("    ");
        for (int x = 0; x < width; x++) {
            System.out.print(map4[y][x] + " ");
        }
        System.out.println();
    }
    
        System.out.println(" ");
        System.out.println("The complete path that is combined :");
        for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
            System.out.print(map1[y][x]+" ");
        }
        for (int x = 0; x < width; x++) {
            System.out.print(map2[y][x]+" ");
        }
        System.out.println();
    }
    for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
            System.out.print(map3[y][x]+" ");
        }
        for (int x = 0; x < width; x++) {
            System.out.print(map4[y][x]+" ");
        }
        System.out.println();
    }
}

}

 
    
    

