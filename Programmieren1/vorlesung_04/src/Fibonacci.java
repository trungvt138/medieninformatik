import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Wie viele Elemente der Fibonacci-Folge sollen ausgegeben werden? ");
        int n = input.nextInt();
        int temp0 = 0;
        int temp1 = 1;
        for (int i = 1; i <= n; i++) {
            System.out.printf("%d ", Math.max(temp0, temp1));
            if (temp1 > temp0) {
                temp0 += temp1;
            } else {
                temp1 += temp0;
            }
        }
    }
}
