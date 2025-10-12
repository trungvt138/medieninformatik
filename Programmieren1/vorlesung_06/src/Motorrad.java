import java.util.Scanner;

public class Motorrad {
//    enum typ {SPORTLER, CHOPPER, CRUISER, ENDURO};
//    enum farbe {SCHWARZ, ROT, BLAU, SILBER};
    String typ;
    String farbe;
    int hubraum;

     Motorrad() {

     }

     void schreibeInfos() {
         System.out.printf("Typ: %s\nFarbe: %s\nHubraum: %d\n", typ, farbe, hubraum);;
     }

     double berechneSteuern() {
         return hubraum/25.0 * 1.84;
     }
}
