package com.scp.multithreading;

import java.util.concurrent.TimeUnit;

public class KillThreadUsingInterrupt {
	public static void main(String[] args) throws InterruptedException {
		
		kill t1=new kill();
		t1.start();
		TimeUnit.SECONDS.sleep(5);
		t1.interrupt();
		System.out.println(Thread.currentThread().getName()+" Thread completed....");
	}
}
class kill extends Thread{
	public void run(){
		while(!(Thread.interrupted()))
			System.out.println("I m running....");
		
		System.out.println("I m stopping...");
	}
}