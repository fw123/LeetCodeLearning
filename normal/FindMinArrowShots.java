package normal;

import java.util.Arrays;
import java.util.Comparator;

public class FindMinArrowShots {

    public static void main(String[] args) {
        int[][] points = new int[][]{{-2147483646,-2147483645},{2147483646,2147483647}};
        System.out.println(findMinArrowShots(points));
    }

    //官方的思路还是牛批啊，先排序，然后使用贪心算法
    //排序是以右边界排序（其实以左边界排序也行，不过贪心的顺序也要反过来，比较反人类）
    //贪心算法按我的理解是：尽可能的一支箭扎爆尽可能多的气球，那最多的情况，就是扎在气球最右侧。
    //第一箭扎在第一个气球的右侧，那接下来有两种情况：
    // 1、如果下一个气球的左侧在这个箭的左边，那么它一定是可以被扎爆的
    // 2、如果一个气球的左侧，在这个箭的右边，那么没法扎爆，那么可以开新一支箭了，新的箭扎在这个气球的右侧
    public static int findMinArrowShots(int[][] points) {
        if(points.length==0) return 0;
        //采用Integer的compare是因为存在int边界值的情况，会出现比较异常{{-2147483646,-2147483645},{2147483646,2147483647}}
        Arrays.sort(points, Comparator.comparingInt(o -> o[1]));//这是lambda写法，最近在学习就正好用上了
        /*这是普通的写法
        Arrays.sort(points, new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        return o1[0]-o2[0];
                    }
                }
        );*/
        int count =1;
        int pos = points[0][1];
        for(int[] arr:points){
            if(arr[0]>pos){
                pos = arr[1];
                count++;
            }
        }
        return count;
    }

    //最开始的自己的思路
    //其实是需要求区间的交集，最后看有几个交集
    //1、先做排序，按左侧由小到大排序
    //2、求交集
    public static int findMinArrowShotsMine(int[][] points) {
        if(points.length==0) return 0;
        //采用Integer的compare是因为存在int边界值的情况，会出现比较异常{{-2147483646,-2147483645},{2147483646,2147483647}}
        Arrays.sort(points, Comparator.comparingInt(o -> o[0]));//这是lambda写法，最近在学习就正好用上了
        /*这是普通的写法
        Arrays.sort(points, new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        return o1[0]-o2[0];
                    }
                }
        );*/
        int i=1;
        int count =1;
        int start = points[0][0];
        int end = points[0][1];
        while(i<points.length){
            int startNew = points[i][0];
            int endNew = points[i][1];
            if(end<startNew){//不相交的情况，交集总数+1，取当前区间为新交集
                start = startNew;
                end = endNew;
                count++;
            }else{//相交，去大的左边界和小的右边界作为新的交集
                start = Math.min(start,startNew);
                end = Math.min(end,endNew);
            }
            i++;
        }
        return count;
    }
}
