import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FloodIt {
    
    /**
     * Class to store info about each square in the grid
     */
    public static class Square{
        private final int gridSize;
        public Square upSame; //The square above it that has the same color if any exists
        public Square leftSame; //The square on the left of it that has the same color if any exists
        public Square rightSame; //The square on the right of it that has the same color if any exists
        public Square downSame; //The square below it that has the same color if any exists
        public int color; //the color of this square
        public int row; //Row number the square is in
        public int column; //Column number the square is in

        /**
         * A Square in the grid that has a color and other squares
         * @param color The color of the square
         * @param row the row it is in the grid
         * @param column the column it is in the grid
         * @param gridSize the size n of the grid (grid is n x n)
         */
        public Square(int color, int row, int column, int gridSize) {
            this.gridSize = gridSize;
            this.color = color;
            this.row = row;
            this.column = column;
        }

        /**
         * Gets the neighbors of the current square, which are squares that are connected to it and have the same color
         * @return An arrayList of neighbors of the square, including the current square
         */
        public ArrayList<Square> getNeighbors(){
            ArrayList<Square> squares = new ArrayList<>();
            getNeighbors(null, squares, new boolean[gridSize][gridSize]);
            return squares;
        }

        /**
         * The recursive function to get the neighbors of the current Square
         * @param prev the previously visited square, null if first square
         * @param squares The arrayList to add the neighbors to
         * @param boolGrid A boolean grid that represents the square grid to ensure duplicate visits
         */
        protected void getNeighbors(Square prev, ArrayList<Square> squares, boolean[][] boolGrid){
            if (!boolGrid[this.row][this.column]){
                boolGrid[this.row][this.column] = true;
                squares.add(this);
                if (this.upSame != null && !this.upSame.equals(prev)){
                    this.upSame.getNeighbors(this, squares, boolGrid);
                }
                if (this.leftSame != null && !this.leftSame.equals(prev)){
                    this.leftSame.getNeighbors(this, squares, boolGrid);
                }
                if (this.rightSame != null && !this.rightSame.equals(prev)){
                    this.rightSame.getNeighbors(this, squares, boolGrid);
                }
                if (this.downSame != null && !this.downSame.equals(prev)){
                    this.downSame.getNeighbors(this, squares, boolGrid);
                }
            }
        }

        /**
         * Checks if current square is equal to another square
         * @param square the other square to compare to
         * @return true if they are equal, false if not
         */
        public boolean equals(Square square){
            return square != null && this.row == square.row && this.column == square.column && this.color == square.color;
        }

        /**
         * Refreshes the up, down, left and right of the current grid to ensure to store Squares that have the same color
         * @param grid the grid of all Squares
         */
        public void refresh(Square[][] grid){
            if (this.row > 0 && grid[this.row-1][this.column].color== this.color){
                this.upSame = grid[this.row-1][this.column];
            }
            if (this.row < (grid.length-1) && grid[this.row+1][this.column].color== this.color){
                this.downSame = grid[this.row+1][this.column];
            }
            if (this.column > 0 && grid[this.row][this.column-1].color== this.color){
                this.leftSame = grid[this.row][this.column-1];
            }
            if (this.column < (grid.length-1) && grid[this.row][this.column+1].color== this.color){
                this.rightSame = grid[this.row][this.column+1];
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int amountOfRuns = Integer.parseInt(br.readLine());

        //Setup of the data structure
        for (int i = 0; i < amountOfRuns; i++) {
            int gridSize = Integer.parseInt(br.readLine());
            Square[][] grid = new Square[gridSize][gridSize];
            for (int j = 0; j < gridSize; j++) { //Go each row
                String line = br.readLine();
                String[] nums = line.split("");

                for (int k = 0; k < nums.length; k++) { //Go each column of row
                    grid[j][k]= new Square(Integer.parseInt(nums[k]), j, k, gridSize); //Make new square in the grid
                    if (j == 0 && k != 0){ //If it is in first row but not is first cell
                        if (grid[j][k].color == grid[j][k-1].color){
                            grid[j][k].rightSame = grid[j][k-1];
                            grid[j][k-1].leftSame = grid[j][k];
                        }

                    } else if (k == 0 && j != 0){ //If it is not first row, but is in first column
                        if (grid[j-1][k].color == grid[j][k].color){
                            grid[j][k].upSame = grid[j-1][k];
                            grid[j-1][k].downSame = grid[j][k];
                        }
                    } else if (j != 0 && k != 0) { //If is not in first row or in the first column.
                        if (grid[j][k].color == grid[j][k-1].color){
                            grid[j][k].rightSame = grid[j][k-1];
                            grid[j][k-1].leftSame = grid[j][k];
                        }
                        if (grid[j-1][k].color == grid[j][k].color){
                            grid[j][k].upSame = grid[j-1][k];
                            grid[j-1][k].downSame = grid[j][k];
                        }
                    }
                }
            }

            ArrayList<Square> originSquares = new ArrayList<>(); //ArrayList of visited squares connected to the original square
            int[] amounts = new int[6]; //Amount of visits per color
            originSquares.add(grid[0][0]);
            addNeighbors(originSquares.get(0), originSquares, null);
            final int totalSize = gridSize * gridSize;
            int count = 0;
            //Go until all squares have been colored the same
            while (originSquares.size() < totalSize){
                int bestColor = 0;
                int topCount = -1;
                ArrayList<ArrayList<Square>> bestSquares = new ArrayList<>();
                for (int j = 0; j < 6; j++) {
                    bestSquares.add(new ArrayList<>());
                }

                //Go through every square connected to the first one
                for (Square square: originSquares){
                    //If not in the first row, has not been visited before and square below has a different color
                    if (square.row > 0 && !isIn(bestSquares.get((grid[square.row-1][square.column].color)-1), grid[square.row-1][square.column])
                            && grid[square.row-1][square.column].color != square.color){
                        Square newSquare = grid[square.row-1][square.column];
                        bestSquares.get(newSquare.color-1).addAll(newSquare.getNeighbors());

                    }
                    //If not in last row, has not been visited before and square above has a different color
                    if (square.row < (gridSize-1) && !isIn(bestSquares.get((grid[square.row+1][square.column].color)-1), grid[square.row+1][square.column])
                            && grid[square.row+1][square.column].color != square.color){
                        Square newSquare = grid[square.row+1][square.column];
                        bestSquares.get((newSquare.color)-1).addAll(newSquare.getNeighbors());


                    }
                    //If not in further left column, has not been visited yet and square on left has a different color
                    if (square.column > 0 && !isIn(bestSquares.get((grid[square.row][square.column-1].color)-1), grid[square.row][square.column-1])
                            && grid[square.row][square.column-1].color != square.color){
                        Square newSquare = grid[square.row][square.column-1];
                        bestSquares.get((newSquare.color)-1).addAll(newSquare.getNeighbors());

                    }
                    //If not in further right column, has not been visited yet and square on right has a different color
                    if (square.column < (gridSize-1) && !isIn(bestSquares.get((grid[square.row][square.column+1].color)-1), grid[square.row][square.column+1])
                            && grid[square.row][square.column+1].color != square.color){
                        Square newSquare = grid[square.row][square.column+1];
                        bestSquares.get((newSquare.color)-1).addAll(newSquare.getNeighbors());

                    }
                }
                for (int j = 0; j < bestSquares.size(); j++) {
                    if (bestSquares.get(j).size() > topCount){
                        bestColor = j+1;
                        topCount = bestSquares.get(j).size();
                    }
                }

                //Now that we have the best color to choose, actually change the color of the ones connected to the origin
                for (Square square:originSquares) {
                    square.color = bestColor;
                }
                if (bestSquares.size() > originSquares.size()){
                    for (Square square: originSquares) {
                        square.refresh(grid);
                    }
                } else {
                    for (Square square: bestSquares.get(bestColor-1)) {
                        square.refresh(grid);
                    }
                }
                originSquares.addAll(bestSquares.get(bestColor-1));
                amounts[bestColor-1]++;
                count++;

            }

            System.out.println(count);
            System.out.println(amounts[0] + " " + amounts[1] + " " + amounts[2] + " " + amounts[3]
                    + " " + amounts[4] + " " + amounts[5]);
        }
    }

    /**
     * Adds all the current neighbors of the first cell to the origin cell list
     * @param square The origin square
     * @param squares The list of squares related to the original squares
     * @param prev the previous square visited
     */
    public static void addNeighbors(Square square, ArrayList<Square> squares, Square prev){
        if (square.upSame != null && square.upSame != prev){
            squares.add(square.upSame);
            addNeighbors(square.upSame, squares, square);
        }
        if (square.leftSame != null && square.leftSame != prev){
            squares.add(square.leftSame);
            addNeighbors(square.leftSame, squares, square);
        }
        if (square.rightSame != null && square.rightSame != prev){
            squares.add(square.rightSame);
            addNeighbors(square.rightSame, squares, square);
        }
        if (square.downSame != null && square.downSame != prev){
            squares.add(square.downSame);
            addNeighbors(square.downSame, squares, square);
        }
    }

    /**
     * Checks if a square is in an ArrayList of Squares
     * @param squares The list of squares to check if the square is in
     * @param square The square to check if is in the list
     * @return true if the square is in the list, false if not.
     */
    public static boolean isIn(ArrayList<Square> squares, Square square){
        for (Square s: squares) {
            if (s.column == square.column && s.row == square.row){
                return true;
            }
        }
        return false;
    }
}