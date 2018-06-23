package com.scp.multithreading.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class PriorityBQComparable {
	public static void main(String[] args) {
		BlockingQueue<Employee>blkQue=new PriorityBlockingQueue<>(5);
		
		PriorityProducer t1=new PriorityProducer("Producer ", blkQue);
		PriorityConsumer t2=new PriorityConsumer("Consumer", blkQue);
		
		t1.start();
		t2.start();
		System.out.println(Thread.currentThread().getName()+" : thread is completed....");
		
	}

}
class PriorityProducer extends Thread{
	String name;
	BlockingQueue<Employee>bq;
	public PriorityProducer(String name, BlockingQueue<Employee> bq) {
		super(name);
		this.bq = bq;
	}
	public void run(){
		int id=1;
		while(true){
			Employee e1=new Employee("John", id);
			System.out.println(Thread.currentThread().getName()+" : is producing : "+e1);
			System.out.println(bq.offer(e1));
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			id++;
		}
		
	}
	
}
class PriorityConsumer extends Thread{
	String name;
	BlockingQueue<Employee>bq;
	public PriorityConsumer(String name, BlockingQueue<Employee> bq) {
		super(name);
		this.bq = bq;
	}
	public void run(){
		while(true){
			Employee obj=bq.poll();
			System.out.println(Thread.currentThread().getName()+" : is consuming : "+obj);
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
class Employee implements Comparable<Employee>{
	String empName;
	int empId;
	public Employee(String empName, int empId) {
		super();
		this.empName = empName;
		this.empId = empId;
	}
	@Override
	public String toString() {
		return "\nEmployee [empName=" + empName + ", empId=" + empId + "]";
	}
	@Override
	public int compareTo(Employee o) {
		return this.empId-o.empId;
	}
	
	
}
