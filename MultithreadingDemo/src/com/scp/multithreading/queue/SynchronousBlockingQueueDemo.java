package com.scp.multithreading.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class SynchronousBlockingQueueDemo {
	public static void main(String[] args) {
		BlockingQueue<Integer>bq=new SynchronousQueue<>();
		SynchProducer t1=new SynchProducer("Producer", bq);
		SynchConsumer t2=new SynchConsumer("Consumer", bq);
		
		t1.start();
		t2.start();
		
		System.out.println(Thread.currentThread().getName()+" : is completed...");
		
	}
}
class SynchProducer extends Thread{
	String name;
	BlockingQueue<Integer>bq;
	public SynchProducer(String name, BlockingQueue<Integer> bq) {
		super(name);
		this.bq = bq;
	}
	public void run(){
		while(true){
			System.out.println(bq);
			int randomNo=ThreadLocalRandom.current().nextInt(200);
			System.out.println(Thread.currentThread().getName()+" : is producing : "+randomNo);
			try {
				bq.put(randomNo);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class SynchConsumer extends Thread{
	String name;
	BlockingQueue<Integer>bq=null;
	public SynchConsumer(String name, BlockingQueue<Integer> bq) {
		super(name);
		this.bq = bq;
	}
	public void run(){
		while(true){
			System.out.println(bq);
			try {
				System.out.println(Thread.currentThread().getName()+" : is consuming : "+bq.take());
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
