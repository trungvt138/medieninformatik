package org.example;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

public class SantaDeliveryService {

    record Target(String name, String address) {
        @Override
        public String toString() {
            return name + " (" + address + ")";
        }
    }

    // Die main-Methode kann Exceptions werfen (ExecutionException, InterruptedException),
    // falls bei den Futures etwas schiefgeht.
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 1. Datenbasis: Eine unveränderliche Liste (List.of) mit den Zielen in Hamburg.
        List<Target> hamburgTargets = List.of(
                new Target("Ada Lovelace", "Jungfernstieg 1, 20095 Hamburg"),
                new Target("Alan Turing", "Reeperbahn 100, 20359 Hamburg"),
                new Target("Grace Hopper", "Elbphilharmonie Platz 1, 20457 Hamburg"),
                new Target("Konrad Zuse", "Speicherstadt Am Sandtorkai, 20457 Hamburg"),
                new Target("Joseph Weizenbaum", "Universität Hamburg, Vogt-Kölln-Str., 22527 Hamburg"),
                new Target("Hedy Lamarr", "Alsterarkaden 10, 20354 Hamburg"),
                new Target("Claude Shannon", "Landungsbrücken, Brücke 3, 20359 Hamburg"),
                new Target("Margaret Hamilton", "Airbus Finkenwerder, 21129 Hamburg"),
                new Target("Tim Berners-Lee", "DESY Forschungszentrum, Notkestr., 22607 Hamburg"),
                new Target("Christian Zuse", "Informatikum Stellingen, 22527 Hamburg"),
                new Target("Augusta Ada King", "HafenCity, Osakaallee 12, 20457 Hamburg"),
                new Target("John von Neumann", "Miniatur Wunderland, Kehrwieder 2, 20457 Hamburg")
        );

        System.out.println("Zu beliefern sind " + hamburgTargets.size() + " Ziele in Hamburg.\n");

        // Startzeitpunkt für das gesamte Programm messen.
        Instant raceStart = Instant.now();

        // --- Startschuss für Team Santa (SERIELL) ---
        // CompletableFuture.supplyAsync startet eine asynchrone Aufgabe in einem Hintergrund-Thread.
        // Das bedeutet: Der Code im Lambda {} läuft parallel zum main-Thread.
        CompletableFuture<Long> teamSantaFuture = CompletableFuture.supplyAsync(() -> {
            // Wir rufen die Simulation auf. Wichtig: 'parallel' ist hier FALSE.
            // Santa arbeitet also alleine (sequenziell).
            return runSimulation("Team Santa \uD83C\uDF85", hamburgTargets, false);
        });

        // --- Startschuss für Team Elfen (PARALLEL) ---
        // Wir starten sofort eine zweite asynchrone Aufgabe.
        // Jetzt laufen 'Team Santa' und 'Team Elfen' gleichzeitig nebeneinander her.
        CompletableFuture<Long> teamElfenFuture = CompletableFuture.supplyAsync(() -> {
            // Kurzes Warten (50ms), damit die Konsolenausgaben beim Start nicht exakt
            // gleichzeitig kommen (reine Kosmetik).
            try { Thread.sleep(50); } catch (InterruptedException e) {}

            // Wir rufen die Simulation auf. Wichtig: 'parallel' ist hier TRUE.
            // Die Elfen nutzen Parallel-Streams (Multithreading).
            return runSimulation("Team Elfen \uD83E\uDDDD", hamburgTargets, true);
        });

        System.out.println("...beide Teams sind unterwegs. Beobachte die Live-Konsole!...\n");

        // CompletableFuture.allOf(...) wartet darauf, dass ALLE übergebenen Futures fertig sind.
        // .join() blockiert den main-Thread an dieser Stelle so lange, bis das letzte Team im Ziel ist.
        CompletableFuture.allOf(teamSantaFuture, teamElfenFuture).join();

        // Endzeitpunkt messen
        Instant raceEnd = Instant.now();

