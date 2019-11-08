package com.algo.optimization.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author kkv4all
 * @description
 * 	Algorithm: Optimization Problem -> Greedy Method -> Huffman Coding
 *  Huffman Coding is also known as huffman encoding is a greedy lossless data compression algorithm.
 * 		Given A set of symbols/character and their frequencies(number of occurrence). 
 * 		Our goal is find A prefix-free binary code (a set of codewords) with minimum expected codeword length.
 *  
 *  Problem Definition:
 *  	A file contains the following characters with the frequencies as shown. If Huffman coding is used for 
 *  data compression, determine-
 *  	1. Huffman code for each character
 *  	2. Average code length
 *  	3. Length of Huffman encoded message (in bits)
 */

public class HuffmanCoding {

	public static void main(String[] args) {
		List<HuffmanNode> charAndFrequencies = Arrays.asList(
//				new HuffmanNode('a',5),
//				new HuffmanNode('b',9),
//				new HuffmanNode('c',12),
//				new HuffmanNode('d',13),
//				new HuffmanNode('e',16),
//				new HuffmanNode('f',45)
				new HuffmanNode('a',10),
				new HuffmanNode('e',15),
				new HuffmanNode('i',12),
				new HuffmanNode('o',3),
				new HuffmanNode('u',4),
				new HuffmanNode('s',13),
				new HuffmanNode('t',1)
			);
		HuffmanCoding huffmanCoding = new HuffmanCoding();
		HuffmanNode rootOfHuffmanTree = huffmanCoding.getHuffmanTree(charAndFrequencies);
		System.out.println("1. Huffman code for each character : ");
		Map<Character,String> characterCodes =  huffmanCoding.getCodesForTreeNode(rootOfHuffmanTree, "");
		characterCodes.forEach((key, value) -> System.out.println(key + ": " + value));
		double averageCodeLength = huffmanCoding.getAverageCodeLength(charAndFrequencies, characterCodes);
		System.out.println("2. Average code length : " + averageCodeLength);
		System.out.println("3. Length of Huffman encoded message (in bits) : " +
				huffmanCoding.getLengthOfHuffmanEncodedMessage(charAndFrequencies, averageCodeLength));
	}


	private HuffmanNode getHuffmanTree(List<HuffmanNode> charAndFrequencies) {
		HuffmanNode rootNode = null;
		// Create min heap  that contains node
		PriorityQueue<HuffmanNode> minHeap = new PriorityQueue<HuffmanNode>(charAndFrequencies.size(),Comparator.comparing(node -> node.getData())); 
		charAndFrequencies.forEach(minHeap::add);
		
		while(minHeap.size()>1) {
			// Extract two minimum frequency nodes from min heap
			HuffmanNode left = minHeap.poll();
			HuffmanNode right = minHeap.poll();
			
			// Add a new internal node with frequency combining two extracted minimum frequencies
			HuffmanNode parent = new HuffmanNode('-', left.getData()+right.getData());
			
			// Set extracted two nodes as left and right of combined node
			parent.setLeft(left);
			parent.setRight(right);
			
			// Set combined Node as Root for tree
			rootNode = parent;
			// Add Combined node in min heap for further iteration
			minHeap.add(parent);
		}
		return rootNode;
	}
	
	private Map<Character, String> getCodesForTreeNode(HuffmanNode root, String code) {
		Map<Character,String> characterMap = new HashMap<>();
		if(root.getLeft() == null && root.getRight() == null && Character.isLetter(root.getCharacter())) {
			characterMap.put(root.getCharacter(), code);
			return characterMap;
		}
		
		characterMap.putAll(getCodesForTreeNode(root.getLeft(), code+0));
		characterMap.putAll(getCodesForTreeNode(root.getRight(), code+1));
		return characterMap;
	}

	private long getLengthOfHuffmanEncodedMessage(List<HuffmanNode> charAndFrequencies, double averageCodeLength) {
		double sumOfFrequencies = 0;
		
		sumOfFrequencies = charAndFrequencies.stream()
				.mapToInt(node -> node.getData())
				.reduce(0,(sum,count) -> sum+count);
		
		return Math.round(averageCodeLength*sumOfFrequencies);
	}
	
	private double getAverageCodeLength(List<HuffmanNode> charAndFrequencies, Map<Character, String> characterCodes) {
		double sumOfProductsOfFrequenciesAndCodeLength = 0;
		double sumOfFrequencies = 0;
		
		sumOfProductsOfFrequenciesAndCodeLength = charAndFrequencies.stream()
				.mapToInt(node -> (node.getData() * characterCodes.get(node.getCharacter()).length()))
				.reduce(0,(sum,count) -> sum+count);
		
		sumOfFrequencies = charAndFrequencies.stream()
				.mapToInt(node -> node.getData())
				.reduce(0,(sum,count) -> sum+count);
		
		return sumOfProductsOfFrequenciesAndCodeLength/sumOfFrequencies;
	}
}

class HuffmanNode {
	private char character;
	private int data;

	private HuffmanNode left;
	private HuffmanNode right;


	public HuffmanNode(char character, int data) {
		this.character = character;
		this.data = data;
	}

	public HuffmanNode getLeft() {
		return left;
	}
	public HuffmanNode getRight() {
		return right;
	}
	public char getCharacter() {
		return character;
	}
	public int getData() {
		return data;
	}

	public void setLeft(HuffmanNode left) {
		this.left = left;
	}
	public void setRight(HuffmanNode right) {
		this.right = right;
	}
	
	@Override
	public String toString() {
		return "{character:" + character + ", data:" + data + ", left:" + left + ", right:" + right + "}";
	}
}