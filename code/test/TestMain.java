///*
// * 文 件 名:  TestMain.java
// * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014-5-9,  All rights reserved  
// */
//package cn.leadeon.cache.tedis.test;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * <jedis多线程测试>
// * 
// * @author  yunhaibin
// * @version  [1.0, 2014-5-9]
// * @see  [相关类/方法]
// * @since  [中国移动手机营业厅BSS系统/Cache底层模块]
// */
//public class TestMain {
//	public static void main(String[] args) {
//		ExecutorService pool = Executors.newFixedThreadPool(10);
//		for(int i = 0; i < 10; i++) {
//			pool.submit(new JedisTest(i));
//		}
//	}
//}
