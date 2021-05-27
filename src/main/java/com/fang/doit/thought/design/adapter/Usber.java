/**   
 * Copyright © 2017 公司名. All rights reserved.
 * @Prject: mqdemo
 * @Package: com.design.adapter
 * @Description: TODO
 * @author: fangfeiyue   
 * @date: 2017年10月9日 下午4:59:29 
 * @version: V1.0   
 */
package com.fang.doit.thought.design.adapter;
/*
 * 插头上存在usb的插口，即可以存在子类继承此接口。
 */
public class Usber implements Usb{

	public Usber() {
		// TODO Auto-generated constructor stub
	}

	
	public void isUsb() {
		System.out.println("USB连接插口的唯一入口");
	}

}
