import java.util.List;

public class Solution {
    public static void main(String[] args) {

        var numbers1 = List.of(11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1); // Erstellt eine UNVERÄNDERLICHE Liste von Integern namens 'numbers1'.
        List<Integer> oddNumbersDescending = numbers1.stream() // Beginnt eine Stream-Verarbeitung
            .filter(n -> n % 2 == 1) // Filtert den Stream, behält nur Elemente, die ungerade sind (Modulo(%): Rest 1 bei Division durch 2).
            .sorted() // Sortiert die verbleibenden ungeraden Zahlen in ihrer natürlichen (aufsteigenden) Reihenfolge.
            .toList(); // Sammelt die Ergebnisse in einer neuen Liste und weist sie 'oddNumbersDescending' zu.
        System.out.println(oddNumbersDescending); // Gibt die Liste aus

        List<Integer> numbers2 = List.of(1, 3, 8, 3, 9, 7, 1, 5, 8, 3, 9, 4, 7, 4, 6, 0, 8, 3, 7); // Erstellt eine zweite Liste, die Duplikate enthält.
        var distinctNumbers = numbers2.stream().distinct().toList(); // Erstellt eine neue Liste ('distinctNumbers'), die nur die einzigartigen Elemente aus 'numbers2' enthält.
        boolean containsDuplicates = distinctNumbers.size() != numbers2.size(); // Prüft, ob Duplikate vorhanden waren, indem die Größe der neuen Liste mit der alten verglichen wird.
        if (containsDuplicates) {
            System.out.println(distinctNumbers);
        } else {
            System.out.println("Die Liste enthält keine Duplikate.");
        }
    }
}