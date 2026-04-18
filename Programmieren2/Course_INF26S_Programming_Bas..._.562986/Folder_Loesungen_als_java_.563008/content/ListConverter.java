//Aufgabe 2

import java.util.Arrays;
import java.util.List;

/**
 * Eine Klasse, die Methoden zur Konvertierung zwischen List<Integer>
 * und Integer[]-Array bereitstellt.
 */
public class ListConverter {

    /**
     * Wandelt die gegebene List<Integer> in ein Integer[]-Array um.
     * @param list Die Eingabeliste vom Typ List<Integer>.
     * @return Das resultierende Array vom Typ Integer[].
     */
    public Integer[] toArray(List<Integer> list) {
        // Die toArray-Methode der Liste wird verwendet, um die Konvertierung durchzuführen.
        // Integer[]::new ist eine Methodenreferenz, die einen leeren Integer-Array-Konstruktor bereitstellt.
        return list.toArray(Integer[]::new);
    }

    /**
     * Wandelt das gegebene Integer[]-Array in eine List<Integer> um.
     * @param array Das Eingabearray vom Typ Integer[].
     * @return Die resultierende Liste vom Typ List<Integer>.
     */
    public List<Integer> toList(Integer[] array) {
        // Arrays.asList() ist eine Hilfsmethode, um ein Array schnell in eine Liste umzuwandeln.
        return Arrays.asList(array);
    }

    public static void main(String[] args){
        // Ein Objekt der Klasse erstellen, da die Methoden nicht statisch sind.
        ListConverter converter = new ListConverter();

        // --- Test für toArray ---
        // 1. Eine Beispielliste erstellen, die in ein Array umgewandelt werden soll.
        List<Integer> eingabeListe = List.of(10, 20, 30);
        // 2. Die Methode aufrufen und das Ergebnis direkt ausgeben.
        // Hinweis: Arrays.toString() wird benötigt, um den Inhalt des Arrays lesbar auszugeben.
        System.out.println("Liste zu Array: " + Arrays.toString(converter.toArray(eingabeListe)));

        // --- Test für toList ---
        // 1. Ein Beispielarray erstellen, das in eine Liste umgewandelt werden soll.
        Integer[] eingabeArray = {40, 50, 60};
        // 2. Die Methode aufrufen und die Ergebnisliste direkt ausgeben.
        System.out.println("Array zu Liste: " + converter.toList(eingabeArray));
    }
}