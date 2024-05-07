package com.fang.doit.design.adapter;
/*
 * 就像我手上有一个ps2插头的设备，但是主机上只有usb的插口。弄个转换器
 */
public class Clienter {
	public static void main(String[] args) {
		Ps2 p = new Adapter();
		p.isPs2();
	}
}
