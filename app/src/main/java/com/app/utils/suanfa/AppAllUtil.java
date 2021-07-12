package com.app.utils.suanfa;

import android.util.Log;

import java.util.Arrays;

public class AppAllUtil {

    /**
     * 你可能惊讶于代码如此简洁
     * 本宝宝花费了一下午的时间想出来的
     * 会不会笨了点。。。。。。。。。
     * @param out 待放置元素的数组
     * @param outIndex 当前要放置元素的位置
     * @param data 待拿取元素的数组
     */
    public static void calc(String[] out, int outIndex, String[] data) {
        //递归退出的条件
        //当放置元素的索引大于放置数组长度后，退出
        if (outIndex >= out.length) {
            System.out.println(Arrays.toString(out));
            return;
        }
        //声明一个缓存数组用，
        //作用是：当从 data 中取完元素后，将剩余元素copy 到其中,因为已经取出了一个元素，所以缓存数组大小要-1
        String[] temp = new String[data.length-1];

        //此处循环的意思就是，data 数组有几个元素就有几种取法
        for (int i = 0; i < data.length; i++) {
            //取元素，放置到指定的位置
            out[outIndex] = data[i];
            //从取元素的位置向前看，看看是不是有剩余的元素，然后拷贝到缓存数组中
            System.arraycopy(data, 0, temp, 0, i);

            //从取元素的位置向后看，看看是不是有剩余的元素，然后拷贝到缓存数组中
            System.arraycopy(data, i + 1, temp, i, temp.length - i);

            //放置数组不变，将要元素的放置位置+1，将缓存数组作为新的 data
            //递归执行
            calc(out, outIndex + 1, temp);
            Log.d("--------", out[0] + out[1]);
        }
    }
}
