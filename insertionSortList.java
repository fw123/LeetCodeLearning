/**
 * 147. 对链表进行插入排序
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null){//节点为0或1个时，直接返回
            return head;
        }
        ListNode res = new ListNode(0);
        res.next = head;
        ListNode curr = head.next;//指向当前需排序节点
        ListNode lastSorted = head;//指向已排序好部分的最后的节点
        ListNode prev;//当前需排序节点的前一个节点
        while(curr!=null){//遍历链表
            if(lastSorted.val <= curr.val){//如果当前排序节点大于最后排序节点，则直接加到排序链表上
                lastSorted = lastSorted.next;
            }else{
                prev = res;
                while(prev.next.val <= curr.val){//遍历排好序的链表，找到第一个比curr大的节点
                    prev = prev.next;
                }
                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }
            curr = lastSorted.next;
        }
        return res.next;
    }

    public ListNode firstSolution(ListNode head){
        if(head == null || head.next == null){//节点为0或1个时，直接返回
            return head;
        }
        ListNode res = new ListNode(0);
        res.next = new ListNode(head.val);
        head = head.next;
        while(head != null){
            int val = head.val;//当前需插入的值
            ListNode tmp = res;//每个元素都从排序好的链表res从头遍历
            while(tmp.next !=null && tmp.next.val < val){//若下一个节点比需插入的值小，则指针向后移
                tmp = tmp.next;
            }
            //将当前节点插入
            ListNode tmpNode = new ListNode(val);
            tmpNode.next = tmp.next;
            tmp.next = tmpNode;
            head = head.next;
        }
        return res.next;
    }

    //无耻的使用了Java自带的排序
    public ListNode trickSort(ListNode head){
        ListNode res = new ListNode(0);
        ListNode tmp = res;
        List<Integer> list = new ArrayList<>();
        while(head != null){//先将链表插入数组中
            list.add(head.val);
            head = head.next;
        }
        Collections.sort(list);//对数组排序
        for(Integer i:list){//将数组串到链表中
            tmp.next = new ListNode(i);
            tmp = tmp.next;
        }
        return res.next;
    }
}
