package cn.leadeon.cache.tedis.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import cn.leadeon.cache.tedis.common.TedisFactory;
import cn.leadeon.cache.tedis.util.CustomBeanFactory;
import cn.leadeon.cache.tedis.util.TimeCount;

import com.taobao.common.tedis.binary.RedisKeyCommands;
import com.taobao.common.tedis.core.HashCommands;
import com.taobao.common.tedis.core.ListCommands;
import com.taobao.common.tedis.core.StringCommands;
import com.taobao.common.tedis.core.ValueCommands;

/**
 * <多实例redis测试类>
 * <测试多实例redis命令测试>
 * 
 * @author  yunhaibin
 * @version  [1.0, 2014-4-25]
 * @since  [中国移动手机营业厅BSS系统/Cache底层模块]
 */
@SuppressWarnings("unused")
public class MultiTest extends Thread {
	private int threadId;
	private static final Logger logger = LoggerFactory.getLogger(MultiTest.class);
	
	private static int method = 0;
	private static int threadPools = 1;
	private static int nameSpaces = 0;

	private static TedisFactory tedisFactory;
	
	private static StringCommands stringCommands;
	private static ValueCommands valueCommands;
	private HashCommands hashCommands;
	public static RedisKeyCommands keyCommands;
	public static ListCommands listCommands;
	
	static {
		ApplicationContext context = CustomBeanFactory.getContext();
		tedisFactory = (TedisFactory) context.getBean("tedisFactory");
		stringCommands = tedisFactory.stringCommands;
		valueCommands = tedisFactory.valueCommands;
		keyCommands = tedisFactory.keyCommands;
		listCommands = tedisFactory.listCommands;
	}
	
	/** 
	 * <默认构造函数>
	 */
	public MultiTest() {
		ApplicationContext context = CustomBeanFactory.getContext();
		tedisFactory = (TedisFactory) context.getBean("tedisFactory");
		stringCommands = tedisFactory.stringCommands;
		valueCommands = tedisFactory.valueCommands;
		hashCommands = tedisFactory.mapCommands;
		
	}
	
	public MultiTest(int id) {
		this.threadId = id;
	}
	
	/**
	 * 重载方法
	 */
	@Override
	public void run() {
		
		switch (method) {
		case 0:
			setValue(nameSpaces);
			break;
		case 1:
			getValue(nameSpaces);
			break;	
		case 2:
			delNameSpaces(nameSpaces);
			break;
		case 3:
			delKey(nameSpaces);
			break;
		case 4:
			exKey(nameSpaces);
			break;
		case 5:
			expire(nameSpaces);
			break;
		case 6:
			ttl(nameSpaces);
			break;
		}
		
	}

	public void setValue(int nameSpaces) {
		TimeCount.start();
		logger.info("==>Add cache");
		String key = "test";
		String value = "test";
		for(int y = 1; y <= 10; y++) {
//			stringCommands.set(nameSpaces, key+y, value+y, 5, TimeUnit.MINUTES);
//			stringCommands.set(nameSpaces, key+y, value+y);
//			valueCommands.set(nameSpaces, key+y, value+y);
			List<String> list = new ArrayList<String>();
			list.add("123");
			list.add("456");
			list.add("789");
			for(String str : list) {
				listCommands.rightPush(nameSpaces, key+y, str);
			}
			if(y % 1000  == 0) {
				System.out.println(y + "---------1000 " + " execute ok");
				System.out.println(threadPools + "个线程并发，当前线程:::::::" + this.threadId);
				TimeCount.exeTime();
				TimeCount.start();
			}
		}
		System.out.println("exit");
		System.exit(0);
	}
	
	public void getValue(int nameSpaces) {
		TimeCount.start();
		logger.info("==>Add cache");
		String key = "test";
		for(int y = 1; y <= 10; y++) {
//			String str = stringCommands.get(nameSpaces, key+y);
//			String str = valueCommands.get(nameSpaces, key+y);
			List<String> list = listCommands.range(nameSpaces, key+y, 0, -1);
			for(String str : list) {
				System.out.println(str);
			}
			if(y % 1000  == 0) {
				System.out.println(y + "---------1000 " + " execute ok");
				System.out.println(threadPools + "个线程并发，当前线程:::::::" + this.threadId);
				TimeCount.exeTime();
				TimeCount.start();
			}
		}
		System.out.println("exit");
		System.exit(0);
	}
	
	public void delNameSpaces(int nameSpaces) {
		Long result = tedisFactory.delNameSpaces(nameSpaces);
		System.out.println("清楚的数据条数：" + result);
		System.out.println("exit");
		System.exit(0);
	}
	
	public void exKey(int nameSpaces) {
		Boolean result = tedisFactory.listCommands.hasKey(nameSpaces, "test2");
		System.out.println(result);
		System.out.println("exit");
		System.exit(0);
	}
	
	public void delKey(int nameSpaces) {
		Long result = tedisFactory.listCommands.delKey(nameSpaces, "test2");
		System.out.println(result);
		System.out.println("exit");
		System.exit(0);
	}
	
	public void expire(int nameSpaces) {
		Boolean result = tedisFactory.listCommands.expire(nameSpaces, "test5", 5, TimeUnit.MINUTES);
		System.out.println(result);
		System.out.println("exit");
		System.exit(0);
	}
	
	public void ttl(int nameSpaces) {
		Long result = tedisFactory.listCommands.ttl(nameSpaces, "test5");
		System.out.println(result);
		System.out.println("exit");
		System.exit(0);
	}

	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入线程数（默认一个线程）：");
		threadPools = sc.nextInt();
		System.out.println("线程数：" + threadPools);
		System.out.println("请输入被执行方法（setValue:0,getValue:1,delNameSpaces:2,delKey:3,exKey:4,expire:5,ttl:6）：");
		method = sc.nextInt();
		System.out.println("被执行操作：" + method);
		System.out.println("请输入命名空间：");
		nameSpaces = sc.nextInt();
		System.out.println("命名空间：" + nameSpaces);
		//pool
		ExecutorService pool = Executors.newCachedThreadPool();
		for(int i = 0; i < threadPools; i++) {
			pool.submit(new Thread(new MultiTest(i)));
		}
	}
}
