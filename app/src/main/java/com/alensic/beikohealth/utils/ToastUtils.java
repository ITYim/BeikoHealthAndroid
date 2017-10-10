package com.alensic.beikohealth.utils;

import android.content.Context;
import android.widget.Toast;

import com.alensic.beikohealth.base.GlobalParams;

public class ToastUtils {

	public static void showToast(Context context, String msg) {
		if (msg == null || msg.trim().length() == 0)
			return;
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	public static void showToast(Context context, String msg, int duration) {
		if (msg == null || msg.trim().length() == 0)
			return;
		if (duration != Toast.LENGTH_SHORT && duration != Toast.LENGTH_LONG) {
			duration = Toast.LENGTH_SHORT;
		}
		Toast.makeText(context, msg, duration).show();
	}

	public static void showToast(String msg) {
		if (msg == null || msg.trim().length() == 0)
			return;
		Toast.makeText(GlobalParams.getContext(), msg, Toast.LENGTH_SHORT).show();
	}

	public static void showToast(String msg, int duration) {
		if (msg == null || msg.trim().length() == 0)
			return;
		if (duration != Toast.LENGTH_SHORT && duration != Toast.LENGTH_LONG) {
			duration = Toast.LENGTH_SHORT;
		}
		Toast.makeText(GlobalParams.getContext(), msg, duration).show();
	}
}
