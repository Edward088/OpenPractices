package open.gxd.mobi.openpractices;

import android.app.Application;

/**
 * Created by edward.ge on 2017/3/9.
 */

public class OpApplication extends Application {
    public static boolean APP_DEBUG = true;
    private static OpApplication INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

    public static OpApplication get() {
        return INSTANCE;
    }
}
