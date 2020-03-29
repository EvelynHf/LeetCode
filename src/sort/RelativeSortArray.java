package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
 * <p>
 * Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.
 * Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.
 * <p>
 * Example 1:
 * <p>
 * Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * Output: [2,2,2,1,4,3,3,9,6,7,19]
 * <p>
 * Constraints:
 * <p>
 * arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * Each arr2[i] is distinct.
 * Each arr2[i] is in arr1.
 *
 * @author EvelynHf
 * @create 2019-09-25 16:19
 **/
public class RelativeSortArray {

    public static void main(String[] args) {
        int[] arr1 = {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] arr2 = {2, 1, 4, 3, 9, 6};
        int[] result = new RelativeSortArray().relativeSortArray1(arr1, arr2);
        System.out.print(Arrays.toString(result));
    }

    public int[] relativeSortArray1(int[] arr1, int[] arr2) {

        int[] count = new int[1001];
        for (int num : arr1) {
            count[num]++;
        }
        int i = 0;
        for (int num : arr2) {
            while (count[num]-- > 0) {
                arr1[i++] = num;
            }
        }
        for (int index = 0; index < count.length; index++) {
            while (count[index]-- > 0) {
                arr1[i++] = index;
            }
        }

        return arr1;
    }

    public int[] relativeSortArray2(int[] arr1, int[] arr2) {

        int[] result = new int[arr1.length];
        if (null == arr1 || 0 == arr1.length) {
            return result;
        }
        List<Integer> other = new ArrayList<>(arr1.length - arr2.length);
        Map<Integer, Integer> arr2Map = new HashMap<>(arr2.length);
        for (int i : arr2) {
            arr2Map.put(i, 0);
        }
        for (int i : arr1) {
            if (arr2Map.containsKey(i)) {
                arr2Map.put(i, arr2Map.get(i) + 1);
            } else {
                other.add(i);
            }
        }
        Collections.sort(other);
        int index = 0;
        for (int i : arr2) {
            int num = arr2Map.get(i);
            while (num > 0) {
                result[index++] = i;
                num--;
            }
        }
        for (int i : other) {
            result[index++] = i;
        }

        return result;
    }
}
