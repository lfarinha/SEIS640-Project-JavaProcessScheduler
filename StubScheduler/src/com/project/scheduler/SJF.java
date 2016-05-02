package com.project.scheduler;

import java.util.List;

public class SJF extends Strategy {
	
	//2 dimensional arrays
	  private int process[][], temp[][];
	  
	  private int cpuTime = 0, process_id_cache, arrival_Time_cache, burst_time_cache; 

	//int variables
	  private int count = 0, avgWtTime=0, avgTrndTm=0;
	 	 
	  
	  public SJF(List<Job> jobs) {
		super(jobs);
	}
	  
	@Override
	public void run() {

		}
	
	public void run(List<Job> jobList, int quantum, int processCount)
	{			
			process = new int[processCount][3];
			temp = new int[processCount][5];
			
			for(Job job:jobList)
			{				
					process[count][0] = job.getProcessId();
					process[count][1] = job.getArrivalTime();
					process[count][2] = job.getBurstTime();
				
					temp[count][0] = job.getProcessId();
					temp[count][1] = job.getArrivalTime();
					temp[count][2] = job.getBurstTime();
					
				count++;
			}
			
				
			for(int i=0; i<temp.length; i++)
			{
				for(int j=0; j<temp.length-1; j++)
				{
					if(temp[j+1][2]<temp[j][2])
					{
						this.process_id_cache = temp[j][0];
						this.arrival_Time_cache = temp[j][1];
						this.burst_time_cache = temp[j][2];
						temp[j][0] = temp[j+1][0];
						temp[j][1] = temp[j+1][1];
						temp[j][2] = temp[j+1][2];
						temp[j+1][0] = this.process_id_cache;
						temp[j+1][1] = this.arrival_Time_cache;
						temp[j+1][2] = this.burst_time_cache;
					}
				}
			}
			
			for(int i=0; i<temp.length; i++)
			{
				System.out.println("Process: "+temp[i][0]);
				System.out.println("=====================================");
				
				if(i == 0)
				{
					if(temp[i][2]==temp[i+1][2])
					{
						if(temp[i][1]<temp[i+1][1])
						{
							this.executeProcess(temp, i);		
						}else if(temp[i][1]>temp[i+1][1])
						{
							this.executeNextProcess(temp, i);	
						}else if(temp[i][1]==temp[i+1][1])
						{
							this.executeProcess(temp, i);
						}
					}else
					{
						this.executeProcess(temp, i);
					}
				}else if(i == temp.length-1)
				{
					this.executeProcess(temp, i);	
				}else if(i < temp.length)
				{
					if(temp[i][2]==temp[i+1][2])
					{
						if(temp[i][1]<temp[i+1][1])
						{
							this.executeProcess(temp, i);	
						}else if(temp[i][1]>temp[i+1][1])
						{
							this.executeNextProcess(temp, i);
						}else if(temp[i][1]==temp[i+1][1])
						{
							this.executeProcess(temp, i);
						}
					}else
					{
						this.executeProcess(temp, i);
					}
				}
			}
	}// EOMethod
	
	public void executeProcess(int[][] temp, int i)
	{
				temp[i][3] = cpuTime; //start time of the process
				System.out.println("Start time "+temp[i][3]);
				
				cpuTime += temp[i][2]; // cpu time
				System.out.println("cpu time consumed: "+cpuTime);
				
				temp[i][4] =  temp[i][3] + temp[i][2];
				System.out.println("Finish time: "+temp[i][4]);
				
				temp[i][2] = 0;
				System.out.println("Process: "+temp[i][0]+" Finished");
				System.out.println("=====================================");	
	}
	
	public void executeNextProcess(int[][] temp, int i)
	{
				temp[i+1][3] = cpuTime; //start time of the process
				System.out.println("Start time "+temp[i+1][3]);
				
				cpuTime += temp[i+1][2]; // cpu time
				System.out.println("cpu time consumed: "+cpuTime);
				
				temp[i+1][4] =  temp[i+1][3] + temp[i+1][2];
				System.out.println("Finish time: "+temp[i+1][4]);
				
				temp[i+1][2] = 0;
				System.out.println("Process: "+temp[i+1][0]+" Finished");
				System.out.println("=====================================");	
	}
	
	public void print()
	{
		for(int i=0; i<process.length; i++)
		{
			this.avgWtTime += temp[i][3] - temp[i][1];
			System.out.println("Avg Wt Time"+this.avgWtTime);
			this.avgTrndTm += temp[i][2] + this.avgWtTime;
			System.out.println("Avg Trn Time"+this.avgTrndTm);
						
		}
		
//		System.out.println("=============================================");
//		System.out.println("Total Run Time: "+this.cpuTime);
//		System.out.println("Average Waiting Time: " +(this.avgWtTime/process.length));
//		System.out.println("Average Turnaround Time: " + (this.avgTrndTm/process.length));
//		System.out.println("=============================================");
		
		
	}
	
	}//EOF
