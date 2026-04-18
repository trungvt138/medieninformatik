import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Das Interface (die Schablone) für den StudentManager.
 * Es definiert, WELCHE Methoden eine Klasse implementieren MUSS,
 * aber nicht WIE.
 */

public interface StudentProcessor {
    Collection<String> getDetails(Collection<Student> students);
    List<String> findNames(Collection<Student> students, double minGrade);
    List<String> findNames(Collection<Student> students , double minGrade, int minAge);
    double averageGrade(Collection<Student> students);
    Optional<Student> findTopStudent(Collection<Student> students);
    boolean exists(Collection<Student> students, String name);
    List<Student> adjustedGrades(Collection<Student> students, double minGrade, int minAge, double adjustment);
}