import java.util.stream.Stream;

public class Cities {
    public static void main(String[] args) {
        var persons = Stream.of(
                new Person("Linus", new Address("Hüxstraße", "Lübeck")),
                new Person("Adele", new Address("Rathausstraße", "Hamburg")),
                new Person("Ada", new Address("Krumme Straße", "Detmold")),
                new Person("Donald", new Address("Bahnhofstraße", "Bielefeld")),
                new Person("Alan", new Address("Breite Straße", "Lübeck")));
        var cities = persons.map(person ->
                person.address().city()).distinct().toList();
        System.out.println(cities);
    }
}