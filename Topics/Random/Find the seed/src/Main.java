import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a, b, n, k;
        a = scanner.nextInt(); // Initial seed
        b = scanner.nextInt(); // Ending Seed
        n = scanner.nextInt(); // Amounts of numbers to generate
        k = scanner.nextInt(); // Range to generate

        int minimumOfMaximum;
        minimumOfMaximum = getMinSeedOfPseudoNumbers(a, b, n, k);



    }
    static int getMinSeedOfPseudoNumbers(int initialSeed, int endingSeed, int n, int k) {
        int[] numbers = new int[n];
        int[] maximums = new int[endingSeed - initialSeed + 1];
        for (int seed = initialSeed; seed <= endingSeed; seed++) {
            Random random = new Random(seed);
            // Gets pseudonumbers of each seed
            for (int i = 0; i < n; i++) {
                numbers[i] = random.nextInt(0, k);
            }
            // Compare numbers and get maximum
            int tempMax = numbers[0];
            for (int number = 0; number < numbers.length; number++) {
                if (tempMax <= numbers[number]) {
                    tempMax = numbers[number];
                }
            }
            // Get the maximums
            maximums[seed - initialSeed] = tempMax;
        }

        // Get the minimum number
        int minNumber = maximums[0]; // Initial value
        for (int i = 0; i < maximums.length - 1; i++) {
            if (minNumber >= maximums[i]) {
                minNumber = maximums[i];
            }
        }
        // Get index
        for (int index = 0; index < maximums.length; index++ ){
            if (maximums[index] == minNumber) {
                System.out.println(index + initialSeed);
                System.out.println(minNumber);
                return index + initialSeed;
            }
        }
        return -1;
    }
}