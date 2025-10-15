public class DurchlaufZaehler {
    public static void main(String[] args) {
        int count = 1;
        int val = 1;
        while(count <= 10){
            System.out.printf("%d. Durchlauf: %d\n", count++, val);
            val <<= 1;
        }
    }
}
