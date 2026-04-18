import java.util.List; // Importiert die 'List'-Schnittstelle, um Listen zu verwenden.

public class Match {
    public static void main(String[] args) {
        // Erstellt eine unveränderliche Liste von Strings (Namen).
        List<String> names = List.of("Anna", "Berta", "Clara", "Diana", "Eva");

        // --- Prüfung 1: Hat irgendein Name 'a' als vierten Buchstaben? ---
        boolean anyFourthLetterIsA = names.stream() // Erstellt einen Stream (eine Daten-Pipeline) aus der Liste.
            // .anyMatch() ist eine Stream-Methode, die 'true' zurückgibt, sobald das erste Element die Bedingung erfüllt.
            // Das (n -> ...) ist die Lambda-Expression (die Prüfregel):
            // 1. n.length() > 3: Prüft zuerst, ob der Name (n) überhaupt 4 Buchstaben hat (um einen Fehler zu vermeiden).
            // 2. n.charAt(3) == 'a': Prüft dann, ob der 4. Buchstabe (an Index 3) ein 'a' ist.
            .anyMatch(n -> n.length() > 3 && n.charAt(3) == 'a');

        // Gibt das Ergebnis der 'anyMatch'-Prüfung aus. "Anna" passt, also 'true'.
        System.out.println(anyFourthLetterIsA);

        // --- Prüfung 2: Enden alle Namen mit 'a'? ---
        boolean allEndWitha = names.stream() // Erstellt einen *neuen* Stream aus der Liste.
            // .allMatch() ist eine Stream-Methode, die 'true' nur dann zurückgibt, wenn *alle* Elemente die Bedingung erfüllen.
            // Die Lambda-Regel: Prüft, ob der Name (n) mit "a" endet.
            .allMatch(n -> n.endsWith("a"));

        // Gibt das Ergebnis der 'allMatch'-Prüfung aus. Alle Namen enden auf 'a', also 'true'.
        System.out.println(allEndWitha);
    }
}