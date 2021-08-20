package com.minkiapps.newspaper.test;

import com.minkiapps.newspaper.test.network.ApiInteractor;
import com.minkiapps.newspaper.test.network.ApiInteractorImpl;
import com.minkiapps.newspaper.test.network.ApiService;
import com.minkiapps.newspaper.test.network.DummyApiInteractor;
import ohos.aafwk.ability.AbilityPackage;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import java.util.concurrent.TimeUnit;

public class MyApplication extends AbilityPackage {

    //TODO add your base url here
    private static final String BASE_API_URL = "Demo";

    private static ApiInteractor apiInteractor;

    @Override
    public void onInitialize() {
        super.onInitialize();
        //TODO replace with real ApiInteractor
        apiInteractor = new DummyApiInteractor();
    }

    public static ApiInteractor getApiInteractor() {
        return apiInteractor;
    }

    private ApiInteractorImpl initApiInteractor() {
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60L, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_API_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(client)
                .build();
        return new ApiInteractorImpl(retrofit.create(ApiService.class));
    }


}
