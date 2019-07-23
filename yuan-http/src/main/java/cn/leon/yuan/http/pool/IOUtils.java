package cn.leon.yuan.http.pool;

import java.io.IOException;
import java.io.InputStream; /**
 * @Author: lianghc
 * @Description:
 * @Date: 2019/7/23 15:37
 * @Version: 1.0
 */
public class IOUtils {
    public static String toString(InputStream in, String s) throws IOException {

        byte[] bs = new byte[1024];
        StringBuilder sb = new StringBuilder();
        while (in.read(bs) != -1) {
            sb.append(new String(bs));
        }
        return sb.toString();
    }
}
