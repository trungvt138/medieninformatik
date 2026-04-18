package de.hawhamburg.v16;

import java.util.LinkedList;

class Recursion {

    static void main() {
        int local = 42; // stack
        Integer number = 42; // heap
        LinkedList<Integer> numbers = new LinkedList<>(); // heap
        while(true) { // iterativ
            numbers.add(1); // heap
            // -> Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        }
    }
}
