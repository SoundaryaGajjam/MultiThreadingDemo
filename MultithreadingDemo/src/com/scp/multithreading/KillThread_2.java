package com.scp.multithreading;

import java.util.concurrent.TimeUnit;

public class KillThread_2 {
	static KillThread t1;
	static KillThread t2;
	public static void main(String[] args) throws InterruptedException {
		
		t1=new KillThread("t1");
		t1.start();
		
		t2=new KillThread("t2");
		t2.start();
		//t1.join();
		
		System.out.println(Thread.currentThread().getName()+" : thread completed...");
		
		
		
	}
	public static void m1() throws InterruptedException{
		t2.join();
		//t2.yield();
	}

}
class KillThread extends Thread{
	String name;
	volatile boolean flag=true;
	volatile int cnt=0;
	public KillThread(String name) {
		super(name);
	}
	public void KillMethod(){
		flag=false;
		System.out.println(Thread.currentThread().getName()+" : Thread killed....");
	}
	public void run(){
		while(flag){
			System.out.println("Count value : "+cnt);
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(int i=0;i<=5;i++){
				System.out.println(Thread.currentThread().getName()+": Gererating No : "+i);
			}
			if(Thread.currentThread().getName().equals("t1")){
				try {
					//new KillThread_2().m1();
					KillThread_2.m1();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(cnt>=5 && Thread.currentThread().getName().equals("t2")){
				/*flag=false;
				System.out.println(Thread.currentThread().getName()+" : Thread killed....");*/
				KillMethod();
			}
			
			
			cnt++;
		}
	}
}
