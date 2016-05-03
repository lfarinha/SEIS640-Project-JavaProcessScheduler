package com.project.scheduler;

import java.util.List;

public class SJF extends Strategy {
	
	//2 dimensional arrays
	  private int process[][], temp[][], ready[][];
	  
	  private int cpuTime = 0, process_id_cache, arrival_Time_cache, burst_time_cache, wait_time_cache, turnaround_time_cache, start_time_cache, finish_time_cache; 

	//int variables
	  private int count = 0, totalWtTime=0, totalTrnTime=0;
	 	 
	  //boolean variables
	  private boolean isFirstProcess = false;
	  
	  public SJF(List<Job> jobs) {
		super(jobs);
	}
	  
	@Override
	public void run() {

		}
	
	public void run(List<Job> jobList, int quantum, int processCount)
	{			
			process = new int[processCount][7];
			temp = new int[processCount][7];
			ready = new int[processCount][7];
			
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
			
			this.reverseProcess(temp);
			
			for(int i=0;i<temp.length;i++)
			{
				for(int j=0;j<temp.length;j++)
				{

					if(temp[j][1]<cpuTime && temp[j][2]>0)
					{
						ready[j][0] = temp[j][0];
						ready[j][1] =temp[j][1];
						ready[j][2] = temp[j][2];
						cpuTime+=temp[j][2];
						temp[j][2]=0;
						System.out.println("< | Excecute process "+ready[j][0]+" | "+ready[j][1]+"  |  "+ready[j][2]+" cputime "+cpuTime);
				  }else if(temp[j][1]==cpuTime && temp[j][2]>0)
					{
						ready[j][0] = temp[j][0];
						ready[j][1] =temp[j][1];
						ready[j][2] = temp[j][2];
						cpuTime+=temp[j][2];
						temp[j][2]=0;
						System.out.println("AT == CPUTIME | Excecute process "+ready[j][0]+" | "+ready[j][1]+"  |  "+ready[j][2]+" cputime "+cpuTime);
				  }
				}
				
			}
						
		}// EOMethod
	
	public void executeProcess(int[][] temp, int i)
	{
			if(this.isFirstProcess == true)
			{
				temp[i][3] = temp[i][1]; //start time of the process
//				System.out.println("Start time "+temp[i][3]);
				
				cpuTime += temp[i][2]+temp[i][3]; // cpu time
				temp[i][4] = cpuTime;
//				System.out.println("cpu time consumed: "+cpuTime);

				
//				System.out.println("Process: "+temp[i][0]+" Finished");
//				System.out.println("=====================================");
				this.isFirstProcess = false;
			}else
			{
				temp[i][3] = temp[i-1][4]; //start time of the process
//				System.out.println("Start time "+temp[i][3]);
				
				cpuTime += temp[i][2]; // cpu time
				temp[i][4] = cpuTime; // FINISH TIME
//				System.out.println("cpu time consumed: "+cpuTime);
				
				
//				System.out.println("Process: "+temp[i][0]+" Finished");
//				System.out.println("=====================================");
			}
	}
	
	public void executeNextProcess(int[][] temp, int i)
	{
				temp[i+1][3] = temp[i-1][4]; //start time of the process
//				System.out.println("Start time "+temp[i+1][3]);
				
				cpuTime += temp[i+1][2]; // cpu time
				temp[i][4] = cpuTime;

				//				System.out.println("Process: "+temp[i+1][0]+" Finished");
//				System.out.println("=====================================");
	}
		
	public void reverseProcess(int[][] temp)
	{
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
	}
	
	public void arrangeProcess(int[][] temp)
	{
		for(int i=0; i<temp.length; i++)
		{
			for(int j=0; j<temp.length-1; j++)
			{
				if(temp[j+1][0]<temp[j][0])
				{
					this.process_id_cache = temp[j+1][0];
					this.arrival_Time_cache = temp[j+1][1];
					this.burst_time_cache = temp[j+1][2];
					temp[j+1][0] = temp[j][0];
					temp[j+1][1] = temp[j][1];
					temp[j+1][2] = temp[j][2];
					temp[j][0] = this.process_id_cache;
					temp[j][1] = this.arrival_Time_cache;
					temp[j][2] = this.burst_time_cache;
				}
			}
		}
		for(int i=0; i<temp.length; i++)
		{
			for(int j=0; j<temp.length-1; j++)
			{
				if(temp[j+1][0]<temp[j][0])
				{
					this.process_id_cache = temp[j+1][0];
					this.arrival_Time_cache = temp[j+1][1];
					this.burst_time_cache = temp[j+1][2];
					this.start_time_cache = temp[j+1][3];
					this.finish_time_cache = temp[j+1][4];
					this.turnaround_time_cache = temp[j+1][5];
					this.wait_time_cache = temp[j+1][6];
					temp[j+1][0] = temp[j][0];
					temp[j+1][1] = temp[j][1];
					temp[j+1][2] = temp[j][2];
					temp[j+1][3] = temp[j][3];
					temp[j+1][4] = temp[j][4];
					temp[j+1][5] = temp[j][5];
					temp[j+1][6] = temp[j][6];
					temp[j][0] = this.process_id_cache;
					temp[j][1] = this.arrival_Time_cache;
					temp[j][2] = this.burst_time_cache;
					temp[j][3] = this.start_time_cache;
					temp[j][4] = this.finish_time_cache;
					temp[j][5] = this.turnaround_time_cache;
					temp[j][6] = this.wait_time_cache;
				}
				}
			System.out.println(temp[i][0]+" | "+temp[i][2]+" | "+temp[i][3]+" | "+temp[i][4]+" | "+temp[i][5]+" | "+temp[i][6]);
			}
	}
	
	public void print()
	{
		this.arrangeProcess(temp);
		
		/*
		 * temp[*][0] == PROCESS ID
		 * temp[*][1] == ARRIVAL TIME
		 * temp[*][2] == BURST TIME
		 * temp[*][3] == START TIME
		 * temp[*][4] == FINISH TIME
		 * temp[*][5] == TURNAROUND TIME
		 * temp[*][6] == WAIT TIME
		*/
		for(int i=0; i<process.length; i++)
		{
			temp[i][6] =  temp[i][3] - temp[i][1];
			this.totalWtTime += temp[i][6];
			temp[i][5] = process[i][2] + temp[i][6];
			this.totalTrnTime += temp[i][5];

		}
				
		double throughput = (this.process.length/this.cpuTime)*1000;
				
		System.out.println("\nTotal Run Time: "+this.cpuTime);
		System.out.println("Average Waiting Time: " +(this.totalWtTime/process.length));
		System.out.println("Average Turnaround Time: " + (this.totalTrnTime/process.length));
		System.out.println("Throughput: " + throughput+" sec\n");
		
		System.out.println("|PID||Arrival Time||Start Time||Finish Time||Wait Time||Turnaround Time|");
		for(int i=0; i<process.length; i++)
		{
		System.out.println("  "+this.temp[i][0]+" \t   "+this.temp[i][1]+"\t        "+this.temp[i][3]+"\t     "+this.temp[i][4]+"\t        "+this.temp[i][6]+"\t      "+this.temp[i][5]);
		System.out.println("------------------------------------------------------------------------------------------");
		}
		
		
	}
	
	}//EOF
