package com.nowcoder.write_exam.inke;

public class InKeE02 {
    static class ListNode{
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2){
        ListNode head = new ListNode(0);
        ListNode first = head;
        ListNode p = l1;
        ListNode q = l2;
        while (p != null || q != null){
            int sum = p.val + q.val;
            ListNode temp = new ListNode(sum);
            // 记得连接next
            first.next = temp;
            first = first.next;
            p = p.next;
            q = q.next;
        }
        while (p != null){
            first.val = p.val;
            p = p.next;
        }
        while (q != null){
            first.val = q.val;
            q = q.next;
        }
        return head.next;
    }

    public static void main(String[] args) {
        InKeE02 inKeE02 = new InKeE02();

    }
}
