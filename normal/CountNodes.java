package normal;

import javax.swing.tree.TreeNode;

public class CountNodes {
    //先思考十分钟，想出所有思路
    //思路有三
    //1、最暴力的办法就是遍历二叉树，就不写了
    //2、递归，求左右子树的完全二叉树
    //3、根据完全二叉树的定义，先求树的深度，然后求得最后一层的节点个数（只有思路，具体实现没想出来）
    //先求最左叶子节点层数和最右叶子节点层数：1）一样那就好求了，是满节点的 2）右节点为最后一层的上一层，求这一层的有空子节点的节点位置

    //递归只要想清楚边界条件写好，太轻松了
    public int recursionCountNodes(TreeNode root) {
        if (root == null) {//无根节点，返回1
            return 0;
        }
        return 1 + recursionCountNodes(root.left) + recursionCountNodes(root.right);//左右节点都不空，则为根节点（1）加上左右子树的节点数
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
