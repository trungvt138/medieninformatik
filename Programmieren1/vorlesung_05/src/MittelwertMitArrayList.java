import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MittelwertMitArrayList {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Float> list = new ArrayList<Float>();
        float sum = 0;
        while (true) {
            System.out.printf("%d. Element: ", list.size() + 1);
            list.add(input.nextFloat());
            sum += list.getLast();
            for (Float element : list) {
                System.out.printf("%.1f ", element);
            }
            System.out.printf("- Mittelwert: %.1f\n", sum/list.size());
        }
    }
}
