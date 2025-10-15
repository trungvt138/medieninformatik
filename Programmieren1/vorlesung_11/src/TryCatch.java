//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class TryCatch {
    public static void main(String[] args) {
        int a = 5;
        int b = 0;
        try {
            System.out.println(a/b);
        } catch (ArithmeticException e) {
            System.out.println("b darf nicht 0 sein!");
        }
    }
}