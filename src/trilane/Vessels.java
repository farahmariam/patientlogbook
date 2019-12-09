package trilane;

public class Vessels 
{
	String vesselName;
	int vesselId;
	
	public Vessels()
	{
		
	}
	public Vessels(String vesselName,int vesselId)
	{
		this.vesselName = vesselName;
		this.vesselId = vesselId;
	}
	
	
	
	public String getVesselName()
	{
		return this.vesselName;
	}
	
	public void setVesselName(String vesselName)
	{
		this.vesselName= vesselName;
	}
	
	public int getVesselId()
	{
		return this.vesselId;
	}
	
	public void setVesselId(int vesselId)
	{
		this.vesselId= vesselId;
	}

	

}
