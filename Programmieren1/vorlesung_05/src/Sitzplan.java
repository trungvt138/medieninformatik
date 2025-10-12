import java.util.Scanner;

public class Sitzplan {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Reihe (1-5): ");
        int reihe = input.nextInt();
        System.out.println("Sitz (1-10): ");
        int sitz = input.nextInt();
        input.close();

        int[][] sitzplan = new int[5][10];

        for (int i=0; i<5; i++) {
            for (int j=0; j<10; j++) {
                if (i == reihe-1 && j == sitz-1){
                    sitzplan[i][j] = 1;
                } else {
                    sitzplan[i][j] = 0;
                }
                System.out.print(sitzplan[i][j]);
            }
            System.out.println();
        }
    }
}
