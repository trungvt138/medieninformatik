import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Implementiert einen generischen Stack (LIFO - Last-In, First-Out)
 * unter Verwendung einer internen LinkedList.
 * @param <T> Der Typ der Elemente, die im Stack gespeichert werden.
 */
public class MyStack<T> {

    // Die interne Datenstruktur zur Speicherung der Stack-Elemente.
    // LinkedList ist effizient für das Hinzufügen/Entfernen am Ende.
    private final LinkedList<T> elements = new LinkedList<>();

    /**
     * Fügt ein Element oben auf den Stack hinzu (ans Ende der Liste).
     * @param element Das hinzuzufügende Element.
     */
    public void push(T element) {
        elements.addLast(element);
    }
    // Die Klasse MyStack verwaltet intern eine LinkedList, erbt aber nicht von ihr.
    // Wir definieren eine eigene push-Methode.
    // Die push-Methode der LinkedList wird nicht verwendet.

    /**
     * Entfernt das oberste Element vom Stack (das letzte Element der Liste)
     * und gibt es zurück.
     * @return Das entfernte Element.
     * @throws NoSuchElementException wenn der Stack leer ist.
     */
    public T pop() throws NoSuchElementException {
        // Prüfen, ob Elemente vorhanden sind, bevor eines entfernt wird.
        if(isEmpty()) {
            // Wenn der Stack leer ist, wird die geforderte Exception geworfen.
            throw new NoSuchElementException("pop() kann nicht auf einen leeren Stack angewendet werden.");
        }
        // Das letzte Element entfernen und zurückgeben.
        return elements.removeLast();
    }

    /**
     * Überprüft, ob der Stack leer ist.
     * @return true, wenn keine Elemente enthalten sind, sonst false.
     */
    public boolean isEmpty() {
        return elements.isEmpty();
    }


    /**
     * Die main-Methode zum Testen der MyStack-Klasse.
     */
    public static void main(String[] args){
        // 1. Erstellen eines neuen Stacks für Integer-Werte.
        MyStack<Integer> numberStack = new MyStack<>();
        System.out.println("Ein neuer Stack wurde erstellt.");

        // 2. Testen der isEmpty()-Methode bei einem leeren Stack.
        System.out.println("Ist der Stack leer? " + numberStack.isEmpty()); // Erwartet: true

        // 3. Hinzufügen von Elementen mit push().
        System.out.println("\nFüge die Elemente 10, 20 und 30 hinzu...");
        numberStack.push(10);
        numberStack.push(20);
        numberStack.push(30);
        System.out.println("Ist der Stack jetzt leer? " + numberStack.isEmpty()); // Erwartet: false

        // 4. Entfernen der Elemente mit pop() in LIFO-Reihenfolge (Last-In, First-Out).
        System.out.println("\nEntferne Elemente mit pop():");
        try {
            System.out.println("Gepoppt: " + numberStack.pop()); // Erwartet: 30
            System.out.println("Gepoppt: " + numberStack.pop()); // Erwartet: 20
            System.out.println("Gepoppt: " + numberStack.pop()); // Erwartet: 10
        } catch (NoSuchElementException e) {
            System.err.println("Unerwarteter Fehler: " + e.getMessage());
        }

        // 5. Überprüfen, ob der Stack nach dem Leeren wieder leer ist.
        System.out.println("Ist der Stack am Ende leer? " + numberStack.isEmpty()); // Erwartet: true

        // 6. Versuch, pop() auf einem leeren Stack aufzurufen, um die Exception zu testen.
        System.out.println("\nVersuche, pop() auf dem leeren Stack aufzurufen...");
        try {
            numberStack.pop();
        } catch (NoSuchElementException e) {
            // Die erwartete Exception wird hier gefangen und die Fehlermeldung ausgegeben.
            System.err.println("Erfolgreich abgefangen: " + e.getMessage());
        }
    }
}