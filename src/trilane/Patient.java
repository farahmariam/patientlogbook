package trilane;

public class Patient 
{
	String patientName;
	String patientId;
	String nhi;
	
	
	public Patient()
	{
		
	}
	
	public Patient(String pName,String pId,String nhi)
	{
		this.patientName = pName;
		this.patientId = pId;
		this.nhi = nhi;
	}
	
	public String getPatientName()
	{
		return this.patientName;
	}
	
	public void setPatientName(String pName)
	{
		this.patientName = pName;
	}
	
	public String getPatientId()
	{
		return this.patientId;
	}
	
	public void setPatientId(String pId)
	{
		this.patientId = pId;
	}
	
	public String getPatientNHI()
	{
		return this.nhi;
	}
	
	public void setPatientNHI(String nhi)
	{
		this.nhi = nhi;
	}

}
