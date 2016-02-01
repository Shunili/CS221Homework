import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * Shuni Li
 * COMP 221 HWA - Experiments with Data Structure
 * Professor Shilad Sen 
 * Jan 31, 2016
 *
 * This program (HWA.java) intends to analyze the performance of 
 * add and delete operations on lists and dictionaries.
 */

public class HWA {

	/*
	* Benchmark for Dictionary
	* @param version an integer representing a specific operation:
	*				 0 means adding to the dictionary
	*			 	 1 means removing from the dictionary
	*/
	public static void dictBenchmark(int version) {
		
		for(int i = 1; i <= 100000000; i *= 10) {

			double totalTime = 0;

			for(int j = 0; j < 10; j++) {
				
				HashMap<String, Integer> map = new HashMap<String, Integer>();

				if(version == 1) {
					for(int t = 0; t < i; t++) {
						map.put(Integer.toString(t), t); //initializing the map before remove
					}
				}
			
				long start = System.currentTimeMillis();

				for(int k = 1; k < i; k++) {

					switch(version) {
						case 0: 
						map.put(Integer.toString(k), k); //add
						break;
						case 1:
						map.remove(Integer.toString(k)); //remove
						break;
					}	
				}

				long elapse = System.currentTimeMillis() - start;
				double timePerOperation = elapse * 1.0 / i;
				totalTime += timePerOperation;
			}

			double avgTime = totalTime / 10;
			System.out.println("dict n: "+ i + " version: " + version + " time: " + avgTime);
			System.out.println();
	}
}

	/*
	* Benchmark for List
	* @param version an integer representing a specific operation:
	*				 0 means adding to the front
	*			 	 1 means removing from the front
	*				 2 means adding to the back 
	*				 3 means removing from the back
	*				 4 means adding to the middle
	*				 5 means removing from the middle
	*/
	public static void listBenchmark(int version) {
		
		for(int i = 1; i <= 100000000; i *= 10) {

			double totalTime = 0;

			for(int j = 0; j < 10; j++) {

				List<Integer> list = new ArrayList<Integer>();

				if(version % 2 == 1) {
					for(int t = 0; t < i; t++) {
						list.add(t);
					}
				}
			
				long start = System.currentTimeMillis();

				for(int k = 1; k <= i; k++) {

					switch(version) {
						case 0: 
						list.add(0, k); //add to front
						break;
						case 1:
						list.remove(0); //remove from front
						break;
						case 2:
						list.add(k); // add to back
						break;
						case 3:
						list.remove(i - k); //remove from end
						break;
						case 4:
						list.add((int) (Math.random() * list.size()), k); //add to middle
						break;
						case 5:
						list.remove((int) (Math.random() * list.size())); //remove from middle
						break;
					}	

					//watch for progress
					//if (k % 1000 == 0) {
					//	System.out.println(k); 
					//}
				}

				long elapse = System.currentTimeMillis() - start;

				double timePerOperation = elapse * 1.0 / i; 

				totalTime += timePerOperation;
			}

			double avgTime = totalTime / 10;
			System.out.println("list n: "+ i + " version: " + version + " time: " + avgTime);
			System.out.println();
		}
	}

	public static void main(String[] args) {

		//Must run these lines seperately instead of looping over; 
		//otherwise, the Out of Memory Message will 
		//prevent the program from running other benchmarks. 
		
		listBenchmark(0); //test add to front
		//listBenchmark(4); //test add to middle
		//listBenchmark(2); //test add to back

		//listBenchmark(1); //test remove from front
		//listBenchmark(5); //test remove from middle
		//listBenchmark(3); //test remove from back

		//dictBenchmark(0); //test add
		//dictBenchmark(1); //test remove		
	}        
}