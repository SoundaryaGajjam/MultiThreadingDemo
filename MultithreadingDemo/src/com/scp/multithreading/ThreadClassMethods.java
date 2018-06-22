package com.scp.multithreading;

public class ThreadClassMethods {
	static P t1;
	public static void main(String[] args) throws InterruptedException {
		System.out.println(Thread.currentThread().getThreadGroup());
		P.mt=Thread.currentThread();
		System.out.println(".................."+Thread.currentThread().getName());
		long id=P.mt.getId();
		System.out.println("Main thread Id : "+id);
		t1=new P();
		t1.setName("t1");
		/*t1.setDaemon(true);
		System.out.println("...........Before Start...........");
		System.out.println("Daemon : "+t1.isDaemon());
		System.out.println("State T1 : "+t1.getState());
		System.out.println("isAlive : "+t1.isAlive());
		System.out.println("Main thread State  : "+P.mt.getState());
		System.out.println("Main thread isAlive : "+P.mt.isAlive());*/
		t1.start();
		System.out.println("T1 Id : "+t1.getId());
		//t1.setDaemon(true); ------throws IllegalThreadStateException.....after starting thread....if we enable setDeamon(true) it will no impact
		//System.out.println(".........After start..........");
		
		/*t1.setName("t2");
		System.out.println("Daemon : "+t1.isDaemon());
		System.out.println("State T1 : "+t1.getState());
		System.out.println("isAlive : "+t1.isAlive());
		System.out.println("Main thread State  : "+P.mt.getState());
		System.out.println("Main thread isAlive : "+P.mt.isAlive());
		//t1.interrupt();
		System.out.println("Interrupeted : "+t1.isInterrupted());
		t1.setName("T333");*/
		for(int i=0;i<=10;i++)
			System.out.println("inside main thread" +i);
	}
	
}
class P extends Thread {
	static Thread mt;
	public long getId(){
		return super.getId()*10;
	}
	public void run(){
		Thread t11=new Thread();
		System.out.println("Child Thread : isDeamon : "+t11.isDaemon());
		System.out.println("................: "+Thread.currentThread().getName());
		try {
			
			mt.join();
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
       for(int i=0;i<10;i++)
       {
		System.out.println("Inside child thread" +i);
       }
	}
}
