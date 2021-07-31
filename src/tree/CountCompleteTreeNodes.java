package src.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 统计完全二叉树的节点个数
 *
 * @author Murphy
 * @Date 2021/07/30 14:39:00
 */
public class CountCompleteTreeNodes {

    public static void main(String[] args) {
        CountCompleteTreeNodes countTreeNodes = new CountCompleteTreeNodes();
        TreeNode bigTree = countTreeNodes.buildBigTree(20);
        long startTime = System.nanoTime();
        //        int count = countTreeNodes.countTree1(bigTree);
        //        int count = countTreeNodes.countTree2(bigTree);
        //        int count = countTreeNodes.countTree3(bigTree);
        int count = countTreeNodes.countTree4(bigTree);
        long endTime = System.nanoTime();
        long time = endTime - startTime;
        System.out.printf("The result is: %d, time: %d(ns)", count, time);
    }

    // 方法四：二分法+位图
    public int countTree4(TreeNode tree) {
        if (null == tree) {
            return 0;
        }
        int h = 0;
        TreeNode p = tree.left;
        while (null != p) {
            h++;
            p = p.left;
        }
        int low = 1 << h, high = (1 << h + 1) - 1;
        while (low < high) {
            int mid = (low + high + 1) / 2;
            if (exist(tree, mid, h)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private boolean exist(TreeNode tree, int mid, int h) {
        TreeNode p = tree;
        int bit = 1 << (h - 1);
        while (null != p && bit > 0) {
            if ((bit & mid) == 0) {
                p = p.left;
            } else {
                p = p.right;
            }
            bit = bit >> 1;
        }
        return (null == p) ? false : true;
    }

    // 方法三：比较子树最左分支和最右分支高度
    public int countTree3(TreeNode tree) {
        if (null == tree) {
            return 0;
        }
        TreeNode l = tree;
        TreeNode r = tree;
        int hl = 0;
        int hr = 0;
        while (null != l) {
            hl++;
            l = l.left;
        }
        while (null != r) {
            hr++;
            r = r.right;
        }
        if (hl == hr) {
            return (int) Math.pow(2, hl) - 1;
        } else {
            return 1 + countTree3(tree.left) + countTree3(tree.right);
        }
    }

    // 方法二：计算子树高度
    public int countTree2(TreeNode tree) {
        if (null == tree) {
            return 0;
        }
        int treeHt = calTreeHeight(tree);
        if (treeHt == 1) {
            return 1;
        }
        if (treeHt == 2) {
            return (null == tree.right) ? 2 : 3;
        }
        int rightTreeHt = calTreeHeight(tree.right);
        int rightCount;
        int leftCount;
        if (rightTreeHt < treeHt - 1) {
            // 计算满树的节点个数
            rightCount = (int) (Math.pow(2, treeHt - 2)) - 1;
            leftCount = countTree2(tree.left);
        } else {
            // 计算满树的节点个数
            leftCount = (int) (Math.pow(2, treeHt - 1)) - 1;
            rightCount = countTree2(tree.right);
        }
        return rightCount + leftCount + 1;
    }

    // 方法一：深度遍历
    public int countTree1(TreeNode tree) {
        if (null == tree) {
            return 0;
        }
        return 1 + countTree1(tree.left) + countTree1(tree.right);
    }

    // 计算树的高度
    private int calTreeHeight(TreeNode tree) {
        int treeHt = 0;
        TreeNode point = tree;
        while (null != point) {
            treeHt++;
            point = point.left;
        }
        return treeHt;
    }

    public static class TreeNode implements Cloneable {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        @Override
        protected TreeNode clone() {
            TreeNode copy = new TreeNode();
            copy.val = this.val;
            if (null != this.left) {
                copy.left = this.left.clone();
            }
            if (null != this.right) {
                copy.right = this.right.clone();
            }
            return copy;
        }
    }

    private TreeNode buildBigTree(int num) {
        // 用一颗节点数为15的满树，来构造一颗比较大的二叉树，用于测试比较各个算法的时间复杂度
        int[] nodes = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        TreeNode root = new TreeNode(nodes[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int index = 1;
        int len = nodes.length;
        while (index < len) {
            TreeNode node = queue.poll();
            node.left = new TreeNode(nodes[index++]);
            queue.offer(node.left);
            if (index < len) {
                node.right = new TreeNode(nodes[index++]);
                queue.offer(node.right);
            }
        }
        // 节点数 = 15*2^num + 2^num - 1
        return buildBigTree(root, num);
    }

    private TreeNode buildBigTree(TreeNode subTree, Integer num) {
        if (num == 0) {
            return subTree;
        }
        TreeNode root = new TreeNode(1);
        root.left = subTree.clone();
        root.right = subTree.clone();
        return buildBigTree(root, num - 1);
    }
}
