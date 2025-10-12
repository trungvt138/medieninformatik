import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Pferd {
    String name;
    double geschwindigkeit;
    double strecke;
    boolean hunger;

    public Pferd(String name, double geschwindigkeit, double strecke, boolean hunger) {
        this.name = name;
        this.geschwindigkeit = geschwindigkeit;
        this.strecke = strecke;
        this.hunger = hunger;
        System.out.printf("Pferd erfolgreich erzeugt mit:\n%s, %.2f, %.2f, %s", name, geschwindigkeit, strecke, hunger);
    }
}