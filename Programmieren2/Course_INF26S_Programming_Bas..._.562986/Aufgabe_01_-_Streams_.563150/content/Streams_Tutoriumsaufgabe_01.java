package streams;

import java.util.ArrayList;
import java.util.List;

/**
 * Aufgabe 01:
 * Verbessert die Funktionen in dieser Klasse so gut wie möglich mit Streams.
 * Reduziert hierbei so gut es geht.
 * Entscheident ist nur, dass der Output am Ende identisch ist.
 *
 * Viel Erfolg :)
 **/

public class Streams_Tutoriumsaufgabe_01 {

    public static void main(String[] args) {
        List<List<Integer>> allLists = List.of(
                List.of(1,2,3,4,5,6,7,8,9,10),
                List.of(2,4,6,8,10,12,14,16,18,20),
                List.of(21,14,23,5,12,57,3,2,11));

        List<Integer> resultList;
        resultList = sortList(filterList(combineLists(combineLists(allLists.get(0), allLists.get(1)), allLists.get(2))));
        System.out.println(resultList);
    }

    private static List<Integer> filterList(List<Integer> integers) {
        List<Integer> filteredList = new ArrayList<>();
        for (Integer integer : integers) {
            if (integer % 3 == 0 && integer > 5) {
                filteredList.add(integer);
            }
            if (integer % 5 == 0){
                filteredList.add(integer);
            }
        }
        return filteredList;
    }

    private static List<Integer> sortList(List<Integer> list) {
        int temp;
        int i = 0;
        while (i < list.size()-1) {
            if (list.get(i) > list.get(i+1)) {
                temp = list.get(i);
                list.set(i, list.get(i+1));
                list.set(i+1, temp);
                i = 0;
            } else
            {
                i++;
            }
        }
        return list;
    }

    private static List<Integer> combineLists(List<Integer> list1, List<Integer> list2) {
        List<Integer> resultList = new ArrayList<>();
        resultList.addAll(list1);
        for (Integer i : list2) {
            if (!resultList.contains(i)) {
                resultList.add(i);
            }
        }
        return resultList;
    }
}
