package src.string;

import java.util.HashSet;

/**
 * @author Murphy
 * @Date 2021/08/02 13:59:00
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        String str = "abccccdd";
        int len = new LongestPalindrome().longestPalindrome(str);
        System.out.println(len);
    }

    public int longestPalindrome(String str) {
        if (null == str || str.length() == 0) {
            return 0;
        }
        HashSet<Character> set = new HashSet<>();
        int count = 0;
        char[] chars = str.toCharArray();
        for (char ch : chars) {
            if (!set.contains(ch)) {
                set.add(ch);
            } else {
                set.remove(ch);
                count++;
            }
        }
        return set.isEmpty() ? count << 1 : (count << 1) + 1;
    }
}
