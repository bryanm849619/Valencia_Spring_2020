
public class MyPair implements Comparable<MyPair>
{
	/** count is a Integer that will keep track of the number of elements that match this token within this pair */
	public  Integer count;
	/** token is a String that will store the string token for this pair */
	public  String token;
	
	/**   
	 *  A Default COnstructor to Instantiate a MyPair with default values
	 */
	public MyPair()
	{
		count = 0;
		token = null;
	}
	/**  
	 *  
	 *  A Parameterized Constructor to Instantiate a MyPair object with count defaulted to 1
	 *  @param tokenToAdd is a string that will be copied to token @see {link {@link #token}
	 */
	public MyPair(String tokenToAdd)
	{
		count = 1;
		token = tokenToAdd;
	}
	/**  
	 *  
	 *  A getter method to retrieve the current count for this given token within this pair.
	 *  @return the current count of elements for this given token within this pair.
	 */
	public Integer getCount() {
		return count;
	}
	/**  
	 *  
	 *  A setter method to set the number of tokens given within this pair
	 *  @param count is the number of times this token is found within this pair @see {@link #count}
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**  
	 *  
	 *  Method to increment the number of elements this pair has for this token @see {@link #count}
	 */
	public void incrementCount()
	{
		this.count++;
	}
	/**  
	 *  
	 *  A getter method to retrieve the token that exists within this pair
	 *  @return the token that exists within this pair
	 */
	public String getToken() {
		return token;
	}

	/**  
	 *  
	 *  A setter method to set the token that will exist within this pair
	 *  @param token is the token that will be added to this pair @see {@link #token}
	 */
	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public int compareTo(MyPair o) 
	{
		//return this.count.compareTo(o.getCount());
		return o.getCount().compareTo(this.getCount());
		/*
		if (this.getCount() > o.getCount())
			return 1;
		
		return 0;*/
	}
	@Override
	public String toString()
	{
		return "MyPair [count=" + count + ", token=" + token + "]";
	}
}

