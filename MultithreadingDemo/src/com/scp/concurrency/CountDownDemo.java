package com.scp.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownDemo {
	public static void main(String[] args) {
		CountDownLatch cnt=new CountDownLatch(2);
		System.out.println("No of count (before) : "+cnt.getCount());
		DevTeam r1=new DevTeam(cnt);
		
		QATeam1 r2=new QATeam1(cnt);
		Thread t1=new Thread(r1);
		Thread t2=new Thread(r1);
		Thread t3=new Thread(r2);
		
		t1.start();
		t2.start();
		
		
		
		
		try {
			cnt.await();
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t3.start();
		//System.out.println("No of count (after)  : "+cnt.getCount());
		System.out.println(Thread.currentThread().getName()+" : thread is completed....");
	}
}

class DevTeam implements Runnable{
	CountDownLatch cntDownLatch=null;
	
	public DevTeam(CountDownLatch cntDownLatch) {
		super();
		this.cntDownLatch = cntDownLatch;
	}

	public DevTeam() {
		super();
		// TODO Auto-generated constructor stub
	}
	/*public static void disp(){
		System.out.println(Thread.currentThread().getName()+" Count : "+new DevTeam().cntDownLatch);
	}*/

	@Override
	public void run() {
		for(int i=0;i<10;i++)
			System.out.println(i);
		
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cntDownLatch.countDown();
		System.out.println(Thread.currentThread().getName()+" Count : "+cntDownLatch.getCount());
		//disp();
		System.out.println(Thread.currentThread().getName()+" : exectution is completed...");
	}
	
}

class QATeam1 implements Runnable{
	CountDownLatch cntDownLatch=null;

	public QATeam1(CountDownLatch cntDownLatch) {
		super();
		this.cntDownLatch = cntDownLatch;
	}

	@Override
	public void run() {
		for(int i=100;i<110;i++)
			System.out.println(i);
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cntDownLatch.countDown();
		System.out.println(Thread.currentThread().getName()+" : execution is completed...");
		System.out.println(Thread.currentThread().getName()+" : Count : "+cntDownLatch.getCount());
		
	}
	
}