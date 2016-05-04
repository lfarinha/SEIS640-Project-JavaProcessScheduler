package com.project.scheduler;

import java.util.List;

public class FCFS extends Strategy {
	
	//2 dimensional arrays
	  private int process[][], ready[][];
	  
	  private int cpuTime = 0; 

	//int variables
	  private int count = 0, totalWtTime=0, totalTrnTime=0;
	 	 
	  //double
	  double throughput=0;
	  
	  public FCFS(List<Job> jobs) {
		super(jobs);
	}
	  
	@Override
	public void run() {

		}
	
	public void run(List<Job> jobList, int processCount)
	{			
			process = new int[processCount][7];
			ready = new int[processCount][7];
			
			for(Job job:jobList)
			{				
					process[count][0] = job.getProcessId();
					process[count][1] = job.getArrivalTime();
					process[count][2] = job.getBurstTime();
					
				count++;
			}
						
			for(int i=0;i<process.length;i++)
			{
				for(int j=0;j<process.length;j++)
				{
					if(process[i][1]<process[j][1] && process[i][2]>0)
					{
						process[i][3] = cpuTime; // process Start Time
						cpuTime += process[i][2]; // cpu time consumed
						process[i][4] = cpuTime; // process Finish Time
						process[i][5] = process[i][3] - process[i][1]; // process Wait Time
						process[i][6] = process[i][2] + process[i][5]; // process Turnaround Time
						this.finishProcess(process);
						process[i][2] = 0; // process executed
					}else if(process[i][2]>0 && (process.length-i==1))
					{
						process[i][3] = cpuTime; // process Start Time
						cpuTime += process[i][2]; // cpu time consumed
						process[i][4] = cpuTime; // process Finish Time
						process[i][5] = process[i][3] - process[i][1]; // process Wait Time
						process[i][6] = process[i][2] + process[i][5]; // process Turnaround Time
						this.finishProcess(process);
						process[i][2] = 0; // process executed
					}else if(process[i][1]==process[j][1] && process[i][2]>0)
					{
						process[i][3] = cpuTime; // process Start Time
						cpuTime += process[i][2]; // cpu time consumed
						process[i][4] = cpuTime; // process Finish Time
						process[i][5] = process[i][3] - process[i][1]; // process Wait Time
						process[i][6] = process[i][2] + process[i][5]; // process Turnaround Time
						this.finishProcess(process);
						process[i][2] = 0; // process executed
					}
					
				}
			}
						
		}// EOMethod
	
	public void finishProcess(int[][] process)
	{
		for(int i=0;i<process.length;i++)
		{
			ready[i][0] = process[i][0];
			ready[i][1] = process[i][1];
			ready[i][2] = process[i][2];
			ready[i][3] = process[i][3];
			ready[i][4] = process[i][4];
			ready[i][5] = process[i][5];
			ready[i][6] = process[i][6];
		}
	}
	
	
	public void print()
	{
		
		/*
		 * temp[*][0] == PROCESS ID
		 * temp[*][1] == ARRIVAL TIME
		 * temp[*][2] == BURST TIME
		 * temp[*][3] == START TIME
		 * temp[*][4] == FINISH TIME
		 * temp[*][5] == WAIT TIME
		 * temp[*][6] == TURNAROUND TIME
		*/
		for(int i=0; i<process.length; i++)
		{
			this.totalWtTime += process[i][5];
			this.totalTrnTime += process[i][6];
		}
		
		double processCount = this.process.length;
		throughput = (double) ((processCount/cpuTime)*1000.0);
				
		System.out.println("\nTotal Run Time: "+this.cpuTime);
		System.out.println("Average Waiting Time: " +(this.totalWtTime/process.length));
		System.out.println("Average Turnaround Time: " + (this.totalTrnTime/process.length));
		System.out.println("Throughput: " + throughput+" sec\n");
		
		System.out.println("|PID||Arrival Time||Start Time||Finish Time||Wait Time||Turnaround Time|");
		for(int i=0; i<process.length; i++)
		{
		System.out.println("  "+this.ready[i][0]+" \t   "+this.ready[i][1]+"\t        "+this.ready[i][3]+"\t     "+this.ready[i][4]+"\t        "+this.ready[i][5]+"\t      "+this.ready[i][6]);
		System.out.println("------------------------------------------------------------------------");
		}
		
		
	}
	
	}//EOF
