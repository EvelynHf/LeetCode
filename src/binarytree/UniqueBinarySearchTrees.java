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
        System.out.println(result);
    }

    /**
     * 思路：动态规划
     * fn[1] = 1;
     * fn[2] = fn[1] * 2;
     * fn[3] = fn[1] * fn[1] + fn[2] * 2;
     * fn[4] = fn[1] * fn[2] + fn[2] * fn[1] + fn[3] * 2;
     */
    public int numTrees(int n) {
        int[] fn = new int[n + 1];
        fn[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            int count = 0;
            for (int j = 1; j < i - 1; j++) {
                count += fn[j] * fn[i - 1 - j];
            }
            count += fn[i - 1] * 2;
            fn[i] = count;
        }
        return fn[n];
    }
}
