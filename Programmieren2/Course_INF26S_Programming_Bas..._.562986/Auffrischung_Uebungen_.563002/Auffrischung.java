public class Auffrischung {

    public static void main(String[] args) {
        System.out.println(abgerundeteSumme(13.37, 42.42));
        System.out.println(abgerundeteSumme(3.8, 1.5));
        System.out.println(abgerundeteSumme(-3.8, -1.5));
        System.out.println();

        System.out.println(berechneDurchschnitt(1.5, 1.5, 3.0));
        System.out.println();

        System.out.println(maximum(1, 42, 21));
        System.out.println();

        Punkt a = new Punkt(1, 2);
        System.out.println(a.getX());
        System.out.println(a.getY());
        System.out.println();

        Taschenrechner t = new Taschenrechner();
        System.out.println(t.divisorSumme(24));
        System.out.println();

        System.out.println(verbindeStrings("Dam", " Trung"));
        System.out.println();

        System.out.println(ServiceMix.findeChar("Trung Dam", 'T'));
        System.out.println();

        System.out.println(gibNtenNachfolger('z', 4));
        System.out.println();

        System.out.println(reverseArray(new char[] { 'T', 'e', 's', 't' }));
    }

    /**
     * ABGERUNDETE SUMME
     * Addiert beide Parameter und liefert die Summe abgerundet zurück.
     * "Abgerundet" = zur 0 gerundet (Nachkommastellen wegfallen).
     *
     * Beispiel 1: a = 13.37; b = 42.42; Rückgabewert: 55
     * Beispiel 2: a = 3.8; b = 1.5; Rückgabewert: 5
     * Beispiel 3: a = -3.8; b = -1.5; Rückgabewert: -5
     */
    public static int abgerundeteSumme(double a, double b) {
        return (int) (a + b);
    }

    /**
     * DURCHSCHNITT
     * Vervollständige die Methode public double
     * berechneDurchschnitt(double a, double b, double
     * c), die den Durchschnitt ihrer drei Double-Parameter liefern soll.
     * Beispiel: berechneDurchschnitt(1.5, 1.5, 3.0)
     * Rückgabewert: 2.0
     * 
     */
    public static double berechneDurchschnitt(double a, double b, double c) {
        return (a + b + c) / 3;
    }

    /**
     * MAXIMUM
     * Schreibe eine Methode public int maximum(int a, int
     * b, int c), die den größten der drei übergebenen int-Werte
     * zurückliefert.
     * Beispiel: a = 1; b = 42; c = 21; Rückgabewert: 42
     */
    public static int maximum(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

    /**
     * 2D PUNKTE
     * Die vorgegebene Klasse Punkt soll Punkte in einem
     * zweidimensionalen Koordinatensystem modellieren.
     * Erweitere sie um einen Konstruktor, der beide Koordinatenwerte als
     * Parameter erhält und sie geeignet ablegt. Implementiere außerdem
     * zwei sondierende Methoden double getX() und double
     * getY(), die den jeweiligen Koordinatenwert eines Punktes liefern.
     */
    static class Punkt {

        private double x;
        private double y;

        public Punkt(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }
    }

    /**
     * DIE RECHENOPTIONEN MODULO
     * Schreibe eine Klasse Taschenrechner, die das Interface
     * ArithmetikMix implementiert.
     * Hinweis: Wie dir der Titel schon verrät, bietet es sich besonders an,
     * diese Aufgabe mittels Modulo-Operation zu lösen.
     */
    public interface ArithmetikMix {
        /**
         * Berechnet die Summe aller Teiler, die eine Ganzzahl ohne
         * Rest teilen, wobei nur die positiven Teiler berücksichtigt werden.
         *
         * @param n Die zu prüfende Ganzzahl
         * @return Die Summe der Teiler.
         */
        int divisorSumme(int n);
    }

    static class Taschenrechner implements ArithmetikMix {

        @Override
        public int divisorSumme(int n) {
            if (n <= 0)
                return 0;

            int result = 1 + n;

            for (int i = 2; i * i <= n; i++) {
                if (n % i == 0) {
                    result += i;
                    if (i != n / i)
                        result += n / i;
                }
            }

            return result;
        }
    }

    /**
     * Strings verketten
     * Schreibe eine öffentliche Methode verbindeStrings(String s1, String s2), die
     * beide Zeichenketten verbindet.
     * Hierbei soll der kürzere hinter den längeren String gehängt werden. Sind
     * beide gleich lang, so wird s2 hinter s1 gehängt. Der neue, zusammengesetzte
     * String soll als Ergebnis geliefert werden.
     * Wenn s1 oder s2 null ist, soll eine IllegalArgumentException geworfen werden.
     * Beispiel: s1 = "Welt!", s2 = "Hallo "; Ergebnis: "Hallo Welt!"
     */
    public static String verbindeStrings(String s1, String s2) {
        if (s1 == null || s2 == null)
            throw new IllegalArgumentException();
        String result = "";
        if (s1.length() >= s2.length()) {
            result = s1 + s2;
        } else {
            result = s2 + s1;
        }
        return result;
    }

    /**
     * Zeichen in einem String finden
     * Oft muss überprüft werden, ob sich ein gesuchtes Zeichen in einer
     * Zeichenkette befindet. In der Klasse String ist bereits eine Methode
     * implementiert, die diese Frage beantwortet.
     * Schreibe dennoch zur Übung in einer Klasse ServiceMix eine öffentliche
     * Methode findeChar(String, char). Sie soll das erste Vorkommen des Zeichens
     * char in der Zeichenkette String finden und den Index des gefundenen Zeichens
     * als int zurückgeben.
     * Wurde das Zeichen nicht gefunden, so wird -1 zurückgegeben.
     * Beispiel: findeChar("HAW", 'H') = 0, findeChar("HAW", 'h') = -1
     * Achtung: Die Verwendung von String.indexOf() ist nicht erlaubt - das wäre zu
     * einfach!
     */
    static class ServiceMix {
        public static int findeChar(String s, char c) {
            if (s == null)
                throw new NullPointerException();
            for (int i = 0; i < s.length(); i++) {
                if (c == s.charAt(i))
                    return i;
            }
            return -1;
        }
    }

    /**
     * Nachfolgebuchstabe
     * Schreibe eine Methode public char
     * gibNtenNachfolger(char buchstabe, int n), die als
     * Rückgabewert den n-ten Nachfolgebuchstaben ausgehend von
     * buchstabe liefert.
     * Beispiel: buchstabe = 'a'; n = 3; Rückgabewert: 'd'
     * Hinweis: Alle weiteren char-Werte nach 'z' gelten hier ebenfalls
     * als gültige Nachfolgebuchstaben. Negative Werte für n können
     * vernachlässigt werden.
     */
    public static char gibNtenNachfolger(char buchstabe, int n) {
        return (char) (buchstabe + n);
    }

    /**
     * Array umkehren
     * Im Folgenden soll eine öffentliche Methode reverseArray()
     * geschrieben werden, welche ein char-Array als Parameter erhält.
     * Dieses soll in umgekehrter Reihenfolge als ein char-Array wieder
     * zurückgegeben werden.
     * Beispiel: ['T', 'e', 's', 't'] ['t', 's', 'e', 'T']
     * Hinweis: Das übergebene Array darf nicht verändert werden.
     */
    public static char[] reverseArray(char[] arr) {
        char[] result = new char[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[result.length - 1 - i] = arr[i];
        }
        return result;
    }

    /**
     * Array-Summe
     * Für ein Programm, welches Klausurergebnisse verwaltet, fehlt eine
     * Methode hatBestanden.
     * Die Methode soll für eine Klausur bestimmen, ob der Student diese
     * bestanden hat. Dafür bekommt sie ein Array mit den Punktzahlen
     * der Teilaufgaben und eine Mindestpunktzahl übergeben. Die
     * Methode liefert true für bestanden bzw. false für nicht bestanden
     * zurück.
     * Implementiere die Methode boolean hatBestanden(int[]
     * aufgabenPunkte, int mindestPunkte), die obiges
     * Verhalten modelliert.
     */
    public static boolean hatBestanden(int[] aufgabenPunkte, int mindestPunkte) {
        int sum = 0;
        for (int punkt : aufgabenPunkte) {
            sum += punkt;
        }
        return sum >= mindestPunkte;
    }
}
