package com.alensic.beikohealth.glide;

import android.content.Context;
import android.widget.ImageView;

import com.alensic.beikohealth.R;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.yim.base.common.ICallBack;

/**
 * 图片显示工具类
 * @author zym
 * @since 2017-08-09 14:15
 */
public final class BitmapUtil {
    private static final int DEFAULT_PLACE_HOLDER = R.mipmap.ic_launcher;
    private static final int DEFAULT_ERROR = R.mipmap.ic_launcher;

    /**
     * 显示图片
     */
    public static void showImage(Context context, String url, ImageView target) {
        show(context, url, 0, 0, null, null, -1, null, null, null, target);
    }

    public static void showImage(Context context, String url, int placeHolder, ImageView target) {
        show(context, url, placeHolder, 0, null, null, -1, null, null, null, target);
    }

    public static void showImage(Context context, String url, ImageView target, int error) {
        show(context, url, 0, error, null, null, -1, null, null, null, target);
    }

    public static void showImage(Context context, String url, ImageView target, int placeHolder, int error) {
        show(context, url, placeHolder, error, null, null, -1, null, null, null, target);
    }

    public static void showImage(Context context, int res, ImageView target) {
        show(context, res, 0, 0, null, null, -1, null, null, null, target);
    }

    public static void showImageWithPlaceHolderAndError(Context context, String url, ImageView target) {
        show(context, url, DEFAULT_PLACE_HOLDER, DEFAULT_ERROR, null, null, -1, null, null, null, target);
    }

    public static void showImageWithPlaceHolderAndError(Context context, int res, ImageView target) {
        show(context, res, DEFAULT_PLACE_HOLDER, DEFAULT_ERROR, null, null, -1, null, null, null, target);
    }

    public static void showImageWithPlaceHolder(Context context, String url, ImageView target) {
        show(context, url, DEFAULT_PLACE_HOLDER, 0, null, null, -1, null, null, null, target);
    }

    public static void showImageWithPlaceHolder(Context context, int res, ImageView target) {
        show(context, res, DEFAULT_PLACE_HOLDER, 0, null, null, -1, null, null, null, target);
    }

    public static void showImageWithError(Context context, String url, ImageView target) {
        show(context, url, 0, DEFAULT_ERROR, null, null, -1, null, null, null, target);
    }

    public static void showImageWithError(Context context, int res, ImageView target) {
        show(context, res, 0, DEFAULT_ERROR, null, null, -1, null, null, null, target);
    }

    /**
     * 显示圆角图片
     */
    public static void showRoundImage(Context context, String url, ImageView target, int radius) {
        show(context, url, 0, 0, null, null, -1, null, null, new GlideConfig.GlideRoundConfig(GlideConfig.Shape.ROUND, radius), target);
    }

    public static void showRoundImage(Context context, int res, ImageView target, int radius) {
        show(context, res, 0, 0, null, null, -1, null, null, new GlideConfig.GlideRoundConfig(GlideConfig.Shape.ROUND, radius), target);
    }

    public static void showRoundImageWithPlaceHolderAndError(Context context, String url, ImageView target, int radius) {
        show(context, url, DEFAULT_PLACE_HOLDER, DEFAULT_ERROR, null, null, -1, null, null, new GlideConfig.GlideRoundConfig(GlideConfig.Shape.ROUND, radius), target);
    }

    public static void showRoundImageWithPlaceHolderAndError(Context context, int res, ImageView target, int radius) {
        show(context, res, DEFAULT_PLACE_HOLDER, DEFAULT_ERROR, null, null, -1, null, null, new GlideConfig.GlideRoundConfig(GlideConfig.Shape.ROUND, radius), target);
    }

    public static void showRoundImageWithPlaceHolder(Context context, String url, ImageView target, int radius) {
        show(context, url, DEFAULT_PLACE_HOLDER, 0, null, null, -1, null, null, new GlideConfig.GlideRoundConfig(GlideConfig.Shape.ROUND, radius), target);
    }

