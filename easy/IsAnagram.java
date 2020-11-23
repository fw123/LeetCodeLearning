package easy;

import java.util.*;

public class IsAnagram {

    public static void main(String[] args) {
        System.out.println(isAnagram("anagram","nagaram"));
    }

    //考虑到unicode还有字符不限于小写字母的情况，使用map存储，s有一个字符将对应value加一，t有一个字符将对应字符减一，最后map为空就是一样，具体往map里加减的判断可以另外实现
    public static boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        Map<Character,Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for(char c:chars){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        char[] chart = t.toCharArray();
        for(char c:chart){
            if(map.get(c) == null){
                return false;
            }else if(map.get(c)==1){
                map.remove(c);
            }else{
                map.put(c,map.get(c)-1);
            }
        }
        if(map.size()==0){
            return true;
        }
        return false;
    }

    //将字符串变成字符数组，排序，然后比对
    public static boolean firstIsAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        char[] chs = s.toCharArray();
        char[] cht = t.toCharArray();
        Arrays.sort(chs);
        Arrays.sort(cht);
        return Arrays.equals(chs,cht);
    }
}
