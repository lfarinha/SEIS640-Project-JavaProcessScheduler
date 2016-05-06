package com.project.scheduler;
/*
 *  THIS CODE IS PROPERTY OF http://javahungry.blogspot.com/2013/09/round-robin-scheduling-algorithm-with-example-java-program-code.html
 */
public interface JobFinishEvent {
    public void onFinish(Job j);
}
