package trilane;

public class Impression {
	
	String impressionName;
	int impressionId;
	
	public Impression()
	{
		
	}
	public Impression(String impName,int impId)
	{
		this.impressionName = impName;
		this.impressionId = impId;
	}
	
	
	
	public String getImpressionName()
	{
		return this.impressionName;
	}
	
	public void setImpressionName(String impName)
	{
		this.impressionName= impName;
	}
	
	public int getImpressionId()
	{
		return this.impressionId;
	}
	
	public void setImpressionId(int impId)
	{
		this.impressionId= impId;
	}

	


}
