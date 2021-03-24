
public class HashObject<T> {

	T genericObject;
	int duplicateCount;
	int probeCount;
	
	T getObject()
	{
		return genericObject;
	}
	
	void setObject(T newObject)
	{
		genericObject = newObject;
	}
	
	int getDuplicateCount()
	{
		return duplicateCount;
	}
	
	void incrementDuplicateCount()
	{
		duplicateCount++;
	}
	
	int getProbeCount()
	{
		return probeCount;
	}
	
	void incrementProbeCount()
	{
		probeCount++;
	}
	
}
