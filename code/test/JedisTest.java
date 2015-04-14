///*
// * 文 件 名:  JedisTest.java
// * 版    权:  Xi'An Leadeon Technologies Co., Ltd. Copyright 2014-5-9,  All rights reserved  
// */
//package cn.leadeon.cache.tedis.test;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import cn.leadeon.cache.tedis.common.ZKDataConfig;
//import cn.leadeon.cache.tedis.util.TimeCount;
//import redis.clients.jedis.Jedis;
//
///**
// * <jedis测试类>
// * <测试jedis相关命令>
// * 
// * @author  yunhaibin
// * @version  [1.0, 2014-5-9]
// * @see  [相关类/方法]
// * @since  [中国移动手机营业厅BSS系统/Cache底层模块]
// */
//public class JedisTest extends Thread {
//	private static final Logger logger = LoggerFactory.getLogger(JedisTest.class);
//	private int threadId;
//	private static Jedis jedis;
//	
//	public JedisTest(int id) {
////		jedis = new Jedis("192.168.10.109");
//		this.threadId = id;
//	}
//	
//	/**
//	 * 重载方法
//	 */
//	@Override
//	public void run() {
//		try {
//			jedis = new Jedis("192.168.10.109");
//			setValue();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void setValue() throws InterruptedException {
//		TimeCount.start();
//		logger.info("==>Add cache");
//		String key = "test";
//		String value = "test";
//		for(int y = 1; y <= 100000; y++) {
////			stringCommands.set(0, key+y, value+y);
//			String str = jedis.set("0:" + key+y, value+y);
//			//String str = jedis.get("0:" + key+y);
//			if(y % 10000  == 0) {
//				System.out.println(y + "---------10000 "+ str + " execute ok");
//				System.out.println("10个线程并发，当前线程:::::::" + this.threadId);
//				TimeCount.exeTime();
//			}
//		}
//		System.out.println("exit");
//	}
//	
//	public static void main(String[] args) {
//		ExecutorService pool = Executors.newCachedThreadPool();
//		for(int i = 0; i < 10; i++) {
//			pool.submit(new JedisTest(i));
//		}
//	}
//}
