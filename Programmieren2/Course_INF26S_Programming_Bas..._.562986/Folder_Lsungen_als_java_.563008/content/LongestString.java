//Aufgabe 4

import java.util.Collection;
import java.util.List;
import java.util.ArrayList; // Wird für einen Testfall mit null benötigt

/**
 * Eine Hilfsklasse, die eine Methode zum Finden des längsten Strings
 * in einer Collection bereitstellt.
 */
public class LongestString {

    /**
     * Findet in einer Collection den längsten String und gibt ihn zurück.
     * Edge-Cases:
     * Leere Collection: Wenn die Collection leer ist, wird ein leerer String "" zurückgegeben.
     * Null-Werte: Wenn die Collection selbst null ist oder null-Elemente enthält,
     * werden diese ignoriert und verursachen keinen Fehler.
     * Gleiche Längen: Wenn mehrere Strings die gleiche maximale Länge haben,
     * wird der erste gefundene String zurückgegeben.
     *
     * @param words Die Collection von Strings, die durchsucht werden soll.
     * @return Der längste gefundene String; "" falls die Collection leer oder null ist.
     */
    public static String findLongestString(Collection<String> words) {
        // Initialisiere mit einem leeren String. Das ist der Standardwert,
        // falls kein String gefunden wird (z.B. bei einer leeren Liste).
        String longestString = "";

        // Sicherheitsabfrage: Wenn die gesamte Collection null ist, gib direkt den leeren String zurück.
        if (words == null) {
            return longestString;
        }

        // Iteriere durch jedes Wort in der Collection.
        for (String word : words) {
            // Prüfe, ob das Wort nicht null ist UND ob es länger als das bisher längste ist.
            if (word != null && word.length() > longestString.length()) {
                // Wenn ja, merke dir dieses Wort als das neue längste.
                longestString = word;
            }
        }
        return longestString;
    }

    /**
     * Die main-Methode testet die Funktion findLongestString mit verschiedenen Szenarien.
     */
    public static void main(String[] args){
        // --- Testfall 1: Standard-Anwendung ---
        System.out.println("--- Testfall 1: Standard-Anwendung ---");
        Collection<String> words = List.of("Apple", "Orange", "Strawberry", "Banana");
        String longest = findLongestString(words);
        System.out.println("Längster String: " + longest); // Erwartet: Strawberry
        System.out.println("----------------------------------------\n");

        // --- Testfall 2: Edge-Case - Leere Collection ---
        System.out.println("--- Testfall 2: Edge-Case - Leere Collection ---");
        Collection<String> emptyList = List.of();
        longest = findLongestString(emptyList);
        System.out.println("Längster String in leerer Liste: '" + longest + "'"); // Erwartet: ""
        System.out.println("----------------------------------------\n");

        // --- Testfall 3: Edge-Case - Collection mit null-Werten ---
        System.out.println("--- Testfall 3: Edge-Case - Collection mit null-Werten ---");
        // List.of() erlaubt keine null-Werte, daher wird eine ArrayList verwendet.
        Collection<String> listWithNull = new ArrayList<>();
        listWithNull.add("Short");
        listWithNull.add(null);
        listWithNull.add("ThisIsTheLongest");
        longest = findLongestString(listWithNull);
        System.out.println("Längster String in Liste mit null: " + longest); // Erwartet: ThisIsTheLongest
        System.out.println("----------------------------------------\n");

        // --- Testfall 4: Edge-Case - Collection ist selbst null ---
        System.out.println("--- Testfall 4: Edge-Case - Collection ist selbst null ---");
        longest = findLongestString(null);
        System.out.println("Längster String bei null-Collection: '" + longest + "'"); // Erwartet: ""
        System.out.println("----------------------------------------\n");
    }
}