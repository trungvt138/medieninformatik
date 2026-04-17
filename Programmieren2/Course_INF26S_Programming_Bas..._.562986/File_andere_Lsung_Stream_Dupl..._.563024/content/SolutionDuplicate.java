import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet; // Wir benutzen absichtlich einen anderen Set-Typ

public class SolutionDuplicate {
    public static void main(String[] args) {

        // --- Beispiel 1: Zwei Sets, die als "gleich" gelten ---

        // Set 1 (ein HashSet): Die Reihenfolge beim Hinzufügen ist beliebig.
        Set<String> setA = new HashSet<>();
        setA.add("Apfel");
        setA.add("Banane");
        setA.add("Kirsche");

        // Set 2 (ein TreeSet): Speichert Elemente sortiert ("Apfel", "Banane", "Kirsche").
        // Wir fügen sie absichtlich in einer anderen Reihenfolge hinzu.
        Set<String> setB = new TreeSet<>();
        setB.add("Kirsche");
        setB.add("Apfel");
        setB.add("Banane");

        // .equals() prüft, ob beide Sets dieselben Elemente enthalten,
        // unabhängig von der internen Reihenfolge oder dem Set-Typ (HashSet vs. TreeSet).
        boolean sindGleich = setA.equals(setB);

        System.out.println("--- Beispiel 1: Gleiche Sets ---");
        System.out.println("Set A: " + setA);
        System.out.println("Set B: " + setB);
        System.out.println("Sind Set A und Set B gleich? " + sindGleich); // Erwartet: true


        // --- Beispiel 2: Zwei Sets, die NICHT gleich sind ---

        // Set 3: Hat einen anderen Inhalt als Set A.
        Set<String> setC = new HashSet<>();
        setC.add("Apfel");
        setC.add("Banane");
        setC.add("Orange"); // "Orange" statt "Kirsche"

        boolean sindUngleich = setA.equals(setC);

        System.out.println("\n--- Beispiel 2: Ungleiche Sets ---");
        System.out.println("Set A: " + setA);
        System.out.println("Set C: " + setC);
        System.out.println("Sind Set A und Set C gleich? " + sindUngleich); // Erwartet: false
    }
}