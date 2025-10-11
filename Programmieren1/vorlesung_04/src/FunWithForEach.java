import java.util.Arrays;

public class FunWithForEach {
    public static void main(String[] args) {
        int[] arr = {1, 5, 2, 5, 7};
        Arrays.sort(arr);
        int sum = 0;
        int evens = 0;
        int multiple = 0;

        for (int element : arr) {
            sum += element;

            if (element % 2 == 0) {
                evens++;
            }

            int checkMulti = 0;
            for (int e : arr) {
                if (element == e) {
                    checkMulti++;
                }
            }
            if (checkMulti > 1) {
                multiple = element;
            }
        }
        System.out.printf("count: %d sum: %d evens: %d multiple: %d", arr.length, sum, evens, multiple);
    }
}
