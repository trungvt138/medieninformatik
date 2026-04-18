import java.util.ArrayList;
import java.util.Collection;
import java.util.List; // Für die main-Methode

/**
 * Implementiert eine lineare Suche, um den Index einer Zahl in einer Collection zu finden.
 */
public class LinearSearch {

    /**
     * Sucht in einer Collection von ganzen Zahlen nach einer bestimmten Zahl und gibt deren Index zurück.
     * Diese Methode implementiert eine lineare Suche. Sie durchläuft die Elemente nacheinander.
     * Bei mehrfachen Vorkommen wird der Index des ersten gefundenen Elements zurückgegeben.
     *
     * @param numbers Die Collection von ganzen Zahlen, die durchsucht werden soll.
     * @param numberToSearchFor Die Zahl, nach der gesucht wird.
     * @return Der Index des ersten Vorkommens der Zahl, oder -1, wenn die Zahl nicht gefunden wurde.
     */
    public static int findIndexOfNumber(Collection<Integer> numbers, int numberToSearchFor) {
        // Tipp aus der Aufgabenstellung: Die Collection wird in eine ArrayList umgewandelt.
        // Das ist notwendig, da nur Listen einen garantierten Index-Zugriff über .get(index) bieten.
        var numbersAsList = new ArrayList<>(numbers);

        // Eine klassische for-Schleife wird hier verwendet, weil wir den Index benötigen.
        //
        // WARUM KEINE FOR-EACH-SCHLEIFE?
        // Eine for-each-Schleife (z.B. for (int number : numbersAsList)) liefert nur den Wert
        // des Elements, aber nicht dessen Position (den Index). Da die Aufgabe aber explizit
        // die Rückgabe des Index verlangt, ist die for-each-Schleife hier ungeeignet.
        for (int index = 0; index < numbersAsList.size(); index++) {
            // Überprüfe, ob das Element am aktuellen Index der gesuchten Zahl entspricht.
            if (numbersAsList.get(index) == numberToSearchFor) {
                // Wenn die Zahl gefunden wurde, gib sofort den aktuellen Index zurück.
                // Dadurch wird sichergestellt, dass es der Index des ersten Vorkommens ist.
                return index;
            }
        }

        // Wenn die Schleife komplett durchläuft, ohne die Zahl zu finden, gib -1 zurück.
        return -1;
    }

    /**
     * Die main-Methode zum Testen der findIndexOfNumber-Funktion.
     */
    public static void main(String[] args) {
        // Beispiel-Collection für die Tests erstellen. Enthält eine doppelte Zahl (25).
        Collection<Integer> numberCollection = List.of(10, 25, 5, 42, 25, 8);
        System.out.println("Test-Collection: " + numberCollection);
        System.out.println("------------------------------------");

        // --- Testfall 1: Zahl ist vorhanden ---
        int numberToFind1 = 42;
        int index1 = findIndexOfNumber(numberCollection, numberToFind1);
        System.out.println("Suche nach: " + numberToFind1 + " -> Gefundener Index: " + index1); // Erwartet: 3

        // --- Testfall 2: Zahl ist NICHT vorhanden ---
        int numberToFind2 = 99;
        int index2 = findIndexOfNumber(numberCollection, numberToFind2);
        System.out.println("Suche nach: " + numberToFind2 + " -> Gefundener Index: " + index2); // Erwartet: -1

        // --- Testfall 3: Zahl kommt mehrfach vor ---
        int numberToFind3 = 25;
        int index3 = findIndexOfNumber(numberCollection, numberToFind3);
        System.out.println("Suche nach: " + numberToFind3 + " -> Gefundener Index: " + index3); // Erwartet: 1 (Index des ersten Vorkommens)
    }
}