import java.util.List;

public class MeanNumbers { // Definiert die Klasse 'MeanNumbers'.
    public static void main(String[] args) { // Startpunkt des Programms.
        // Erstellt eine unveränderliche Liste von Integer-Zahlen.
        List<Integer> numbers = List.of(9, 1, 5, 3, 2, 4, 6);

        // --- BERECHNUNG 1: Summe aller Zahlen ---
        // .stream() erstellt einen Stream (Daten-Pipeline) aus der Liste: [9, 1, 5, 3, 2, 4, 6]
        // .reduce() kombiniert (reduziert) alle Elemente des Streams zu einem einzigen Wert (hier der Summe).
        // 0 ist der Startwert ('identity') für die Summe ('subtotal').
        // (subtotal, number) -> subtotal + number ist die Lambda-Funktion (Akkumulator):
        // Sie wird für jedes 'number' im Stream aufgerufen und zum 'subtotal' addiert.
        // Ablauf: 0+9=9 -> 9+1=10 -> 10+5=15 ... Endsumme ist 30.
        double sum = numbers.stream().reduce(0, (subtotal, number) -> subtotal + number);

        // (Alternative 1) Effizientere Summierung durch Umwandlung in einen IntStream.
        // double sum = numbers.stream().mapToInt(Integer::intValue).sum();

        // --- BERECHNUNG 2: Anteile der Zahlen an der Summe ---
        var fractions = numbers.stream() // Erstellt einen *neuen* Stream aus der Liste: [9, 1, 5, ...]

                // .map() transformiert (mappt) jedes Element 'n' im Stream.
                // (n -> (double) n / sum) ist die Lambda-Regel für die Transformation:
                // 1. (double) n: Wandle die Ganzzahl 'n' in eine Kommazahl (double) um.
                // 2. / sum: Teile diese Kommazahl durch die *zuvor berechnete* Gesamtsumme (30.0).
                // z.B. wird 9 zu (9.0 / 30.0) = 0.3
                //      wird 1 zu (1.0 / 30.0) = 0.0333...
                .map(n -> (double) n / sum)

                // .toList() sammelt alle berechneten Anteile (die Kommazahlen) in einer neuen Liste.
                .toList();

        System.out.println(fractions); // Gibt die Liste der Anteile auf der Konsole aus.
    }
}