    public static void showRoundImageWithPlaceHolder(Context context, int res, ImageView target, int radius) {
        show(context, res, DEFAULT_PLACE_HOLDER, 0, null, null, -1, null, null, new GlideConfig.GlideRoundConfig(GlideConfig.Shape.ROUND, radius), target);
    }

    public static void showRoundImageWithError(Context context, String url, ImageView target, int radius) {
        show(context, url, 0, DEFAULT_ERROR, null, null, -1, null, null, new GlideConfig.GlideRoundConfig(GlideConfig.Shape.ROUND, radius), target);
    }

    public static void showRoundImageWithError(Context context, int res, ImageView target, int radius) {
        show(context, res, 0, DEFAULT_ERROR, null, null, -1, null, null, new GlideConfig.GlideRoundConfig(GlideConfig.Shape.ROUND, radius), target);
    }

    /**
     * 显示圆形图片
     */
    public static void showCircleImage(Context context, String url, ImageView target) {
        show(context, url, 0, 0, null, null, -1, null, null, new GlideConfig.GlideRoundConfig(GlideConfig.Shape.CIRCLE), target);
    }

    public static void showCircleImage(Context context, int res, ImageView target) {
        show(context, res, 0, 0, null, null, -1, null, null, new GlideConfig.GlideRoundConfig(GlideConfig.Shape.CIRCLE), target);
    }

    public static void showCircleImageWithPlaceHolderAndError(Context context, String url, ImageView target) {
        show(context, url, DEFAULT_PLACE_HOLDER, DEFAULT_ERROR, null, null, -1, null, null, new GlideConfig.GlideRoundConfig(GlideConfig.Shape.CIRCLE), target);
    }

    public static void showCircleImageWithPlaceHolderAndError(Context context, int res, ImageView target) {
        show(context, res, DEFAULT_PLACE_HOLDER, DEFAULT_ERROR, null, null, -1, null, null, new GlideConfig.GlideRoundConfig(GlideConfig.Shape.CIRCLE), target);
    }

    public static void showCircleImageWithPlaceHolder(Context context, String url, ImageView target) {
        show(context, url, DEFAULT_PLACE_HOLDER, 0, null, null, -1, null, null, new GlideConfig.GlideRoundConfig(GlideConfig.Shape.CIRCLE), target);
    }

    public static void showCircleImageWithPlaceHolder(Context context, int res, ImageView target) {
        show(context, res, DEFAULT_PLACE_HOLDER, 0, null, null, -1, null, null, new GlideConfig.GlideRoundConfig(GlideConfig.Shape.CIRCLE), target);
    }

    public static void showCircleImageWithError(Context context, String url, ImageView target) {
        show(context, url, 0, DEFAULT_ERROR, null, null, -1, null, null, new GlideConfig.GlideRoundConfig(GlideConfig.Shape.CIRCLE), target);
    }

    public static void showCircleImageWithError(Context context, int res, ImageView target) {
        show(context, res, 0, DEFAULT_ERROR, null, null, -1, null, null, new GlideConfig.GlideRoundConfig(GlideConfig.Shape.CIRCLE), target);
    }

    /**
     * 播放一次gif
     */
    public static void showGif(Context context, String url, final ImageView target, int repeatCount) {
        if (repeatCount < 0 && repeatCount != -1)
            repeatCount = -1;
        show(context, url, 0, 0, null, null, -1, null, new GlideConfig.GifConfig(true, repeatCount), null, target);
    }

    public static void showGif(Context context, int res, final ImageView target, int repeatCount) {
        if (repeatCount < 0 && repeatCount != -1)
            repeatCount = -1;
        show(context, res, 0, 0, null, null, -1, null, new GlideConfig.GifConfig(true, repeatCount), null, target);
    }

