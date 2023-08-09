import java.util.Random;

public class TestCaseGenerator {
    public static void main(String[] args) {
        int sequenceSumInput = produceSequenceSumInput();
        int squaredSumInput = produceSquaredSumInput();
        System.out.println("Sequence Sum Input: " + sequenceSumInput);
        System.out.println("Squared Sum Input: " + squaredSumInput);
    }

    private static int produceSquaredSumInput() {
        double upperBound = Math.pow(10,9);
        int lowerBound = 2;
        Random random = new Random();
        return random.nextInt(((int)upperBound + 1) - lowerBound) + lowerBound;
    }

    private static int produceSequenceSumInput() {
        double upperBound = Math.pow(10,9);
        int lowerBound = 1;
        Random random = new Random();
        return random.nextInt(((int)upperBound + 1) - lowerBound) + lowerBound;
    }
}

