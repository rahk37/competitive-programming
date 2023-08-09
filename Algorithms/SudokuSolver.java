import java.io.IOException;

/**
 * This class will solve any sudoku board.
 */
public class SudokuSolver {
    public static void main(String[] args) throws IOException {
        //For leet code we do not need to take in input so I made this static variable of the board.
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        // Call recursive method to start the sudoku solver and after print the solved board.
        solveSudoku(board);
        printBoard(board);
    }

    /**
     * Prints the board row by column with a space in between
     * the numbers for reading clarity.
     * @param board the sudoku game board as a double char array.
     */
    private static void printBoard(char[][] board) {
        //Loop through row by column and print the sudoku value
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    /**
     * Recursive method that solves the given sudoku board.
     * @param board the sudoku game board as a double char array.
     * @return true if the board has been solved, false if the board is
     * still being solved.
     */
    private static boolean solveSudoku(char[][] board) {
        //Call helper method that finds an empty spot on the board
        //Save the helper method return value (x, y coordinate) as an array length 2.
        int[] gridCoord = findEmptySpot(board);
        int xCoord = gridCoord[0];
        int yCoord = gridCoord[1];

        //Exit condition, if the xCoord and yCoord are these values then the sudoku
        // board as been solved.
        if (xCoord  == -1 && yCoord == -1) {
            return true;
        }

        //Try possible sudoku values 1 - 9 (note the board uses char type as values).
        for (char x = '1'; x <= '9'; x++) {
            // If the position value is a valid position then make that x value the new board value.
            if (checkValid(x, board, xCoord, yCoord)) {
                board[xCoord][yCoord] = x;
                // Recursively call the method to make sure the board has more missing values.
                if (solveSudoku(board)) {
                    return true;
                }
                // Since the board still has missing values and the other value did not work the value becomes empty.
                board[xCoord][yCoord] ='.';
            }
        }
        return false;
    }

    /**
     * Helper method that finds an empty spot on the board.
     * @param board the sudoku game board as a double char array.
     * @return returns the x, y coord of the empty spot in an array.
     */
    private static int[] findEmptySpot(char[][] board) {
        //Initialize the x, y coordinates as -1 so we know if they have been adjusted. (Used in exit condition)
        int[] retVal = new int[2];
        retVal[0] = -1;
        retVal[1] = -1;

        // Iterate through the board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                //Check for empty Sudoku value and if there is one save the x, y coordinate in the array.
                if (board[i][j] == '.') {
                    retVal[0] = i;
                    retVal[1] = j;
                    return retVal;
                }
            }
        }
        return retVal;
    }

    /**
     * Helper method that will see if the row, column and grid have the specific
     * number in the sudoku checking condition.
     * @param val value on the board.
     * @param board the sudoku game board as a double char array.
     * @param valI xCoordinate
     * @param valJ yCoordinate
     * @return true if the number is valid at that postion.
     */
    private static boolean checkValid(char val, char[][] board, int valI, int valJ) {
        return checkCol(val, board, valI, valJ) && checkRow(val, board, valI, valJ) &&
                checkGrid(val, board, valI, valJ);
    }

    /**
     * Helper method that checks the specific sudoku grid for the number at
     * the specific x, y coordinate.
     * @param val value on the board.
     * @param board the sudoku game board as a double char array.
     * @param valI x coordinate
     * @param valJ y coordinate
     * @return true if the number is valid false if it is not valid.
     */
    private static boolean checkGrid(char val, char[][] board, int valI, int valJ) {
        // Using floor division to find what sudoku grid the x, y coordinate is in.
        int boxX = Math.floorDiv(valJ, 3);
        int boxY = Math.floorDiv(valI, 3);
        boolean retVal = true;
        //Loop through the sudoku grid that has been found and determine if the value can be used in the grid.
        for (int i = boxY * 3; i < boxY * 3 + 3; i++) {
            for (int j = boxX * 3; j < boxX * 3 + 3; j++) {
                if (valI != i && valJ != j && board[i][j] == val) {
                    retVal = false;
                    break;
                }
            }
        }
        return retVal;
    }

    /**
     * Check the column to see if the given value is valid.
     * @param val value on the board.
     * @param board the sudoku game board as a double char array.
     * @param valI x coordinate
     * @param valJ y coordinate
     * @return true if it is valid false if it is not valid.
     */
    private static boolean checkCol(char val, char [][] board, int valI, int valJ) {
        boolean retVal = true;
        //Iterate through the column and determine if the value is in the column.
        for (int i = 0; i < 9; i++) {
            if (valI != i && board[i][valJ] == val) {
                retVal = false;
                break;
            }
        }
        return retVal;
    }

    /**
     * Check the row to see if the given value is valid.
     * @param val value on the board.
     * @param board the sudoku game board as a double char array.
     * @param valI x coordinate
     * @param valJ y coordinate
     * @return true if it is valid false if it is not valid.
     */
    private static boolean checkRow(char val, char [][] board, int valI, int valJ) {
        boolean retVal = true;
        //Iterate through the row and determine if the value is in the column.
        for (int i = 0; i < 9; i++) {
            if (valJ != i && board[valI][i] == val) {
                retVal = false;
                break;
            }
        }
        return retVal;
    }
}

