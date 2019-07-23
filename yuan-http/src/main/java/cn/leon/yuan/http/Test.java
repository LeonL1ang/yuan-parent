package cn.leon.yuan.http;

import cn.leon.yuan.http.pool.HttpConnectionPoolUtil;
import com.google.gson.JsonObject;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * @Author: lianghc
 * @Description:
 * @Date: 2019/7/23 13:41
 * @Version: 1.0
 */
public class Test {

    @org.junit.Test
    public void TestCon(){
        CloseableHttpClient httpClient = HttpConnectionPoolUtil.getHttpClient("http://www.baidu.com");
        JsonObject post = HttpConnectionPoolUtil.post("http://www.baidu.com", null);
        System.out.println("post = " + post);

    }
}
