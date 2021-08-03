package src.tree;

/**
 * 998. Maximum Binary Tree
 *
 * @author Murphy
 * @Date 2021/08/03 20:43:00
 */
public class MaximumBinaryTree {

    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        if (null == root) {
            return node;
        }
        if (root.val < val) {
            node.left = root;
            return node;
        }
        TreeNode pre = root;
        while (null != pre.right) {
            if (pre.right.val < val) {
                node.left = pre.right;
                pre.right = node;
                break;
            }
            pre = pre.right;
        }
        if (null == node.left) {
            pre.right = node;
        }
        return root;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
