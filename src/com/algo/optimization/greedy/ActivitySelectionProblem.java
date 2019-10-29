package com.algo.optimization.greedy;

/**
 * @author kkv4all
 * @description
 * 	Algorithm: Optimization Problem -> Greedy Method -> Activity Selection Problem(Scheduling Problem)
 *  	It is a problem of scheduling several activity to  to Common Resource.
 *  	Our goal is to select the maximum size set of mutually compatible activity
 *  
 *  Problem Definition:
 *  	There is a College auditorium where programmes can be held. During college fest there are multiple 
 *  requests come to book the auditorium for programmes. The auditorium manager has to select the maximum 
 *  programmes can be held in auditorium without any clashes. So that the programmes those can be held can 
 *  look for alternative arrangement.
 *	So,
 *	Given is list of request of programmes with start time and end time.
 *	Criteria is that no programme should clash with each other.
 *	Objective is to select maximum number of programmes can be held.
 */

public class ActivitySelectionProblem {

	public static void main(String[] args) {
 
	}
}

class Activity {
	int id;
	int startTime;
	int endTime;
	
	public Activity(int id, int startTime, int endTime) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int getId() {
		return id;
	}
	public int getStartTime() {
		return startTime;
	}
	public int getEndTime() {
		return endTime;
	}
	
	@Override
	public String toString() {
		return "{id:" + id + ", startTime:" + startTime + ", endTime:" + endTime + "}";
	}
}
