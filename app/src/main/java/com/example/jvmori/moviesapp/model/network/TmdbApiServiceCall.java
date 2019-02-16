package com.example.jvmori.moviesapp.model.network;


import com.example.jvmori.moviesapp.util.Consts;
import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TmdbApiServiceCall
{
    private final static Interceptor interceptor = new Interceptor() {
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
