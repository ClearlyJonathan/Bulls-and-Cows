package bullscows;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static final int MAX_CODE_LENGTH = 36;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length, posSymbols;
        try {
            System.out.println("Input length of code: ");
            length = Integer.parseInt(scanner.nextLine());
            System.out.println("Input the number of possible symbols in the code: ");
            posSymbols = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Error: Invalid input");
            return;
        }

        if (length > posSymbols) {
            System.out.println("Error, length is higher than possible symbols");
            return;
        }
        String code = new String();
        code = GenerateCode(length, code, posSymbols);
        if (code.equals("Error")) {
            System.out.println(code);
            return;
        }
        char[] secretCode = code.toCharArray();
        boolean isGameOver = false;
        int bulls = 0, cows = 0, turns = 0;
        String enteredCode;
        char[] eCode;

        System.out.println("Okay, let's start a game!");


         while (!isGameOver) {
             System.out.printf("Turn %d:\n", turns);
             enteredCode = scanner.nextLine();
             eCode = enteredCode.toCharArray();
             try {
                 isGameOver = OutputDeterminedGrade(code, secretCode, bulls, cows, eCode);
             } catch (Exception e) {
                 System.out.println("Error: Output != length of code");
                 return;
             }
             turns++;
         }

    }

    private static boolean OutputDeterminedGrade(String code, char[] secretCode, int bulls, int cows, char[] eCode) {
        // Calculate and determine Grade level
        for (int i = 0; i < secretCode.length; i++) {
            for (int j = 0; j < secretCode.length; j++) {
                if (eCode[i] == secretCode[j] && i == j) {
                    bulls++;
                } else if (eCode[i] == secretCode[j] && i != j) {
                    cows++;
                }
            }
        }
        if (bulls == secretCode.length) {
            System.out.printf("Congratulations! You guessed the secret code: %s", code);
            System.out.printf("Grade: %d bulls", secretCode.length);
            return true;
        }
        if (bulls == 0 && cows == 0) {
            System.out.printf("Grade: None. 0 bull(s) and 0 cow(s)");
        } else if (bulls == 0) {
            System.out.printf("Grade: %d cow(s). ", cows);
        } else if (cows == 0) {
            System.out.printf("Grade: %d bull(s).", bulls);
        }
        else {
            System.out.printf("Grade : %d bull(s) and %d cow(s).", bulls, cows);
        }
        System.out.println();
        return false;
    }
    private static String GenerateCode(int length, String cde, int symbolsLength) {
        long pseudoRandomNumber = System.nanoTime();
        char[] characters = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        if (length > MAX_CODE_LENGTH || length == 0 || symbolsLength > (characters.length) + 10) {
            return "Error";
        }
        String code = cde;
        StringBuilder getValueOfLongToString = new StringBuilder();
        // From a random long, loop and extract unique numbers

        for (int i = 0; code.length() < length; i++) {
            // Check if there is no more unique numbers
            if (i >= String.valueOf(pseudoRandomNumber).length()) {

                if (code.length() != length) {
                    code = GenerateCode(length, code, symbolsLength);
                    return code;
                }
                return code;
            }

            getValueOfLongToString.append(String.valueOf(pseudoRandomNumber).toCharArray()[i]);
            if (code.isEmpty()){
                code += getValueOfLongToString;
            } else if (!code.contains(getValueOfLongToString) && code.isEmpty() == false) {
                code += getValueOfLongToString;
            }
            if (code.length() != length) {
                code += characters[i];
            }
            getValueOfLongToString.delete(0, getValueOfLongToString.length());
        }
        if (code.length() != length) {
            code += GenerateCode(length, code, symbolsLength);
        }
        // If everything works well.
        String stars = "";
        for (int i = 0; i < length; i++) {
            stars += "*";
        }
        int lastSymbol = 0;
        if (symbolsLength - 10 <= 0){
            lastSymbol = 0;
        } else {
            lastSymbol = (symbolsLength - 10) - 1;
        }
        System.out.printf("The secret code is prepared: %s (0-9, %s-%s)", stars, characters[0], characters[lastSymbol]);
        return code;
    }
}
