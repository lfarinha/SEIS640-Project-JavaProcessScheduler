package com.project.scheduler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 *  THIS CODE IS PROPERTY OF http://javahungry.blogspot.com/2013/09/round-robin-scheduling-algorithm-with-example-java-program-code.html
 */

public class Main {

	public static void main(String[] args) {

        String filename ;
        String allocationStrategy;
        int processCount=0;
        
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Enter the training data file name: ");
        String path =  scan.next();
        

        filename = path;
        allocationStrategy = "FCFS";
        
        
        BufferedReader br = null;
        
        try {
            
            String sCurrentLine;
            
            br = new BufferedReader(new FileReader("../"+filename));
            //System.out.println("processId  arrivalTime  cpuTime");
            br.readLine();
            List<Job> jobList = new ArrayList<Job>();
            while ((sCurrentLine = br.readLine()) != null) {
                
                String a[] = sCurrentLine.split(",");
                int processId = new Integer(a[0]);
                int arrivalTime = new Integer(a[1]);
                int burstTime = new Integer(a[2]);
                int priority = new Integer(a[3]);
                
                Job job = new Job(processId,arrivalTime,burstTime,priority);
                
                jobList.add(job);
                
                processCount++;
            }
            
            if("FCFS".equalsIgnoreCase(allocationStrategy)){
                
                FCFS firstComeFirstServed = new FCFS(jobList);
                firstComeFirstServed.run(jobList, processCount);
                firstComeFirstServed.print();
            	}
            
            
            } catch (IOException e) {
            e.printStackTrace();
            } finally {
            try {
                if (br != null)br.close();
                } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        JobFinishEvent callback = new JobFinishEvent() {
            @Override
            public void onFinish(Job j) {
                // this will be called when a job is finished.
            }
        };
        }
	

}
