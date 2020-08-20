package com.bgurung.demoTest.util;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import com.bgurung.demoTest.model.Calculate;

public class TimeUtil {
    private Timer timer;   
    private Calculate calculate;
    public TimeUtil(Integer seconds) {
    	timer = new Timer();
        long msec = 1000;
        //timer.schedule(new MyTask(), seconds.intValue()*msec);
        timer.schedule(new MyTask(), 10*msec);
    }
    public class MyTask extends TimerTask {
    	@Override
        public void run() {
            // task to do
    		calculate = new Calculate();
    		System.out.println("My task run");
    		System.out.println("test");
    		calculate.marks();
    		completeTask();
            System.out.println("Timer task finished at:"+new Date());
            
 
        }
    	private void completeTask() {
            try {
                //assuming it takes 20 secs to complete the task
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void timerStopped() {
        timer.cancel();
        //try { Thread.sleep(2000L); } catch (Exception e) {} // Use this thread sleep
    }
}