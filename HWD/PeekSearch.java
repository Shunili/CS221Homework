import java.util.ArrayList;
import java.util.List;

/**
 * Shuni Li
 * COMP221 Algorithms Design and Analysis HWD Problem 1
 * Professor Shilad Sen
 * 4/13/16
 */

/**
 * Analysis
 * The function peak(List<Integer> L) has O(log n)time complexity.
 * Essentially, it is a binary search problem. In the while loop,
 * we are dividing the size by 2 every time.
 * Thus, O(n) = O(n/2) + 1 = log(n) + 1 = O(log n).
 */
public class PeekSearch {

    /**
     * To find the peak entry p for a unimodal arrayList L
     * @param L the input arrayList
     * @return the peak p
     */
    public static int peak(List<Integer> L) {
        if (L.size() == 0) {
            return -1;   //empty list, define peek as -1
        }

        int fin = L.size() - 1;
        int init = 0;
        int pos;

        while (fin - init > 1) {
            pos = (init + fin) / 2;
            if (L.get(pos - 1) < L.get(pos) && L.get(pos) < L.get(pos + 1)) {
                init = pos;
            } else if (L.get(pos - 1) > L.get(pos) && L.get(pos) > L.get(pos + 1)) {
                fin = pos;
            } else if (L.get(pos - 1) < L.get(pos) && L.get(pos) > L.get(pos + 1)) {
                return pos;
            }
        }

        //L has distinct entries, only two cases:
        if (L.get(fin) > L.get(init)) {
            return fin;   //increasing, peek is fin
        } else {
            return init;  //decreasing, peek is init
        }
    }

    public static void main(String[] args) {
        //example tests
        List<Integer> list = new ArrayList<>();
        System.out.println(peak(list));   //edge case -- empty list; return -1

        list.add(1);
        System.out.println(peak(list));   //edge case -- only one element; return index 0

        list.add(3);
        list.add(5);
        System.out.println(peak(list));   //edge case -- all increasing increasing elements; return 2

        list.add(4);
        list.add(2);
        System.out.println(peak(list));    //normal case -- return 2

        List<Integer> list2 = new ArrayList<>();
        list2.add(5);
        list2.add(3);
        list2.add(1);
        System.out.println(peak(list2));    //edge case -- all decreasing elements; return 0

        List<Integer> list3 = new ArrayList<>();
        list3.add(3);
        list3.add(5);
        list3.add(8);
        list3.add(10);
        list3.add(12);
        list3.add(5);
        list3.add(1);
        System.out.println(peak(list3));   //example in the problem, return 4
    }
}
