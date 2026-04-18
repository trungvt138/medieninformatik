import java.util.*;

// Was macht das Interface hier?
// Ein Interface (hier 'StudentProcessor') ist wie eine Schablone für eine Klasse.
// Wenn eine Klasse (hier 'StudentManager') ein Interface 'implementiert'
// MUSS sie alle Methoden bereitstellen, die im Interface definiert sind.
//
// Vorteil: Man kann Code schreiben, der gegen das Interface (den Vertrag)
// programmiert ist, ohne die konkrete Implementierung (die Klasse) zu kennen.
// Man garantiert, dass jedes "StudentProcessor"-Objekt eine "findNames"-Methode HAT.

public class StudentManager implements StudentProcessor {

    public Collection<String> getDetails(Collection<Student> students) {
        return students.stream() // 1. Wandle die Eingabe-Collection (students) in einen Stream um.
            .map(student -> String.format( // 2. Führe für JEDES Element (jeden 'student') im Stream eine Transformation (Mapping) durch.
                Locale.ENGLISH, // 3. (Argument 1 für String.format) Stelle sicher, dass Zahlen Englisch formatiert werden (z.B. 1.3 statt 1,3).
                "%s | %d Jahre | Durchschnittsnote: %.1f", // 4. (Argument 2) Definiere die Text-Schablone mit Platzhaltern (%s=String, %d=Ganzzahl, %.1f=Fließkommazahl mit 1 Nachkommastelle).
                student.name(), student.age(), student.avgGrade())) // 5. (Argument 3+) Liefere die Werte, die in die Platzhalter eingesetzt werden sollen.
            .toList(); // 6. Sammle alle transformierten Strings (das Ergebnis von .map()) in einer neuen Liste und gib diese zurück.
    }

    public List<String> findNames(Collection<Student> students, double minGrade) {
        return students.stream() // 1. Wandle die Eingabe-Collection (students) in einen Stream um.
            .filter(student -> student.avgGrade() >= minGrade) // 2. Behalte nur die Studenten im Stream, deren Note (avgGrade) größer oder gleich minGrade ist.
            .map(Student::name) // 3. Transformiere (mappe) jedes verbleibende Student-Objekt auf seinen Namen (einen String). Der Stream ist jetzt ein Stream<String>.
            .sorted() // 4. Sortiere die Namen im Stream. Bei Strings bedeutet das: alphabetisch aufsteigend (A-Z).
            .toList(); // 5. Sammle alle sortierten Namen in einer neuen Liste und gib diese zurück.
    }

    public List<String> findNames(Collection<Student> students, double minGrade, int minAge) {
        return students.stream() // 1. Wandle die Eingabe-Collection (students) in einen Stream um.
            .filter(student -> student.age() >= minAge && student.avgGrade() >= minGrade) // 2. Behalte nur Studenten, die BEIDE Bedingungen erfüllen: Mindestalter UND Mindestnote.
            .sorted((student1, student2) -> Integer.compare(student1.age(), student2.age())) // 3. Sortiere die verbleibenden Studenten aufsteigend nach ihrem Alter (der sicherste Weg, 'int' zu vergleichen).
            // .sorted((student1, student2) -> student1.age() - student2.age()) // 4. (Alternative 1) Sortierung durch Subtraktion. Funktioniert, ist aber anfällig für Integer-Überlauf bei sehr großen/kleinen Zahlen.
            // .sorted(Comparator.comparingInt(Student::age)) // 5. (Alternative 2) Eleganteste Sortierung mit einer Comparator-Hilfsmethode, die explizit für 'int'-Werte gedacht ist.
            .map(Student::name) // 6. Transformiere (mappe) jedes sortierte Student-Objekt auf seinen Namen (einen String). Der Stream ist jetzt ein Stream<String>.
            .toList(); // 7. Sammle alle Namen in einer neuen Liste (in der sortierten Reihenfolge) und gib diese zurück.
    }

    public double averageGrade(Collection<Student> students) {
        return students.stream() // 1. Wandle die Eingabe-Collection (students) in einen Stream um.
            .mapToDouble(Student::avgGrade) // 2. Erstelle einen spezialisierten Stream (DoubleStream), der nur die avgGrade-Werte (als double) enthält.
            .average() // 3. Berechne den Durchschnitt aller double-Werte im Stream. Das Ergebnis ist ein 'OptionalDouble'.
            .orElse(0.0); // 4. Falls das 'OptionalDouble' leer ist (weil der Stream/die Liste leer war), gib stattdessen 0.0 zurück.
    }

    public Optional<Student> findTopStudent(Collection<Student> students) {
        return students.stream() // 1. Wandle die Eingabe-Collection (students) in einen Stream um.
            .min((student1, student2) -> Double.compare(student1.avgGrade(), student2.avgGrade())); // 2. Finde das 'minimale' Element. Da 1.0 (beste Note) kleiner als 6.0 ist, findet .min() den Studenten mit der besten Note.
        // return students.stream() // 3. (Alternative 1)
        // .max((student1, student2) -> Double.compare(student2.avgGrade(), student1.avgGrade())); // 4. (Alternative 1) Finde das 'maximale' Element, aber drehe die Logik des Vergleichs um (Note 2 vs Note 1). Führt zum selben Ergebnis wie .min().
        // return students.stream() // 5. (Alternative 2)
        // .max(Comparator.comparingDouble(Student::avgGrade)); // 6. (Alternative 2 - FALSCH FÜR DIESE AUFGABE) Würde den Studenten mit der HÖCHSTEN Note (z.B. 6.0), also den schlechtesten, finden.
        // return students.stream() // 7. (Alternative 3)
        // .sorted(Comparator.comparing(student -> student.avgGrade())).findFirst(); // 8. (Alternative 3) Sortiere alle Studenten aufsteigend nach Note (1.0 zuerst) und nimm den ersten. Funktioniert, ist aber meist ineffizienter als .min().
    }

