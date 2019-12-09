package trilane;

public class Procedure {
	String procedureName;
	int procedureId;
	
	
	public Procedure()
	{
		
	}
	
	public Procedure(String pName,int pId)
	{
		this.procedureName = pName;
		this.procedureId = pId;
		
	}
	
	public String getProcedureName()
	{
		return this.procedureName;
	}
	
	public void setProcedureName(String pName)
	{
		this.procedureName = pName;
	}
	
	public int getProcedureId()
	{
		return this.procedureId;
	}
	
	public void setProcedureId(int pId)
	{
		this.procedureId = pId;
	}


}
