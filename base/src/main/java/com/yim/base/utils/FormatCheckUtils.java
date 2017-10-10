package com.yim.base.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 各种文本格式校验的工具类，如手机号码，邮箱，邮编...
 */
public class FormatCheckUtils {
	/**
	 * 验证输入的邮箱格式是否符合
	 * 
	 * @param 
	 * @return 是否合法
	 */
	public static boolean checkEmail(String email) {
		boolean tag = true;
		final String format = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
		final Pattern pattern = Pattern.compile(format);
		final Matcher mat = pattern.matcher(email);
		if (!mat.find()) {
			tag = false;
		}
		return tag;
	}
	
	/**
	 * 验证输入的身份证格式是否符合
	 * @param idCardNum    身份证号码
	 * @return 是否合法
	 */
	public static boolean checkIdCard(String idCardNum) {
		boolean tag = true;
		final String format = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$";
		final Pattern pattern = Pattern.compile(format);
		final Matcher mat = pattern.matcher(idCardNum);
		if (!mat.find()) {
			tag = false;
		}
		return tag;
	}
	
	/**
	 * 验证该字符串是否包含中文
	 * @param str
	 * @return 是否合法
	 */
	public static boolean containsChinese(String str) {
		boolean tag = true;
		final String format = "^[\u4e00-\u9fa5]";
		final Pattern pattern = Pattern.compile(format);
		final Matcher mat = pattern.matcher(str);
		if (!mat.find()) {
			tag = false;
		}
		return tag;
	}
	
	/**
	 * 验证是否为中国邮编
	 * @param  postcode
	 * @return 是否合法
	 */
	public static boolean checkPostcode(String postcode) {
		boolean tag = true;
		final String format = "[1-9]\\d{5}(?!\\d)";
		final Pattern pattern = Pattern.compile(format);
		final Matcher mat = pattern.matcher(postcode);
		if (!mat.find()) {
			tag = false;
		}
		return tag;
	}
	
	/**
	 * 验证输入是否是手机号码
	 * @param phoneNumber　手机号码
	 * @return  是否合法
	 */
	public static boolean checkPhoneNumber(String phoneNumber) {
		boolean flag = false;
		try {
			Pattern p = Pattern.compile("^1[34578]\\d{9}$");
			Matcher m = p.matcher(phoneNumber);
			flag = m.matches();
		}
		catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	/*
	 * 目前全国有27种手机号段。
移动有16个号段：134、135、136、137、138、139、147、150、151、152、157、158、159、182、187、188。其中147、157、188是3G号段，其他都是2G号段。
联通有7种号段：130、131、132、155、156、185、186。其中186是3G（WCDMA）号段，其余为2G号段。
电信有4个号段：133、153、180、189。其中189是3G号段（CDMA2000），133号段主要用作无线网卡号。
150、151、152、153、155、156、157、158、159 九个
130、131、132、133、134、135、136、137、138、139 十个 180、182、185、186、187、188、189 七个13、15、18三个号段共30个号段，154、181、183、184暂时没有，加上147共27个。
	 * */
}
