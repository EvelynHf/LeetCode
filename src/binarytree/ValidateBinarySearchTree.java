package src.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 98. Validate Binary Search Tree
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * @author Murphy
 * @Date 2021/08/11 21:00:00
 */
public class ValidateBinarySearchTree {

    public static void main(String[] args) {
        ValidateBinarySearchTree searchTree = new ValidateBinarySearchTree();
        TreeNode root = new TreeNode(2, new TreeNode(2), new TreeNode(2));
        System.out.print(searchTree.isValidBST(root));
    }

    public boolean isValidBST(TreeNode root) {
        if (null == root) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        root = root.left;
        TreeNode pre = null;
        while (null != root || !stack.isEmpty()) {
            while (null != root) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pre != null && pre.val > root.val) {
                return false;
            }
            pre = root;
            root = root.right;
        }
        return true;
    }

    public boolean isValidBST2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        return isValidBST2(root, list);
    }

    public boolean isValidBST2(TreeNode root, List<Integer> list) {
        if (null == root) {
            return true;
        }
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int val = root.val;
        boolean leftValid = isValidBST2(root.left, left);
        if (!leftValid) {
            return leftValid;
        }
        if (left.size() == 0 || left.get(left.size() - 1) < val) {
            list.addAll(left);
            list.add(val);
        } else {
            return false;
        }
        boolean rightValid = isValidBST2(root.right, right);
        if (!rightValid) {
            return rightValid;
        }
        if (right.size() == 0 || right.get(0) > val) {
            list.addAll(right);
        } else {
            return false;
        }
        return true;
    }

    public boolean isValidBST1(TreeNode root) {
        List<Integer> inorder = inorder1(root);
        if (inorder.size() == 0) {
            return true;
        }
        int pre = inorder.get(0);
        int val;
        for (int i = 1; i < inorder.size(); i++) {
            val = inorder.get(i);
            if (pre < val) {
                pre = val;
            } else {
                return false;
            }
        }
        return true;
    }

    public List<Integer> inorder1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (null == root) {
            return list;
        }
        list.addAll(inorder1(root.left));
        list.add(root.val);
        list.addAll(inorder1(root.right));
        return list;
    }
}
