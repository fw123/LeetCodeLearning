package normal;

import java.util.HashMap;
import java.util.Map;

public class FourSumCount {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        //思路
        //先将四个数组排序，然后把所有数组嵌套循环遍历一遍
        //边界条件优化，一旦相加的值大于0，则跳出循环
        //脑子不行啊，这暴力解法确实不行
        //
        //官方解法
        //将四元组分解为二元组A[a]+B[b]的值，放入Map中计数，最终对应的可以求得0的就是-（C[c]+D[d]）
        //距离A[0]+B[0] = 3  那么对应的就是 -3，相加为0对应的C[c]+D[d] = -3,在对应的Map中取值时需要取正，因此是-（C[c]+D[d]）
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : A) {
            for (int b : B) {
                map.put(a + b, map.getOrDefault(a + b, 0) + 1);
            }
        }
        for (int c : C) {
            for (int d : D) {
                count += map.getOrDefault(-(c + d), 0);
            }
        }

        return count;
    }
}
