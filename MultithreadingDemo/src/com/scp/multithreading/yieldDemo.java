package com.scp.multithreading;

import java.util.concurrent.TimeUnit;

public class yieldDemo extends Thread{
	static yieldDemo t1;
	static yieldDemo t2,t3,t4;
	public static void main(String[] args) {
		t1=new yieldDemo();
		t1.setName("t1");
		t1.start();
		//t1.yield();
		
		
		t2=new yieldDemo();
		t2.setName("t2");
		t2.start();
		/*t3=new yieldDemo();
		t3.setName("t3");
		t3.start();
		t4=new yieldDemo();
		t4.setName("t4");
		t4.start();
		
		t3.setPriority(MAX_PRIORITY);*/
		System.out.println(Thread.currentThread().getName()+" Thread completed...");
	}
	public void m1(){
		t1.yield();
	}
	volatile boolean flag=true;
	volatile int cnt=0;
	public void run(){
			
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i=0;i<=5;i++){
				System.out.println(Thread.currentThread().getName()+" : produceing No : "+i);
			}
			if(Thread.currentThread().getName().equals("t2")){
				try {
					TimeUnit.SECONDS.sleep(3);
					m1();
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				/*if(Thread.currentThread().getName().equals("t2")){
					flag=false;
					System.out.println(Thread.currentThread().getName()+" : Thread Killed....");
				}
			*/
			
		}
	

}