    /**
     * 播放gif并获取播放完成回调
     */
    public static void showGifWithCallback(Context context, String url, final ImageView target, int repeatCount, final ICallBack<ImageView> callBack) {
        if (repeatCount < 0 && repeatCount != -1)
            repeatCount = -1;
        show(context, url, 0, 0, null, null, -1, null, new GlideConfig.GifConfig(true, repeatCount, new GlideConfig.Callback() {
            @Override
            public void onGifPlayFinish() {
                if (callBack != null)
                    callBack.callback(target);
            }
        }), null, target);
    }

    public static void showGifWithCallback(Context context, int res, final ImageView target, int repeatCount, final ICallBack<ImageView> callBack) {
        if (repeatCount < 0 && repeatCount != -1)
            repeatCount = -1;
        show(context, res, 0, 0, null, null, -1, null, new GlideConfig.GifConfig(true, repeatCount, new GlideConfig.Callback() {
            @Override
            public void onGifPlayFinish() {
                if (callBack != null)
                    callBack.callback(target);
            }
        }), null, target);
    }

    /**
     * 播放gif并获取播放完成回调
     */
    public static void showGifWithCallback(Context context, String url, final ImageView target, final ICallBack<ImageView> callBack) {
        GlideConfig.GifConfig gif = new GlideConfig.GifConfig(true, new GlideConfig.Callback() {
            @Override
            public void onGifPlayFinish() {
                if (callBack != null)
                    callBack.callback(target);
            }
        });
        show(context, url, 0, 0, null, null, -1, null, gif, null, target);
    }

    public static void showGifWithCallback(Context context, int res, final ImageView target, final ICallBack<ImageView> callBack) {
        GlideConfig.GifConfig gif = new GlideConfig.GifConfig(true, new GlideConfig.Callback() {
            @Override
            public void onGifPlayFinish() {
                if (callBack != null)
                    callBack.callback(target);
            }
        });
        show(context, res, 0, 0, null, null, -1, null, gif, null, target);
    }

    /**
     * @param context        上下文
     * @param urlOrResId     图片url或本地资源id，类型应为字符串和整型
     * @param placeholder    图片加载过程显示的图片，0表示不设置
     * @param error          图片加载错误显示的图片，0表示不设置
     * @param cropType       图片裁切类型 {@link GlideConfig.CropType#CENTER_CROP }, {@link GlideConfig.CropType#CENTER_FIT }
     * @param strategy       图片缓存策略，默认是{@link DiskCacheStrategy#RESULT }
     * @param sizeMultiplier 缩略图占原图的比例大小，-1表示不设置缩略图
     * @param fade           图片展示动画封装类
     * @param gifConfig      gif图片显示封装类
     * @param roundConfig    圆角图片显示封装类
     * @param target         显示图片的控件
     */
    private static void show(Context context, Object urlOrResId, int placeholder, int error, GlideConfig.CropType cropType,
                             DiskCacheStrategy strategy, float sizeMultiplier, GlideConfig.CrossFade fade, GlideConfig.GifConfig gifConfig,
                             GlideConfig.GlideRoundConfig roundConfig, ImageView target) {
        RequestManager glideManager = Glide.with(context);
        DrawableTypeRequest drawableTypeRequest = null;
        if (urlOrResId instanceof String) {
            drawableTypeRequest = glideManager.load((String) urlOrResId);
        } else if (urlOrResId instanceof Integer) {
            drawableTypeRequest = glideManager.load((int) urlOrResId);
        }
        if (drawableTypeRequest == null) {
            if (error != 0) {
                target.setImageResource(DEFAULT_ERROR);
            }
            return;
        }

        // 配置默认图
        if (placeholder != 0)
            drawableTypeRequest.placeholder(placeholder);
        if (error != 0)
            drawableTypeRequest.error(error);

        // 配置图片裁切
        if (cropType != null) {
            if (cropType == GlideConfig.CropType.CENTER_CROP)
                drawableTypeRequest.centerCrop();
            if (cropType == GlideConfig.CropType.CENTER_FIT)
                drawableTypeRequest.fitCenter();
        }

        // 配置缓存策略
        if (strategy != null)
            drawableTypeRequest.diskCacheStrategy(strategy);
        else
            drawableTypeRequest.diskCacheStrategy(DiskCacheStrategy.SOURCE);

        // 配置缩略图
        if (sizeMultiplier >= 0 && sizeMultiplier <= 1f)
            drawableTypeRequest.thumbnail(sizeMultiplier);

        // 配置动画
        if (fade == null) {
            drawableTypeRequest.crossFade();
        } else if (fade.getAnimationId() == -1) {
            if (fade.getDuration() == 0)
                drawableTypeRequest.crossFade();
            else
                drawableTypeRequest.crossFade(fade.getDuration());
        } else {
            drawableTypeRequest.crossFade(fade.getAnimationId(), fade.getDuration());
        }

        // 配置圆角/圆形图
        if (roundConfig != null && roundConfig.getShape() != null) {
            if (roundConfig.getShape() == GlideConfig.Shape.CIRCLE)
                drawableTypeRequest.transform(new GlideCircleTransform(context));
            else if (roundConfig.getShape() == GlideConfig.Shape.ROUND)
                drawableTypeRequest.transform(new GlideRoundTransform(context, roundConfig.getRadius()));
        }

        // 配置gif选项
        if (gifConfig == null) {
            drawableTypeRequest.into(target);
        } else if (gifConfig.isSupportGif()) {
            if (gifConfig.getRepeatCount() == -1) {
                if (gifConfig.getCallback() != null)
                    drawableTypeRequest.listener(new GlideConfig.GifRequestListener(gifConfig.getCallback(), -1))
                            .into(new GlideDrawableImageViewTarget(target));
                else
                    drawableTypeRequest.into(target);
            } else if (gifConfig.getCallback() != null) {
                drawableTypeRequest.listener(new GlideConfig.GifRequestListener(gifConfig.getCallback(), gifConfig.getRepeatCount()))
                        .into(new GlideDrawableImageViewTarget(target, gifConfig.getRepeatCount()));
            } else {
                drawableTypeRequest.into(new GlideDrawableImageViewTarget(target, gifConfig.getRepeatCount()));
            }
        } else {
            drawableTypeRequest.asBitmap().into(target);
        }
    }

