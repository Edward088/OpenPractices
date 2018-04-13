package open.gxd.mobi.openpractices.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import open.gxd.mobi.openpractices.Utils.MyLog;

/**
 * Created by edward.ge on 2018/3/8.
 */

public class TestService1 extends Service {

    public static final String TAG = "TestService1";

    private MyBinder myBinder;

    @Override
    public void onCreate() {
        super.onCreate();
        MyLog.d(TAG, "onCreate");
        myBinder = new MyBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MyLog.d(TAG, "onStartCommand");
        String msg = intent.getStringExtra("start");
        MyLog.d(TAG, "onStartCommand msg = " + msg);

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        String msg = intent.getStringExtra("onBind");
        MyLog.d(TAG, "onBind msg = " + msg);
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        String msg = intent.getStringExtra("onBind");
        MyLog.d(TAG, "onUnbind msg = " + msg);
        return super.onUnbind(intent);
    }

    public class MyBinder extends Binder {

        public TestService1 getTestServiceInstance() {
            return TestService1.this;
        }
    }

    public String getMsg() {
        return "this is TestService1";
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyLog.d(TAG, "TestService is destroyed");
    }
}
