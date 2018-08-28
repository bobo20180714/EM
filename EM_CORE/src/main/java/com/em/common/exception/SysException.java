package com.em.common.exception;

/**
 * 
 * @Description: 自定义异常类
 * @author liuyx
 * @date 2015年10月26日11:17:25
 */
public class SysException extends RuntimeException {

	private Object obj;

	public SysException() {

		super();

	}

	public SysException(String msg) {

		super(msg);

	}

	public SysException(String msg, Object obj) {
		super(msg);
		this.obj = obj;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public SysException(String msg, Throwable cause) {

		super(msg, cause);

	}

	public SysException(Throwable cause) {

		super(cause);

	}
}
