/**
 * 
 */ 
package com.yim.base.common;

/** 
 * 类说明 :公共的回调接口
 * @author  郑依民
 * 创建时间：2016/07/21
 */
public interface ICallBack<T> {

	void callback(T... result);

}
 