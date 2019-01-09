import java.util.ArrayList;
import java.util.List;

/**
 * Shuni Li
 * COMP221 Algorithms Design and Analysis HWD Problem 2
 * Professor Shilad Sen
 * 4/13/16
 */

/**
 * The function maxSum(List<Integer> L) has O(n log n) time complexity.
 * First, we recurse the first half of the array
 * Second, we recurse the second half of the array
 * Then, we check all possible solutions that span the halves of the arrayList.
 * The first two pieces have O(log n) time complexity and the third piece can be found in linear time.
 * Thus, the total time complexity is O(n log n).
 */

public class MaxSum {

    /**
     * To finds the maximum sum of contiguous numbers in the passed-in arrayList
     * @param L the input arrayList
     * @return the maximum sum
     */
    public static int maxSum(List<Integer> L) {
        int len = L.size();

        if (len == 0) {
            return 0;   //empty list, define maxSum as 0
        } else if (len == 1) {
            return L.get(0);   //only has 1 element, define maxSum as itself
        }

        int fin = len - 1;
        int init = 0;
        int mid = (init + fin) / 2;

        int leftMax = maxSum(L.subList(init, mid + 1));
        int rightMax = maxSum(L.subList(mid + 1, len));
        int crossMax;

        int crossMaxleft = L.get(mid) + L.get(mid + 1);
        int currentSum = crossMaxleft;
            for (int i = 1; i <= mid; i++) {
                currentSum += L.get(mid - i);
                if (currentSum > crossMaxleft) {
                    crossMaxleft = currentSum;
                }
            }

        int crossMaxright = L.get(mid) + L.get(mid + 1);
        currentSum = L.get(mid) + L.get(mid + 1);
        for (int j = mid + 2; j < len; j++){
            currentSum += L.get(j);
            if (currentSum > crossMaxright) {
                crossMaxright = currentSum;
            }
        }

        crossMax = crossMaxright + crossMaxleft - L.get(mid) - L.get(mid + 1); //subtract the overlapping parts

        int temp = Math.max(leftMax, rightMax);
        return Math.max(temp, crossMax);
    }

    public static void main(String[] args) {
        //example tests
        List<Integer> list = new ArrayList<>();
        System.out.println(maxSum(list));   //edge case -- empty list; return 0

        list.add(1);
        System.out.println(maxSum(list));   //edge case -- only one element; return the value of the element (1)

        list.add(3);
        list.add(5);
        System.out.println(maxSum(list));   //normal case -- all positive elements; return 1+3+5=9

        List<Integer> list2 = new ArrayList<>();
        list2.add(-3);
        list2.add(-1);
        list2.add(-4);
        System.out.println(maxSum(list2));    //normal case -- all negative elments; return -1

        List<Integer> list3 = new ArrayList<>();
        list3.add(3);
        list3.add(-6);
        list3.add(1);
        list3.add(0);
        list3.add(9);
        list3.add(-4);
        list3.add(2);
        list3.add(1);
        list3.add(-2);
        list3.add(6);
        list3.add(-4);
        System.out.println(maxSum(list3));   //example in the problem -- return 13

        List<Integer> list4 = new ArrayList<>();
        list4.add(1);
        list4.add(2);
        System.out.println(maxSum(list4));  //contain 2 elements -- return 3
    }
}
