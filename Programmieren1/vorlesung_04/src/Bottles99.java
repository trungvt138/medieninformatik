public class Bottles99 {
    public static void main(String[] args) {
        int n = 99;
        while (n > 0) {
            System.out.printf("%d bottles of beer on the wall, %d bottles of beer.\n", n, n);
            System.out.printf("Take one down and pass it around, %d bottles of beer on the wall.\n", --n);
            System.out.println();
        }
    }
}
