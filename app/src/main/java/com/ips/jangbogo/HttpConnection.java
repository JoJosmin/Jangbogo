package com.ips.jangbogo;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpConnection {
    private OkHttpClient okClient;
    private static HttpConnection instance = new HttpConnection();
    public static HttpConnection getInstance(){
        return instance;
    }

    public HttpConnection() {
        this.okClient = new OkHttpClient();
    }

    // 웹서버로 요청
    public void requestWebServer(Callback callback) {
        //String parameter1, String parameter2,
//        RequestBody body = new FormBody.Builder()
//                .add("parameter1", parameter1)
//                .add("parameter2", parameter2)
//                .build();
        Request request = new Request.Builder()
                .url("https://jangbogo-shop-default-rtdb.firebaseio.com/product.json")
                //.post(body)
                .get()
                .build();
        okClient.newCall(request).enqueue(callback);
    }
}
