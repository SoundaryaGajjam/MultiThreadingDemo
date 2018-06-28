package com.scp.concurrency;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {
	public static void main(String[] args) {
		Exchanger xchg=new Exchanger();
		MyThread t1=new MyThread(xchg, "Pune");
		MyThread t2=new MyThread(xchg, "Mumbai");
		
		t1.start();
		t2.start();
	}

}
class MyThread extends Thread{
	Exchanger xchg=null;
	String address;
	public MyThread(Exchanger xchg, String address) {
		super();
		this.xchg = xchg;
		this.address = address;
	}
	public void run(){
		System.out.println("......Before exchange....");
		System.out.println(address+" is "+Thread.currentThread().getName()+" address");
		
		try {
			this.address=(String) xchg.exchange(this.address);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("............After exchange.........");
		System.out.println(address+" is"+Thread.currentThread().getName()+ " Address");
	}
}
