package com.project.scheduler;

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
