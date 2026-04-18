/**
 * Das Student-Objekt: implementiert als 'record'.
 * Ein Record generiert automatisch Konstruktoren, Getter (z.B. name()),
 * hashCode(), equals() und toString().
 */

public record Student(String name, int age, double avgGrade){}