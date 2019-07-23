package cn.leon.yuan.http.pool;


/**
 * @Author: lianghc
 * @Description:
 * @Date: 2019/7/23 15:48
 * @Version: 1.0
 */

public class Config {

    private static final int httpConnectTimeout = 10;
    private static final int httpSocketTimeout = 10;
    private static final int httpMaxPoolSize = 3;
    private static final long httpIdelTimeout = 30000;
    private static final long httpMonitorInterval = 30000;

    public static int getHttpConnectTimeout() {
        return httpConnectTimeout;
    }

    public static int getHttpSocketTimeout() {
        return httpSocketTimeout;
    }

    public static int getHttpMaxPoolSize() {
        return httpMaxPoolSize;
    }

    public static long getHttpIdelTimeout() {
        return httpIdelTimeout;
    }

    public static long getHttpMonitorInterval() {
        return httpMonitorInterval;
    }
}
