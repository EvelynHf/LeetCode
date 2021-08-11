package src.binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 95. Unique Binary Search Trees II
 * Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of
 * unique values from 1 to n. Return the answer in any order.
 *
 * @author Murphy
 * @Date 2021/08/10 17:26:00
 */
public class UniqueBinarySearchTreesII {

    public static void main(String[] args) {
        UniqueBinarySearchTreesII searchTreesII = new UniqueBinarySearchTreesII();
        List<TreeNode> trees = searchTreesII.generateTrees(3);
        trees.forEach(tree -> System.out.println(TreeNodeUtil.listPreOrder(tree)));
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        Map<Integer, List<TreeNode>> treeMap = new HashMap<>();
        treeMap.put(0, new ArrayList<>());
        treeMap.get(0).add(null);
        for (int i = 1; i <= n; i++) {
            List<TreeNode> curList = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                /**
                 * left = j - 1;
                 * right = i - j;
                 * offset = j;
                 */
                List<TreeNode> leftList = clone(0, treeMap.get(j - 1));
                List<TreeNode> rightList = clone(j, treeMap.get(i - j));
                List<TreeNode> rootList = mergeTrees(j, leftList, rightList);
                curList.addAll(rootList);
            }
            treeMap.put(i, curList);
        }
        return treeMap.get(n);
    }

    public TreeNode clone(int diff, TreeNode tree) {
        if (null == tree) {
            return null;
        }
        TreeNode cloneTree = new TreeNode(tree.val + diff);
        cloneTree.left = clone(diff, tree.left);
        cloneTree.right = clone(diff, tree.right);
        return cloneTree;
    }

    public List<TreeNode> clone(int offset, List<TreeNode> trees) {
        List<TreeNode> cloneTrees = new ArrayList<>();
        if (trees.size() == 0) {
            return cloneTrees;
        }
        trees.forEach(tree -> cloneTrees.add(clone(offset, tree)));
        return cloneTrees;
    }

    public List<TreeNode> mergeTrees(int root, List<TreeNode> leftList, List<TreeNode> rightList) {
        List<TreeNode> mergeTrees = new ArrayList<>();
        leftList.forEach(left ->
                rightList.forEach(right -> {
                    mergeTrees.add(new TreeNode(root, left, right));
                })
        );
        return mergeTrees;
    }
}
