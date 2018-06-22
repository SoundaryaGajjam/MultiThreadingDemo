package com.scp.multithreading;

public class AnonymousInnerDemo {
	public static void main(String[] args) {
		
		System.out.println(Thread.currentThread().getName()+" : thread completed....");
		new Thread(){
		
			public void run(){
				Thread.currentThread().setName("t1");
				for(int i=0;i<10;i++)
					System.out.println(Thread.currentThread().getName()+" : Producing No : "+i);
			}
		}.start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Thread.currentThread().setName("t2");
				for(int i=0;i<=10;i++)
					System.out.println(Thread.currentThread().getName()+" : Producing Square : "+i*i);
				
			}
		}).start();
	}
}
