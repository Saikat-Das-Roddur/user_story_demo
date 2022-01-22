package com.example.userstorydemo;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class HeaderInterceptor implements Interceptor {
    Context context;

    public HeaderInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder()
                //.addHeader("token", ShareInfoUtils.getInstance().getAuthenticationToken(context))
                .build();

        return chain.proceed(request);
    }
}