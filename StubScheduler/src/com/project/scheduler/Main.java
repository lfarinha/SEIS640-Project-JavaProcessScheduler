package com.project.scheduler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        
        
        String filename ;
        String allocationStrategy;
        int quantum=4;
       int processCount=0;
        
        filename = "example_2.csv";
        allocationStrategy = "RR";
        
        if(args.length==3){
            quantum = new Integer(args[2]);
        }
        
        BufferedReader br = null;
        
        try {
            
            String sCurrentLine;
            
            br = new BufferedReader(new FileReader("C://"+filename));
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
                firstComeFirstServed.run(jobList);
                
            	}else if("RR".equalsIgnoreCase(allocationStrategy)){
                    
                    RoundRobin roundRobin = new RoundRobin(jobList);
                    roundRobin.run(jobList,quantum, processCount);
                    roundRobin.print();
                    
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
