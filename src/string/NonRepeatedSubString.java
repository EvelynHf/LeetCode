package src.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 最长非重复子串
 * 一个字符串中最长的没有重复字符的子串
 * 举例：
 * abcabcbb 最长子串 abc 长度为3
 * bbbbbbb 最长子串 b 长度为1
 * abdevbac 最长子串devbac长度为6
 * 思路：
 * ① 滑动窗口
 * ② 动态规划
 *
 * @author EvelynHf
 * @Date 2021/07/23 18:53:00
 */
public class NonRepeatedSubString {

    public static void main(String[] args) {
        String str = "abdevbac";
        //        String str = "abcabcbb";
        //        String str = "";
        //        String str = "bbbbbbb";
        NonRepeatedSubString nonRepeatedSubString = new NonRepeatedSubString();
        String longestSubStr = nonRepeatedSubString.longestSubString1(str);
        //        String longestSubStr = nonRepeatedSubString.longestSubString2(str);
        System.out.printf("longest non repeated sub string: %s, length: %d", longestSubStr, longestSubStr.length());
    }

    /**
     * 方法一：滑动窗口
     * 思路：遍历每个字符，计算以该字符为起始索引的最长非重复子串
     * chSet 保存当前非重复子串的字符
     * lIndex 保存当前非重复子串的起始索引
     * rIndex 保存当前非重复子串的结束索引
     * lIndex向右滑动，计算以lIndex开始的最长非重复子串：
     * ① 计算最长非重复子串的结束索引：若chSet.contains(s[rIndex])，rIndex-1为最长非重复子串的结束索引，否则rIndex向右滑动，rIndex++
     * ② 记录以lIndex开始的最长非重复子串，lIndex向右滑动，chSet.remove(s[lIndex])，lIndex++
     *
     * @param str
     * @return
     */
    public String longestSubString1(String str) {
        String longestStr = "";
        if (null == str || str.length() == 0) {
            return longestStr;
        }
        Set<Character> chSet = new HashSet<>();
        int len = str.length();
        int lIndex = -1;
        int rIndex = -1;
        while (lIndex < len && rIndex < len) {
            if (lIndex == -1) {
                lIndex = 0;
                chSet.add(str.charAt(lIndex));
                rIndex = lIndex + 1;
            }
            while (rIndex < len && !chSet.contains(str.charAt(rIndex))) {
                chSet.add(str.charAt(rIndex));
                rIndex++;
            }
            if (rIndex - lIndex > longestStr.length()) {
                longestStr = str.substring(lIndex, rIndex);
            }
            chSet.remove(str.charAt(lIndex));
            lIndex++;
        }
        return longestStr;
    }

    /**
     * 方法二：动态规划
     * 思路：遍历每个字符，计算以该字符为结束索引的最长非重复子串
     * dp[] 保存不重复子串的长度
     * li[] 保存已经遍历过的字符最后的索引，没有遍历过的字符索引为-1
     * ls 保存s[0...i-1]字符串中不重复子串的起始索引
     * 当计算dp[i]时:
     * 若 li[s[i]] >= ls，表示s[i]在s[0...i-1]的不重复子串中存在，更新起始索引 ls = li[s[i]] + 1，dp[i] = i - ls + 1;
     * 否则 dp[i] = dp[i - 1] + 1;
     *
     * @param str
     * @return
     */
    public String longestSubString2(String str) {
        String longestStr = "";
        if (null == str || str.length() == 0) {
            return longestStr;
        }
        // 保存不重复子串的长度
        int[] dp = new int[str.length()];
        // 保存已经遍历过的字符最后的索引，没有遍历过的字符索引为-1
        int[] li = new int[256];
        Arrays.fill(li, -1);
        // 保存s[0...i-1]中不重复子串的起始索引
        int ls = 0;
        for (int i = 0; i < str.length(); i++) {
            Character ch = str.charAt(i);
            if (i == 0) {
                dp[i] = 1;
            } else if (li[ch] >= ls) {
                ls = li[ch] + 1;
                dp[i] = i - ls + 1;
            } else {
                dp[i] = dp[i - 1] + 1;
            }
            if (dp[i] > longestStr.length()) {
                longestStr = str.substring(ls, i + 1);
            }
            li[ch] = i;
        }
        return longestStr;
    }
}
