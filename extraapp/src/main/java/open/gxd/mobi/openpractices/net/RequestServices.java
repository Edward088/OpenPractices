package open.gxd.mobi.openpractices.net;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import open.gxd.mobi.openpractices.moudle_douban.data.MovieInfo;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by edward.ge on 2017/4/17.
 */

public interface RequestServices {

    @POST("/p/config?")
    Observable<String> getContent(@QueryMap Map<String, String> map);

    @GET("v2/movie/top250")
    Observable<ApiResult<List<MovieInfo>>> getMovieList(@Query("start") int start, @Query("count") int count);
}
