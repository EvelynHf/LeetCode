package src.string;

import java.util.ArrayList;
import java.util.List;

/**
 * KMP algorithm
 *
 * @author EvelynHf
 * @Date 2021/07/21 10:32:00
 */
public class StringMatchKMP {

    public static void main(String[] args) {
        String str = "BBC ABCDAB ABCDABCDABDE";
        String subStr = "ABCDABD";
        List<MatchBean> mathResult = new StringMatchKMP().kmp(str, subStr);
        System.out.print(mathResult.toString());
    }

    public List<MatchBean> kmp(String str, String subStr) {
        /**
         * 字符串: 前缀集合, 后缀集合, 前缀与后缀最长的共有元素的长度
         * A: {}, {}, 0
         * AB: {A}, {B}, 0
         * ABC: {A,AB}, {C,BC}, 0
         * ABCD: {A,AB,ABC}, {D,CD,BCD}, 0
         * ABCDA: {A,AB,ABC,ABCD}, {A,DA,CDA,BCDA}, 1
         * ABCDAB: {A,AB,ABC,ABCD,ABCDA}, {B,AB,DAB,CDAB,BCDAB}, 2
         * ABCDABD: {A,AB,ABC,ABCD,ABCDA,ABCDAB}, {D,BD,ABD,DABD,CDABD,BCDABD}, 0
         */
        List<MatchBean> matchResult = new ArrayList<>();
        Integer[] preSufFixNum = new Integer[] {0, 0, 0, 0, 1, 2, 0};
        int strLeft = 0, strRight = 0;
        int subPoint = 0;
        int strLen = str.length();
        int subLen = subStr.length();
        while (strRight < strLen) {
            if (str.charAt(strRight) == subStr.charAt(subPoint)) {
                if (subPoint == subLen - 1) {
                    // at the end of substring and matched, record the start index and end index
                    MatchBean matchBean = new MatchBean(strLeft, strRight);
                    matchResult.add(matchBean);
                    strRight++;
                    strLeft = strRight;
                    subPoint = 0;
                } else {
                    // not at the end of substring and matched, move one step forward
                    strRight++;
                    subPoint++;
                }
            } else {
                if (subPoint == 0) {
                    // at the begin of substring and not matched, move one step forward
                    strLeft++;
                    strRight++;
                } else {
                    // not at the begin of substring and not matched, move forward by kmp
                    int fixLen = preSufFixNum[subPoint - 1];
                    int move = subPoint - fixLen;
                    strLeft += move;
                    strRight = strLeft + fixLen;
                    subPoint = fixLen;
                }
            }
        }
        return matchResult;
    }

    /**
     * match bean
     */
    public static class MatchBean {

        /**
         * start index of match string
         */
        private int left;

        /**
         * end index of match string
         */
        private int right;

        public MatchBean(int left, int right) {
            this.left = left;
            this.right = right;
        }

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getRight() {
            return right;
        }

        public void setRight(int right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return "MatchBean{" +
                    "left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
