import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

//import java.util.concurrent.TimeUnit;

//import java.util.Arrays;

public class SumThread implements Runnable
{
	// how many threads will run this task
	//private static final int threadCount = 1000;
	private static final int threadCount = 5;
	
	// threads that will be used for this task
	Thread taskedThreads[];
	
	// the finalized sum of the entire array
	private int sumOfArray;
	
	// the entire array
	private Integer arrayToSum[];
	
	// the size of the entire array
	private static int arraySize;
	
	// a list of AtomicIntegers to keep track of its current index
	List <AtomicInteger> segmentCountList;
	//ArrayList <ArrayList<AtomicInteger>> test;
	
	// how big each segment will be
	private int segmentSize;
	
	// sum of each segmented array [threadCount]
	private int segmentedSum[];
	
	// the entire array broken into segments based on the amount of threads
	// [threadCount][segmentSize]
	private Integer segmentedArray[][];

	
	// timers for how long the process takes
	public long startTime, endTime, durationTime;

	
	public SumThread(Integer intializedArray[], int size)
	{
		arrayToSum = intializedArray;
		arraySize = size;
		startTime = endTime = durationTime = 0;
		taskedThreads = new Thread[threadCount];
		segmentSize = arraySize / threadCount;
		segmentedArray = new Integer [threadCount][segmentSize];
		segmentCountList = new ArrayList <AtomicInteger>();
		segmentedSum = new int [segmentSize];
		
		// TESTING
		// TODO : For odd division into segment size use % to add the remainder size to the segmentSize
		//test = new ArrayList<ArrayList<AtomicInteger>>();
		
		
		// here i break down the whole array into segments for later so that each thread can handle a segment of the array
		int counter = 0; 
		int i = 0;
		int j = 0;
		
		for (i = 0; i < threadCount; i++)
		{
			for (j = 0; j < segmentSize; j++)
			{
				segmentedArray[i][j] = arrayToSum[counter];
				counter++;
			}
		}
	}

	@Override
	public void run() 
	{
		startTime = System.nanoTime();
		
		int index = Integer.parseInt(Thread.currentThread().getName());
		
		for (int i = segmentCountList.get(index).get(); i < segmentSize; i++)
		{
		    //System.out.println("value = " + segmentedArray[index][i]);
			segmentedSum[index] += segmentedArray[index][i];
			//System.out.println("segmentSum = " + segmentedSum[index] + "\tindex = " + index);
		}	
		
		endTime = System.nanoTime();
		durationTime = (endTime - startTime);
		//durationTime = TimeUnit.MILLISECONDS.convert(durationTime, TimeUnit.NANOSECONDS);
	}
	
	public void start()
	{
		for (Integer i = 0; i < threadCount; i++)
		{
			AtomicInteger atomicToAdd = new AtomicInteger();
			segmentCountList.add(atomicToAdd);
			
			taskedThreads[i] = new Thread(this, i.toString());
			taskedThreads[i].start();
			
			// TESTING
			/*
			test.get(i).add(atomicToAdd);
			
			for (Integer j = 0; j < segmentSize; j++)
			{
				test.add(i, new ArrayList<AtomicInteger>());
				
			}
			*/
		}
	}
	public void join()
	{
		try 
		{
			for (int i = 0; i < threadCount; i++)
			{
				taskedThreads[i].join();
			}
		}catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	void printSegmentedArray()
	{
		for (int i = 0; i < threadCount; i++)
		{
			for (int j = 0; j < segmentSize; j++)
			{
				System.out.print(segmentedArray[i][j] + ", ");
			}
		}
	}
	
	void finalizeSum()
	{
		for (int i = 0; i < segmentSize; i++)
		{
			//System.out.println("segmentSum = " + segmentedSum[i]);
			sumOfArray += segmentedSum[i];
		}
	}

	@Override
	public String toString()
	{
		//return "SumThread [arrayToSum=" + Arrays.toString(arrayToSum) + "]\nsumOfArray=" + sumOfArray + "\nTime Elapsed : " + durationTime;
		return "SumThread\t[sumOfArray = " + sumOfArray + "\tTime Elapsed = " + durationTime + "]";
	}

	public static int getThreadcount() 
	{
		return threadCount;
	}
}
