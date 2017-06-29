package com.xuyao.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class MyTest {
	static int count = 10000;
	static ExecutorService es = Executors.newFixedThreadPool(5);
	static Integer j = 0;
	static AtomicInteger ai = new AtomicInteger(1);
	public static void main(String[] args) {
		/*ClassLoader cl = new Myloader();
		System.out.println(Thread.currentThread().getContextClassLoader());
		System.out.println(Thread.currentThread().getContextClassLoader().getParent());
		System.out.println(Thread.currentThread().getContextClassLoader().getParent().getParent());
		System.out.println(List.class.getClassLoader());
		System.out.println(Runtime.getRuntime().availableProcessors());*/
		long b = System.currentTimeMillis();
		for(int i = 0; i < count; i++){
			es.execute(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					/*synchronized(es){
						j++;
					}*/
					j = ai.getAndIncrement();
				}
				
			});
		}
		es.shutdown();
		while(true){
			if(es.isTerminated()){
				System.out.println(j);
				break;
			}
		}
		System.out.println("end: " + (System.currentTimeMillis() - b));
	}

	static class Myloader extends ClassLoader{
		
	}
}
