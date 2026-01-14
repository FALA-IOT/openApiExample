package org.example;

import cn.hutool.core.map.MapBuilder;
import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;

/**
 * Http request
 * @Description
 * @Author wsk
 * @Date 2024-04-10
 */
@Slf4j
public enum FalaHttp {
    INSTANCE;

    /* Initialize httpclient */
    private HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    /**
     * Get device information (non-K series)
     * @throws Exception
     */
    public void getDeviceInfo(String deviceId) throws Exception {
        get(Constant.baseUrl + "/en/device/" + deviceId + "/info", null);
    }

    /**
     * Get device real-time data
     * @throws Exception
     */
    public void getDeviceData(String deviceId) throws Exception {
        get(Constant.baseUrl + "/en/device/" + deviceId + "/data", null);
    }

    /**
     * Http get request
     * @param url
     * @param headMap
     * @param <T>
     * @throws Exception
     */
    private <T> void get(String url, Map<Object, Object> headMap) throws Exception {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET();
        send(builder, headMap);
    }

    /**
     * Http post request
     * @param url
     * @param body
     * @param headMap
     * @param <T>
     * @throws Exception
     */
    private <T> void post(String url, String body, Map<Object, Object> headMap) throws Exception {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body == null ? "" : body));
        send(builder, headMap);
    }

    /**
     * Send request using httpclient
     * @param builder
     * @param headMap
     * @param <T>
     * @throws Exception
     */
    private <T> void send(HttpRequest.Builder builder, Map<Object, Object> headMap) throws Exception {
        Long nowTime = System.currentTimeMillis();
        builder.header("time", nowTime + "")
                .header("token", MD5.create().digestHex(Constant.USER_KEY + nowTime))
                .header("userId", Constant.USER_ID);
        if (headMap != null){
            for (Object s : headMap.keySet()) {
                builder.header(s.toString(), headMap.get(s).toString());
            }
        }

        HttpRequest request = builder.build();

        HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) { // Success
            log.info("\nAddress：{} \nParameters：{} \nSuccess：{}\n", request.toString(), headMap == null ? "" : headMap.values(), response.body().toString());
        } else {   // Failure
            log.info("\nAddress：{} \nParameters：{} \nFailure：{}\n", request.toString(), headMap == null ? "" : headMap.values(), response.statusCode());
        }

    }


}
