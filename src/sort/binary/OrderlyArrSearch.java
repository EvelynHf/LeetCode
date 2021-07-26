package src.sort.binary;

/**
 * 寻找有序数组中元素第一次出现和最后一次出现的索引
 * 思路：二分查找
 *
 * @author EvelynHf
 * @Date 2021/07/23 17:34:00
 */
public class OrderlyArrSearch {

    public static void main(String[] args) {
        int[][] arrs = {
                {},
                {0, 1, 2, 4, 5},
                {4},
                {0, 1, 2, 3, 4},
                {0, 1, 2, 4, 4},
                {4, 4, 4, 4, 5}
        };
        int[] nums = {4, 3, 4, 4, 4, 4};
        OrderlyArrSearch search = new OrderlyArrSearch();
        int firstIndex;
        int lastIndex;
        for (int i = 0; i < nums.length; i++) {
            firstIndex = search.getFirstIndex(arrs[i], nums[i], 0, arrs[i].length - 1);
            lastIndex = search.getLastIndex(arrs[i], nums[i], 0, arrs[i].length - 1);
            System.out.printf("first index: %d, last index: %d", firstIndex, lastIndex);
            System.out.println();
        }
    }

    public int getFirstIndex(int[] arr, int num, int left, int right) {
        if (null == arr || 0 == arr.length || left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (num == arr[mid] && (mid - 1 < 0 || arr[mid - 1] < num)) {
            return mid;
        } else if (num <= arr[mid]) {
            return getFirstIndex(arr, num, left, mid - 1);
        } else {
            return getFirstIndex(arr, num, mid + 1, right);
        }
    }

    public int getLastIndex(int[] arr, int num, int left, int right) {
        if (null == arr || 0 == arr.length || left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (num == arr[mid] && (mid + 1 >= arr.length || arr[mid + 1] > num)) {
            return mid;
        } else if (num >= arr[mid]) {
            return getLastIndex(arr, num, mid + 1, right);
        } else {
            return getLastIndex(arr, num, left, mid - 1);
        }
    }
}
