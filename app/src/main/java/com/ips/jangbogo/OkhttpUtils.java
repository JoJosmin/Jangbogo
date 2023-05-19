package com.ips.jangbogo;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkhttpUtils {
    /**
     * post 호출
     * @param url
     * @param body
     * @param mediaType
     * @return
     */
    public static String post(String url, Map<String, String> body, MediaType mediaType) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(new Gson().toJson(body), mediaType);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            //log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * post 호출
     * @param url
     * @param body
     * @param mediaType
     * @return
     */
    public static String post(String url, Map<String, String> paramHeader, Map<String, String> body, MediaType mediaType) {
//        if (ObjectUtils.isEmpty(paramHeader)) {
//            throw new IllegalArgumentException("paramHeader is null");
//        }
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(new Gson().toJson(body), mediaType);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .headers(Headers.of(paramHeader))
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            //log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * GET METHOD
     * @param url
     * @return
     */
    public static String get(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            //log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}