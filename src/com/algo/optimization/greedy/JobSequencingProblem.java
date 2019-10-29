package com.algo.optimization.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author kkv4all
 * @description
 * 	Algorithm: Optimization Problem -> Greedy Method -> Job Sequencing Problem(with Deadline)
 * 		Given a list of Jobs where every job has a deadline and associated profit if the job is 
 * 		finished before the deadline. 
 * 		Our goal is to maximize total profit if only one job can be scheduled at a time.
 *  
 *  Problem Definition:
 *  	A processor has to perform set of jobs where each job has a defined deadline and some profit 
 *  associated with it. The profit of that job is given only when that job is completed within its deadline. 
 *  Only one processor is available for processing all jobs. Processor takes one unit of time to complete a job. 
 *  All the jobs have to be completed within their respective deadlines to obtain the profits associated with them. 
 *	So,
 *	Given is set of Jobs of with deadline and profit on completion.
 *	Criteria is Processor completes a Job in one Unit of time.
 *	Objective is to schedule Jobs completing within deadline by getting maximum profit.
 */

public class JobSequencingProblem {

	public static void main(String[] args) {
		List<Job> jobsToDo = Arrays.asList(
			new Job(1,2,60),new Job(2,1,100),new Job(3,3,20),
			new Job(4,2,40),new Job(5,1,20)
			);
		JobSequencingProblem jobSequencingProblem = new JobSequencingProblem();
		List<Job> jobsInSequence = jobSequencingProblem.greedySolution(jobsToDo);
		jobsInSequence.forEach(System.out::println);
	}

	public List<Job> greedySolution(List<Job> jobsToDo) {
		int maxDeadline = jobsToDo.stream()
				.map(job->job.getDeadline())
				.max(Comparator.comparingInt(deadline -> deadline)).orElse(0);
		Job[] jobsInSequence = new Job[maxDeadline];
		
		jobsToDo.sort(Comparator.comparing(Job::getProfit).reversed());
		
		int jobAdded = 0;
		for(int i=0; i < jobsToDo.size() && jobAdded<maxDeadline;i++) {
			Job job = jobsToDo.get(i);
			for(int j=job.getDeadline()-1;j>=0;j--) {
				if(jobsInSequence[j]==null) {
					jobsInSequence[j] = job;
					jobAdded++;
					break;
				}
			}
		}
		return Arrays.asList(jobsInSequence);
	}
}

class Job {
	int id;
	int deadline;
	int profit;

	public Job(int id, int deadline, int profit) {
		this.id = id;
		this.deadline = deadline;
		this.profit = profit;
	}

	public int getDeadline() {
		return deadline;
	}
	public int getProfit() {
		return profit;
	}

	@Override
	public String toString() {
		return "{id:" + id + ", deadline:" + deadline + ", profit:" + profit + "}";
	}
}