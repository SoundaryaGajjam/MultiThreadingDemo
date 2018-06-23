package com.scp.multithreading.queue;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class PriorityBlockingQueueDemo {
	public static void main(String[] args) {
		BlockingQueue<Student> bq=new PriorityBlockingQueue<>(2,new IdBased());
		ProducerPriority t1=new ProducerPriority("Producer", bq);
		ConsumerPriority t2=new ConsumerPriority("Consumer", bq);
		t2.start();
		t1.start();
		
		System.out.println(Thread.currentThread().getName()+" : is completed...");
		
	}
}
class ProducerPriority extends Thread{
	String name;
	BlockingQueue<Student>bq;
	public ProducerPriority(String name, BlockingQueue<Student> bq) {
		super(name);
		this.bq = bq;
	}
	public void run(){
		int id=1;
		while(true){
			System.out.println(bq);
			Student st1=new Student(id, "Smith");
			
			try {
				System.out.println("\n"+Thread.currentThread().getName()+" is producing :  "+st1);
				bq.put(st1);
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			id++;
		}
		
	}
	
}
class ConsumerPriority extends Thread{
	String name;
	BlockingQueue<Student>bq;
	public ConsumerPriority(String name, BlockingQueue<Student> bq) {
		super(name);
		this.bq = bq;
	}
	public void run(){
		while(true){
		System.out.println(bq);
		try {
			Student st=bq.take();
			System.out.println("\n"+Thread.currentThread().getName()+" : is consuming : "+st);
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	}
}
class Student {
	int studId;
	String studName;
	
	public Student(int studId, String studName) {
		super();
		this.studId = studId;
		this.studName = studName;
	}
	@Override
	public String toString() {
		return "\nStudent [studId=" + studId + ", studName=" + studName + "]";
	}
	
	
}
class IdBased implements Comparator<Student>{

	@Override
	public int compare(Student o1, Student o2) {
		
		return o1.studId-o2.studId;
	}
	
}