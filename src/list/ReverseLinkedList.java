package list;

/**
 * Reverse a singly linked list.
 * <p>
 * Example:
 * <p>
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 *
 * @author EvelynHf
 * @create 2020-03-29 22:12
 **/
public class ReverseLinkedList {

    public static void main(String[] args) {
        // init list
        ListNode head = new ListNode(1);
        ListNode temp = head;
        for (int i = 2; i <= 10; i++) {
            ListNode node = new ListNode(i);
            temp.next = node;
            temp = node;
        }

        // reverse list
        ListNode newHead = new ReverseLinkedList().reverseList1(head);

        // print list
        temp = newHead;
        while (null != temp) {
            System.out.println(temp.val);
            temp = temp.next;
        }
    }

    public ListNode reverseList1(ListNode head) {

        if (null == head || null == head.next) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        while (null != cur) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    public ListNode reverseList2(ListNode head) {
        return swapNewHead(head, null);
    }

    private ListNode swapNewHead(ListNode head, ListNode newHead) {
        if (null == head) {
            return newHead;
        }
        ListNode next = head.next;
        head.next = newHead;
        return swapNewHead(next, head);
    }
}