package open.gxd.mobi.openpractices;

import android.content.Intent;
import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by edward.ge on 2017/3/8.
 */

public class ObserverTest {

    public static void a() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                if (true) {
                    e.onNext("true");
                } else {
                    e.onError(new RuntimeException());
                }
                e.onComplete();
            }
        }).subscribe(o);




    }
    static Observer<String> o = new Observer<String>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(String s) {
            Log.d("gxd", "onNext");
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };

    Subscriber<Intent> observer = new Subscriber<Intent>() {
        @Override
        public void onSubscribe(Subscription s) {

        }

        @Override
        public void onNext(Intent intent) {

        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onComplete() {

        }
    };

}
