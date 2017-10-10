package com.alensic.beikohealth.glide;

import android.os.Handler;
import android.os.Message;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.yim.base.utils.Logger;


/**
 * Created by zym on 2017/3/14.
 */
class GlideConfig {

    enum CropType {
        /**
         * {@link DrawableRequestBuilder#centerCrop()}
         */
        CENTER_CROP,

        /**
         * {@link DrawableRequestBuilder#fitCenter()}
         */
        CENTER_FIT
    }

    enum Shape {
        /**
         * 圆角
         */
        ROUND,

        /**
         * 圆形
         */
        CIRCLE
    }

    class CrossFade {
        private int animationId;
        private int duration;

        public CrossFade() {
            this(-1, 0);
        }

        public CrossFade(int animationId, int duration) {
            this.animationId = animationId;
            this.duration = duration;
        }

        public int getAnimationId() {
            return animationId;
        }

        public int getDuration() {
            return duration;
        }
    }

    static class GifConfig {
        private boolean supportGif;
        private int repeatCount;
        private Callback callback;

        public GifConfig() {
            this(true);
        }

        public GifConfig(boolean supportGif) {
            this(supportGif, supportGif ? -1 : 1);
        }

        public GifConfig(boolean supportGif, int repeatCount) {
            this(supportGif, repeatCount, null);
        }

        public GifConfig(boolean supportGif, Callback callback) {
            this(supportGif, supportGif ? -1 : 1, callback);
        }

        public GifConfig(boolean supportGif, int repeatCount, Callback callback) {
            this.supportGif = supportGif;
            this.repeatCount = repeatCount;   // -1:表示无限循环
            this.callback = callback;
        }

        public boolean isSupportGif() {
            return supportGif;
        }

        public int getRepeatCount() {
            return repeatCount;
        }

        public Callback getCallback() {
            return callback;
        }
    }

    interface Callback {
        void onGifPlayFinish();
    }

    static class GlideRoundConfig {
        private Shape shape;  // 是否显示圆角图片
        private int radius;     // 圆角角度半径，单位dp

        public GlideRoundConfig() {
            this(null, 0);
        }

        public GlideRoundConfig(Shape shape) {
            this(shape, 0);
        }

        public GlideRoundConfig(Shape shape, int radius) {
            this.shape = shape;
            this.radius = radius;
        }

        public Shape getShape() {
            return shape;
        }

        public int getRadius() {
            return radius;
        }
    }

    static class GifRequestListener implements RequestListener {
        private Callback callback;
        private int repeatCount;

        public GifRequestListener(Callback callback, int repeatCount) {
            this.callback = callback;
            this.repeatCount = repeatCount;
        }

        @Override
        public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
            Logger.e(BitmapUtil.class.getSimpleName(), "DrawableTypeRequest request error.");
            return false;
        }

        @Override
        public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
            if (!(resource instanceof GifDrawable) || callback == null)
                return false;
            GifDrawable gifDrawable = (GifDrawable) resource;
            GifDecoder gifDecoder = gifDrawable.getDecoder();
            int duration = 0;
            for (int i = 0; i < gifDrawable.getFrameCount(); i++) {
                duration += gifDecoder.getDelay(i);
            }
            if (repeatCount >= 0)
                duration *= repeatCount;
            final int finalDuration = duration ;
            Message msg = Message.obtain();
            msg.obj = callback;
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    ((GlideConfig.Callback) msg.obj).onGifPlayFinish();
                }
            }.sendMessageDelayed(msg, finalDuration);
            return false;
        }
    }
}
