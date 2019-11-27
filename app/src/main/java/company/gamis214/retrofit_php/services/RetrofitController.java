package company.gamis214.retrofit_php.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;

import company.gamis214.retrofit_php.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import okio.Okio;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitController {

    static final String BASE_URL = "http://10.0.2.2";

    public Retrofit getInstanceRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClientBuild())
                .build();

        return retrofit;
    }

    public OkHttpClient getOkHttpClientBuild(){
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(new LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .request("Request")
                .response("Response")
                .addHeader("version", BuildConfig.VERSION_NAME)
                .build());
        return client.build();
    }

}
