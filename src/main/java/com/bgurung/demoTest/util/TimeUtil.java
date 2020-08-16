package com.bgurung.demoTest.util;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimeUtil extends TimerTask{

	@Override
    public void run() {
		// Time Started
        System.out.println("Timer task started at:"+new Date());
        completeTask();
        // when complete
        System.out.println("Timer task finished at:"+new Date());
    }
	public void run(int timeForExam) {
		// Time Started
		System.out.println(timeForExam);
        System.out.println("Timer task started at:"+new Date());
        completeTask();
        // when complete
        System.out.println("Timer task finished at:"+new Date());
    }

    private void completeTask() {
        try {
            //assuming it takes 20 secs to complete the task
            Thread.sleep((long) 20 * 1000);
        	//Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
