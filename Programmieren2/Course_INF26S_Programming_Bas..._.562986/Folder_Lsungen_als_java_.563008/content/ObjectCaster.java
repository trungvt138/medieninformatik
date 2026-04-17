//Aufgabe 1

import java.util.List;
import java.util.ArrayList;

public class ObjectCaster {

    /**
     * Nimmt eine Liste von Objekten, filtert alle Integer-Werte heraus,
     * quadriert diese und gibt sie in einer neuen Liste zurück.
     *
     * @param objectList Eine Liste, die beliebige Objekte enthalten kann (z.B. Zahlen, Texte).
     * @return Eine neue Liste, die ausschließlich die quadrierten Integer-Werte enthält.
     */
    public static List<Integer> toSquaredIntegerList(List<Object> objectList) {
        // Erstelle die neue Liste, in der die Ergebnisse (Quadrate) gespeichert werden.
        List<Integer> integerList = new ArrayList<>(objectList.size());
        // Wenn du new ArrayList<>() ohne Größenangabe verwendest, startet die Liste mit einer kleinen Standardkapazität (oft 10).
        // Fügst du dann mehr als 10 Elemente hinzu, passiert intern Folgendes:
        // 1. Ein neues, größeres Array wird im Speicher angelegt.
        // 2. Alle bisherigen Elemente werden vom alten in das neue Array umkopiert.
        // 3. Das alte Array wird gelöscht.
        // Dieser Vorgang ist rechenintensiv und kostet Zeit, besonders bei sehr großen Listen.
        // Wenn du also viele Elemente hinzufügst, muss die ArrayList sich mehrfach vergrößern und kopiert jedes Mal alle Elemente um.



        // Iteriere über jedes einzelne Element in der übergebenen "objectList".
        for (Object object : objectList) {

            // Prüfe, ob das aktuelle Objekt ein Integer ist.
            // Dies ist der Schritt, der sicherstellt, dass Objekte, die nicht gecastet
            // werden können, ignoriert werden.
            if(object instanceof Integer) {
            //if(object instanceof Integer integer) { // Pattern Matching ist ein relativ neues Feature
                // Wenn es ein Integer ist, caste das Object sicher in den Datentyp "int".
                int integer = (Integer) object; //Bei Pattern Matching nicht nötig

                // Berechne das Quadrat der Zahl und füge es der Ergebnisliste hinzu.
                integerList.add(integer * integer);
                // Faustregel:
                //Für das Quadrieren oder Potenzieren mit kleinen, festen Ganzzahlen (wie 2, 3, 4): Immer die direkte Multiplikation verwenden (i * i, i * i * i).
                //Für variable oder gebrochene Exponenten: Dafür ist Math.pow() gemacht.
            }
        }
        // Gebe die neue Liste mit den Quadraten zurück.
        return integerList;
    }

    public static void main(String[] args){
        // Eine Testliste mit verschiedenen Objekttypen erstellen.
        List<Object> testList = List.of(1, "zwei", 3, 4.5, 5, "sechs", 10);

        // Die Methode aufrufen und das Ergebnis ausgeben.
        System.out.println(toSquaredIntegerList(testList)); // Erwartete Ausgabe: [1, 9, 25, 100]
    }
}