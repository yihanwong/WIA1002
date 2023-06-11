/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assignment;

import java.io.*;
import java.util.*;

/**
 *
 * @author User
 */
public class shortestPath1 {

public static List<List<int[]>> findShortestPaths(int[][] matrix) {
    int rows = matrix.length;
    int cols = matrix[0].length;
    boolean[][] visited = new boolean[rows][cols];
    int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int requiredStations = 4;

    List<List<int[]>> paths = new ArrayList<>();
    Queue<List<int[]>> queue = new LinkedList<>();

    List<int[]> initialPath = new ArrayList<>();
    initialPath.add(new int[]{0, 0});
    queue.offer(initialPath);

    while (!queue.isEmpty()) {
        List<int[]> currentPath = queue.poll();
        int[] currentCell = currentPath.get(currentPath.size() - 1);
        int x = currentCell[0];
        int y = currentCell[1];

        if (x == rows - 1 && y == cols - 1 && countStations(currentPath, matrix) == requiredStations) {
            paths.add(currentPath);
            continue;  // Continue exploring other possible paths
        }

        visited[x][y] = true;

        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];

            if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && !visited[newX][newY]
                    && matrix[newX][newY] != 1) {
                List<int[]> newPath = new ArrayList<>(currentPath);
                newPath.add(new int[]{newX, newY});
                queue.offer(newPath);
            }
        }
    }

    return paths;
}

private static int countStations(List<int[]> path, int[][] matrix) {
    int count = 0;
    for (int[] cell : path) {
        int x = cell[0];
        int y = cell[1];
        if (matrix[x][y] == 2) {
            count++;
        }
    }
    return count;
}

public static void main(String[] args) {
    int[][] matrix = readArrayFromFile("modified_array.txt");

    List<List<int[]>> shortestPaths = findShortestPaths(matrix);
    System.out.println("Shortest Paths:");
    int count = 1;
    for (List<int[]> path : shortestPaths) {
        System.out.print("Path " + count + ": ");
        for (int[] cell : path) {
            System.out.print("(" + cell[0] + ", " + cell[1] + ") ");
        }
        System.out.println();
        count++;
    }
}
public static int[][] readArrayFromFile(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            int rows = scanner.nextInt();
            int cols = scanner.nextInt();
            int[][] array = new int[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    array[i][j] = scanner.nextInt();
                }
            }
            scanner.close();
            return array;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}