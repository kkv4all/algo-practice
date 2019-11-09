package com.algo.optimization.dynamicprog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author kkv4all
 * @description
 * 	Algorithm: Optimization Problem -> Dynamic Programming -> 0/1 Knapsack Problem(Using Tabulation)
 *  	Given weights (W) and values (P) of N items.
 *  	We need to put these items in a knapsack of capacity M to get the Maximum total value (Sum(Pi)) in the knapsack.
 *  	The criteria is we cannot break an item, either pick the complete item, or don’t pick it
 *  
 *  Problem Definition:
 *  	There is a Electronics Transporter who deliver electronics to customers. His Carriage Van can load certain capacity of 75kg
 *  in one trip. He need to select the items which will give him maximum profit.
 *	So,
 *	Given is list of electronics with profit and weight of each electronic item can be picked up for delivery(Kgs)
 *	Criteria is the Carriage Van can load 75kgs maximum in one trip
 *	Objective is to get maximum profit from one 
 */

public class ZeroOneKnapsackProblem {

	public static void main(String[] args) {
		List<ZeroOneKnapsackItem> items = Arrays.asList(
				new Electronic("Refrigrator", 20, 60),
				new Electronic("Microwave", 10, 80),
				new Electronic("Air Conditioner", 30, 120),
				new Electronic("Washing Machine", 25, 60),
				new Electronic("Home Theatre", 5, 30),
				new Electronic("Television", 15, 100)
				);
		ZeroOneKnapsackProblem fractionalKapsackProblem = new ZeroOneKnapsackProblem();
		int  maxBasketCapacity = 75;
		List<ZeroOneKnapsackItem> itemsTaken = fractionalKapsackProblem.greedySolution(items,maxBasketCapacity);

		if(!itemsTaken.isEmpty()) {
			System.out.println("Items Selected: ");
			itemsTaken.forEach(System.out::println);
			int maxProfit = itemsTaken.stream()
					.mapToInt(electronic -> electronic.getValue())
					.reduce(0,(sum,value)->sum+value);
			System.out.println("Max profit from one trip: "+maxProfit);
		} else {
			System.out.println("No Items Selected");
		}
	}

	public List<ZeroOneKnapsackItem> greedySolution(List<ZeroOneKnapsackItem> knapsackItems, int maxKnapsackCapacity) {
		List<ZeroOneKnapsackItem> itemsTaken = new ArrayList<ZeroOneKnapsackItem>();

		int[][] k = getKnapSackTable(new ArrayList<>(knapsackItems),maxKnapsackCapacity);

		knapsackItems.sort(Comparator.comparing(ZeroOneKnapsackItem::getValue));
		int index = knapsackItems.size();
		int capacity = maxKnapsackCapacity;
		while(index>0 && capacity>0) {
			if(k[index][capacity] == k[index-1][capacity]) {
				index --;
			} else {
				itemsTaken.add(knapsackItems.get(index-1));
				index--;
				capacity-=(knapsackItems.get(index).getWeight());
			}
		}

		return itemsTaken;
	}

	private int[][] getKnapSackTable(List<ZeroOneKnapsackItem> knapsackItems, int maxKnapsackCapacity) {
		knapsackItems.sort(Comparator.comparing(ZeroOneKnapsackItem::getValue));
		int numberOfItems = knapsackItems.size();
		int[][] k = new int[numberOfItems+1][maxKnapsackCapacity+1];

		for(int itemIndex=0; itemIndex<=numberOfItems;itemIndex++) {
			for(int weight=0;weight<=maxKnapsackCapacity;weight++) {
				if(itemIndex==0 || weight == 0)
					k[itemIndex][weight] = 0;
				else if(knapsackItems.get(itemIndex-1).getWeight() <= weight)
					k[itemIndex][weight] = max(
							knapsackItems.get(itemIndex-1).getValue() + k[itemIndex-1][weight - knapsackItems.get(itemIndex-1).getWeight()],
							k[itemIndex-1][weight]);
				else
					k[itemIndex][weight] = k[itemIndex-1][weight];
			}
		}

		return k;
	}

	private int max(int i, int j) {
		return (i > j)? i : j;
	}
}

class ZeroOneKnapsackItem{
	private int weight;
	private int value;

	ZeroOneKnapsackItem(int weight,int value){
		this.weight = weight;
		this.value = value;
	}

	public int getWeight() {
		return weight;
	}

	public int getValue() {
		return value;
	}
}

class Electronic extends ZeroOneKnapsackItem {
	String name;

	Electronic(String name, int weightInKgs, int profit){
		super(weightInKgs,profit);
		this.name = name;
	}

	@Override
	public String toString() {
		return "{name:" + name + ", weight:" + getWeight() + ", value:" + getValue() + "}";
	}
}