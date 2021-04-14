package com.app.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;


/**
 * 清除数据 清除的是 /Android/data/包名/ 和 /data/data/包名/ 下的所有内容;
 *
 * 清除缓存 会清除 /sdcard/Android/data/包名/cache/ 和 /data/data/包名/cache/ 内的内容.
 * /sdcard/xxx 下的内容会依然存在.
 *
 *
 * 清除数据  清除的是保存在app中所有数据，就是上面提到的位于packagename下面的所有文件，
 * 包含内部存储(/data/data/packagename/)和外部存储(/storage/emulated/0/Android/data/packagename/)。
 * 当然除了SD卡上面的数据，SD卡上面的数据当app卸载之后还会存在的。
 *
 *
 *
 *
 */
public class AppPathUtil {


    //****系统缓存目录**********************************************************************************************


    /**
     * /data/data/包名/files
     *
     * /data/user/0/包名/files
     * 用户无法查看
     * @param context
     * @return
     */
    public static String getFilePath(Context context) {
        return context.getFilesDir().getPath();
    }

    /**
     * /data/data/包名/cache
     *
     * /data/user/0/包名/cache
     * 用户无法查看
     * 当安卓设备的存储空间少，或者不够用的时候，系统会自动删除这个目录下的文件。
     * @param context
     * @return
     */
    public static String getCachePath(Context context) {
        return context.getCacheDir().getPath();
    }


    //****Sdcard文件目录**********************************************************************************************


    /**
     * /sdcard/Android/data/包名/files
     *
     * 外部存储 私有目录
     * @param context
     *
     * Environment.DIRECTORY_MUSIC  音乐目录
     * Environment.DIRECTORY_PICTURES  图片目录
     * Environment.DIRECTORY_MOVIES  电影目录
     * Environment.DIRECTORY_DOWNLOADS  下载目录
     * Environment.DIRECTORY_DCIM   相机拍照或录像文件的存储目录
     * Environment.DIRECTORY_DOCUMENTS   文件文档目录
     *
     * null   返回主目录
     *
     * @return
     */
    public static String getExternalFilePath(Context context, String type) {
        return context.getExternalFilesDir(type).getPath();
    }


    /**
     * /sdcard/Android/data/包名/cache
     *
     * 外部存储 私有目录
     * @param context
     * @return
     */
    public static String getExternalCachePath(Context context) {
        return context.getExternalCacheDir().getPath();
    }


    /**
     * 获取SD卡路径
     *
     * 应用外部存储空间（数据文件非私有，可以被手机的系统程序访问）
     *
     * /mnt/sdcard
     * @return
     */
    public static String getExternalStorageDirectory() {

        if (isMountSdcard()) {
            //sd卡已经安装，可以进行相关文件操作

            File file = Environment.getExternalStorageDirectory();

            return file.getPath();
        } else {
            return null;
        }
    }


    //****公共文件夹**********************************************************************************************

    /**
     *
     * 获取SD卡上的公共目录
     * @param type
     * Environment.DIRECTORY_MUSIC  音乐目录
     * Environment.DIRECTORY_PICTURES  图片目录
     * Environment.DIRECTORY_MOVIES  电影目录
     * Environment.DIRECTORY_DOWNLOADS  下载目录
     * Environment.DIRECTORY_DCIM   相机拍照或录像文件的存储目录
     * Environment.DIRECTORY_DOCUMENTS   文件文档目录
     *
     * @return
     */
    public static String getExternalStoragePublicDirectory(String type) {
        String externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(type).getPath();
        return externalStoragePublicDirectory;
    }


    //****相关工具**********************************************************************************************

    /**
     * 格式化文件路径
     * 示例：  传入 "sloop" "/sloop" "sloop/" "/sloop/"
     * 返回 "/sloop"
     */
    public static String formatPath(String path) {
        if (!path.startsWith("/"))
            path = "/" + path;
        while (path.endsWith("/"))
            path = new String(path.toCharArray(), 0, path.length() - 1);
        return path;
    }


    /**
     * @return 存储卡是否挂载(存在)
     */
    public static boolean isMountSdcard() {
        String status = Environment.getExternalStorageState();
        return status.equals(Environment.MEDIA_MOUNTED);
    }

}
