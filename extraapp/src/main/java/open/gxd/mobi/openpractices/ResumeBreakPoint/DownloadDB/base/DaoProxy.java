package open.gxd.mobi.openpractices.ResumeBreakPoint.DownloadDB.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 */
public class DaoProxy {

    private static DaoProxy mInstance;
    private MyDatabaseHelper mDatabaseHelper;
    private final SQLiteDatabase db;


    private DaoProxy(Context context) {
        mDatabaseHelper = new MyDatabaseHelper(context);
        db = mDatabaseHelper.getWritableDatabase();
    }

    public static DaoProxy getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DaoProxy.class) {
                if(mInstance == null) {
                    mInstance = new DaoProxy(context.getApplicationContext());
                }
            }
        }
        return mInstance;
    }

    public void close() {
        if (db != null && db.isOpen()) {
            db.close();
        }
        if (mDatabaseHelper != null) {
            mDatabaseHelper.close();
        }
    }

}
