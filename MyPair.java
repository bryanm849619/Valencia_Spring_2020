package module2;

public class MyPair implements Comparable<MyPair>
{
	private Integer count;
	private String token;
	
	public MyPair()
	{
		count = 0;
		token = null;
	}
	public MyPair(String tokenToAdd)
	{
		count = 1;
		token = tokenToAdd;
	}
	
	public Integer getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public void incrementCount()
	{
		this.count++;
	}

	public String getToken() {
		return token;
	}

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
