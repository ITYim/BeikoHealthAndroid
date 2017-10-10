package com.alensic.beikohealth.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.alensic.beikohealth.R;
import com.alensic.beikohealth.activity.FifthActivity;
import com.alensic.beikohealth.activity.FirstActivity;
import com.alensic.beikohealth.activity.FourthActivity;
import com.alensic.beikohealth.activity.LoginActivity;
import com.alensic.beikohealth.activity.SecondActivity;
import com.alensic.beikohealth.activity.ThirdActivity;
import com.alensic.beikohealth.bean.PhotoInfo;
import com.alensic.beikohealth.image.PhotoPreviewFragment;
import com.alensic.beikohealth.image.PictureSelectorFragment;
import com.yim.base.BaseFragmentActivity;

import java.util.ArrayList;

/**
 * 负责界面跳转统一管理
 * @author zym
 * @since 2017-07-27 10:55
 */
public class UIHelper {

    public static void goToLoginActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
        if (context instanceof Activity) {
            ((Activity) context).overridePendingTransition(R.anim.login_in, R.anim.no_anim);
        }
    }

    /**
     * 打开系统图片选择界面
     */
    public static void openPicturesDepot(Context context, int maxCount, ArrayList<String> list) {
        Bundle bundle = new Bundle();
        bundle.putInt(PictureSelectorFragment.KEY_MAX_PICTURE_SELECT, maxCount);
        bundle.putStringArrayList(PictureSelectorFragment.KEY_ORIGINAL_PICTURE_SELECTED, list);
        UIHelper.goToNextFragmentActivity(context, PictureSelectorFragment.class.getName(), bundle);
    }

    /**
     * 打开图片预览界面
     */
    public static void openPhotoPreview(Context context, ArrayList<PhotoInfo> list, int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(PhotoPreviewFragment.KEY_PHOTO_LIST, list);
        bundle.putInt(PhotoPreviewFragment.KEY_PREVIEW_POSITION, position);
        UIHelper.goToNextFragmentActivity(context, PhotoPreviewFragment.class.getName(), bundle);
    }

    /**
     * 根据当前Activity打开层次FragmentActivity
     * Activity展示的Fragment的Tag是目标Fragment的class.getSimpleName();
     */
    public static void goToNextFragmentActivity(Context context, String fragmentName, Bundle bundle) {
        if (context == null || !(context instanceof Activity) || fragmentName == null)
            return;
        Class<?> clazz = FirstActivity.class;
        if (context instanceof FirstActivity) {
            clazz = SecondActivity.class;
        } else if (context instanceof SecondActivity) {
            clazz = ThirdActivity.class;
        } else if (context instanceof ThirdActivity) {
            clazz = FourthActivity.class;
        } else if (context instanceof FourthActivity) {
            clazz = FifthActivity.class;
        }
        goToActivity(context, createFragmentActivityIntent(context, clazz, fragmentName, bundle));
    }

    /**
     * 根据当前Activity打开层次FragmentActivity
     * Activity展示的Fragment的Tag是目标Fragment的class.getSimpleName();
     */
    public static void goToNextFragmentActivity(Context context, Intent intent, String fragmentName, Bundle bundle) {
        if (context == null || !(context instanceof Activity) || fragmentName == null)
            return;
        Class<?> clazz = FirstActivity.class;
        if (context instanceof FirstActivity) {
            clazz = SecondActivity.class;
        } else if (context instanceof SecondActivity) {
            clazz = ThirdActivity.class;
        } else if (context instanceof ThirdActivity) {
            clazz = FourthActivity.class;
        } else if (context instanceof FourthActivity) {
            clazz = FifthActivity.class;
        }
        goToActivity(context, createFragmentActivityIntent(context, intent, clazz, fragmentName, bundle));
    }

    private static Intent createFragmentActivityIntent(Context context, Class<?> activityClass, String fragmentName, Bundle args) {
        boolean fullClassName = fragmentName.contains(".");
        Intent intent = new Intent(context, activityClass);
        intent.putExtra(BaseFragmentActivity.KEY_FULL_FRAGMENT_NAME, fullClassName ? fragmentName : "");
        intent.putExtra(BaseFragmentActivity.KEY_SIMPLE_FRAGMENT_NAME, fullClassName ? "" : fragmentName);
        intent.putExtra(BaseFragmentActivity.KEY_FRAGMENT_ARGUMENT, args);
        return intent;
    }

    private static Intent createFragmentActivityIntent(Context context, Intent intent, Class<?> activityClass, String fragmentName, Bundle args) {
        boolean fullClassName = fragmentName.contains(".");
        intent.setClass(context, activityClass);
        intent.putExtra(BaseFragmentActivity.KEY_FULL_FRAGMENT_NAME, fullClassName ? fragmentName : "");
        intent.putExtra(BaseFragmentActivity.KEY_SIMPLE_FRAGMENT_NAME, fullClassName ? "" : fragmentName);
        intent.putExtra(BaseFragmentActivity.KEY_FRAGMENT_ARGUMENT, args);
        return intent;
    }

    public static void goToActivity(Context context, Intent intent) {
        context.startActivity(intent);
    }

    public static void goToActivity(Context context, Class<?> activityClass) {
        goToActivity(context, activityClass, null);
    }

    public static void goToActivity(Context context, Class<?> activityClass, Bundle bundle) {
        Intent intent = new Intent(context, activityClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * 创建具有SingleInstance启动模式作用的Intent
     */
    public static Intent createNewTaskLaunchMode(Intent intent) {
        if (intent == null)
            intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    /**
     * 创建具有SingleTop启动模式作用的Intent
     */
    public static Intent createSingleTopLaunchMode(Intent intent) {
        if (intent == null)
            intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return intent;
    }

    /**
     * 创建具有SingleTask启动模式作用的Intent
     */
    public static Intent createSingleTaskLaunchMode(Intent intent) {
        if (intent == null)
            intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }
}
