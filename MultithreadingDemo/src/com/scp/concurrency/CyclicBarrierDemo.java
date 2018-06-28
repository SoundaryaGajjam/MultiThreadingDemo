package com.scp.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
	public static void main(String[] args) {
		CyclicBarrier cycBar=new CyclicBarrier(5, new Thread(){
			public void run(){
				System.out.println("Yes....Parties reached at this location...");
			}
			
		});//Anonymous Implementation
		
		Biker b1=new Biker(cycBar);
		Biker b2=new Biker(cycBar);
		Biker b3=new Biker(cycBar);
		Biker b4=new Biker(cycBar);
		
		Thread t1=new Thread(b1, "Biker 1");
		Thread t2=new Thread(b2, "Biker 2");
		Thread t3=new Thread(b3, "Biker 3");
		Thread t4=new Thread(b4, "Biker 4");
		Car_Riding t5=new Car_Riding(cycBar,"Car Ridding");
		t1.start();
		t2.start();
		
		t3.start();
		//t2.interrupt();
		
		t4.start();
		
		//cycBar.reset();
		t5.start();
		////t3.interrupt();
		System.out.println(Thread.currentThread().getName()+" : thread is completed...");
	}

}
/*class CheckPoint implements Runnable{

	@Override
	public void run() {
		System.out.println("Yes...Parties reached at this location ....");
		
	}
	
}*/
class Biker implements Runnable{
	CyclicBarrier cycBar=null;

	public Biker(CyclicBarrier cycBar) {
		super();
		this.cycBar = cycBar;
	}

	@Override
	public void run() {
		try {
		System.out.println(Thread.currentThread().getName()+" -  has left to Pune...");

		cycBar.await();
		System.out.println(Thread.currentThread().getName()+" -  has reached to lonavala...");
		
		cycBar.await();
		System.out.println(Thread.currentThread().getName()+" -  has reached to Panvel...");
		
		
		cycBar.await();
		System.out.println(Thread.currentThread().getName()+" -  has reached to Vashi...");
		
		cycBar.await();
		System.out.println(Thread.currentThread().getName()+" -  has reached to Dadar...");
		
		
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}	
	}
	

}
class Car_Riding extends Thread{
	CyclicBarrier cycBar=null;
	String name;

	public Car_Riding(CyclicBarrier cycBar,String name) {
		super(name);
		this.cycBar = cycBar;
	}
	
	public void run(){
		try {
			System.out.println(Thread.currentThread().getName()+" -  has left to Pune...");

			cycBar.await();
			System.out.println(Thread.currentThread().getName()+" -  has reached to lonavala...");
			 
			cycBar.await();
			System.out.println(Thread.currentThread().getName()+" -  has reached to Panvel...");
			
			cycBar.await();
			System.out.println(Thread.currentThread().getName()+" -  has reached to Vashi...");
			
			cycBar.await();
			System.out.println(Thread.currentThread().getName()+" -  has reached to Dadar...");
			
			
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}	
	}
}