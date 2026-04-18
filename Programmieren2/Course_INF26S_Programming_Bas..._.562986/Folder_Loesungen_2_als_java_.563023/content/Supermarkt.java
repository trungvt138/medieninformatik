import java.util.List;

// Für diese Aufgabe gibt es viele denkbare Lösungsansätze
public class Supermarkt {
    public static void main(String[] args) {
        // Erstellt eine UNVERÄNDERBARE Liste mit 'Artikel'-Objekten (definiert im record).
        var sortiment = List.of(
                new Artikel("Banane", 100, 1.39),
                new Artikel("Tomate", 200, 0.29),
                new Artikel("Gurke", 50, 0.99),
                new Artikel("Apfel", 300, 0.59),
                new Artikel("Aubergine", 10, 2.19));

        // --- BERECHNUNG 1: Gesamte Stückzahl ---
        var artikelAufLager = sortiment.stream() // 1. Erstellt einen Stream (Daten-Pipeline) aus der 'sortiment'-Liste.
                .mapToInt(artikel -> artikel.aufLager()) // 2. [Stream-Methode] Transformiert den Stream von Artikeln in einen Stream von Zahlen (int).
                //    [Lambda] Die Regel: "Für jeden 'artikel', nimm seine 'aufLager'-Zahl".
                .sum(); // 3. [Stream-Methode] Addiert alle Zahlen im Stream (100 + 200 + 50 + 300 + 10).

        System.out.printf("Anzahl Artikel auf Lager insgesamt: %d\n", artikelAufLager);

        // --- BERECHNUNG 2: Gesamter Lagerwert ---
        var lagerwert = sortiment.stream() // 1. Erstellt einen *neuen* Stream aus der 'sortiment'-Liste.
                .mapToDouble(artikel -> artikel.aufLager() * artikel.preis()) // 2. [Stream-Methode] Transformiert den Stream in einen Stream von Kommazahlen (double).
                //    [Lambda] Die Regel: "Für jeden 'artikel', berechne 'aufLager' * 'preis'".
                .sum(); // 3. [Stream-Methode] Addiert alle diese berechneten Werte (Gesamtwert) auf.

        System.out.printf("Lagerwert insgesamt: %.2f Euro\n", lagerwert); // Gibt das Ergebnis der zweiten Berechnung aus.

        System.out.println("Lagerwert pro Artikel: "); // Gibt eine Überschrift aus.

        // --- BERECHNUNG 3: Einzelwerte auflisten ---
        sortiment.stream() // 1. Erstellt einen *dritten* Stream aus der 'sortiment'-Liste.
                .map(artikel -> String.format( // 2. [Stream-Methode] Transformiert den Stream: Jedes 'Artikel'-Objekt wird zu einem 'String'.
                        "%s: %.2f Euro", //    [Lambda] Die Regel: "Nimm einen 'artikel' und erstelle einen formatierten String..."
                        artikel.name(), artikel.aufLager() * artikel.preis())) // "...aus seinem Namen und dem berechneten Einzelwert."
                .forEach(artikelMitLagerwert -> System.out.println(artikelMitLagerwert)); // 3. [Stream-Methode] Führt eine Aktion für jeden String im Stream aus.
        //    [Lambda] Die Aktion: "Nimm den fertigen String ('artikelMitLagerwert') und drucke ihn".
    }
}