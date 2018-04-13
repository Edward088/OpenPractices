package open.gxd.mobi.openpractices;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import net.vsona.projecta.Book;
import net.vsona.projecta.BookController;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import open.gxd.mobi.openpractices.Base.BaseActivity;
import open.gxd.mobi.openpractices.Utils.MyLog;
import open.gxd.mobi.openpractices.service.TestService1;

/**
 * Created by edward.ge on 2018/3/8.
 */

public class ServiceTestActivity extends BaseActivity implements View.OnClickListener {
    public static final String TAG = "ServiceTestActivity";
    @BindView(R.id.but_start_service)
    Button butStartService;
    @BindView(R.id.but_bind_service)
    Button butBindService;
    @BindView(R.id.but_stop_service)
    Button butStopService;
    @BindView(R.id.but_unbind_service)
    Button butUnbindService;
    @BindView(R.id.btn_add_book)
    Button btnAddBook;
    @BindView(R.id.btn_get_book_list)
    Button btnGetBookList;

    private boolean isBinded;
    private BookController bookController;
    private List<Book> bookList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_test_activity_layout);
        ButterKnife.bind(this);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isBinded = true;
            MyLog.d(TAG, "onServiceConnected" + "ComponentName = " + name);
//            TestService1.MyBinder myBinder = (TestService1.MyBinder) service;
//            TestService1 testService1 = myBinder.getTestServiceInstance();
//            MyLog.d(TAG, testService1.getMsg());
            bookController = BookController.Stub.asInterface(service);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBinded = false;
            MyLog.d(TAG, "onServiceDisconnected" + "ComponentName = " + name);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (serviceConnection != null && isBinded) {
            unbindService(serviceConnection);
            serviceConnection = null;
        }
//        stopService(new Intent(this, TestService1.class));
    }

    @OnClick({R.id.but_bind_service, R.id.but_stop_service, R.id.but_unbind_service, R.id.btn_add_book, R.id.btn_get_book_list})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_start_service:
                Intent startServiceIntent = new Intent(this, TestService1.class);
                startServiceIntent.putExtra("start", "start");
                this.startService(startServiceIntent);
                break;
            case R.id.but_bind_service:
                Intent intent = new Intent();
                intent.setPackage("net.vsona.projecta");
                intent.setAction("net.vsona.projecta.aidl");
                bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.but_unbind_service:
                if(isBinded){
                    unbindService(serviceConnection);
                }
                break;
            case R.id.but_stop_service:
                finish();
                break;
            case R.id.btn_add_book:
                if (isBinded) {
                    Book book = new Book("这是一本新书 InOut", 6);

                    try {
                        bookController.addBookInOut(book);
                        MyLog.e(TAG, "向服务器以InOut方式添加了一本新书");
                        MyLog.e(TAG, "新书名：" + book.getName());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }

                break;
            case R.id.btn_get_book_list:
                if (isBinded) {
                    try {
                        bookList = bookController.getBookList();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    for (Book book2 : bookList) {
                        MyLog.d(TAG, book2.getName());
                    }
                }
                break;

        }
    }

    @OnClick(R.id.but_start_service)
    public void startService() {
        Intent startServiceIntent = new Intent(this, TestService1.class);
        startServiceIntent.putExtra("start", "start");
        this.startService(startServiceIntent);
    }

}
