package com.scp.multithreading;

import java.util.*;
import java.util.Random;

public class ProducerConsumerDemo {
	public static void main(String[] args) throws InterruptedException {
		
		int max_size=10;
		ArrayList list=new ArrayList<>();
		
		Producer p1=new Producer(list, max_size, "Producer1");
		Consumer c1=new Consumer(list, max_size, "Consumer1");
		
		Producer p2=new Producer(list, max_size, "Producer2");
		Consumer c2=new Consumer(list, max_size, "Consumer2");
		
		p1.start();
		c1.start();
		c1.interrupt();
		p2.start();
		c2.start();
		
	}
}
class Producer extends Thread{
	ArrayList al=null;
	int max_size;
	String name;
	
	public Producer(ArrayList al, int max_size, String name) {
		super(name);
		this.al = al;
		this.max_size = max_size;
		//this.name = name;
	}

	public void run(){
		System.out.println("Inside Producer....");
		boolean flag=false;
		while(true){
			synchronized (al) {
				
				System.out.println(Thread.currentThread());
				try {
					Thread.sleep(3000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
				}
				if(al.size()==max_size){
					try {
						System.out.println("list is full....");
						al.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					}
				
				int rNo=new Random().nextInt(100);
				al.add(rNo);
				System.out.println("Produced element : "+rNo);
				
				al.notify();
				
					
		}
		}
	}
}
class Consumer extends Thread{
	ArrayList al;
	int max_size;
	String name;
	
	public Consumer(ArrayList  al, int max_size, String name) {
		super(name);
		this.al = al;
		this.max_size = max_size;
		//this.name = name;
	}

	public void run(){
		System.out.println("Inside Consumer....");
		while(true){
		synchronized (al) {
			System.out.println(Thread.currentThread());
			
			try {
				Thread.sleep(3000);
				
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
				if(al.isEmpty()){
					try {
						System.out.println("list is empty...");
						al.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
					System.out.println("Consumed element : "+al.remove(0));
					al.notify();
			}			
		}
		}
	}
