import java.util.Random;

public class MainApp
{
	private static int arraySize = 200000000;
	//private static int arraySize = 10;
	
	public static void main(String[] args)
	{ 	
		/*
		 * GENERATE ARRAY OF RANDOM NUMBERS
		 */
		long seed = System.currentTimeMillis();
		Random randomUtil = new Random(seed);
		Integer arrayOfNumbers[] = new Integer [arraySize];
		
		for (int i = 0; i < arraySize; i++)
			arrayOfNumbers[i] = 1 + randomUtil.nextInt(10);
		
		/*
		 * Internal Check: Can run only if the amount of threads asking for is < the segment size of the divided array
		 * 				   Also threadCount needs to divide into arraySize evenly or else will not function properly. 
		 * Program logic cannot have more Threads than a segment size
		 * Example - segment size of array 3 and threads asking for 8
		 * 		   - segment size = arraySize / threads asking for
		 * 		   - 3				24			8
		 * See: SumThread.threadCount - private static final int
		 */
		
		int segSize = arraySize / SumThread.getThreadcount();
		if (segSize < SumThread.getThreadcount())
		{
			System.out.println("To many threads for this process");
			return;
		}
		else if(arraySize % SumThread.getThreadcount() != 0)
		{
			System.out.println("Make sure the number of threads you are asking for divides into arraySize eveningly");
			return;
		}
			
		/*
		 * SUM UP ARRAY OF NUMBERS WITHOUT THREAD AND PRINT
		 */
		SumNoThread noThread = new SumNoThread(arrayOfNumbers);
		noThread.sumArray();
		System.out.println(noThread);
		
		/*
		 * SUM UP ARRAY OF NUMBERS WITH THREADS AND PRINT
		 */
		SumThread runnableClass = new SumThread(arrayOfNumbers, arraySize);
		runnableClass.start();
		runnableClass.join();
		
		runnableClass.finalizeSum();
		//threadedClass.printSegmentedArray();
	
		System.out.println(runnableClass);
	}

}
