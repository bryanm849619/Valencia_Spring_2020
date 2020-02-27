package fib_Module5;

import java.util.Arrays;

public class FibSequencer 
{
	/** fibElements is a double array that will store the Fibonacci Sequence */
	public double[] fibElements;
	
	/** sizeOfSequence is how many cycles any given algorithm will undertake in generating Fibonacci Sequence */
	public int sizeOfSequence;
	
	/**  
	 *  recursiveCounter is used when generating Fibonacci Sequence utilizing a recursion approach
	 *  It will track the number of iterations it was called within the method
	 *  @see #recursiveGenerator(int)
	 *  @see #optimizedRecursiveGenerator(int)
	 */
	public int recursiveCounter;
	
	/**  
	 *  previousValue 
	 *  			used in not optimized algorithms to track the values of the previous numbers used in the generation
	 *  @see #iterationGenerator()
	 *  @see #recursiveGenerator(int)
	 */
	public double previousValue;
	/**  
	 *  currentValue 
	 *  			used in not optimized algorithms to track the values of the previous numbers used in the generation
	 *  @see #iterationGenerator()
	 *  @see #recursiveGenerator(int)
	 */
	public double currentValue;
	
	/**  
	 *  startTime
	 *  			the beginning time for {@link #generateSequence()}
	 *  @see #generateSequence()
	 */
	public long startTime;
	/**  
	 *  endTime
	 *  			the end time for {@link #generateSequence()}
	 *  @see #generateSequence()
	 */
	public long endTime;
	/**  
	 *  durationTime
	 *  			the total time it took for {@link #generateSequence()} to complete
	 *  @see #generateSequence()
	 */
	public long durationTime;
	
	/**  
	 *  AlgorithmType is an enumeration to help determine how to generate the Fibonacci Sequence
	 *  @see #generateSequence()
	 */
	public enum AlgorithmType
	{
		ITERATION,
		RECURSIVE,
		ITERATION_OPT,
		RECURSIVE_OPT
	}
	
	/** the defined algorithm type */
	public AlgorithmType sequenceBy;
	
	/**   
	 *  A Default COnstructor to Instantiate a FibSequencer object with default values
	 */
	public FibSequencer()
	{
		sizeOfSequence = recursiveCounter = 10;
		fibElements = new double[sizeOfSequence];
		sequenceBy = AlgorithmType.ITERATION;
		fibElements[0] = previousValue = 0.0;
		fibElements[1] = currentValue = 1.0;
		startTime = endTime = durationTime = 0;
	}
	
	/**  
	 *  
	 *  A Parameterized Constructor to Instantiate a FibSequencer object said values defined by user
	 *  @param timesToIterate determine how many times any given algorithm will cycle @see {@link #sizeOfSequence}
	 *  @param wayToIterate an enumrated value to determine the algorithm that will be used in generating the Fibonacci Sequence @see {@link #sequenceBy}
	 */
	public FibSequencer(int timesToIterate, AlgorithmType wayToIterate)
	{
		sizeOfSequence = recursiveCounter = timesToIterate;
		fibElements = new double[sizeOfSequence];
		sequenceBy = wayToIterate;
		previousValue = 0.0;
		fibElements[1] = currentValue = 1.0;
		startTime = endTime = durationTime = 0;
	}
	
	/**  
	 *  
	 *  generateSequence()
	 *  generate Fibonacci Sequence based on {@link #sequenceBy} and time how long it took to do so.
	 *  
	 */
	public void generateSequence()
	{
		switch (sequenceBy)
		{
			case ITERATION:
				startTime = System.nanoTime();
				iterationGenerator();
				endTime = System.nanoTime();
				durationTime = (endTime - startTime);
				break;
			case RECURSIVE:
				startTime = System.nanoTime();
				recursiveGenerator(2);
				endTime = System.nanoTime();
				durationTime = (endTime - startTime);
				break;
			case ITERATION_OPT:
				startTime = System.nanoTime();
				optimizedIterationGenerator();
				endTime = System.nanoTime();
				durationTime = (endTime - startTime);
				break;
			case RECURSIVE_OPT:
				startTime = System.nanoTime();
				optimizedRecursiveGenerator(2);
				endTime = System.nanoTime();
				durationTime = (endTime - startTime);
				break;
			default:
				System.out.println("Problem with generating the sequence!");
		}
	}
	
	/**  
	 * 
	 *  iterationGenerator()
	 *  uses a non optimized iterative approach in generating the Fibonacci Sequence
	 *  
	 */
	public void iterationGenerator()
	{
		for (int i = 2; i < sizeOfSequence; i++)
		{
			fibElements[i] =  previousValue + currentValue;
			previousValue = currentValue;
			currentValue = fibElements[i];
		}
	}
	
	/**  
	 * 
	 *  optimizedIterationGenerator()
	 *  uses a optimized iterative approach in generating the Fibonacci Sequence
	 *  
	 */
	public void optimizedIterationGenerator()
	{
		for (int i = 2; i < sizeOfSequence; i++)
			fibElements[i] = fibElements[i - 1] + fibElements[i - 2];
	}
	
	/**  
	 * 
	 *  recursiveGenerator()
	 *  uses a non optimized recursive approach in generating the Fibonacci Sequence
	 *  @param index the index of the current cycle for {@link #fibElements}
	 *  
	 */
	public void recursiveGenerator(int index)
	{
		if (recursiveCounter > 2)
		{
			fibElements[index] = previousValue + currentValue;
			previousValue = currentValue;
			currentValue = fibElements[index];
			recursiveCounter--;
			recursiveGenerator(++index);
		}
	}

	/**  
	 * 
	 *  optimizedRecursiveGenerator()
	 *  uses a optimized recursive approach in generating the Fibonacci Sequence
	 *  @param index the index of the current cycle for {@link #fibElements}
	 *  
	 */
	public void optimizedRecursiveGenerator(int index)
	{
		if (recursiveCounter > 2)
		{
			fibElements[index] = fibElements[index - 1] + fibElements[index - 2];
			recursiveCounter--;
			optimizedRecursiveGenerator(++index);
		}
	}
	
	@Override
	public String toString()
	{
		return durationTime + " nanoseconds\n" + "Depth of Iterations: " + 
			   sizeOfSequence + "\n" + 
			   "Sequence generated by: " + sequenceBy + 
			   "\nFibonacci Sequence = " + Arrays.toString(fibElements) + "\n";
	}
}
