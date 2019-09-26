package sort;

import com.sun.org.apache.regexp.internal.RE;

import java.util.Arrays;

/**
 * Given an array A of non-negative integers, half of the integers in A are odd, and half of the integers are even.
 * Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.
 * You may return any answer array that satisfies this condition.
 * <p>
 * Example 1:
 * Input: [4,2,5,7]
 * Output: [4,5,2,7]
 * Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.
 * <p>
 * Note:
 * 2 <= A.length <= 20000
 * A.length % 2 == 0
 * 0 <= A[i] <= 1000
 *
 * @author EvelynHf
 * @create 2019-09-24 21:38
 **/
public class SortArrayByParityII {

    public static void main(String[] args) {
        int[] A = {2, 3, 3, 4};
        int[] result = new Solution2().sortArrayByParityII(A);
        System.out.print(Arrays.toString(result));
    }

    private static class Solution2 {
        public int[] sortArrayByParityII(int[] A) {
            int i = 0;
            int j = 1;
            int n = A.length;
            while (i < n && j < n) {
                while (i < n && A[i] % 2 == 0) {
                    i += 2;
                }
                while (j < n && A[j] % 2 == 1) {
                    j += 2;
                }

                if (i < n && j < n) {
                    this.swap(A, i, j);
                }
            }

            return A;
        }

        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    private static class Solution {
        public int[] sortArrayByParityII(int[] A) {
            int[] result = new int[A.length];
            if (null == A || 0 == A.length) {
                return result;
            }
            int oddPoint = 0;
            int evenPoint = 1;
            int length = A.length;
            for (int i = 0; i < length; i++) {
                int value = A[i];
                if (0 == value % 2) {
                    // odd
                    result[oddPoint] = value;
                    oddPoint += 2;
                } else {
                    // even
                    result[evenPoint] = value;
                    evenPoint += 2;
                }
            }

            return result;
        }
    }
}


