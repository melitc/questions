package com.questions.backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ArrayLeaderMain {

	public static void main(String[] args) {

		//Write an efficient algorithm for the following assumptions:
		//N and M are integers within the range [1..100,000];
		//K is an integer within the range [1..N];
		//each element of array A is an integer within the range [1..M].
		Random r = new Random();
		int N = r.nextInt((100000 - 1) + 1) + 1;
		int M = r.nextInt((100000 - 1) + 1) + 1;
		int K =  r.nextInt((N - 1) + 1) + 1;
		
		//generate a stream 
		Stream<IntStream> streamA = Stream.of(new Random().ints(N, 0, M));  
		int[] A = streamA.flatMapToInt(x -> x).toArray();
		int[] leaders = solution (K,M,A);
		
		IntStream.range(0, leaders.length)
		  .forEach(index -> {System.out.println(leaders[index]);
		  });
	}
	
	private static int[] solution (int K, int M, int[] A){ 

		int variants = A.length - K + 1;
		double halfSize = ((double) A.length/2);

		List<Integer> leaderList = new ArrayList<>();
		IntStream.range(0,variants)
			.forEach(indexJ -> {
				//save the array A to another auxiliary array (copiedArray)
				int[] copiedArray = Arrays.copyOf(A, A.length);
				
				//sum 1 
				IntStream.range(indexJ, indexJ+K)
				  .forEach(index -> {
					  copiedArray [index] = copiedArray[index] + 1;
				  });
				
				//search leader for each variant
				HashMap<Integer, Integer> counterMap = countOccurrences(copiedArray, copiedArray.length);
				for (Map.Entry<Integer, Integer> entry : counterMap.entrySet()) { 
		            if(entry.getValue() > Math.floor(halfSize)) {
		            	leaderList.add(entry.getKey());
		            	break;
		            }
		        } 

			});

		int[] arrayLeaders = leaderList.stream().mapToInt(i->i).toArray();
		return arrayLeaders;
	}
	
	public static HashMap<Integer, Integer> countOccurrences(int arr[], int size) { 
		
        HashMap<Integer, Integer> occuMap = new HashMap<Integer, Integer>();
        IntStream.range(0,size)
			.forEach(index -> {
				 if (occuMap.containsKey(arr[index])) { 
					 occuMap.put(arr[index], occuMap.get(arr[index]) + 1); 
				 }else {
					 occuMap.put(arr[index], 1);  
				 }
				
			});

        return occuMap;
	} 
	  

}
