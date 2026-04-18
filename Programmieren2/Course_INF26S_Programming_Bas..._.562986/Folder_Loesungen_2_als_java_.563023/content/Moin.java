import java.util.List; // Importiert die 'List'-Schnittstelle.

public class Moin { // Definiert die Klasse 'Moin'.
    public static void main(String[] args) { // Startpunkt des Programms.
        var letters = List.of("m", "o", "i", "n"); // Erstellt eine unveränderliche Liste von Strings.

        String uppercaseString = letters.stream() // 1. Erstellt einen Stream (Daten-Pipeline) aus der Liste: ["m", "o", "i", "n"]

                // 2. Transformiert (mappt) jedes Element im Stream.
                //    String::toUpperCase ist eine Methodenreferenz, die für jedes Element die toUpperCase()-Methode aufruft.
                //    Der Stream ist jetzt: ["M", "O", "I", "N"]
                .map(String::toUpperCase)

                // .map(letter -> letter.toUpperCase()) // (Alternative zu Zeile 8) Exakt dieselbe Logik, nur als Lambda-Ausdruck geschrieben.

                // 3. .reduce() kombiniert alle Elemente des Streams zu einem einzigen Ergebnis (hier einem String).
                // 4. Der erste Parameter "" ist der "Identitätswert" (identity) – der Startwert für die Reduzierung.
                // 5. Der zweite Parameter (der Lambda-Ausdruck) ist der "Akkumulator".
                //    (substring, letter) -> substring + letter
                //    'substring' = das bisherige Zwischenergebnis.
                //    'letter' = das *nächste* Element aus dem Stream ["M", "O", "I", "N"].
                //    Ablauf der Reduzierung:
                //    Iteration 1: substring = "" (Startwert), letter = "M" -> Ergebnis: "M"
                //    Iteration 2: substring = "M",        letter = "O" -> Ergebnis: "MO"
                //    Iteration 3: substring = "MO",       letter = "I" -> Ergebnis: "MOI"
                //    Iteration 4: substring = "MOI",      letter = "N" -> Ergebnis: "MOIN"
                .reduce("", (substring, letter) -> substring + letter);

        System.out.println(uppercaseString); // 6. Gibt das Endergebnis der Reduzierung ("MOIN") auf der Konsole aus.
    }
}