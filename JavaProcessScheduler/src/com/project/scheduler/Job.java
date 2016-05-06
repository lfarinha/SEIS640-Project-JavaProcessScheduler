package com.project.scheduler;

public class Job {
	 	private int id, submitTime, CPUTime, CPUTimeLeft, priority;
	    
	    private int startTime = 0, endTime = 0;
	    
	    
	    public int ProcessCompletionTime;
	    public int processArrivalTime;
	    public int waitingTime;
	    public int turnAroundTime;
	    private JobFinishEvent evt;
	    
	    private int arrivalTime,burstTime,processId;
	    
	    public Job(int id, int submitTime, int burstTime, int priority, JobFinishEvent evt) {
	        super();
	        this.id = id;
	        this.submitTime = submitTime;
	        this.burstTime = burstTime;
	        this.CPUTimeLeft = burstTime;
	        this.priority = priority;//new
	        this.evt = evt;
	    }
	    
	    public Job(int processId, int arrivalTime, int burstTime, int priority) {
	        
	        this.processId = processId;
	        this.arrivalTime = arrivalTime;
	        this.burstTime = burstTime;
	        this.priority = priority;//new
	    }
	    
	    public void start(int sysTime) {
	        startTime = sysTime;
	    }
	    
	    public void tick(int sysTime) {
	        CPUTimeLeft --;
	        if (CPUTimeLeft <= 0){
	            endTime = sysTime;
	            evt.onFinish(this);
	        }
	        
	    }
	    
	    public int getId() {
	        return id;
	    }
	    
	    public void setId(int id) {
	        this.id = id;
	    }
	    
	    public int getSubmitTime() {
	        return submitTime;
	    }
	    
	    public void setSubmitTime(int submitTime) {
	        this.submitTime = submitTime;
	    }
	    
	    public int getBurstTime() {
	        return burstTime;
	    }
	    
	    public void setBurstTime(int burstTime) {
	    	this.burstTime = burstTime;
	    }
	    
	    public int getPriority() /* new */
	    {
	    	return priority;
	    }
	    
	    public void setPriority(int priority)
	    {
	    	this.priority = priority;
	    } /* new */
	    
	    public int getCPUTimeLeft() {
	        return CPUTimeLeft;
	    }
	    
	    public void setCPUTimeLeft(int cPUTimeLeft) {
	        CPUTimeLeft = cPUTimeLeft;
	    }
	    
	    public int getStartTime() {
	        return startTime;
	    }
	    
	    public void setStartTime(int startTime) {
	        this.startTime = startTime;
	    }
	    
	    public int getEndTime() {
	        return endTime;
	    }
	    
	    public void setEndTime(int endTime) {
	        this.endTime = endTime;
	    }
	    
	    public int getArrivalTime() {
	        return arrivalTime;
	    }
	    
	    public void setArrivalTime(int arrivalTime) {
	        this.arrivalTime = arrivalTime;
	    }
	    
	    public int getCpuTime() {
	        return burstTime;
	    }
	    
	    public void setCpuTime(int cpuTime) {
	        this.burstTime = cpuTime;
	    }
	    
	    public int getProcessId() {
	        return processId;
	    }
	    
	    public void setProcessId(int processId) {
	        this.processId = processId;
	    }
}
