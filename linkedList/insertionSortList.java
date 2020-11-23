package linkedList; /**
 * 147. 对链表进行插入排序
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
  class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }

class Solution {
    
    //第一反应就是使用Java自带的东西
    //无耻的使用了Java自带的排序，没什么好说的，既然使用了Java，那自带工具类里的排序不用白不用，虽然不合题意，不过可以拿来用用，熟悉一下
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
    
    //第一次写出来的插入排序
    //大致思路就是，维护一个排好序的res链表，从输入链表head中每一个取出的值，从res头节点开始遍历，插入到对应的位置
    //最差的情况就是一个排好序的链表，时间复杂度达到最大
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
    
    //看了官方的解法后，进行了优化
    //优化的地方有两处：
    //1、减少了空间的消耗，只维护了一个链表（微乎其微的作用）
    //2、减少了比对次数（这个挺重要的）
    //优化的方式，就是多维护了一个指针，比对排好序链表的最后节点lastSorted，这样每次比对节点的时候，如果比最后一个节点大，直接接到最后，不用再从头开始遍历比大小
    //思路：
    //维护三个关键指针：1、指向排好序链表的最后一个节点  2、指向要比对的节点  3、需要插入的节点的前一个节点
    //简而言之输入链表head从前往后遍历进行排序，每个节点在判断的时候，前面一个节点都是已排序号的链表的最后一个节点，1）如果大于lastSorted，直接接入排序链表，
    //2）如果小于lastSorted，就从头开始遍历，插入对应的位置
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
    



}
