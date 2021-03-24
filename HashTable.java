
public class HashTable<T> {

	HashObject<T>[] table;
	boolean useDoubleHash;
	int numKeys = 0;
	
	public HashTable (int tableSize, boolean useDoubleHash)
	{
		table = new HashObject[tableSize];
		this.useDoubleHash = useDoubleHash;
	}
	
	public void insert (HashObject<T> hashObject) throws Exception
	{
		if (numKeys >= table.length)
		{
			throw new Exception();
		}
		
		T key = hashObject.getObject();
		int h2 = 1 + positiveMod(key.hashCode(), table.length - 2);
		
		int index = positiveMod(key.hashCode(), table.length);
		
		while (true)
		{
			hashObject.incrementProbeCount();
			
			if(table[index] == null)
			{
				table[index] = hashObject;
				break;
			}
			
			if (table[index].hashCode() == key.hashCode())
			{
				if (table[index].equals(hashObject))
				{
					table[index].incrementDuplicateCount();
					break;
				}
			}
			
			if (!useDoubleHash)
			{
				index = positiveMod(index + 1, table.length);
			}
			else
			{
				index = positiveMod(index + (h2), table.length);
			}
			
		}
	}
	
	public HashObject<T>[] getTable ()
	{
		return table.clone();
	}
	
	private int positiveMod (int dividend, int divisor)
	{
		int value = dividend % divisor;
		if (value < 0) 
		{
			value += divisor;
		}
		return value;
	}
	
}
