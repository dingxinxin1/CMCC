/*
 * 文 件 名:  AtomicTest.java
 * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014-4-25,  All rights reserved  
 */
package cn.leadeon.cache.tedis.test;

import org.springframework.context.ApplicationContext;

import cn.leadeon.cache.tedis.common.AtomicTedis;
import cn.leadeon.cache.tedis.util.CustomBeanFactory;

import com.taobao.common.tedis.core.StringCommands;

/**
 * <单实例redis测试类>
 * <测试单实例redis命令测试>
 * 
 * @author  yunhaibin
 * @version  [1.0, 2014-4-25]
 * @since  [中国移动手机营业厅BSS系统/Cache底层模块]
 */
public class AtomicTest {
	private static StringCommands stringCommands;
	private AtomicTedis atomicTedis;
	
	/** 
	 * <默认构造函数>
	 */
	public AtomicTest() {
		ApplicationContext context = CustomBeanFactory.getContext();
		atomicTedis = (AtomicTedis) context.getBean("atomicTedis");
		stringCommands = atomicTedis.getStringCommands();
	}
	
	public void set() {
		stringCommands.set(0, "test1", "test1");
	}
	
	public static void main(String[] args) {
		AtomicTest atomicTest = new AtomicTest();
		atomicTest.set();
	}
}
