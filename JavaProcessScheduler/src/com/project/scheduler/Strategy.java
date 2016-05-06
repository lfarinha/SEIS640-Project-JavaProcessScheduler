package com.project.scheduler;
/*
 *  THIS CODE IS PROPERTY OF http://javahungry.blogspot.com/2013/09/round-robin-scheduling-algorithm-with-example-java-program-code.html
 */
import java.util.ArrayList;
import java.util.List;

public abstract class Strategy {
	protected List<Job> Jobs;
    protected ArrayList<Job> Queue;
    
    public Strategy(List<Job> jobs) {
        super();
        Jobs = jobs;
    }
    
    public abstract void run();
    // update current job by 1 tick
    // check if the job queue might need to be changed.
    // check for jobs to add to the queue
}