    /**
     * 展示静态图片
     * @param context       上下文
     * @param url           图片url
     * @param target        图片控件
     * @param placeHolder   加载过程图片
     * @param errorHolder   加载错误图片
     * @param shape         图片形状
     * @param radius        圆角角度
     * @param strategy      缓存策略
     */
    public static boolean showStaticImage(Context context, String url, ImageView target,
                                    int placeHolder, int errorHolder, GlideConfig.Shape shape, int radius,
                                    DiskCacheStrategy strategy) {
        try {
            DrawableTypeRequest<String> drawableRequest = Glide.with(context).load(url);
            if (placeHolder > 0)
                drawableRequest.placeholder(placeHolder);
            if (errorHolder > 0)
                drawableRequest.error(errorHolder);
            if (shape == GlideConfig.Shape.CIRCLE) {
                drawableRequest.transform(new GlideCircleTransform(context));
            } else if (shape == GlideConfig.Shape.ROUND) {
                drawableRequest.transform(new GlideRoundTransform(context, radius));
            }
            drawableRequest.diskCacheStrategy(strategy != null ? strategy : DiskCacheStrategy.RESULT);
            drawableRequest.thumbnail(0.1f);
            drawableRequest.into(target);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 展示静态图片
     * @param context       上下文
     * @param resId         本地图片资源id
     * @param target        图片控件
     * @param placeHolder   加载过程图片
     * @param errorHolder   加载错误图片
     * @param shape         图片形状
     * @param radius        圆角角度
     */
    public static boolean showStaticImage(Context context, int resId, ImageView target,
                                    int placeHolder, int errorHolder, GlideConfig.Shape shape, int radius) {
        try {
            DrawableTypeRequest<Integer> drawableRequest = Glide.with(context).load(resId);
            if (placeHolder > 0)
                drawableRequest.placeholder(placeHolder);
            if (errorHolder > 0)
                drawableRequest.error(errorHolder);
            if (shape == GlideConfig.Shape.CIRCLE) {
                drawableRequest.transform(new GlideCircleTransform(context));
            } else if (shape == GlideConfig.Shape.ROUND) {
                drawableRequest.transform(new GlideRoundTransform(context, radius));
            }
            drawableRequest.into(target);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 展示Gif图片
     * @param context       上下文
     * @param url           图片url
     * @param target        图片控件
     * @param placeHolder   加载过程图片
     * @param errorHolder   加载错误图片
     * @param shape         图片形状
     * @param radius        圆角角度
     * @param strategy      缓存策略
     */
    public static boolean showGif(Context context, String url, ImageView target,
                                  int placeHolder, int errorHolder, GlideConfig.Shape shape, int radius,
                                  DiskCacheStrategy strategy, final int repeatCount, final ICallBack<Integer> playTimeCallback) {
        try {
            DrawableTypeRequest<String> drawableRequest = Glide.with(context).load(url);
            if (placeHolder > 0)
                drawableRequest.placeholder(placeHolder);
            if (errorHolder > 0)
                drawableRequest.error(errorHolder);
            if (shape == GlideConfig.Shape.CIRCLE) {
                drawableRequest.transform(new GlideCircleTransform(context));
            } else if (shape == GlideConfig.Shape.ROUND) {
                drawableRequest.transform(new GlideRoundTransform(context, radius));
            }
            drawableRequest.diskCacheStrategy(strategy != null ? strategy : DiskCacheStrategy.SOURCE);
            if (playTimeCallback != null) {
                drawableRequest.listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (!(resource instanceof GifDrawable))
                            return false;
                        GifDrawable gifDrawable = (GifDrawable) resource;
                        GifDecoder gifDecoder = gifDrawable.getDecoder();
                        int duration = 0;
                        for (int i = 0; i < gifDrawable.getFrameCount(); i++) {
                            duration += gifDecoder.getDelay(i);
                        }
                        final int finalDuration = duration ;
                        playTimeCallback.callback(finalDuration);
                        return false;
                    }
                });
            }
            drawableRequest.into(new GlideDrawableImageViewTarget(target, repeatCount));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 展示gif图片
     * @param context       上下文
     * @param resId         本地图片资源id
     * @param target        图片控件
     * @param placeHolder   加载过程图片
     * @param errorHolder   加载错误图片
     * @param shape         图片形状
     * @param radius        圆角角度
     */
    public static boolean showGif(Context context, int resId, ImageView target,
                                    int placeHolder, int errorHolder, GlideConfig.Shape shape, int radius, final ICallBack<Integer> playTimeCallback) {
        try {
            DrawableTypeRequest<Integer> drawableRequest = Glide.with(context).load(resId);
            if (placeHolder > 0)
                drawableRequest.placeholder(placeHolder);
            if (errorHolder > 0)
                drawableRequest.error(errorHolder);
            if (shape == GlideConfig.Shape.CIRCLE) {
                drawableRequest.transform(new GlideCircleTransform(context));
            } else if (shape == GlideConfig.Shape.ROUND) {
                drawableRequest.transform(new GlideRoundTransform(context, radius));
            }
            drawableRequest.diskCacheStrategy(DiskCacheStrategy.SOURCE);
            if (playTimeCallback != null) {
                drawableRequest.listener(new RequestListener<Integer, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, Integer model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, Integer model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (!(resource instanceof GifDrawable))
                            return false;
                        GifDrawable gifDrawable = (GifDrawable) resource;
                        GifDecoder gifDecoder = gifDrawable.getDecoder();
                        int duration = 0;
                        for (int i = 0; i < gifDrawable.getFrameCount(); i++) {
                            duration += gifDecoder.getDelay(i);
                        }
                        final int finalDuration = duration ;
                        playTimeCallback.callback(finalDuration);
                        return false;
                    }
                });
            }
            drawableRequest.into(target);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}