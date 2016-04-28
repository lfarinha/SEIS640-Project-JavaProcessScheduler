package com.project.scheduler;

import java.util.List;

public class RoundRobin extends Strategy {
	
	//2 dimensional arrays
	  private int process[][], temp[][], notComplete[][], complete[][];

	//int variables
	  private int processCount, flag, count = 0, avgWtTime=0, avgTrndTm=0;
	 
	  public RoundRobin(List<Job> jobs) {
		super(jobs);
	}
	  
	@Override
	public void run() {

		}
	
	public void run(List<Job> jobList, int quantum, int processCount)
	{
			this.processCount = processCount;
			
			process = new int[processCount][3];
			temp = new int[processCount][3];
			notComplete = new int[processCount][3];
			complete = new int[processCount][3];
			
			for(Job job:jobList)
			{				
					process[count][0] = job.getProcessId();
					process[count][1] = job.getArrivalTime();
					process[count][2] = job.getBurstTime();
				
					temp[count][0] = job.getProcessId();
					temp[count][1] = job.getArrivalTime();
					temp[count][2] = job.getBurstTime();
					
					notComplete[count][0] = job.getProcessId();
					notComplete[count][1] = job.getArrivalTime();
					
					complete[count][0] = job.getProcessId();
					complete[count][1] = job.getArrivalTime();
					
				count++;
			}
			
			do{
				flag=0;
				for(int i=0; i<temp.length; i++)
				{
					if(temp[i][2]>=quantum)
					{
						for(int j=0; j<temp.length; j++)
						{
							if(j==i)
							{
								temp[i][2] = temp[i][2] - quantum;
							}else if(temp[j][2]>0)
							{
								notComplete[j][2] += quantum;
							}
						}
					}else if(temp[i][2]>0)
					{
						for(int k=0; k<temp.length; k++)
						{
							if(k==i)
							{
								temp[i][2] = 0;
							}else if(temp[k][2]>0)
							{
								notComplete[k][2] += temp[i][2];
							}
						}
					}
				}
				for(int i=0; i<temp.length; i++)
				{
					if(temp[i][2]>0)
					{
						flag=1;
					}
				}
			}while(flag==1);
			for(int i=0; i<process.length; i++)
			{
				complete[i][2] += notComplete[i][2] + process[i][2];;
			}
	}
	
	public void print()
	{
			System.out.println("\nProcess ID\tArrivalTime\tBurstTime\tWaitingTime\tTurnAround");
			for(int i=0; i<process.length; i++)
			{
				System.out.println("Process "+process[i][0]+"\t"+process[i][1]+"\t\t"+process[i][2]+"\t\t"+notComplete[i][2]+"\t\t"+complete[i][2]);
				this.avgWtTime+=notComplete[i][2];
				this.avgTrndTm+=complete[i][2];
			}
			System.out.println("=============================================");
			System.out.println("Average Waiting Time: " + (this.avgWtTime/process.length));
			System.out.println("Average Turnaround Time: " + (this.avgTrndTm/process.length));
			System.out.println("=============================================");
	}
	
	}//EOF
