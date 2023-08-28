import java.util.ArrayList;
import java.util.Scanner;

public class oddecho {
    private  static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int N = sc.nextInt();
        ArrayList<String> words = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            words.add(sc.nextLine());
        }

        for (int i = 0; i < words.size(); i++) {
            if (i % 2 != 0) {
                System.out.println(words.get(i));
            }
        }
    }
}
