package sort;

import java.util.Arrays;

/**
 * Given an array A of positive lengths, return the largest perimeter of a triangle with non-zero area, formed from 3 of these lengths.
 * <p>
 * If it is impossible to form any triangle of non-zero area, return 0.
 * <p>
 * Example 1:
 * Input: [2,1,2]
 * Output: 5
 * <p>
 * Example 2:
 * Input: [1,2,1]
 * Output: 0
 * <p>
 * Example 3:
 * Input: [3,2,3,4]
 * Output: 10
 * <p>
 * Example 4:
 * Input: [3,6,2,3]
 * Output: 8
 * <p>
 * <p>
 * Note:
 * <p>
 * 3 <= A.length <= 10000
 * 1 <= A[i] <= 10^6
 *
 * @author EvelynHf
 * @create 2019-09-26 14:50
 **/
public class LargestPerimeterTriangle {
    public static void main(String[] args) {
        int[] A = {3, 6, 2, 3};
        int result = new LargestPerimeterTriangle().largestPerimeter1(A);
        System.out.print(result);
    }

    public int largestPerimeter1(int[] A) {
        Arrays.sort(A);
        for (int x = A.length - 1; x > 1; x--) {
            if (A[x] < A[x - 1] + A[x - 2] && A[x - 1] > A[x] - A[x - 2]) {
                return A[x] + A[x - 1] + A[x - 2];
            }
        }
        return 0;
    }

    public int largestPerimeter2(int[] A) {
        if (null == A || A.length < 3) {
            return 0;
        }
        this.sort(A, 0, A.length - 1);
        int x = A.length - 1;
        int y = x - 1;
        int z = y - 1;

        while (z >= 0 && y >= 0 && x >= 0) {
            int maxLength = A[x];
            int midLength = A[y];
            int minLength = A[z];
            if (minLength + midLength > maxLength && maxLength - minLength < midLength) {
                return maxLength + midLength + minLength;
            }
            x--;
            y--;
            z--;
        }

        return 0;
    }

    private void sort(int[] A, int start, int end) {
        if (start >= end) {
            return;
        }

        int i = start;
        int j = end;
        while (i < j) {
            while (i < j && A[j] > A[i]) {
                j--;
            }
            if (i < j) {
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                i++;
            }
            while (i < j && A[i] < A[j]) {
                i++;
            }
            if (i < j) {
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                j--;
            }
        }

        sort(A, start, i - 1);
        sort(A, i + 1, end);
    }
}
