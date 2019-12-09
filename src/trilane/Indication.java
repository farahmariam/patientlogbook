package trilane;

public class Indication {
	String indicationName;
	int indicationId;
	
	public Indication()
	{
		
	}
	public Indication(String indName,int indId)
	{
		this.indicationName = indName;
		this.indicationId = indId;
	}
	
	
	
	public String getIndicationName()
	{
		return this.indicationName;
	}
	
	public void setIndicationName(String indName)
	{
		this.indicationName= indName;
	}
	
	public int getIndicationId()
	{
		return this.indicationId;
	}
	
	public void setIndicationId(int indId)
	{
		this.indicationId= indId;
	}

	

}
