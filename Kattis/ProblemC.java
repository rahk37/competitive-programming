import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProblemC {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] grid = new int[4][4];

        for (int i = 0; i < 4; i++) {
            String[] ithRow = br.readLine().split(" ");
            for (int j = 0; j < 4; j++) {
                grid[i][j] = Integer.parseInt(ithRow[j]);
            }
        }

        int direction = Integer.parseInt(br.readLine());
        if (direction == 0) {
            updateGridLeft(grid, direction);

        } else if (direction ==  1) {
            updateGridUp(grid, direction);

        } else if (direction ==  2) {
            updateGridRight(grid, direction);

        } else if (direction ==  3) {
            updateGridDown(grid, direction);
        }
    }

    private static void updateGridDown(int[][] grid, int direction) {

    }

    private static void updateGridRight(int[][] grid, int direction) {
    }

    private static void updateGridUp(int[][] grid, int direction) {
    }

    private static void updateGridLeft(int[][] grid, int direction) {
        for (int i = 0; i <= 3; i++) {
            for (int j = 3; j >= 0; j--) {
                if (grid[i][j - 1] == grid[i][j] || grid[i][j - 1] == 0) {

                }
                grid[i][j-1] += grid[i][j];
                grid[i][j] = 0;
            }

        }
    }
}
