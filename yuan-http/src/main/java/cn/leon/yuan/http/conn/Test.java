package cn.leon.yuan.http.conn;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: lianghc
 * @Description:
 * @Date: 2019/7/23 13:46
 * @Version: 1.0
 */
public class Test {

    @org.junit.Test
    public void Test() throws IOException {

        //设置连接KeepAlive策略
        ConnectionKeepAliveStrategy keepAliveStrategy=new DefaultConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                // TODO Auto-generated method stub
                long keeAlive=super.getKeepAliveDuration(response, context);
                if(keeAlive==-1) {
                    // 如果服务器没有显示的设置 keep-alive的时间，就设置为保持连接5秒
                    keeAlive=5000;
                }
                return keeAlive;
            }
        };
        //配置Request的interceptors拦截器，在请求中添加或者拦截特殊的头部信息或者上下文信息
        HttpRequestInterceptor httpRequestInterceptor=new HttpRequestInterceptor() {
            @Override
            public void process(HttpRequest request, HttpContext context)
                    throws HttpException, IOException {
                AtomicInteger count = (AtomicInteger) context.getAttribute("count");
                request.addHeader("Count", Integer.toString(count.getAndIncrement()));

            }
        };
        CloseableHttpClient httpclient=HttpClients.custom().setKeepAliveStrategy(keepAliveStrategy)
                .addInterceptorLast(httpRequestInterceptor)
                .build();
        AtomicInteger count=new AtomicInteger(1);
        HttpClientContext localContext=HttpClientContext.create();
        localContext.setAttribute("count", count);
        RequestConfig requestConfig=RequestConfig.custom()
                .setSocketTimeout(1000)
                .setConnectTimeout(1000).build();

        HttpPost httpPost=new HttpPost("http://www.baidu.com");
        httpPost.setConfig(requestConfig);
        for(int i=0;i<10;i++) {
            CloseableHttpResponse response=httpclient.execute(httpPost,localContext);
            try {
                HttpEntity entity=response.getEntity();
            }finally {
                response.close();
            }
        }
    }
}
