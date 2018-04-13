package open.gxd.mobi.openpractices.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import open.gxd.mobi.openpractices.OpApplication;
import open.gxd.mobi.openpractices.Utils.NetUtils;

/**
 * Created by edward.ge on 2018/3/14.
 */

public class OfflineCacheInterceptor implements Interceptor {
    private int offlineCacheTime;//离线的时候的缓存的过期时间

    public OfflineCacheInterceptor(int offlineCacheTime) {
        this.offlineCacheTime = offlineCacheTime;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetUtils.checkNet(OpApplication.get())) {

            request = request.newBuilder()
//                        .cacheControl(new CacheControl
//                                .Builder()
//                                .maxStale(60,TimeUnit.SECONDS)
//                                .onlyIfCached()
//                                .build()
//                        ) 两种方式结果是一样的，写法不同
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + offlineCacheTime)
                    .build();
        }
        return chain.proceed(request);
    }
}
