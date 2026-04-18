import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class CollectionsAufgaben {

    /**
     * Object Caster
     * Schreibe eine Methode, die eine Object-Liste von Zahlen erhält.
     * Iteriere über die Liste und berechne für jede Zahl das Quadrat.
     * Gebe die Quadrate als neue Liste zurück.
     * Falls ein Object nicht gecastet werden kann, soll das Objekt nicht in die
     * Ergebnisliste mit aufgenommen werden.
     * Schreibe einen Unit Test die Klasse.
     */
    public static List<Integer> toSquaredIntegerList(List<Object> objectList) {
        List<Integer> result = new ArrayList<>();

        for (Object o : objectList) {
            if (o instanceof Integer i)
                result.add(i * i);
        }
        return result;
    }

    // -------------------------------------------------------------------------

    /**
     * ListConverter
     * Schreibe eine Klasse ListConverter und implementiere die folgenden Methoden.
     * Schreibe einen Unit Test die Klasse.
     *
     * Wandle die gegebene List<Integer> in ein Integer[]-Array um und gebe das Array zurück.
     * public Integer[] toArray(List<Integer> list)
     *
     * Wandle das gegegebene Integer[]-Array in eine List<Integer> um und gebe die List zurück.
     * public List<Integer> toList(Integer[] array)
     */
    static class ListConverter {

        public static Integer[] toArray(List<Integer> list) {
            Integer[] result = new Integer[list.size()];
            int index = 0;

            for (Integer i : list) {
                result[index] = i;
                index++;
            }

            return result;
        }

        public static List<Integer> toList(Integer[] array) {
            List<Integer> result = new ArrayList<>();

            for (Integer i : array) {
                result.add(i);
            }

            return result;
        }
    }

    // -------------------------------------------------------------------------

    /**
     * MyStack<T>
     * Implementiere eine generische Klasse MyStack<T>, die intern eine
     * LinkedList verwendet und folgende Methoden besitzt.
     * Schreibe einen Unit Test die Klasse.
     *
     * Fügt das element ans Ende des Stacks hinzu.
     * public void push(T element)
     *
     * Entfernt das zuletzt gepushte element aus dem Stack und gibt es zurück.
     * Wenn kein Element vorhanden ist, wird eine NoSuchElementException geworfen.
     * public T pop() throws NoSuchElementException
     *
     * true, wenn keine Elemente enthalten sind, sonst false.
     * public boolean isEmpty()
     */
    static class MyStack<T> {

        private LinkedList<T> list = new LinkedList<>();

        public void push(T element) {
            list.push(element);
        }

        public T pop() throws NoSuchElementException {
            
            return null;
        }

        public boolean isEmpty() {
            return false;
        }
    }

    // -------------------------------------------------------------------------

    /**
     * Longest String
     * Finde in einer Collection den längsten String und gebe ihn zurück.
     * Falls kein String gefunden wird, gebe einen leeren String "" zurück.
     * Überlege dir, welcher Edge-Case hier wahrscheinlich auftreten könnte.
     * Schreibe einen Unit Test für deine Lösung.
     *
     * Beispiel:
     * Collection<String> words = List.of("Apple", "Orange", "Strawberry", "Banana");
     * String longestString = LongestString.findLongestString(words); // Strawberry
     */
    static class LongestString {

        public static String findLongestString(Collection<String> words) {
            return "";
        }
    }

    // -------------------------------------------------------------------------

    /**
     * Linear Search
     * Implementiere eine Methode, die eine Collection von ganzen Zahlen sowie eine
     * gesuchte Zahl entgegennimmt.
     * Die Methode gibt den Index der gesuchten Zahl zurück, falls vorhanden, sonst -1.
     * Bei mehrfachen Vorkommen soll der Index des zuerst gefundenen zurückgegeben werden.
     * Tipp: Es könnte hilfreich sein, die Collection in eine Liste umzuwandeln.
     * Schreibe einen Unit Test für deine Lösung.
     *
     * Beispiel:
     * Collection<Integer> numbers = List.of(1, 2, 3, 4, 5);
     * int index = LinearSearch.findIndexOfNumber(numbers, 3); // 2
     */
    static class LinearSearch {

        public static int findIndexOfNumber(Collection<Integer> numbers, int target) {
            return -1;
        }
    }

    // -------------------------------------------------------------------------

    /**
     * Find MinMax
     * Implementiere eine Methode, die eine Collection von Ganzzahlen entgegennimmt
     * und ein record MinMax mit zwei Attributen zurückgibt: dem kleinsten und dem
     * größten Wert in der Eingabe-Collection.
     * Falls nur ein Element vorhanden ist, ist es zugleich Minimum und Maximum.
     * Wenn keine gültigen Werte gefunden werden, wird eine NoSuchElementException geworfen.
     * Schreibe einen Unit Test für deine Lösung.
     *
     * Beispiel:
     * Collection<Integer> integers = List.of(1, 2, 3, 4, 5, 0);
     * MinMax minMax = MinMaxUtil.findMinMax(integers); // MinMax[min: 0, max: 5]
     */
    record MinMax(int min, int max) {}

    static class MinMaxUtil {

        public static MinMax findMinMax(Collection<Integer> integers) throws NoSuchElementException {
            return null;
        }
    }
}
