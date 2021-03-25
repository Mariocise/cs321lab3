
public class HashObject<T> {

	private T genericObject;
	private int duplicateCount;
	private int probeCount;
	
	public HashObject (T genericObject)
	{
		this.genericObject = genericObject;
	}
	
	T getObject ()
	{
		return genericObject;
	}
	
	void setObject (T newObject)
	{
		genericObject = newObject;
	}
	
	int getDuplicateCount ()
	{
		return duplicateCount;
	}
	
	void incrementDuplicateCount ()
	{
		duplicateCount++;
	}
	
	int getProbeCount ()
	{
		return probeCount;
	}
	
	void incrementProbeCount ()
	{
		probeCount++;
	}
	
	boolean equals (HashObject<T> hashObject2)
	{
		if(genericObject.equals(hashObject2.getObject()))
		{
			return true;
		}
		return false;
	}
	
}
