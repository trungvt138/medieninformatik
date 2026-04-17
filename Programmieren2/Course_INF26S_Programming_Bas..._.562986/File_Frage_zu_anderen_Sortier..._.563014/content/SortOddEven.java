import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class SortOddEven {
    public static void main (String[] args){
        IntStream intstream = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        List<Integer> sortedList = intstream
                /*
                 * 1. Umwandlung von int (primitiv) zu Integer (Objekt)
                 *
                 * WARUM .boxed()?
                 * Primitive Streams (wie IntStream) sind zwar generell performanter,
                 * da sie das "Boxing" (Erstellen von Wrapper-Objekten) vermeiden.
                 *
                 * DAS PROBLEM: Die Methode intstream.sorted() akzeptiert KEINEN Comparator.
                 * Sie kann nur nach der natürlichen Reihenfolge (1, 2, 3...) sortieren.
                 *
                 * DIE LÖSUNG:
                 * Um eine eigene Sortierlogik (gerade/ungerade) anzuwenden, brauchen wir
                 * die Methode .sorted(Comparator), und diese existiert nur für
                 * Objekt-Streams (z.B. Stream<Integer>).
                 *
                 * Daher ist .boxed() hier der notwendige Schritt, um den Stream-Typ zu wechseln.
                 */
                .boxed()

                /*
                 * 2. Sortierung mit einem zweistufigen Comparator
                 */
                .sorted(Comparator
                        /*
                         * Stufe 1: Sortiere nach dem Rest von (n % 2).
                         * - Gerade Zahlen (2, 4, 6...) bekommen den Wert 0.
                         * - Ungerade Zahlen (1, 3, 5...) bekommen den Wert 1.
                         * Da 0 vor 1 kommt, stehen alle geraden Zahlen vor den ungeraden.
                         */
                        .comparingInt((Integer n) -> n % 2)

                        /*
                         * Stufe 2: Falls Stufe 1 gleich ist (z.B. 2 und 4 sind beide "0").
                         * Sortiere einfach nach dem natürlichen Zahlenwert (n).
                         * (Stellt sicher, dass [2, 4, 6, 8] rauskommt und nicht [8, 2, 6, 4])
                         */
                        .thenComparing(n -> n))

                // 3. Sammle das Ergebnis in einer Liste
                .toList();

        System.out.println(sortedList);
        // Ausgabe: [2, 4, 6, 8, 1, 3, 5, 7, 9]
    }
}