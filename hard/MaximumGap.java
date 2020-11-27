package hard;

import java.util.Arrays;

public class MaximumGap {

    //思路：
    //最简单的直接的思路，就是按照题意，先排序，然后遍历求最大差值
    //看了官方的思路，原来是问题在排序上，根据题意，是可以使用基数排序，即桶排序，遍历求最大值这个都一样
    //基数排序就是从低位到高位，每个位数上进行分组和排序
    //举例：73, 22, 93, 43, 55, 14, 28, 65, 39, 81
    //先按个位数，分组从0-9，然后再把这些数字串起来，再从十位数进行分组，直到最高位

    //代码直接贴的官方的，看明白也是一大锻炼
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }
        long exp = 1;//进行分组的位数，1代表个位数，
        int[] buf = new int[n];//放排序好的数组
        int maxVal = Arrays.stream(nums).max().getAsInt();//求最大值，用来做进位，求到最高位，这里用的Java8里的新特性Stream，不学后悔一辈子

        while (maxVal >= exp) {//循环条件，一直到高位，因为每次循环完exp会进一位
            int[] cnt = new int[10];//用来存放每个数字有多少个从0-9，例如有10个数字个位是0，那么cnt[0] = 10
            for (int i = 0; i < n; i++) {//求每个分组的数字的个数
                int digit = (nums[i] / (int) exp) % 10;//当前位是数字多少，给对应的数组里的+1
                cnt[digit]++;
            }
            for (int i = 1; i < 10; i++) {//其实是变相的赋值每个数字的填充的起点，比如0有10个，那1应该从11开始填充，下面会使用cnt[i]来当做填充的位置，所以cnt[1]的值应该是加上cnt[0]
                cnt[i] += cnt[i - 1];
            }
            for (int i = n - 1; i >= 0; i--) {//开始填充排序数组buf
                int digit = (nums[i] / (int) exp) % 10;
                buf[cnt[digit] - 1] = nums[i];//cnt[i]里存的就是这个数字对应的存放的位置，因为不需要排序，所以只要符合数字要求就可以
                cnt[digit]--;
            }
            System.arraycopy(buf, 0, nums, 0, n);//将排序好的数组放入nums
            exp *= 10;//进入高位，比如从个位进到十位
        }

        int ret = 0;
        for (int i = 1; i < n; i++) {//求最大差值
            ret = Math.max(ret, nums[i] - nums[i - 1]);
        }
        return ret;
    }

    //直接使用普通排序的渣渣方法，好处就是简单不费脑，坏处是，基本这题做了跟没做一样
    public int maximumGapMine(int[] nums) {
        if(nums.length<2){
            return 0;
        }
        Arrays.sort(nums);
        int max = 0;
        for(int i=0;i<nums.length-1;i++){
            max = Math.max(max,nums[i+1]-nums[i]);
        }
        return max;
    }
}
