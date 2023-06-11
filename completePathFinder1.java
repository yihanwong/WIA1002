/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assignment;

import java.util.*;
import java.io.*;

/**
 *
 * @author User
 */
public class completePathFinder1 {            
    public static void main(String[] args) {
        int[][] matrix = readArrayFromFile("modified_array.txt");
        if (matrix != null) {
            List<List<int[]>> paths = findPossiblePaths(matrix);
            System.out.println("Number of possible paths: " + paths.size());
   
        }
    }

     public static List<List<int[]>> findPossiblePaths(int[][] matrix) {
            int rows = matrix.length;
            int cols = matrix[0].length;
            boolean[][] visited = new boolean[rows][cols];
            int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

            List<List<int[]>> paths = new ArrayList<>();
            List<int[]> currentPath = new ArrayList<>();
            dfs(matrix, 0, 0, 0, visited, directions, currentPath, paths);
            return paths;
    }

    private static void dfs(int[][] matrix, int x, int y, int stationCount, boolean[][] visited, int[][] directions,
                            List<int[]> currentPath, List<List<int[]>> paths) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        if (x == rows - 1 && y == cols - 1 && stationCount == 4) {
            paths.add(new ArrayList<>(currentPath));
            return;
        }

        visited[x][y]=true;
        currentPath.add(new int[]{x, y});

        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];

            if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && !visited[newX][newY]
                    && matrix[newX][newY] != 1) {
                int newStationCount = stationCount + (matrix[newX][newY] == 2 ? 1 : 0);

                if (newStationCount <= 4) {
                    dfs(matrix, newX, newY, newStationCount, visited, directions, currentPath, paths);
                }
            }
        }

        visited[x][y]=false;
        currentPath.remove(currentPath.size() - 1);
    }


    public static int[][] readArrayFromFile(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            int rows = scanner.nextInt();
            int cols = scanner.nextInt();
            int[][] matrix = new int[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = scanner.nextInt();
                }
            }
            scanner.close();
            return matrix;
        } catch (FileNotFoundException e) {
            System.out.println("Failed to read array from file: " + e.getMessage());
            return null;
        }
    }
}