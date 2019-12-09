package trilane;

public class Intervention 
{
	
	String interventionName;
	int interventionId;
	
	public Intervention()
	{
		
	}
	public Intervention(String intName,int intId)
	{
		this.interventionName = intName;
		this.interventionId = intId;
	}
	
	
	
	public String getInterventionName()
	{
		return this.interventionName;
	}
	
	public void setInterventionName(String intName)
	{
		this.interventionName= intName;
	}
	
	public int getInterventionId()
	{
		return this.interventionId;
	}
	
	public void setInterventionId(int intId)
	{
		this.interventionId= intId;
	}

}
