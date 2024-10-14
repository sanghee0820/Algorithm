/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
import java.util.*;

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        var answer = new ListNode();
        var current = answer;

        while(l1 != null || l2 != null){
            int l1Value = 0;
            int l2Value = 0;
            if(l1 != null){
                l1Value = l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                l2Value = l2.val;
                l2 = l2.next;
            }

            current.val += (l1Value + l2Value);
            int upper = current.val / 10;
            current.val = current.val % 10;
            if(l1 == null && l2 == null && upper == 0){
                continue;
            }
            current.next = new ListNode(upper);
            current = current.next;
        }
        
        return answer;
    }
}