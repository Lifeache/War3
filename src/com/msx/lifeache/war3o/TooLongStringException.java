package com.msx.lifeache.war3o;

public class TooLongStringException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4401120919658100631L;
	
	@Override
	public String getMessage() {
		// TODO 自动生成的方法存根
		return "字符串过于庞大";
	}
}