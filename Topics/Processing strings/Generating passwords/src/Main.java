import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        final char[] numbers = "1234567890".toCharArray();

        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        int n = scanner.nextInt();

        String pass = "";

        for (int i = 0; pass.length() < n; i++ ) {
            // If the password is empty, assign a letter
            boolean isNotComplete = pass.length() < n;
            if (pass.isEmpty()) {
                if (a != 0 ) { pass += letters[getLoopedValueForLetters(i)]; }
                if (b != 0) { pass += Character.toLowerCase(letters[getLoopedValueForLetters(i)]); }
                if (c != 0) { pass += numbers[getLoopedValueForNumbers(i)]; }

                isNotComplete = pass.length() < n;
            }
            if (a > 0 && isNotComplete) {
                pass = setUpperLetters(letters, pass, i);
                isNotComplete = pass.length() < n;
                a--;
            }
            if (b > 0 && isNotComplete) {
                pass = setLowerLetters(letters, pass, i);
                isNotComplete = pass.length() < n;
                b--;
            }
            if (c > 0 && isNotComplete) {
                pass = setNumbers(numbers, pass, i);
                isNotComplete = pass.length() < n;
                c--;
            }
            //Requirements fulfilled
            if (a + b + c == 0) {
                if (pass.isEmpty() && n >= 2) {
                    pass += 'A' + 'z';
                }
                if (pass.isEmpty() && n == 1) {
                    pass += 'A';
                }
                if (pass.length() < n && isNotComplete) {
                    pass = setNumbers(numbers, pass, i + 1);
                    isNotComplete = pass.length() < n;
                    if (pass.length() < n && isNotComplete) {
                        pass = setUpperLetters(letters, pass, i + 1);
                        isNotComplete = pass.length() < n;
                    } if (pass.length() < n && isNotComplete) {
                        pass = setLowerLetters(letters, pass, i + 1);
                    }
                }
            }
        }
        System.out.println(pass);
    }

    private static String setNumbers(char[] numbers, String pass, int i ) {
        if (pass.charAt(i) == numbers[getLoopedValueForNumbers(i)]) {
            pass += numbers[getLoopedValueForNumbers(i + 2)];
            return pass;
        }
        pass += numbers[getLoopedValueForNumbers(i)];
        return pass;
    }
    private static  String setLowerLetters(char[] letters, String pass, int i) {
        if (pass.charAt(i) == Character.toLowerCase(letters[getLoopedValueForLetters(i)])) {
            pass += Character.toLowerCase(letters[getLoopedValueForLetters(i + 1)]);
            return pass;
        }
        pass += Character.toLowerCase(letters[getLoopedValueForLetters(i)]);
        return pass;
    }
    private static String setUpperLetters(char[] letters, String pass, int i) {
        if (pass.charAt(i) == letters[getLoopedValueForLetters(i)]) {
            pass += letters[getLoopedValueForLetters(i + 1) ];
            return pass;
        }
        pass += letters[getLoopedValueForLetters(i)];
        return pass;
    }


    public static int getLoopedValueForLetters(int a) {
        int loopedTimes = a / 26;

        if (a >= 26) {
            return a - (26 * loopedTimes);
        }
        return a;
    }

    public static  int getLoopedValueForNumbers(int a) {
        int loopedTimes = a / 10;
        if (a >= 10) {
            return a - (10 * loopedTimes);
        }
        return a;
    }
}