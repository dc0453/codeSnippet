import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: dc0453
 * Since: 2017/8/10 上午10:26
 */
public class DomainRegex {

    /**
     * 获取url域名部分，包括host:port这种形式的
     */
    public static final String pattern = "(?<=[://])[^/]+(?=/?)";

    public static final Pattern p = Pattern.compile(pattern);

    public static void main(String[] args) {
        String str = "http://www.baidu.com";
        Matcher m = p.matcher(str);
        if (m.find()) {
            System.out.println(m.group(0));
        }
    }
}
