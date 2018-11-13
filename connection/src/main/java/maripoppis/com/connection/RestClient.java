/*
 * Copyright (c) Developed by John Alves at 2018/10/29.
 */

package maripoppis.com.connection;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import maripoppis.com.connection.ConstantsUrls.ConstantsConnect;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(Activity activity) {

        OkHttpClient.Builder client = new OkHttpClient.Builder();
//        final String token = activity.getResources().getString(R.string.public_key);

        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
//                        .addHeader("Client-ID", token)
                        .build();
                return chain.proceed(newRequest);
            }
        });
        client.connectTimeout(20, TimeUnit.SECONDS);
        client.writeTimeout(20, TimeUnit.SECONDS);
        client.readTimeout(30, TimeUnit.SECONDS);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        if (BuildConfig.DEBUG) {
            // development build
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            // production build
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }

        client.addInterceptor(logging);
//        httpClient.addInterceptor(httpLoggingInterceptor);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ConstantsConnect.getBaseUrl())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client.build())
//                    .client(client)
                    .build();
        }
        return retrofit;
    }

}
