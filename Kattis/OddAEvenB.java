import java.io.IOException;
import java.util.Scanner;

/**
 *
 * Group Members: Lucas Cabello, Milan Kablar, Colton Rivard, Riley Wikel
 */
public class OddAEvenB {
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        int numNotes = sc.nextInt(); // takes input
        sc.close();

        int count = 0;
        int index = 1;
        int[] notes = new int[100];
        notes[0] = 1; // the array of notes starts with the first element as 1

        boolean loop = true;
        while(loop) {

            if(notes[0] == numNotes) {
                // the algorithm will continue to run until the first element is equal to the number of notes which means that no more songs/sequences can be made
                count++; // this is to count the last song/sequence
                loop = false;
                break;
            }

            if(sum(notes) == numNotes) { // if the sum of the notes equals number of notes, then the song count is incremented and index moves back in the array
                count++;
                index--;
                notes[index] = 0;
                index--;

                if(index == 0) { // if the index is back at the first element, then the first element increments by 1
                    notes[0]++;
                    index++;
                }
            }

            if(sum(notes) > numNotes) { // if the sum of notes is more than the number of notes, then the index only moves make in the array
                index--;
                notes[index] = 0;
                index--;

                if(index == 0) { // if the index is back at the first element, then the first element increments by 1
                    notes[0]++;
                    index++;
                }
            }
            
            if(notes[index-1]%2 == 1) { // if the previous element is odd, then increment next element by 2 assuming it is 0 or even
                notes[index] += 2;
                index++;

            } else if(notes[index-1]%2 == 0) { // if the previous element is even, then increment next element by 2 assuming it is odd or if 0 set it to 1

                if(notes[index] == 0) {
                    notes[index] = 1;

                } else {
                    notes[index] += 2;
                }

                index++;
            }
        }

        System.out.println(count);
    }

    /**
     * Simple sum function that I wrote to find the sum of all elements in a primitive int array
     * @param array
     * @return sum
     */
    public static int sum(int[] array) {

        int sum = 0;
        for(int i = 0; i < array.length; i++) {
            sum += array[i];
        }

        return sum;
    }
}