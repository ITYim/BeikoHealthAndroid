package com.alensic.beikohealth.http;

import android.content.Context;

/**
 *
 * @author zym
 * @since 2017-08-09 12:21
 */
public class SimpleHandler<T> extends RequestCallback<T> {

    public SimpleHandler(Context context) {
        super(context);
    }

    public SimpleHandler() {
        super();
    }

}
