package trilane;

public class Consultant 
{
	String consultantFirstName;
	String consultantLastName;
	int consultantId;
	
	public Consultant()
	{
		
	}
	
	public Consultant(String cfName,String clName, int cId)
	{
		this.consultantFirstName = cfName;
		this.consultantLastName = clName;
		this.consultantId = cId;
	}
	
	public String getConsultantfName()
	{
		return this.consultantFirstName;
	}
	
	public void setConsultantFirstName(String cName)
	{
		this.consultantFirstName = cName;
	}
	
	public String getConsultantlName()
	{
		return this.consultantLastName;
	}
	
	public void setConsultantLastName(String cName)
	{
		this.consultantLastName = cName;
	}
	
	public int getConsultantId()
	{
		return this.consultantId;
	}
	
	public void setConsultantId(int cId)
	{
		this.consultantId = cId;
	}
	

}
