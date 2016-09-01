package com.demo.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.DisplayMetrics;

import com.demo.DemoApplication;

public class UIUtils {

	public static Context getContext() {
		return DemoApplication.getContext();
	}

	public static Resources getResources() {
		return getContext().getResources();
	}

	public static String getString(int resId) {
		return getResources().getString(resId);
	}

	public static String[] getStringArray(int resId) {
		return getResources().getStringArray(resId);
	}

	public static String getPackageName() {
		return getContext().getPackageName();
	}

	public static int getColor(int resId) {
		return getResources().getColor(resId);
	}

	public static Handler getMainHandler() {
		return DemoApplication.getMainHandler();
	}

	public static Thread getMainThread() {
		return DemoApplication.getMainThread();
	}

	public static long getMainThreadId() {
		return DemoApplication.getMainThreadId();
	}

	/**
	 * 让task在主线程中执行
	 */
	public static void post(Runnable task) {
		int myTid = android.os.Process.myTid();

		if (myTid == getMainThreadId()) {
			// 在主线程中执行的
			task.run();
		} else {
			// 在子线程中执行的
			getMainHandler().post(task);
		}
	}

	/** 获取颜色选择器 */
	public static ColorStateList getColorStateList(int resId) {
		return getResources().getColorStateList(resId);
	}

	/** 获取drawable */
	public static Drawable getDrawable(int resId) {
		return getResources().getDrawable(resId);
	}

	/**
	 * dip 转 px
	 * 
	 * @param dip
	 * @return
	 */
	public static int dip2px(int dip) {
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		float density = metrics.density;
		return (int) (dip * density + 0.5f);
	}

	/**
	 * px 转 dip
	 * 
	 * @param px
	 * @return
	 */
	public static int px2dip(int px) {
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		float density = metrics.density;
		return (int) (px / density + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 * 
	 * @param spValue
	 * @param fontScale
	 *            (DisplayMetrics类中属性scaledDensity)
	 * @return
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 * 
	 * @param pxValue
	 * @param fontScale
	 *            (DisplayMetrics类中属性scaledDensity)
	 * @return
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 执行延时任务
	 */
	public static void postDelayed(Runnable task, int delayed) {
		getMainHandler().postDelayed(task, delayed);
	}

	/**
	 * 移除任务
	 * 
	 * @param task
	 */
	public static void removeCallbacks(Runnable task) {
		getMainHandler().removeCallbacks(task);
	}

	public static String getString(int id, Object... formatArgs) {
		return getResources().getString(id, formatArgs);
	}

	public static String getFormateTime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-AddressBookFragment HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * 设置整个窗体透明度
	 * 
	 * @param activity
	 * @param alpha
	 *            0.0-1.0f;
	 */
	public static void setBackgroundAlpha(Activity activity, float alpha) {
		android.view.WindowManager.LayoutParams params = activity.getWindow()
				.getAttributes();
		params.alpha = alpha;
		activity.getWindow().setAttributes(params);
	}
}
