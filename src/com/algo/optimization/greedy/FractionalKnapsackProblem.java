package com.algo.optimization.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @author kkv4all
 * @description
 * 	Algorithm: Optimization Problem -> Greedy Method -> Knapsack Problem(Fractional)
 *  	Given weights (W) and values (P) of N items.
 *  	We need to put these items in a knapsack of capacity M to get the Maximum total value (Sum(Pi)) in the knapsack.
 *  
 *  Problem Definition:
 *  	There is a green-grocer who used to sell the vegetable daily in market in city near by. He picks vegetables
 *  from his farm daily in his basket of 15kg maximum capacity. considering whatever vegetables he bring to market 
 *  are sold out everyday, he need to take the different varieties vegetables which gives him maximum profit.
 *	So,
 *	Given is list of vegetables with total profit on each vegetable and weight of vegetable can be picked up for sale(Kgs)
 *	Criteria is the green-grocer can take 15kgs maximum
 *	Objective is to yield maximum profit from vegetable sold 
 */

public class FractionalKnapsackProblem {

	public static void main(String[] args) {
		List<KnapsackItem> vegetables = Arrays.asList(
				new Vegetable("Tomato", 2, 10),
				new Vegetable("Onion", 4, 5),
				new Vegetable("Cabbage", 5, 15),
				new Vegetable("Potato", 7, 7),
				new Vegetable("Peas", 1, 6),
				new Vegetable("Cauliflower", 4, 18),
				new Vegetable("Cucumber", 1, 3)
				);
		FractionalKnapsackProblem fractionalKapsackProblem = new FractionalKnapsackProblem();
		int  maxBasketCapacity = 15;
		List<KnapsackItem> itemsTaken = fractionalKapsackProblem.greedySolution(vegetables,maxBasketCapacity);

		if(!itemsTaken.isEmpty()) {
			System.out.println("Items Selected: ");
			itemsTaken.forEach(System.out::println);
			Optional<Double> profitYieldFromSale = itemsTaken.stream()
					.map(vegetable -> vegetable.getValue()*vegetable.getWeight())
					.reduce((sum,value)->sum+value);
			System.out.println("Total Profit can be yield from sale: "+profitYieldFromSale);
		} else {
			System.out.println("No Items Selected");
		}
	}

	private List<KnapsackItem> greedySolution(List<KnapsackItem> knapsackItems, int maxKnapsackCapacity) {
		List<KnapsackItem> itemsTaken = new ArrayList<KnapsackItem>();
		int remainingCapacity = maxKnapsackCapacity;

		knapsackItems.sort(Comparator.comparing(KnapsackItem::getCost).reversed());

		Iterator<KnapsackItem> itr = knapsackItems.iterator();
		while(itr.hasNext() && remainingCapacity>0) {
			KnapsackItem item = itr.next();
			if(remainingCapacity > item.getWeight()) {
				itemsTaken.add(item);
				remainingCapacity -= item.getWeight();
			} else {
				double fraction = ((double)remainingCapacity/item.getWeight());
				itemsTaken.add(item.getFractionOfItem(fraction));
				remainingCapacity = 0;
			}
		}

		return itemsTaken;
	}
}

abstract class KnapsackItem{
	private double weight;
	private double value;
	private double cost;

	KnapsackItem(double weight,double value){
		this.weight = weight;
		this.value = value;
		this.cost  = value/weight;
	}

	public abstract KnapsackItem getFractionOfItem(double fraction);

	public double getWeight() {
		return weight;
	}

	public double getValue() {
		return value;
	}

	public double getCost() {
		return cost;
	}
}

class Vegetable extends KnapsackItem {
	String name;

	Vegetable(String name, double weightInKgs, double profit){
		super(weightInKgs,profit);
		this.name = name;
	}

	@Override
	public KnapsackItem getFractionOfItem(double fraction) {
		return new Vegetable(this.name,fraction,getValue()*fraction);
	}

	@Override
	public String toString() {
		return "{name:" + name + ", weight:" + getWeight() + ", value:" + getValue() + 
				", cost:" + getCost() + "}";
	}
}