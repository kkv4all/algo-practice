# Greedy Method
## Introduction
Greedy method is the algorithmic approach to find the solution for optimization. This paradigm  follows the problem solving approach of making the locally optimal choice at each stage with the hope of finding a global optimum.

In Greedy method, There is a problem given with some conditions and we need to find the optimal solution.

## Algorithm
```
greedysolution(a,n)
	for i = 1 to n do
		x = select(a)
		if feasible(x) then
			solution = solution + x 
		end
	end
end
```
## Pros and Cons
**Pros**
 - Simple, easy to code and implement.

**Cons** 
 - There are less chances to get optimal solution.

Unlike Dynamic programming, It never reconsiders its choice. Optimal solution by greedy may not be best but best by the method of selection.

## When to use?
The problems on which Greedy approach works has following two properties: 
 
1. greedy-choice property: a global optimum can be arrived at by selecting a local optimum.

2. Optimal substructure: an optimal solution to the problem contains an optimal solution to subproblems.

## Application & Examples
 - Knapsack Problem
 	- Fractional Knapsack
 	- 0/1 Knapsack 
 - Activity selection
 - Huffman Coding
 - Job Sequencing Problem
 - Prim's Minimum spanning tree
