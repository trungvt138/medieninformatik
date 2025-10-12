import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Pferd {
    String name;
    double geschwindigkeit;
    double strecke;
    boolean hunger;
    static List<Pferd> pferde = new ArrayList<>();

    public Pferd(String name, double geschwindigkeit) {
        this.name = name;
        this.geschwindigkeit = geschwindigkeit;
        this.strecke = 0.0;
        this.hunger = false;
        pferde.add(this);
    }

    public void traben() {
        hunger = Math.random() < 0.5;
        if (hunger) {
            geschwindigkeit -= 0.1;
        } else {
            geschwindigkeit += 0.1;
        }
        System.out.printf("%s trabt %.1f Meter\n", name, strecke+=geschwindigkeit);
    }

    public String toString() {
        return String.format("%s - Gesch: %.1f Strecke: %.1f", name, geschwindigkeit, strecke);
    }

    static void resetPony() {
        for (Pferd p : pferde) {
            p.strecke = 0.0;
        }
    }

    static void racePony(double distance) {
        for (Pferd p : pferde) {
            while (p.strecke < distance) {
                p.traben();
            }
        }
    }
}