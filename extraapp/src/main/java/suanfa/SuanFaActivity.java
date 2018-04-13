package suanfa;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.Arrays;

import open.gxd.mobi.openpractices.Base.BaseActivity;
import open.gxd.mobi.openpractices.Utils.MyLog;

/**
 * Created by edward.ge on 2018/3/22.
 */

public class SuanFaActivity extends BaseActivity {

    private int[] a = new int[]{3, 2, 4, 6, 81, 1, 0, 9, 7};

    private void maoPao() {
        int temp;
        int length = a.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (a[j + 1] > a[j]) {
                    temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                }
            }
        }
        MyLog.d("result:" + Arrays.toString(a));

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        maoPao();
//        xuanzePaixu();
        insertionSort();
    }

    private void xuanzePaixu() {
        int length = a.length;


        for (int i = 0; i < length - 1; i++) {
            int min = a[i];
            int n = i; //最小数的索引
            for (int j = i + 1; j < length; j++) {
                if (a[j] < min) {  //找出最小的数
                    min = a[j];
                    n = j;
                }
            }
            a[n] = a[i];//交换位置
            a[i] = min;
        }
        MyLog.d("result:" + Arrays.toString(a));
    }

    private void insertionSort() {
        int length = a.length;

        for (int i = 1; i < length; i++) {
            //待插入元素
            int temp = a[i];
            int j;
                   /*for (j = i-1; j>=0 && a[j]>temp; j--) {
               //将大于temp的往后移动一位
                a[j+1] = a[j];
             }*/
            for (j = i - 1; j >= 0; j--) {
                //将大于temp的往后移动一位
                if (a[j] > temp) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[j + 1] = temp;
        }
        MyLog.d("result:" + Arrays.toString(a));
    }

    /**
     * 二分插入排序
     */
    private void InsertionSortDichotomy(){

    }

    /**
     * 插入排序中的希尔排序
     */
    private void ShellSort(){

    }
}
