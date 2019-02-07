package com.example.jvmori.moviesapp.model.network;

import android.content.Context;

import com.example.jvmori.moviesapp.util.Consts;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TmdbApiServiceCall
{
    private Context context;
    private int cacheSize = 10 * 1024 * 1024; // 10 MB
    //private File httpCacheDirectory = new File();
    //Cache cache = new Cache(httpCacheDirectory,  cacheSize);

    final static Interceptor networkInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            CacheControl cacheControl = new CacheControl.Builder()
                    .maxAge(60, TimeUnit.MINUTES)
                    .build();

            Request request = chain.request().newBuilder()
                    .header("Cache-Control", cacheControl.toString())
                    .build();
            return chain.proceed(request);
        }
    };

    final static Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            HttpUrl url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("api_key", Consts.api_key)
                    .build();
            Request request = chain.request()
                    .newBuilder().url(url).build();
            return chain.proceed(request);
        }
    };

    public static TmdbApi init(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Consts.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TmdbApi.class);
    }
}
