package com.algo.optimization.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
		List<Activity> activityRequests = Arrays.asList(
				new Activity(1,"Dance Competetion",0,6),
				new Activity(2,"Quiz Competetion",1,4),
				new Activity(3,"Entrepreneurship Seminar",8,12),
				new Activity(4,"Engineering Workshop",5,7),
				new Activity(5,"Literature Conference",3,5),
				new Activity(6,"Sports Prize Distribution",8,11),
				new Activity(7,"Skill Development Seminar",3,8),
				new Activity(8,"Art Exibition ",2,13),
				new Activity(9,"Music Concert",6,10),
				new Activity(10,"Alumini Meeting",12,14)
				);
		ActivitySelectionProblem activitySelectionProblem = new ActivitySelectionProblem();
		List<Activity> activitySelected = activitySelectionProblem.greedySolution(activityRequests);
		activitySelected.forEach(System.out::println);
	}

	public List<Activity> greedySolution(List<Activity> activityRequests) {
		List<Activity> activitySelected = new ArrayList<Activity>();
		
		//Sort Activity by Acivity completeing earliest to latest (By endtime ascending order)
		activityRequests.sort(Comparator.comparing(activity -> activity.getEndTime()));
		
		//Select first activity always
		activitySelected.add(activityRequests.get(0));
		int lastActivityEndTime = activityRequests.get(0).getEndTime();
		
		//Select Activity if that starts after the last selected activity ends
		for(int i=1; i < activityRequests.size();i++) {
			Activity activity = activityRequests.get(i);
			if(activity.getStartTime()>=lastActivityEndTime) {
				activitySelected.add(activity);
				lastActivityEndTime = activity.getEndTime();
			}
		}
		
		return activitySelected;
	}
}

class Activity {
	private int id;
	private String name;
	private int startTime;
	private int endTime;

	public Activity(int id, String name, int startTime, int endTime) {
		this.id = id;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int getStartTime() {
		return startTime;
	}
	public int getEndTime() {
		return endTime;
	}

	@Override
	public String toString() {
		return "{id:" + id + ", name:" + name + ", startTime:" + startTime + ", endTime:" + endTime + "}";
	}
}
