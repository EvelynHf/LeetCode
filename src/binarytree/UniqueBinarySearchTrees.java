package src.binarytree;

/**
 * 96. Unique Binary Search Trees
 * Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes
 * of unique values from 1 to n.
 *
 * @author Murphy
 * @Date 2021/08/09 21:13:00
 */
public class UniqueBinarySearchTrees {

    public static void main(String[] args) {
        UniqueBinarySearchTrees uniqueBinarySearchTrees = new UniqueBinarySearchTrees();
        int result = uniqueBinarySearchTrees.numTrees(4);
        //        int result = uniqueBinarySearchTrees.numTrees1(4);
        System.out.println(result);
    }

    /**
     * 思路：动态规划
     * fn[0] = 1;
     * fn[1] = 1;
     * fn[2] = fn[0] * fn[1] + fn[1] * fn[0];
     * fn[3] = fn[0] * fn[2] + fn[1] * fn[1] + fn[2] * fn[0];
     * fn[4] = fn[0] * fn[3] + fn[1] * fn[2] + fn[2] * fn[1] + fn[3] * fn[0];
     */
    public int numTrees(int n) {
        int[] fn = new int[n + 1];
        fn[0] = 1;
        fn[1] = 1;
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= i; j++) {
                int left = fn[j - 1];
                int right = fn[i - j];
                if (left == 0) {
                    count += right;
                } else if (right == 0) {
                    count += left;
                } else {
                    count += left * right;
                }
            }
            fn[i] = count;
        }
        return fn[n];
    }

    /**
     * 思路：递归
     */
    public int numTrees1(int n) {
        if (0 == n || 1 == n) {
            return n;
        }
        int count = 0;
        for (int i = 1; i <= n; i++) {
            int left = numTrees1(i - 1);
            int right = numTrees1(n - i);
            if (left == 0) {
                count += right;
            }
            if (right == 0) {
                count += left;
            }
            count += left * right;
        }
        return count;
    }
}
