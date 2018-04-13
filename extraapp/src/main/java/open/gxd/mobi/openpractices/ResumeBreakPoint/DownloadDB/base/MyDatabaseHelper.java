package open.gxd.mobi.openpractices.ResumeBreakPoint.DownloadDB.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import open.gxd.mobi.openpractices.Utils.MyLog;


/**
 * Created by wangjun on 15/10/16.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    private final String TAG = this.getClass().getSimpleName();

    private static final int VERSION = 5;

    public MyDatabaseHelper(Context context) {
        super(context, "spc.db", null, VERSION);
    }

    public MyDatabaseHelper(Context context, String databaseName) {
        super(context, databaseName, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        MyLog.d(TAG+ " onCreate ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        MyLog.d(TAG+ " onUpgrade oldVersion:"+oldVersion+" newVersion:"+newVersion);

        onCreate(db);
    }


}
