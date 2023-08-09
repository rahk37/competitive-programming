import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class KittenTree {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out);
        String kitten = scan.next();
        scan.nextLine();

        HashMap<String, String> tree = new HashMap<>();
        while(true) {
            String s = scan.next();
            if(s.equals("-1")) {
                break;

            } else {
                String[] leaves = scan.nextLine().split(" ");
                for (String leaf : leaves) tree.put(leaf, s);
            }

        }

        while(true) {

            writer.print(kitten);
            if(!tree.containsKey(kitten)) {
                break;

            } else {
                writer.print(" ");
                kitten = tree.get(kitten);
            }
        }

        scan.close();
        writer.close();
    }
}
