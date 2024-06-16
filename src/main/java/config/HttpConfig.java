package config;

import okhttp3.OkHttpClient;

public class HttpConfig {
    private static OkHttpClient httpClient;

    private HttpConfig(){}

    public static OkHttpClient getInstance(){
        if(httpClient == null){
            httpClient = new OkHttpClient();
        }
        return httpClient;
    }
}
