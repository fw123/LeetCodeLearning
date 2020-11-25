package easy;

import java.util.Arrays;

public class SortString {

    public static void main(String[] args) {
        String str = "aaaabbbbcccc";
        System.out.println(sortString(str));
    }

    //思路：
    //1、将字符串转为字符数组，排序，从小到大遍历，再从大到小遍历，直到数组为空(对此题不适用)
    //2、维护一个26长度数组，代表每个字符存储的数量，从前往后再从后往前不断遍历，直到所有字符数量为0
    public static String sortString(String s) {
        StringBuilder sb = new StringBuilder();
        int[] cNum = new int[26];
        for(char c:s.toCharArray()){
            cNum[c-'a']++;
        }
        int len = s.length();
        while(len>0){
            for(int i=0;i<26;i++){
                if(cNum[i]>0){
                    cNum[i]--;
                    sb.append((char)(i+'a'));
                    len--;
                }
            }
            for(int i=25;i>=0;i--){
                if (cNum[i] > 0) {
                    cNum[i]--;
                    sb.append((char)(i+'a'));
                    len--;
                }
            }
        }
        return sb.toString();
    }
}
