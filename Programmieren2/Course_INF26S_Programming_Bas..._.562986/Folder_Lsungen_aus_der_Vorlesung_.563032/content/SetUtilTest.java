import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Set;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Dies ist die Testklasse für die SetUtil-Klasse.
 * Für eine 100%ige Testabdeckung wären weitere Tests notwendig
 * (z.B. mit leeren Sets, Sets ohne Schnittmenge, null-Parametern etc.),
 * aber dieser Test prüft den Hauptanwendungsfall ("Happy Path").
 */
class SetUtilTest {

    /**
     * @Test ist eine Annotation von JUnit.
     * Sie markiert diese Methode als einen einzelnen, automatisierten Testfall.
     * JUnit wird diese Methode ausführen und prüfen, ob sie erfolgreich ist
     * (d.h. keine Exceptions wirft und alle 'Assertions' bestehen).
     */
    @Test
    void testIntersection() {
        // Gute Unit-Tests folgen oft dem "Arrange, Act, Assert" (AAA)-Muster.

        // 1. ARRANGE (Vorbereiten):
        // In diesem Schritt werden alle notwendigen Variablen,
        // Eingabedaten und das erwartete Ergebnis definiert.

        // Eingabe 1
        Set<Integer> set1 = Set.of(1, 2, 3);
        // Eingabe 2
        Set<Integer> set2 = Set.of(2, 3, 4);
        // Erwartetes Ergebnis: Die Elemente, die in *beiden* Sets vorkommen.
        Set<Integer> expected = Set.of(2, 3);

        // 2. ACT (Ausführen):
        // In diesem Schritt wird die *eine* Methode aufgerufen,
        // die wir testen wollen (das "System Under Test" oder SUT).
        Set<Integer> actual = SetUtil.intersection(set1, set2);

        // 3. ASSERT (Prüfen):
        // In diesem Schritt vergleichen wir das tatsächliche Ergebnis (actual)
        // mit dem erwarteten Ergebnis (expected).

        // 'assertEquals' ist eine statische Methode von JUnit.
        // Sie prüft, ob die beiden übergebenen Werte gleich sind.
        // Wenn sie nicht gleich sind, schlägt der Test fehl
        // und gibt eine entsprechende Fehlermeldung aus.
        assertEquals(expected, actual);
    }

    /**
     * Dieser Test prüft den Fall, dass die beiden Sets
     * keine gemeinsamen Elemente haben (disjunkte Mengen).
     * Das Ergebnis muss eine leere Menge (empty set) sein.
     */
    @Test
    void testEmptyIntersection() {
        // 1. ARRANGE
        // Definiere zwei Sets, die keine Überschneidung haben.
        Set<Integer> set1 = Set.of(1, 2, 3);
        Set<Integer> set2 = Set.of(4, 5, 6);

        // Das erwartete Ergebnis ist ein leeres Set.
        Set<Integer> expected = Set.of();

        // 2. ACT
        // Rufe die zu testende Methode auf.
        Set<Integer> actual = SetUtil.intersection(set1, set2);

        // 3. ASSERT
        // Prüfe, ob das tatsächliche Ergebnis dem erwarteten leeren Set entspricht.
        assertEquals(expected, actual);
    }
}