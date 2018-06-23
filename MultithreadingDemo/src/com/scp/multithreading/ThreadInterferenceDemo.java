package com.scp.multithreading;

public class ThreadInterferenceDemo extends Thread {
	public static void main(String[] args) {
		Shared s1=new Shared();
		
		Thread t1=new Thread(){
			public void run(){
				System.out.println(Thread.currentThread().getName());
				s1.m1();
			}
		};
		
		
		Thread t2=new Thread(){
			public void run(){
				System.out.println(Thread.currentThread().getName());
				s1.m1();
			}
		};
		
		t1.start();
		t2.start();
		
		
		
	}
}
class Shared{
	//volatile int i;
	int i;
	public synchronized void m1(){
		i++;
		System.out.println(i);
		i++;
		System.out.println(i);
		i++;
		System.out.println(i);
	}
	
}
/*thread interference ----same variable shared between 2 threads
 * --- arises data inconsistency problem
 * ---no thread safe
 * 
 * ways of avoid thread interference in java
 * ---declared variable as final
 * ---declared variable as voliatle
 * ---declared method as synchronized
 * ----creating immutable objects
 * ----
 * 
 * 
 */


