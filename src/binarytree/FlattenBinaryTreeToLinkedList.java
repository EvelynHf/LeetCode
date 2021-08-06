package src.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 114. Flatten Binary Tree to Linked List
 *
 * @author Murphy
 * @Date 2021/08/05 20:44:00
 */
public class FlattenBinaryTreeToLinkedList {

    public static void main(String[] args) {
        FlattenBinaryTreeToLinkedList flattenBean = new FlattenBinaryTreeToLinkedList();
        Integer[] arr = new Integer[] {1, 2, 5, 3, 4, null, 6};
        //        Integer[] arr = new Integer[] {1, 2, 5};
        //        Integer[] arr = new Integer[] {1, 2};
        //        Integer[] arr = new Integer[] {0};
        TreeNode root = flattenBean.buildTree(arr);
        flattenBean.flatten(root);
        flattenBean.preOrder(root);
    }

    public void flatten(TreeNode root) {
        flattenChild(root);
    }

    public TreeNode flattenChild(TreeNode root) {
        if (null == root) {
            return root;
        }
        if (null == root.right && root.left == null) {
            return root;
        }
        if (null == root.right) {
            root.right = root.left;
            root.left = null;
            return flattenChild(root.right);
        }
        if (null == root.left) {
            return flattenChild(root.right);
        }
        TreeNode leftLast = flattenChild(root.left);
        TreeNode rightLast = flattenChild(root.right);
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = null;
        leftLast.right = temp;
        return rightLast;
    }

    public TreeNode buildTree(Integer[] arr) {
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(arr[0]);
        queue.offer(root);
        int i = 1;
        while (i < arr.length) {
            TreeNode node = queue.poll();
            if (null == node) {
                continue;
            }
            if (null != arr[i]) {
                node.left = new TreeNode(arr[i]);
            }
            queue.offer(node.left);
            i++;
            if (i < arr.length) {
                if (null != arr[i]) {
                    node.right = new TreeNode(arr[i]);
                }
                queue.offer(node.right);
                i++;
            }
        }
        return root;
    }

    public void preOrder(TreeNode root) {
        TreeNode node = root;
        if (null == root) {
            System.out.printf("null\t");
        } else {
            System.out.printf("%d\t", node.val);
            if (null == root.left && null == root.right) {
                return;
            }
            preOrder(root.left);
            preOrder(root.right);
        }
    }
}
