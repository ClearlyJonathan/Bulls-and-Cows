import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        String text = scanner.nextLine();
        String[] parts = text.split("");

        for (int i = 0; i < parts.length; i++) {
            sb.append(parts[i]).append(parts[i]);
        }
        System.out.println(sb.toString());
    }
}