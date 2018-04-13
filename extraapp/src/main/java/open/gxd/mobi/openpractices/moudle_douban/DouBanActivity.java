package open.gxd.mobi.openpractices.moudle_douban;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import open.gxd.mobi.openpractices.Base.BaseActivity;
import open.gxd.mobi.openpractices.OpApplication;
import open.gxd.mobi.openpractices.R;
import open.gxd.mobi.openpractices.Utils.MyLog;
import open.gxd.mobi.openpractices.moudle_douban.data.MovieInfo;
import open.gxd.mobi.openpractices.net.ApiManager;
import open.gxd.mobi.openpractices.net.ApiResult;

/**
 * Created by edward.ge on 2018/3/12.
 */

public class DouBanActivity extends BaseActivity implements Handler.Callback {


    public static final int stopRefresh = 0x101;
    public static final String TAG = "DouBanActivity";
    @BindView(R.id.rv_movie_list)
    RecyclerView rvMovieList;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    @BindView(R.id.id_toolbar)
    Toolbar idToolbar;

    private Handler handler = new Handler(this);
    private MovieAdapter movieAdapter;
    private int start;
    private int count = 10;

    private MenuItem menuItem, menuItem1, menuItem2, menuItem3, menuItem4, menuItem5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_activity_douban);
        ButterKnife.bind(this);

        setSupportActionBar(idToolbar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMovieList.setLayoutManager(layoutManager);
        movieAdapter = new MovieAdapter(null, MovieAdapter.TYPE_NORMAL);
        rvMovieList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    movieAdapter.setScrolling(false);
                    movieAdapter.notifyDataSetChanged();
                } else {
                    movieAdapter.setScrolling(true);
                }
            }
        });

//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, VERTICAL);
//        rvMovieList.setLayoutManager(layoutManager);
//        movieAdapter = new MovieAdapter(null, MovieAdapter.TYPE_STAGGERED);

        rvMovieList.setAdapter(movieAdapter);
        movieAdapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(DouBanActivity.this, "点击了第" + position + "个条目", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(int position) {

            }
        });

        swipeContainer.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorPrimary), Color.GRAY);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MyLog.d(TAG, "onRefresh");
                loadData(1, count, true);
            }
        });

        loadData(start, count, false);
    }

    private void loadData(int start, int count, final boolean reFresh) {
        ApiManager.getInstance().getRequestServices().getMovieList(start, count)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map(new Function<ApiResult<List<MovieInfo>>, List<MovieInfo>>() {
                    @Override
                    public List<MovieInfo> apply(ApiResult<List<MovieInfo>> listApiResult) throws Exception {
                        return listApiResult.getSubjects();
                    }
                }).subscribe(new Observer<List<MovieInfo>>() {
            @Override
            public void onSubscribe(Disposable d) {
                MyLog.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(List<MovieInfo> movieInfos) {
                MyLog.d(TAG, "onNext");


                movieAdapter.setInfos(movieInfos);
                if (reFresh) {
                    Toast.makeText(OpApplication.get(), "刷新成功", Toast.LENGTH_SHORT).show();
                    stopRefresh();
                }
            }

            @Override
            public void onError(Throwable e) {
                MyLog.d(TAG, "onError" + e.getMessage());
                stopRefresh();
            }

            @Override
            public void onComplete() {
                MyLog.d(TAG, "onComplete");
            }
        });
    }

    @Override
    public boolean handleMessage(Message msg) {
        MyLog.d(TAG, "stopRefresh");
        if (msg.what == stopRefresh) {

            if (swipeContainer.isRefreshing()) {
                swipeContainer.setRefreshing(false);
            }
        }
        return false;
    }

    private void stopRefresh() {
        Message message = handler.obtainMessage();
        message.what = stopRefresh;
        handler.sendMessage(message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.douban_activity_menu, menu);
        menuItem = menu.findItem(R.id.action_settings);
        menuItem1 = menu.findItem(R.id.action_settings1);
        menuItem2 = menu.findItem(R.id.action_settings2);
        menuItem3 = menu.findItem(R.id.action_settings3);
        menuItem4 = menu.findItem(R.id.action_settings4);
        menuItem5 = menu.findItem(R.id.action_settings5);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "item", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings1:
                Toast.makeText(this, "item1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings2:
                Toast.makeText(this, "item2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings3:
                Toast.makeText(this, "item3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings4:
                Toast.makeText(this, "item4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings5:
                Toast.makeText(this, "item5", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
//        return super.onOptionsItemSelected(item);
    }
}
