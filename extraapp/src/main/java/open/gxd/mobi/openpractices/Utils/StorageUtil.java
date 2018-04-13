package open.gxd.mobi.openpractices.Utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import open.gxd.mobi.openpractices.OpApplication;


/**
 * Created by sdsds on 16.3.24.
 */
public class StorageUtil {
    private static Method sGetVolListMethod = null;
    private static Method sGetVolumePaths = null;

    static {
        StorageManager storageManager = (StorageManager) OpApplication.get().getSystemService(Context.STORAGE_SERVICE);
        Class<?> clz = storageManager.getClass();
        Class[] array_class = new Class[0];
        try {
            sGetVolListMethod = clz.getMethod("getVolumeList", array_class);
            sGetVolumePaths = clz.getMethod("getVolumePaths", array_class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();

        }
    }

    public static String[] getVolumePaths() {
        String[] paths = null;
        try {
            StorageManager storageManager = (StorageManager) OpApplication.get().getSystemService(Context.STORAGE_SERVICE);
            paths = (String[]) sGetVolumePaths.invoke(storageManager);
        } catch (IllegalArgumentException illegalArgumentException) {
            illegalArgumentException.printStackTrace();

        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        } catch (InvocationTargetException invocationTargetException) {
            invocationTargetException.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return paths;
    }

    public static List<String> getSearchPathList() {
        ArrayList<String> searchPathList = new ArrayList<>();
        if (Build.VERSION.SDK_INT > 10) {
            String[] volumePaths = getVolumePaths();
            if (volumePaths == null || volumePaths.length <= 0) {
                return searchPathList;
            }
            for (String path : volumePaths) {
                if (!path.equals("/data") && !path.equals("/system") && !path.equals("/dev")
                        && !path.equals("/cache") && !path.equals("/sys")) {
                    File file = new File(path);
                    File[] files = file.listFiles();
                    if (files != null && files.length > 0) {
                        if (Build.VERSION.SDK_INT < 19) {
                            searchPathList.add(path);
                        } else if (CheckStorageAccess(path)) {
                            searchPathList.add(path);
                        }
                    }
                }
            }
        }

        if (searchPathList.size() == 0) {
            String state = Environment.getExternalStorageState();
            if (state.equals(Environment.MEDIA_MOUNTED)) {
                File path = Environment.getExternalStorageDirectory();
                File[] files = path.listFiles();
                if (files != null && files.length > 0) {
                    searchPathList.add(path.getAbsolutePath());
                }
            }
        }

        return searchPathList;
    }

    public static List<String> getStoragePathList() {
        ArrayList<String> searchPathList = new ArrayList<>();
        if (Build.VERSION.SDK_INT > 10) {
            String[] volumePaths = getVolumePaths();
            if (volumePaths == null || volumePaths.length <= 0) {
                return searchPathList;
            }
            for (String path : volumePaths) {
                if (!path.equals("/data") && !path.equals("/system") && !path.equals("/dev")
                        && !path.equals("/cache") && !path.equals("/sys")) {
                    File file = new File(path);
                    File[] files = file.listFiles();
                    if (files != null && files.length > 0) {
                        searchPathList.add(path);
                    }
                }
            }
        }

        if (searchPathList.size() == 0) {
            String state = Environment.getExternalStorageState();
            if (state.equals(Environment.MEDIA_MOUNTED)) {
                File path = Environment.getExternalStorageDirectory();
                File[] files = path.listFiles();
                if (files != null && files.length > 0) {
                    searchPathList.add(path.getAbsolutePath());
                }
            }
        }

        return searchPathList;
    }

    public static boolean CheckStorageAccess(String path) {
        boolean result;
        try {
            File file = new File(path);
            File file1 = new File(file, "swift.txt");
            if (!file1.exists()) {
                file1.createNewFile();
            }

            file1.delete();
            result = true;
        } catch (Exception exception) {
            result = false;
        }

        return result;
    }
}
