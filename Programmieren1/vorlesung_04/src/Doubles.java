//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Doubles {
    public static void main(String[] args) {
       int i = 1;
       System.out.printf("%d", i);
       while (i < 128) {
           System.out.printf(", %d", i*=2);
       }
    }
}