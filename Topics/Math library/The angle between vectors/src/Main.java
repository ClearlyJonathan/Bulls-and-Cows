import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        double pointx1, pointx2, pointy1, pointy2;
        pointx1 = scanner.nextDouble();
        pointx2 = scanner.nextDouble();
        scanner.nextLine();
        pointy1 = scanner.nextDouble();
        pointy2 = scanner.nextDouble();
        double uv = pointx1 * pointy1 + pointx2 * pointy2;
        double uMod = Math.sqrt(Math.pow(pointx1, 2) + Math.pow(pointx2, 2));
        double vMod = Math.sqrt(Math.pow(pointy1, 2) + Math.pow(pointy2, 2));
        double angle = uv/ (uMod * vMod);
        System.out.println((int) (Math.acos(angle) * 180 / Math.PI));


    }
}