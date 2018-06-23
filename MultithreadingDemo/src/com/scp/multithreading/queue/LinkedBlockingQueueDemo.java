package com.scp.multithreading.queue;

import java.util.concurrent.*;

public class LinkedBlockingQueueDemo {
	static BlockingQueue<Integer> blockingQueue;
	public static void main(String[] args) {
		 blockingQueue = new LinkedBlockingQueue<>(2);
		 ProducerLink t1=new ProducerLink("Producer",blockingQueue);
		 ConsumerLink t2=new ConsumerLink( "Consumer",blockingQueue);
		 
		 t1.start();
		 t2.start();
		 
		 System.out.println(Thread.currentThread().getName()+" Thread is completed...");
		 
	}

}
class ProducerLink extends Thread{
	String name;
	BlockingQueue<Integer> bq;
	public ProducerLink(String name, BlockingQueue<Integer> bq) {
		super(name);
		this.bq = bq;
	}
	public void run(){
		while(true){
			int randomNo=ThreadLocalRandom.current().nextInt(1,100);
			System.out.println(Thread.currentThread().getName()+" : is Producing : "+randomNo);
			System.out.println(bq.offer(randomNo));
			try {
				
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
class ConsumerLink extends Thread{
	String name;
	BlockingQueue<Integer> bq;
	public ConsumerLink(String name, BlockingQueue<Integer> bq) {
		super(name);
		this.bq = bq;
	}
	public void run(){
		while(true){
			try {
				int no=bq.poll();
				System.out.println(Thread.currentThread().getName()+" : is consuming : "+no);
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
