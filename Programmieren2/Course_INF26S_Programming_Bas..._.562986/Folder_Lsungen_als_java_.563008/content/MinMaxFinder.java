import java.util.List;
import java.util.NoSuchElementException;

/**
 * HINWEIS ZUR DATEISTRUKTUR:
 * In Java gilt die grundlegende Regel, dass jede "public" deklarierte top-level Klasse
 * oder jeder "public" Record in einer eigenen Datei liegen muss, deren Name exakt
 * dem Namen der Klasse/des Records entspricht (z.B. "MinMax.java").
 * Warum ist das so?
 * 1. Organisation: Es schafft eine klare und vorhersagbare Projektstruktur.
 * Jeder weiß sofort, in welcher Datei der Code für "MinMax" zu finden ist.
 * 2. Compiler-Effizienz: Der Java-Compiler verlässt sich auf diese Konvention,
 * um den Quellcode für öffentliche Typen schnell und ohne das Durchsuchen
 * mehrerer Dateien zu finden.
 * In diesem Beispiel wurde der Record "MinMax" im selben File wie die Logik-Klasse
 * belassen, um alles an einem Ort zu zeigen. In einem realen Projekt würde man
 * ihn jedoch in eine eigene Datei "MinMax.java" auslagern, um diese Konvention
 * einzuhalten.
 */
record MinMax(int min, int max) {
}

/**
 * Eine Hilfsklasse, die Methoden zur Berechnung von Min/Max-Werten bereitstellt.
 */
public class MinMaxFinder {

    /**
     * Findet den kleinsten und größten Wert in einer Liste von Ganzzahlen
     * mithilfe einer iterativen for-each-Schleife.
     *
     * @param numbers Die Eingabe-Liste, die durchsucht werden soll.
     * @return Ein MinMax-Record, der den kleinsten und größten Wert enthält.
     * @throws NoSuchElementException wenn die Liste leer oder null ist.
     */
    public static MinMax findMinMax(List<Integer> numbers) {
        // Edge-Case: Prüfen, ob die Liste null oder leer ist.
        if (numbers == null || numbers.isEmpty()) {
            throw new NoSuchElementException("Die Eingabe-Liste darf nicht leer sein.");
        }

        // Initialisiere min mit dem größtmöglichen und max mit dem kleinstmöglichen Wert,
        // damit der erste Vergleich in der Schleife die Werte korrekt setzt.
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // Iteriere durch jede Zahl in der Liste.
        for (int number : numbers) {
            // Wenn die aktuelle Zahl kleiner als das bisherige Minimum ist, aktualisiere min.
            if (number < min) {
                min = number;
            }
            // Wenn die aktuelle Zahl größer als das bisherige Maximum ist, aktualisiere max.
            if (number > max) {
                max = number;
            }
        }

        // Erstellt und gibt den neuen MinMax-Record mit den gefundenen Werten zurück.
        return new MinMax(min, max);
    }

    /**
     * Die main-Methode testet die findMinMax-Funktion mit verschiedenen Szenarien.
     */
    public static void main(String[] args) {
        // Die Testfälle funktionieren unverändert, da List.of() eine Liste zurückgibt.

        // --- Testfall 1: Standard-Anwendung ---
        System.out.println("--- Testfall 1: Standard-Collection ---");
        List<Integer> list1 = List.of(5, 10, 0, 42, -5, 23);
        MinMax result1 = findMinMax(list1);
        System.out.println("Collection: " + list1);
        System.out.println("Ergebnis: " + result1); // Erwartet: MinMax[min=-5, max=42]
        System.out.println("----------------------------------------\n");

        // --- Testfall 2: Nur ein Element ---
        System.out.println("--- Testfall 2: Collection mit einem Element ---");
        List<Integer> list2 = List.of(7);
        MinMax result2 = findMinMax(list2);
        System.out.println("Collection: " + list2);
        System.out.println("Ergebnis: " + result2); // Erwartet: MinMax[min=7, max=7]
        System.out.println("----------------------------------------\n");

        // --- Testfall 3: Leere Collection (löst eine Exception aus) ---
        System.out.println("--- Testfall 3: Leere Collection ---");
        List<Integer> emptyList = List.of();
        try {
            findMinMax(emptyList);
        } catch (NoSuchElementException e) {
            System.err.println("Erfolgreich abgefangen: " + e.getMessage());
        }
    }
}