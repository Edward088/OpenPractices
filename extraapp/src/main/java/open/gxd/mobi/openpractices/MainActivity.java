package open.gxd.mobi.openpractices;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import open.gxd.mobi.openpractices.Base.BaseActivity;
import open.gxd.mobi.openpractices.Utils.MyLog;
import suanfa.SuanFaActivity;

public class MainActivity extends BaseActivity {

    public static final String TEST_INTET_SERVICE = "testIntetService";
    @BindView(R.id.btn_start_douban_activity)
    Button btnStartDoubanActivity;
    private Toolbar mToolbar;
    private Button btnStartTestServiceActivity;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    });

    private IntentService myItentService = new IntentService("myItentService") {
        @Override
        protected void onHandleIntent(@Nullable Intent intent) {
            if (intent == null) {
                return;
            }
            String action = intent.getStringExtra(TEST_INTET_SERVICE);
            int taskId = intent.getIntExtra("id", -1);
            if (action.equals(TEST_INTET_SERVICE)) {
                stopSelf();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        Glide.with(this).
        Log.d("mainThread", String.valueOf(Process.myPid()));
        Log.d("mainThread", String.valueOf(Looper.myLooper()));
        handler.sendEmptyMessage(0);
        new Thread(runnable).start();

        BuilderMode builderMode = new BuilderMode.MyBuilder().setBoolean(false).build();

//        ObserverTest.a();
//        startActivity(new Intent(this, SuanFaActivity.class));
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
//        handlerThread.start();
        btnStartTestServiceActivity = (Button) findViewById(R.id.start_servicetest_activity);
        btnStartTestServiceActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ServiceTestActivity.class);
                startActivity(intent);
            }
        });
        HandlerThread handlerThread = new HandlerThread("HandlerThread") {
            @Override
            public void run() {
                MyLog.d("MainActivity", "cunrrent Thread name =   " + Thread.currentThread().getName());
                super.run();

            }
        };
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {

                //这已经在子线程中了 handler的作用就是切换线程
                MyLog.d("MainActivity", "handlerThread" + "====msg=" + msg.what);
                MyLog.d("MainActivity", "cunrrent Thread name =   " + Thread.currentThread().getName());
                return false;
            }
        });
        handler.sendEmptyMessage(1);


    }

    @OnClick(R.id.btn_start_douban_activity)
    public void onClick() {
        this.startActivity(new Intent(this, SuanFaActivity.class));
    }

    static class MyHandler extends Handler {
        private WeakReference<MainActivity> m;

        public MyHandler(Activity activity) {
            m = new WeakReference<MainActivity>((MainActivity) activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myItentService.stopSelf();
    }

    private Looper looper;
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {


            Looper.prepare();
            looper = Looper.myLooper();

            Looper.loop();
        }
    });


}
