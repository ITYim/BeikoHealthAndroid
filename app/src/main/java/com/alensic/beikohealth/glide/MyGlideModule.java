package com.alensic.beikohealth.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;

/**
 * Glide全局Module
 * Created by zym on 2017/3/14.
 */
public class MyGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        int memoryCacheSize = (int) (Runtime.getRuntime().maxMemory() / 4);//设置图片内存缓存占用八分之一
        //设置内存缓存大小
        builder.setMemoryCache(new LruResourceCache(memoryCacheSize));
        builder.setBitmapPool(new LruBitmapPool(memoryCacheSize));
        //设置磁盘缓存大小
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, 1024 * 1024 * 50));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
