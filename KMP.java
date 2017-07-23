import java.util.Arrays;

/**
 * Author: dc0453
 * Since: 2017/7/23 下午9:52
 */
public class KMP {

    /**
     * next[j] = value表示的意思，是当第j+1匹配失败之后，需要跳转的位置为value即next[value]
     * 如果j+1匹配成功了，那么就应该将next[j+1] = value + 1
     * next数组的本质，是前缀与后缀的最大匹配长度。
     *
     * ref：http://www.cnblogs.com/c-cloud/p/3224788.html
     * @param pattern
     * @return
     */
    public int[] next(String pattern) {
        int[] next = new int[pattern.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < next.length; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            next[i] = j;
        }

        return next;
    }

    /**
     * 未在source中找到search，返回-1
     * 否则返回第一次出现的位置
     * @param source
     * @param search
     * @return
     */
    public int search(String source, String search) {
        int[] next = next(search);
        for (int i = 0, j = 0; i < source.length(); ++i) {
            while (j > 0 && source.charAt(i) != search.charAt(j)) {
                j = next[j - 1];
            }
            if (source.charAt(i) == search.charAt(j)) {
                j++;
            }
            if (j == next.length) {
                return i - j + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        String text = "ababxbababcdabdsss";
        String pattern = "abcdabd";

        System.out.println(Arrays.toString(new KMP().next(pattern)));
        System.out.println(new KMP().search(text, pattern));
    }
}
