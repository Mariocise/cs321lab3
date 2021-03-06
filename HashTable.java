/**
 * A table that uses hashing for indexing
 * 
 * @author Sam Humrichouse
 *
 * @param <T> - Type of elements to be held
 */

public class HashTable<T> {

	HashObject<T>[] table;
	boolean useDoubleHash;
	int numKeys = 0;
	
	public HashTable (int tableSize, boolean useDoubleHash)
	{
		table = new HashObject[tableSize];
		this.useDoubleHash = useDoubleHash;
	}
	
	//returns true if inserted item was a duplicate
	public boolean insert (HashObject<T> hashObject)
	{
		if (numKeys >= table.length)
		{
			System.out.println("Table is full");
			return false;
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
				numKeys++;
				return false;
			}
			
			if (table[index].getObject().hashCode() == key.hashCode())
			{
				if (table[index].equals(hashObject))
				{
					table[index].incrementDuplicateCount();
					return true;
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
	
	//get the array
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
