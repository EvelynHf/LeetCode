package src.binarytree;

/**
 * TreeNode
 *
 * @author Murphy
 * @Date 2021/08/05 20:45:00
 */
public class TreeNode implements Cloneable {

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
