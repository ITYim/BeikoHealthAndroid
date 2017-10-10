package com.alensic.beikohealth.utils;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Log;

import com.yim.base.utils.Logger;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件路径工具类
 * 负责SD卡文件读写操作
 * Created by zym on 2016/12/13.
 */
@SuppressWarnings({"ResultOfMethodCallIgnored", "unused", "SpellCheckingInspection", "WeakerAccess"})
public final class FilePathUtils {
    private static final String TAG = "FilePathUtils";
    private static final String ROOT_PATH = "/Beiko";
    private static boolean init = false;
    private static File rootDirectory;

    /**
     * 初始化
     */
    public static void init() {
        if (!isSDCardMounted())
            return;
        init = true;
        rootDirectory = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + ROOT_PATH);
        if (!rootDirectory.exists())
            rootDirectory.mkdirs();
    }

    /**
     * 获取应用在sd卡的根路径
     */
    public static String getAppRootPath() {
        if (rootDirectory != null)
            return rootDirectory.getAbsolutePath();
        return "";
    }

    /**
     * 创建文件
     */
    public static File createFile(String fileName) {
        if (!isSDCardMounted())
            return null;
        if (!init) {
            Log.e(TAG, "The method init() had not call at IApplication");
            return null;
        }
        File file = new File(rootDirectory, fileName);
        if (!file.exists())
            file.mkdirs();
        return file;
    }

    /**
     * 判断SD卡是否挂载
     */
    public static boolean isSDCardMounted() {
        boolean existsSDCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (!existsSDCard) {
            Logger.e(TAG, "The mobile not found sd card.");
        }
        return existsSDCard;
    }

    /**
     * IO流关闭
     *
     * @param closeable IO流操作类的顶级接口
     */
    public static void closeIO(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @return 获取sd卡剩余内存空间大小
     */
    @SuppressWarnings("deprecation")
    public static long getSDFreeSpace() {
        String rootPath = Environment.getExternalStorageDirectory().getPath();
        StatFs statFs = new StatFs(rootPath);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            return statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong();
        } else {
            return statFs.getBlockSize() * statFs.getAvailableBlocks();
        }
    }

    /**
     * 将Bitmap保存到指定路径的文件中
     */
    public static boolean saveBitmapToDisk(Bitmap bitmap, String savePath) {
        if (bitmap == null || TextUtils.isEmpty(savePath))
            return false;
        File file = new File(savePath);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            boolean compress = bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fos);
            fos.flush();
            fos.close();
            return compress;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
