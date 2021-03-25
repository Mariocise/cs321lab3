/**
 * Wrapper for a generic object for use in HashTable
 * 
 * @author Sam Humrichouse
 *
 * @param <T> - type of element to be held
 */
public class HashObject<T> {

	private T genericObject;
	private int duplicateCount;
	private int probeCount;
	
	public HashObject (T genericObject)
	{
		this.genericObject = genericObject;
	}
	
	public T getObject ()
	{
		return genericObject;
	}
	
	public void setObject (T newObject)
	{
		genericObject = newObject;
	}
	
	public int getDuplicateCount ()
	{
		return duplicateCount;
	}
	
	public void incrementDuplicateCount ()
	{
		duplicateCount++;
	}
	
	public int getProbeCount ()
	{
		return probeCount;
	}
	
	public void incrementProbeCount ()
	{
		probeCount++;
	}
	
	//override the equals method
	public boolean equals (HashObject<T> hashObject2)
	{
		if(genericObject.equals(hashObject2.getObject()))
		{
			return true;
		}
		return false;
	}
	
}