    public boolean exists(Collection<Student> students, String name) {
        return students.stream() // 1. Wandle die Eingabe-Collection (students) in einen Stream um.
            .anyMatch(student -> student.name().equalsIgnoreCase(name)); // 2. Prüft, ob *irgendein* Student im Stream die Bedingung erfüllt (Name ist gleich, Groß/Kleinschreibung ignoriert). Stoppt bei 'true', sobald der erste Treffer gefunden wird.
    }

    public List<Student> adjustedGrades(Collection<Student> students, double minGrade, int minAge, double adjustment) {
        return students.stream() // 1. Wandle die Eingabe-Collection (students) in einen Stream um.
            .filter(student -> student.age() >= minAge) // 2. Behalte nur Studenten im Stream, die das Mindestalter (minAge) erfüllen.
            .map(student -> new Student(student.name(), student.age(), student.avgGrade() + adjustment)) // 3. Transformiere jeden verbleibenden Studenten in ein *neues* Student-Objekt mit der angepassten Note.
            .filter(student -> student.avgGrade() >= minGrade) // 4. Filtere den Stream (der jetzt die *neuen* Studenten enthält) erneut. Behalte nur die, deren *neue* Note die Mindestnote (minGrade) erfüllt.
            .sorted((student1, student2) -> Double.compare(student2.avgGrade(), student1.avgGrade())) // 5. Sortiere die verbleibenden Studenten *absteigend* (schlechteste Note zuerst), indem Note 2 mit Note 1 verglichen wird.
            .toList(); // 6. Sammle alle gefilterten, gemappten und sortierten Studenten in einer neuen Liste und gib diese zurück.
    }

    /**
     * Main-Methode zum Testen aller Funktionen des StudentManager.
     */
    public static void main(String[] args) {
        // 1. Testdaten erstellen
        List<Student> students = List.of(
            new Student("Ada Lovelace", 23, 1.3),
            new Student("Alan Turing", 22, 4.1),
            new Student("Donald Knuth", 20, 2.5),
            new Student("Grace Hopper", 23, 1.7),
            new Student("Tim Berners-Lee", 22, 3.0)
        );

        // 2. Manager-Instanz erstellen

        StudentManager manager = new StudentManager();

        // 3. Methoden testen

        System.out.println("--- getDetails (Alle formatiert) ---");
        manager.getDetails(students).forEach(System.out::println);

        System.out.println("\n--- findNames (Note >= 3.0, alphabetisch) ---");
        // Erwartet: Alan Turing (4.1), Tim Berners-Lee (3.0)
        // Sortiert: Alan Turing, Tim Berners-Lee
        manager.findNames(students, 3.0).forEach(System.out::println);

        System.out.println("\n--- findNames (Note >= 1.5 UND Alter >= 23, sortiert nach Alter) ---");
        // Ada Lovelace (23, 1.3) -> Note zu gut (1.3 < 1.5)
        // Grace Hopper (23, 1.7) -> Trifft zu
        // Erwartet: Grace Hopper
        manager.findNames(students, 1.5, 23).forEach(System.out::println);

        System.out.println("\n--- averageGrade (Durchschnitt aller Noten) ---");
        // (1.3 + 4.1 + 2.5 + 1.7 + 3.0) / 5 = 12.6 / 5 = 2.52
        System.out.println(manager.averageGrade(students));

        System.out.println("\n--- findTopStudent (Beste/Niedrigste Note) ---");
        // Erwartet: Ada Lovelace (1.3)
        manager.findTopStudent(students).ifPresent(System.out::println);

        System.out.println("\n--- exists (Prüfung auf 'Donald Knuth' und 'Linus Torvalds') ---");
        System.out.println("Existiert 'Donald Knuth'? " + manager.exists(students, "Donald Knuth")); // true
        System.out.println("Existiert 'ADA LOVELACE'? " + manager.exists(students, "ADA LOVELACE")); // true
        System.out.println("Existiert 'Linus Torvalds'? " + manager.exists(students, "Linus Torvalds"));   // false

        System.out.println("\n--- adjustedGrades (Alter >= 22, Anpassung: -0.5, Neue Note >= 2.0) ---");
        // Ada Lovelace (23, 1.3)    -> Alter T. Neue Note 0.8. Note F. -> RAUS
        // Alan Turing (22, 4.1)     -> Alter T. Neue Note 3.6. Note T. -> REIN
        // Donald Knuth (20, 2.5)    -> Alter F. -> RAUS
        // Grace Hopper (23, 1.7)    -> Alter T. Neue Note 1.2. Note F. -> RAUS
        // Tim Berners-Lee (22, 3.0) -> Alter T. Neue Note 2.5. Note T. -> REIN


        // Problem: 'double'-Berechnungen (z.B. 4.1 - 0.5) können zu Fließkomma-Ungenauigkeiten führen (z.B. 3.599...).
        manager.adjustedGrades(students, 2.0, 22, -0.5).forEach(System.out::println);
        // Erwartet (absteigend sortiert): Alan Turing (3.6), Tim Berners-Lee (2.5)
    }
}