        // Ergebnisse abholen.
        // .get() holt den Rückgabewert (hier: Long duration) aus dem Future.
        // Da wir oben schon mit join() gewartet haben, sind die Werte hier sofort verfügbar.
        long durationSanta = teamSantaFuture.get();
        long durationElfen = teamElfenFuture.get();

        // Auswertung und Ausgabe der Statistik
        System.out.println("\n>>> RENNEN BEENDET! <<<");
        System.out.println("Gesamtdauer des Events: " + Duration.between(raceStart, raceEnd).toMillis() + " ms");
        System.out.println("-----------------------------------------");
        System.out.println("Zeit Team Santa: " + durationSanta + " ms");
        System.out.println("Zeit Team Elfen: " + durationElfen + " ms");
        System.out.println("-----------------------------------------");

        // Siegerehrung basierend auf der Zeit
        if (durationElfen < durationSanta) {
            System.out.println("🏆 SIEGER: Team Elfen! (Viele Hände, schnelles Ende)");
            // Berechnung des Speedup-Faktors (Wie viel mal schneller?)
            double speedup = (double) durationSanta / durationElfen;
            System.out.printf("Speedup-Faktor: %.2fx schneller\n", speedup);
        } else {
            System.out.println("🏆 SIEGER: Team Santa? (Hat er den Zeitturbo eingeschaltet?)");
        }
    }


    /**
     * Führt die Simulation durch und gibt die benötigte Zeit in Millisekunden zurück.
     * @param teamName Der Name des Teams für die Konsolenausgabe.
     * @param targets Die Liste der Ziele.
     * @param parallel Wenn true, wird parallel gearbeitet, sonst sequenziell.
     */
    private static long runSimulation(String teamName, List<Target> targets, boolean parallel) {
        // Startzeit für dieses spezifische Team
        Instant start = Instant.now();

        // Erzeugt einen Stream aus der Liste. Standardmäßig ist dieser sequenziell.
        Stream<Target> stream = targets.stream();

        // Wenn parallel gefordert ist, wandeln wir den Stream um.
        // Java verteilt die Aufgaben dann auf den "Common ForkJoinPool" (mehrere Threads).
        if (parallel) {
            stream = stream.parallel();
        }

        // map() transformiert Elemente. Wir nutzen es hier, um den Seiteneffekt (Arbeit) auszuführen.
        stream.map(target -> {
                    // 1. Die harte Arbeit simulieren (1 Sekunde warten pro Haus)
                    climbChimney();

                    // 2. Info holen: Welcher Thread macht gerade die Arbeit?
                    // Bei Santa ist es immer derselbe Thread. Bei den Elfen sind es viele verschiedene.
                    String workerInfo = Thread.currentThread().getName();

                    // 3. Live-Ausgabe auf der Konsole
                    System.out.println("[" + teamName + "] Beliefert: " + target.name + " in " + target.address +
                            " (durch: " + workerInfo + ")");

                    // map erwartet einen Rückgabewert, wir geben das Ziel einfach weiter.
                    return target;
                })
                // .toList() ist die "Terminale Operation".
                // Streams sind "lazy" (faul). Ohne eine terminale Operation (wie toList, forEach, collect)
                // würde der Stream gar nicht loslaufen. Hier wird die Verarbeitung gestartet.
                .toList();

        // Endzeit messen und Dauer berechnen
        Instant end = Instant.now();
        return Duration.between(start, end).toMillis();
    }

    /**
     * Simuliert die Arbeit pro Haus (durch den Kamin klettern).
     * Wirft eine RuntimeException, da Streams keine Checked Exceptions mögen.
     */
    private static void climbChimney() {
        try {
            // Thread schläft für 1000 Millisekunden (1 Sekunde).
            // Das simuliert eine rechenintensive oder zeitaufwendige Aufgabe.
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Thread.sleep wirft eine Checked Exception (InterruptedException).
            // Da wir uns in einem Lambda befinden (im Stream), müssen wir diese fangen
            // und in eine Unchecked Exception (RuntimeException) verpacken.
            throw new RuntimeException(e);
        }
    }
}