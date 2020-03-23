//import java.util.concurrent.TimeUnit;

//import java.util.Arrays;

public class SumNoThread 
{
	// the entire array that needs to be summed
	Integer arrayToSum[];
	
	// the sum of the array
	int sumOfArray;
	
	// timers for how long the process takes
	public long startTime, endTime, durationTime;
	
	public SumNoThread(Integer initializedArray[])
	{
		arrayToSum = initializedArray;
		sumOfArray = 0;
		startTime = endTime = durationTime = 0;
	}
	
	public void sumArray()
	{
		startTime = System.nanoTime();
		
		for (int i = 0; i < arrayToSum.length; i++)
			sumOfArray += arrayToSum[i];
		
		endTime = System.nanoTime();
		durationTime = (endTime - startTime);
		//durationTime = TimeUnit.MILLISECONDS.convert(durationTime, TimeUnit.NANOSECONDS);
	}

	@Override
	public String toString()
	{
		//return "SumNoThread [arrayToSum=" + Arrays.toString(arrayToSum) + "]\nsumOfArray=" + sumOfArray + "\nTime Elapsed : " + durationTime;
		return "SumNoThread\t[sumOfArray = " + sumOfArray + "\tTime Elapsed = " + durationTime + "]";
	}
}
	
