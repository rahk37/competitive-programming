import java.io.IOException;
import java.util.Scanner;

/**
 * A flow layout manager takes rectangular objects and places them in a rectangular window from left to right.
 * If there isn’t enough room in one row for an object, it is placed completely below all the objects in the first
 * row at the left edge, where the order continues from left to right again.
 *
 * This program takes a set of rectangle dimensions and a max window width from the console
 * and computes the dimensions of the final window after all the rectangles have been placed in it.
 */
public class FlowLayout {
    public static void main(String args[]) throws IOException {
        // Used Scanner to get input from the console. Was quick enough and easier to use than BufferedReader
        Scanner sc = new Scanner(System.in);
        // Program runs until the user inputs 0
        boolean isZero = false;

        while (isZero == false) {
            int maxWidth = sc.nextInt();
            if (maxWidth == 0) {
                isZero = true;
                break;
            }

            int totalWidth = 0;
            int totalHeight = 0;
            int currentWidth = 0;
            int currentHeight = 0;

            // Takes input and computes the dimensions of final window until user inputs -1 -1
            boolean isNeg = false;

            while (isZero == false && isNeg == false) {
                int w = sc.nextInt(); // Takes in rectangle width
                int h = sc.nextInt(); // Takes in rectangle height

                // If the width and height are -1, signals the end of the list of rects and moves on to the next list of rects
                if (w == -1 && h == -1) {
                    isNeg = true;
                    break;
                }

                // If the width of rect plus other rects is greater than the maxWidth, a new row is started
                if (currentWidth + w > maxWidth) {

                    // Height of the row is added to the total height
                    totalHeight += currentHeight;
                    // Width replaces the total width
                    if (currentWidth > totalWidth) {
                        totalWidth = currentWidth;
                    }
                    // Width and height of the row are reset
                    currentWidth = 0;
                    currentHeight = 0;
                }

                // In the current row, the width of rect is added to the others
                currentWidth += w;
                // In the current row, the height of rect becomes the height of the row if greater than previous height
                if (h > currentHeight) {
                    currentHeight = h;
                }
            }

            // After the user inputs -1 -1, the last rect is computed to the window
            totalHeight += currentHeight;
            if (currentWidth > totalWidth) {
                totalWidth = currentWidth;
            }

            // Outputs dimensions of the final window rectangle
            System.out.println(totalWidth + " x " + totalHeight);
        }
        sc.close();
    }
}