package com.scp.multithreading;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class KillThreadDemo {
	public static void main(String[] args) throws InterruptedException {
		KillDemo t1=new KillDemo("t1");
		KillDemo t2=new KillDemo("t2");
		System.out.println("T1 Id : "+t1.getId());
		System.out.println("T2 ID : "+t2.getId());
		System.out.println("Main Thread Id : "+Thread.currentThread().getId());
		
		//Thread.currentThread().join();
		
		/*System.out.println("T1 Priority : "+t1.getPriority());
		System.out.println("T2 Priority : "+t2.getPriority());
		
		System.out.println("Main Thread Priority : "+Thread.currentThread().getPriority());*/
		t1.setPriority(8);
		t1.start();
		t2.start();
		t1.join();
		
		/*Thread.currentThread().setPriority(7);
		System.out.println("T1 Priority : "+t1.getPriority());
		System.out.println("T2 Priority : "+t2.getPriority());
		
		System.out.println("Main Thread Priority : "+Thread.currentThread().getPriority());
		*/
		//t2.join();
		//t1.killThreadMethod();
		
		System.out.println(Thread.currentThread().getName()+" Thread Completed..........");
		
	}

		
}
class KillDemo extends Thread{
	String name;
	volatile boolean flag=true;
	
	volatile int cnt=0;
	public KillDemo(String name) {
		super(name);
		//this.name = name;
	}
	
	public void killThreadMethod(){
		flag=false;
		System.out.println(Thread.currentThread().getName()+" : Thread Killed..... ");
		
	}
	public void run(){
		
		while(flag){
					
			System.out.println(Thread.currentThread().getName()+" : Count : "+cnt);
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+" : Generating no: "+ThreadLocalRandom.current().nextInt(100));
			
			//if(Thread.currentThread().getState().equals(getState())){
			if(Thread.currentThread().getName().equals("t1")){
				if(cnt>=10)
					killThreadMethod();
			}
			
			cnt++;
			}
		
	}
	
}
