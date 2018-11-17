package Recursion;

/**
 * Created by Jline on 2018/11/17.
 */
public class recursion {
    public ListNode removeElement(ListNode head , int val){
        if(head == null)
            return null;
        head.next =removeElement(head.next , val);
        if(head.val == val)
            return head.next;
        else{
            return head;
        }

    }

    public static void main( String[] args ) {
        int[] nums = {1,2,6,3,4,5,6};
        ListNode node = new ListNode(nums);
        System.out.println(node);

        System.out.println(new recursion().removeElement(node , 6));
    }
}
