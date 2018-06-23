package com.scp.multithreading.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueueDemo {
	static BlockingQueue<Integer> blkingQue=null;
	public static void main(String[] args) {
		 blkingQue=new ArrayBlockingQueue<>(2);
		
		Producer t1=new Producer(blkingQue, "Producer");
		Consumer t2=new Consumer(blkingQue, "Consumer");
		
		t1.start();
		t2.start();
		System.out.println(Thread.currentThread().getName()+ " : Thread is completed....");
		
	}

}
class Producer extends Thread{
	BlockingQueue<Integer> producerBQ;
	String name;
	public Producer(BlockingQueue<Integer> producerBQ, String name) {
		super(name);
		this.producerBQ = producerBQ;
	}
	public void run(){
		while(true){
			int randomNo=ThreadLocalRandom.current().nextInt(1,200);
			
			/*producerBQ.add(randomNo);
			
			System.out.println(Thread.currentThread().getName()+" : is produced : "+randomNo);
*/			
			try {
				TimeUnit.SECONDS.sleep(1);
				System.out.println(Thread.currentThread().getName()+" : is produced : "+randomNo);
				System.out.println(producerBQ.offer(randomNo));
				//System.out.println(producerBQ.offer(randomNo, 1000,TimeUnit.SECONDS));
				//producerBQ.put(randomNo);
				
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
		}
		
		
	}
}
class Consumer extends Thread{
	BlockingQueue<Integer> consumerBQ;
	String name;
	public Consumer(BlockingQueue<Integer> consumerBQ, String name) {
		super(name);
		this.consumerBQ = consumerBQ;
	}
	public void run(){
		while(true){
			/*int no1=consumerBQ.element();
			consumerBQ.remove(no1);
			
			System.out.println(Thread.currentThread().getName()+" : is consumed : "+no1);
			*/
			try {
				Thread.sleep(10000);
				int no=consumerBQ.poll();
				//int no=consumerBQ.take();
				//int no=consumerBQ.poll(2000, TimeUnit.SECONDS);
				System.out.println(Thread.currentThread().getName()+" : is consumed : "+no);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			
			
		}
	}
}
