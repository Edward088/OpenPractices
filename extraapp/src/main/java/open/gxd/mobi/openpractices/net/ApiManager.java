package open.gxd.mobi.openpractices.net;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import open.gxd.mobi.openpractices.OpApplication;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by edward.ge on 2018/3/13.
 */

public class ApiManager {

    private RequestServices requestServices;

    private ApiManager() {

        OfflineCacheInterceptor offlineCacheInterceptor = new OfflineCacheInterceptor(60 * 60 * 24);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .cache(new Cache(new File(OpApplication.get().getExternalCacheDir(), "test_cache"), 10 * 1024 * 1024))
                .addInterceptor(offlineCacheInterceptor)
                .addNetworkInterceptor(new NetCacheInterceptor())
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetConst.DOUBAN_BASE_API)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        requestServices = retrofit.create(RequestServices.class);
    }

    private static class Holder {
        private static ApiManager INSTANCE = new ApiManager();
    }

    public static ApiManager getInstance() {
        return Holder.INSTANCE;
    }

    public RequestServices getRequestServices() {
        return requestServices;
    }

}

