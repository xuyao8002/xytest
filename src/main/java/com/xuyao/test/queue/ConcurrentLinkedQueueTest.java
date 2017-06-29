package com.xuyao.test.queue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentLinkedQueueTest {
    static int count = 5;
	static ExecutorService es = Executors.newFixedThreadPool(count);
	static CountDownLatch cdl = new CountDownLatch(count);
    public static void main(String[] args) throws InterruptedException{
    	long start = System.currentTimeMillis();
    	
    	for(int i = 0; i < count; i++){
    		final int j = i;
        	es.execute(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						
						Thread.sleep(j % 2 == 0? 4000 : 5000);
						System.out.println(j);
						cdl.countDown();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
        		
        	});
        }
    	cdl.await();
    	System.out.println(System.currentTimeMillis() - start);
    	es.shutdown();
    }
}
