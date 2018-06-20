package com.scp.multithreading;

import java.util.ArrayList;
import java.util.Random;

public class ProducerConsumerAlternate {
	public static void main(String[] args) {
		ArrayList list=new ArrayList<>();
		Producer1 pThread1=new Producer1(list);
		Consumer1 cThread1=new Consumer1(list);
		
		Thread t1=new Thread(pThread1);
		Thread t2=new Thread(cThread1);
		
		t1.start();
		t1.setName("PRODUCER");
		t2.start();
		t2.setName("CONSUMER");
	}

}
class Producer1 implements Runnable{
	ArrayList  al;
	public Producer1(ArrayList al) {
		super();
		this.al = al;
	}
	@Override
	public void run() {
		while(true){
			synchronized (al) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(al.size()==0){
					int randomNo=new Random().nextInt(100);
					al.add(randomNo);
					System.out.println(Thread.currentThread()+" ---Produced element : "+randomNo);
					try {
						al.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
					
				al.notify();
				
			}
		}
			
	}
	
}
class Consumer1 implements Runnable{
	ArrayList al;
	public Consumer1(ArrayList al) {
		super();
		this.al = al;
	}
	@Override
	public void run() {
		while(true){
			synchronized (al) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(al.size()==1){
					System.out.println(Thread.currentThread()+"---- Consumed element : "+al.remove(0));
					al.notify();
				}
				try {
					al.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}			
			}
		}
		
		
	}
}
