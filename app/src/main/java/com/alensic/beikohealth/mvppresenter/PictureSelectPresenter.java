package com.alensic.beikohealth.mvppresenter;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.provider.MediaStore;

import com.alensic.beikohealth.mvp.BasePresenter;
import com.alensic.beikohealth.bean.PhotoInfo;
import com.alensic.beikohealth.mvp.BaseView;
import com.alensic.beikohealth.mvpview.PictureSelectView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author zym
 * @since 2017-08-08 14:13
 */
public class PictureSelectPresenter extends BasePresenter<PictureSelectView> {
    public PictureSelectPresenter(Context context, BaseView mvpView) {
        super(context, mvpView);
    }

    public void loadAllPictures() {
        Observable
                .create(new Observable.OnSubscribe<ArrayList<PhotoInfo>>() {
                    @Override
                    public void call(Subscriber<? super ArrayList<PhotoInfo>> subscriber) {
                        subscriber.onStart();
                        String[] projection = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.DISPLAY_NAME,
                                MediaStore.Images.Media.SIZE, MediaStore.Images.Media.DESCRIPTION};
                        CursorLoader loader = new CursorLoader(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                projection, null, null, null);
                        Cursor cursor = loader.loadInBackground();
                        if (cursor == null) {
                            subscriber.onNext(null);
                            subscriber.onCompleted();
                            return;
                        }
                        try {
                            ArrayList<PhotoInfo> result = new ArrayList<>();
                            if (cursor.moveToFirst()) {
                                do {
                                    String path = cursor.getString(cursor.getColumnIndex(projection[0]));
                                    String name = cursor.getString(cursor.getColumnIndex(projection[1]));
                                    long size = cursor.getLong(cursor.getColumnIndex(projection[2]));
                                    String description = cursor.getString(cursor.getColumnIndex(projection[3]));
                                    result.add(new PhotoInfo(path, name, size, description));
                                }
                                while (cursor.moveToNext());
                            }
                            subscriber.onNext(result);
                            subscriber.onCompleted();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            cursor.close();
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArrayList<PhotoInfo>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mvpView.showPictures(null);
                    }

                    @Override
                    public void onNext(ArrayList<PhotoInfo> result) {
                        mvpView.showPictures(result);
                        if (result == null)
                            return;
                        Set<String> typeSet = new HashSet<>();
                        File file;
                        for (int i = 0, size = result.size(); i < size; i++) {
                            file = new File(result.get(i).getPhotoPath());
                            typeSet.add(file.getParentFile().getName());
                        }
                        mvpView.buildPictureTypes(new ArrayList<>(typeSet));
                    }
                });
    }

    public void loadPictureByType(final String type) {
        if (type == null)
            return;
        if ("全部图片".equals(type)) {
            loadAllPictures();
            return;
        }
        Observable
                .create(new Observable.OnSubscribe<ArrayList<PhotoInfo>>() {
                    @Override
                    public void call(Subscriber<? super ArrayList<PhotoInfo>> subscriber) {
                        subscriber.onStart();
                        String[] projection = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.DISPLAY_NAME,
                                MediaStore.Images.Media.SIZE, MediaStore.Images.Media.DESCRIPTION};
                        CursorLoader loader = new CursorLoader(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                projection, null, null, null);
                        Cursor cursor = loader.loadInBackground();
                        if (cursor == null) {
                            subscriber.onNext(null);
                            subscriber.onCompleted();
                            return;
                        }
                        try {
                            ArrayList<PhotoInfo> result = new ArrayList<>();
                            if (cursor.moveToFirst()) {
                                do {
                                    String path = cursor.getString(cursor.getColumnIndex(projection[0]));
                                    if (type.equals(new File(path).getParentFile().getName())) {
                                        String name = cursor.getString(cursor.getColumnIndex(projection[1]));
                                        long size = cursor.getLong(cursor.getColumnIndex(projection[2]));
                                        String description = cursor.getString(cursor.getColumnIndex(projection[3]));
                                        result.add(new PhotoInfo(path, name, size, description));
                                    }
                                }
                                while (cursor.moveToNext());
                            }
                            subscriber.onNext(result);
                            subscriber.onCompleted();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            cursor.close();
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArrayList<PhotoInfo>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mvpView.showPictures(null);
                    }

                    @Override
                    public void onNext(ArrayList<PhotoInfo> result) {
                        mvpView.showPictures(result);
                    }
                });
    }
}
