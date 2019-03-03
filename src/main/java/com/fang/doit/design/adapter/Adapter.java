/**   
 * Copyright © 2017 公司名. All rights reserved.
 * @Prject: mqdemo
 * @Package: com.design
 * @Description: TODO
 * @author: fangfeiyue   
 * @date: 2017年10月9日 下午4:50:52 
 * @version: V1.0   
 */
package com.fang.doit.design.adapter;

/**
 * 转换器：可以对任何接口转换成可用的usb接口。目前是Ps2的接口
 */
public class Adapter extends Usber implements Ps2 {

	public Adapter() {
		// TODO Auto-generated constructor stub
	}

	public void isPs2() {
		isUsb();
	}

	/**
	 * 也可以通过组合的方式实现适配器功能
	 */
	private Usb usb;

	public Adapter(Usb usb) {
		this.usb = usb;
	}

}
