package src.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * TreeNode Util
 *
 * @author Murphy
 * @Date 2021/08/11 10:25:00
 */
public class TreeNodeUtil {

    public static void printPreOrder(TreeNode root) {
        if (null == root) {
            System.out.printf("null\t");
        } else {
            System.out.printf("%d\t", root.val);
            if (null == root.left && null == root.right) {
                return;
            }
            printPreOrder(root.left);
            printPreOrder(root.right);
        }
    }

    public static List<Integer> listPreOrder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (null == root) {
            list.add(null);
        } else {
            list.add(root.val);
            if (null != root.left || null != root.right) {
                list.addAll(listPreOrder(root.left));
                list.addAll(listPreOrder(root.right));
            }
        }
        return list;
    }
}
