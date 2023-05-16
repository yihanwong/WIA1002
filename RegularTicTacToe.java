import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class RegularTicTacToe {

    static ArrayList<Integer> playerPos = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPos = new ArrayList<Integer>();
    public static void main(String[] args) {
        //create gameboard interface
        //creating array
        char [][] gameBoard = {{' ','|',' ','|',' ','|',' ','|',' '},
                               {'_','+','_','+','_','+','_','+','_'},
                               {' ','|',' ','|',' ','|',' ','|',' '},
                               {'_','+','_','+','_','+','_','+','_'},
                               {' ','|',' ','|',' ','|',' ','|',' '},
                               {'_','+','_','+','_','+','_','+','_'},
                               {' ','|',' ','|',' ','|',' ','|',' '},
                               {'_','+','_','+','_','+','_','+','_'},
                               {' ','|',' ','|',' ','|',' ','|',' '}};

        printGameBoard(gameBoard);

        System.out.println("Welcome to 5x5 tic tac toe");
        for(int i=0; i<=12 ; i++){
            Scanner sc =  new Scanner(System.in);
            System.out.println("Enter position (0-24)");
            int position = sc.nextInt();

            while(playerPos.contains(position) || cpuPos.contains(position)){
                System.out.println("Position Taken !");
                position=sc.nextInt();
            }
            placePosition(gameBoard,position,"player");

            Random rd = new Random();
            int cpuPosition = rd.nextInt(25)+1;
            while (playerPos.contains(cpuPosition) || cpuPos.contains(cpuPosition)) {
                cpuPosition = rd.nextInt(25) + 1;
            }
            placePosition(gameBoard, cpuPosition, "cpu");

            printGameBoard(gameBoard);
        }
        String result = checkWinner();
        System.out.println(result);
    }
    public static void printGameBoard (char[][] gameBoard){
        //using for-each loop
        for(char[]row : gameBoard){
            for(char c : row){
                System.out.print(c);
            }
            System.out.println();
        }

    }
    public static void placePosition (char[][]gameboard, int position, String user){
        char symbol = ' ';
        if(user.equals("player")){
            symbol = 'X';
            playerPos.add(position);
        }else if (user.equals("cpu")){
            symbol = 'O';
            cpuPos.add(position);
        }

        // use switch and case to allign position
        switch(position){
            case 1:
                gameboard[0][0] = symbol;
                break;
            case 2:
                gameboard[0][2] = symbol;
                break;
            case 3:
                gameboard[0][4] = symbol;
                break;
            case 4:
                gameboard[0][6] = symbol;
                break;
            case 5:
                gameboard[0][8] = symbol;
                break;
            case 6:
                gameboard[2][0] = symbol;
                break;
            case 7:
                gameboard[2][2] = symbol;
                break;
            case 8:
                gameboard[2][4] = symbol;
                break;
            case 9:
                gameboard[2][6] = symbol;
                break;
            case 10:
                gameboard[2][8] = symbol;
                break;
            case 11:
                gameboard[4][0] = symbol;
                break;
            case 12:
                gameboard[4][2] = symbol;
                break;
            case 13:
                gameboard[4][4] = symbol;
                break;
            case 14:
                gameboard[4][6] = symbol;
                break;
            case 15:
                gameboard[4][8] = symbol;
                break;
            case 16:
                gameboard[6][0] = symbol;
                break;
            case 17:
                gameboard[6][2] = symbol;
                break;
            case 18:
                gameboard[6][4] = symbol;
                break;
            case 19:
                gameboard[6][6] = symbol;
                break;
            case 20:
                gameboard[6][8] = symbol;
                break;
            case 21:
                gameboard[8][0] = symbol;
                break;
            case 22:
                gameboard[8][2] = symbol;
                break;
            case 23:
                gameboard[8][4] = symbol;
                break;
            case 24:
                gameboard[8][6] = symbol;
                break;
            case 25:
                gameboard[8][8] = symbol;
                break;
            default:
                break;
        }

    }
    public static String checkWinner (){
        List<List<Integer>> winningPositions = new ArrayList<>();
        winningPositions.add(Arrays.asList(1, 2, 3)); // Row 1
        winningPositions.add(Arrays.asList(4, 5, 6)); // Row 2
        winningPositions.add(Arrays.asList(7, 8, 9)); // Row 3
        winningPositions.add(Arrays.asList(1, 4, 7)); // Column 1
        winningPositions.add(Arrays.asList(2, 5, 8)); // Column 2
        winningPositions.add(Arrays.asList(3, 6, 9)); // Column 3
        winningPositions.add(Arrays.asList(1, 5, 9)); // Diagonal 1
        winningPositions.add(Arrays.asList(3, 5, 7)); // Diagonal 2

        int playerWinCount = 0;
        int cpuWinCount = 0;

        // Check if player or CPU has winning positions
        for (List<Integer> positionList : winningPositions) {
            if (playerPos.containsAll(positionList)) {
                playerWinCount++;
            }
            if (cpuPos.containsAll(positionList)) {
                cpuWinCount++;
            }
        }

        // Determine the winner
        if (playerWinCount > cpuWinCount) {
            return "Player wins!";
        } else if (cpuWinCount > playerWinCount) {
            return "CPU wins!";
        } else {
            return "It's a tie!";
        }
    }

}
