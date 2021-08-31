package src.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * 99. Recover Binary Search Tree
 * You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were
 * swapped by mistake. Recover the tree without changing its structure.
 *
 * @author Murphy
 * @Date 2021/08/31 20:34:00
 */
public class RecoverBinarySearchTree {

    TreeNode first = null;
    TreeNode second = null;
    TreeNode pre = new TreeNode(Integer.MIN_VALUE);

    public static void main(String[] args) {
        RecoverBinarySearchTree searchTree = new RecoverBinarySearchTree();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.left = node3;
        node3.right = node2;
        TreeNode root = node1;
        List<TreeNode> list = searchTree.inOrder(root);
        list.stream().map(a -> a.val).forEach(System.out::println);
        searchTree.recoverTree(root);
        list.stream().map(a -> a.val).forEach(System.out::println);

    }

    public void recoverTree(TreeNode root) {
        inOrderTraverse(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
        return;
    }

    private void inOrderTraverse(TreeNode root) {
        if (null == root) {
            return;
        }
        inOrderTraverse(root.left);
        // find first
        if (first == null && (pre.val > root.val)) {
            first = pre;
        }
        // find second
        if (first != null && (pre.val > root.val)) {
            second = root;
        }
        pre = root;
        inOrderTraverse(root.right);
    }

    public void recoverTree2(TreeNode root) {
        List<TreeNode> list = inOrder(root);
        if (null == list || list.size() == 1) {
            return;
        }

        boolean change = true;
        while (change) {
            change = false;
            TreeNode pre;
            TreeNode cur;
            for (int i = 1; i < list.size(); i++) {
                pre = list.get(i - 1);
                cur = list.get(i);
                if (pre.val > cur.val) {
                    int tempVal = cur.val;
                    cur.val = pre.val;
                    pre.val = tempVal;
                    change = true;
                }
            }
        }
        return;
    }

    public List<TreeNode> inOrder(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        if (null == root) {
            return list;
        }
        if (null != root.left) {
            list.addAll(inOrder(root.left));
        }
        list.add(root);
        if (null != root.right) {
            list.addAll(inOrder((root.right)));
        }
        return list;
    }
}
