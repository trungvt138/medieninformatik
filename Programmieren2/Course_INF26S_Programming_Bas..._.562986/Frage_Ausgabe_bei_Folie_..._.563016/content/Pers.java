import java.util.List;
import java.util.stream.IntStream;

record Pers(String name, int age) {

    public static void main(String[] args) {
        // List.of() erstellt eine unveränderliche (immutable) Liste.
        List<Pers> persons = List.of(
                new Pers("Bob", 2),   // Index 0
                new Pers("Alice", 3),  // Index 1
                new Pers("Jane", 1)    // Index 2
        );

        // --- Block 1: "Old school" ---
        // Dies ist der klassische, imperative Ansatz.
        System.out.println("--- Old school ---");
        int indexOfAlice = -1; // Startwert, falls nichts gefunden wird.

        // Wir iterieren über die Liste mit einem Zähler 'i' (dem Index).
        for (int i = 0; i < persons.size(); i++) {
            Pers person = persons.get(i); // Das Objekt am aktuellen Index holen.

            // Wir prüfen die Bedingung: Ist der *Name* gleich "Alice"?
            // Das Alter wird hier ignoriert!
            if (person.name().equals("Alice")) {
                indexOfAlice = i; // Wir haben den Index gefunden.
                break; // Die Schleife abbrechen, da wir den ersten Treffer haben.
            }
        }
        // Ausgabe: 1 (Alice ist an Position 1)
        System.out.println(indexOfAlice);


        // --- Block 2: "Statt old school" (List::indexOf) ---
        // Dieser Ansatz verwendet die eingebaute Methode der Liste.
        System.out.println("--- Statt old school (mit indexOf) ---");

        // ACHTUNG: Dieser Ansatz funktioniert hier NICHT wie erwartet.
        // ERKLÄRUNG, WARUM ES NICHT FUNKTIONIERT:
        // 1. `persons.indexOf(obj)` sucht nach 'obj' in der Liste.
        // 2. Um Objekte zu vergleichen, verwendet `indexOf` die `.equals()`-Methode.
        // 3. Da 'Pers' ein 'record' ist, vergleicht die automatisch generierte
        //    `.equals()`-Methode *alle* Felder: 'name' UND 'age'.
        // 4. Wir suchen hier nach `new Pers("Alice", 23)`.
        // 5. Die Liste enthält aber `new Pers("Alice", 3)` an Index 1.
        // 6. Der interne Vergleich ist: `new Pers("Alice", 23).equals(new Pers("Alice", 3))`
        // 7. Dieser Vergleich gibt `false` zurück, weil `23` nicht gleich `3` ist.
        // 8. Da kein Objekt *exakt* (Name und Alter) übereinstimmt, wird nichts gefunden.
        indexOfAlice = persons.indexOf(new Pers("Alice", 23));

        // Ausgabe: -1 (weil das Objekt nicht gefunden wurde)
        System.out.println(indexOfAlice);

        // (HINWEIS: `persons.indexOf(new Pers("Alice", 3))` würde 1 ausgeben,
        // da dies ein exakter Treffer wäre.)


        // --- Block 3: "Stream" (mit IntStream) ---
        // Dies ist der moderne, funktionale Ansatz, um einen *Index* basierend
        // auf einer *benutzerdefinierten Bedingung* zu finden.
        System.out.println("--- Stream (IntStream) ---");

        // 1. `IntStream.range(0, persons.size())`: Erzeugt einen Stream von Zahlen (Indizes): 0, 1, 2.
        // 2. `.filter(i -> persons.get(i).name.equals("Alice"))`:
        //    - Das ist unser "Prädikat" (unsere Bedingung).
        //    - Es behält nur die Indizes 'i', bei denen der Name der Person an diesem Index "Alice" ist.
        //    - Genau wie der "Old school"-Ansatz prüft dies *nur* den Namen.
        //    - Der Stream enthält nach dem Filtern nur noch die Zahl: 1.
        // 3. `.findFirst()`: Nimmt das erste Element aus dem gefilterten Stream (also die 1).
        //    - Das Ergebnis ist ein `OptionalInt`, da der Stream auch leer sein könnte.
        // 4. `.orElse(-1)`:
        //    - Wenn das `OptionalInt` einen Wert enthält (hier: 1), gib diesen Wert zurück.
        //    - Wenn das `OptionalInt` leer ist (d.h. "Alice" wurde nicht gefunden), gib stattdessen -1 zurück.
        indexOfAlice = IntStream.range(0, persons.size())
                .filter(i -> persons.get(i).name.equals("Alice"))
                .findFirst()
                .orElse(-1);

        // Ausgabe: 1 (korrekt gefunden)
        System.out.println(indexOfAlice);


        // --- Block 4: NEUE METHODE (Gekapselter Stream-Ansatz) ---
        // Das ist der sauberste Weg: Wir lagern die Logik aus Block 3
        // in eine eigene, wiederverwendbare Methode aus.
        System.out.println("--- Neue, saubere Methode ---");

        // Wir rufen unsere unten definierte Helfermethode auf.
        // Das ist lesbar und verbirgt die Komplexität des IntStream.
        indexOfAlice = findIndexByName(persons, "Alice");

        // Ausgabe: 1 (korrekt gefunden)
        System.out.println(indexOfAlice);
    }

    public static int findIndexByName(List<Pers> personList, String name) {
        // Die Logik ist identisch mit dem Stream-Beispiel oben.
        return IntStream.range(0, personList.size())
                // Wir filtern die Indizes 'i'
                .filter(i -> personList.get(i).name().equals(name))
                // Finden den ersten Treffer
                .findFirst()
                // Geben -1 zurück, falls nichts gefunden wurde
                .orElse(-1);
    }
}