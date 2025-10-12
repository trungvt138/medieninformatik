import java.util.Scanner;

public class Wuerfel {
    enum Wuerfelseite {EINS, ZWEI, DREI, VIER, FUENF, SECHS};
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char cont;
         do {
            int random = (int) (Math.random() * 6) ;
            System.out.printf("Der Wuerfel zeigt %s\n",Wuerfelseite.values()[random].name());
            System.out.print("Nochmal würfeln (j/n)? ");
            cont = input.next().charAt(0);
        } while (cont == 'j');
    }
}
