package fib_Module5;


public class main_Module
{
	public static void main(String[] args)
	{
		// Instantiate some FibSequencers
		FibSequencer fibonacci_iter1 			= new FibSequencer(10, 		FibSequencer.AlgorithmType.ITERATION);
		FibSequencer fibonacci_iter2 			= new FibSequencer(100, 	FibSequencer.AlgorithmType.ITERATION);
		FibSequencer fibonacci_iter3 			= new FibSequencer(1000, 	FibSequencer.AlgorithmType.ITERATION);
		FibSequencer fibonacci_iter_opt1 		= new FibSequencer(10, 		FibSequencer.AlgorithmType.ITERATION_OPT);
		FibSequencer fibonacci_iter_opt2 		= new FibSequencer(100, 	FibSequencer.AlgorithmType.ITERATION_OPT);
		FibSequencer fibonacci_iter_opt3 		= new FibSequencer(1000, 	FibSequencer.AlgorithmType.ITERATION_OPT);
		FibSequencer fibonacci_recurve1 		= new FibSequencer(10, 		FibSequencer.AlgorithmType.RECURSIVE);
		FibSequencer fibonacci_recurve2 		= new FibSequencer(100, 	FibSequencer.AlgorithmType.RECURSIVE);
		FibSequencer fibonacci_recurve3 		= new FibSequencer(1000, 	FibSequencer.AlgorithmType.RECURSIVE);
		FibSequencer fibonacci_recurve_opt1 	= new FibSequencer(10, 		FibSequencer.AlgorithmType.RECURSIVE_OPT);
		FibSequencer fibonacci_recurve_opt2 	= new FibSequencer(100, 	FibSequencer.AlgorithmType.RECURSIVE_OPT);
		FibSequencer fibonacci_recurve_opt3 	= new FibSequencer(1000, 	FibSequencer.AlgorithmType.RECURSIVE_OPT);
		
		// Lets generate some fibonacci sequences 
		fibonacci_iter1.generateSequence();
		fibonacci_iter2.generateSequence();
		fibonacci_iter3.generateSequence();
		fibonacci_recurve1.generateSequence();
		fibonacci_recurve2.generateSequence();
		fibonacci_recurve3.generateSequence();
		fibonacci_iter_opt1.generateSequence();
		fibonacci_iter_opt2.generateSequence();
		fibonacci_iter_opt3.generateSequence();
		fibonacci_recurve_opt1.generateSequence();
		fibonacci_recurve_opt2.generateSequence();
		fibonacci_recurve_opt3.generateSequence();
		
		// Print out the fibonacci sequences and its corresponding features 
		System.out.println("Iteration 1: " + fibonacci_iter1);
		System.out.println("Iteration Optimized 1: " + fibonacci_iter_opt1);
		System.out.println("Recursive 1: " + fibonacci_recurve1);
		System.out.println("Recursive Optimized 1: " + fibonacci_recurve_opt1);
		
		System.out.println("Iteration 2: " + fibonacci_iter2);
		System.out.println("Iteration Optimized 2: " + fibonacci_iter_opt2);
		System.out.println("Recursive 2: " + fibonacci_recurve2);
		System.out.println("Recursive Optimized 2: " + fibonacci_recurve_opt2);
		
		System.out.println("Iteration 3: " + fibonacci_iter3);
		System.out.println("Iteration Optimized 3: " + fibonacci_iter_opt3);
		System.out.println("Recursive 3: " + fibonacci_recurve3);		
		System.out.println("Recursive Optimized 3: " + fibonacci_recurve_opt3);
	
		
	}
}
