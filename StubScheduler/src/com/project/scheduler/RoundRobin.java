package com.project.scheduler;

import java.util.List;

public class RoundRobin extends Strategy {
	
	  
	  int count = 0;
	  int maxBurstTime = 0;
	  int temp[][] = new int[20][20];
	  

	  public RoundRobin(List<Job> jobs) {
		super(jobs);
	}
	  
	@Override
	public void run() {

		}
	
	public void run(List<Job> jobList, int quantum)
	{

			System.out.println("Burst Time - quantum");
			System.out.println("----------------------");

			
			for(Job job:jobList)
			{
					temp[count][0] = job.getProcessId();
					temp[count][1] = job.getArrivalTime();
					temp[count][2] = job.getBurstTime();
					
					if(count > 1)
					{
						if(temp[count][2] > this.maxBurstTime)
						{
							this.maxBurstTime = temp[count][2];
						}
					}
				count++;
			}
			
			System.out.println("biggest burst time  " + this.maxBurstTime);
			
			
			while(this.maxBurstTime > 0)
			{
				for(int i=0; i<count; i++)
				{
						temp[i][2] -=  quantum;
						if(this.maxBurstTime > quantum)
						{
							this.maxBurstTime -= quantum;
							System.out.println(" | process id " + temp[i][0] + " |" + " burst time " + temp[i][2] + "  |");
						}
				}
			}
			

	}
			
	
	}//EOF
