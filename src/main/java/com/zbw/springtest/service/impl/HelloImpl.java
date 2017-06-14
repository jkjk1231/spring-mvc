package com.zbw.springtest.service.impl;

import com.zbw.springtest.service.IHello;

import java.util.Date;

public class HelloImpl implements IHello {
	
	private String msg;
	
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String sayHi() {
		return "当前时间：" + new Date() + " msg: " + msg;
	}

}
