import java.util.Set;
import java.util.stream.Collectors;

class SetUtil {

    /**
     * Berechnet die Schnittmenge der beiden Mengen.
     * Eine Schnittmenge enthält nur die Elemente, die *in beiden* Mengen vorhanden sind.
     *
     * @param set1 Die erste Menge.
     * @param set2 Die zweite Menge.
     * @return Ein neues Set, das die gemeinsamen Elemente von set1 und set2 enthält.
     */
    public static Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2) {
        // .stream(): Wandelt die erste Menge (set1) in einen Stream um.
        // Ein Stream ist eine Sequenz von Elementen (hier: die Zahlen aus set1),
        // die nacheinander verarbeitet werden.
        return set1.stream()

                // .filter(...): Dies ist eine "intermediäre Operation". Sie filtert den Stream.
                // Sie behält nur die Elemente, für die die Bedingung (Lambda-Ausdruck) 'true' ergibt.
                // 'e' repräsentiert nacheinander jedes Element aus set1.
                // 'e -> set2.contains(e)': Diese Bedingung prüft für jedes Element 'e' aus set1,
                // ob es auch in set2 enthalten ist.
                // Nur wenn 'e' IN set1 UND IN set2 ist, wird es weitergegeben.

                //.filter(e -> set2.contains(e)) //Lamda-Ausdruck
                .filter(set2::contains) //Methodenreferenz

                // .collect(...): Dies ist eine "terminale Operation". Sie beendet den Stream
                // und sammelt die verbliebenen (gefilterten) Elemente in einer neuen Datenstruktur.
                // 'Collectors.toSet()': Dieser Collector sammelt die Elemente in einem neuen 'Set'.
                // Das Ergebnis ist die Schnittmenge.
                .collect(Collectors.toSet());
    }
}