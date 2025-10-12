import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Pferd {
    String name;
    double geschwindigkeit;
    double strecke;
    boolean hunger;

    public Pferd(String name, double geschwindigkeit) {
        this.name = name;
        this.geschwindigkeit = geschwindigkeit;
        this.strecke = 0.0;
        this.hunger = false;
    }
}