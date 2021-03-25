import java.math.BigInteger;
import java.util.Random;

public class HashTest {

	public static void main(String[] args) {
		
		final int MIN_TABLE_SIZE = 95500;
		
		if (args.length < 2 || args.length > 3)
	    {
			System.out.println
			    ("Usage: java HashTest <input type> <load factor> [<debug level>]");
			System.out.println
			    ("<input type>: type of input (from java.util.Random, System.currentTimeMillis() or word-list.");
			System.out.println
			    ("<load factor>: value between 0 and 1 - ratio of elements to table size.");
			System.out.println
			    ("[<debug level>]: 0 to print summary, 1 to print summary and hash tables."); 
			System.exit(1);
	    }		
		
		int inputType = Integer.parseInt(args[0]);
		if (inputType < 1 || inputType > 3)
		{
			throw new IllegalArgumentException
			("Illegal argument: input type must be 1, 2, or 3.");
		}
		float loadFactor = Float.parseFloat(args[1]);
		if (loadFactor <= 0 || loadFactor >= 1)
		{
			throw new IllegalArgumentException
			("Illegal argument: load factor must be between 0 and 1.");
		}
		
		int tableSize = findTableSize(MIN_TABLE_SIZE);
		System.out.println("A good table size is found: " + tableSize);
		
		int numElements = (int) (loadFactor * tableSize);
		
		String dataSourceStr = "java.util.Random";
		System.out.println("Data source type: " + dataSourceStr);
		System.out.println("\n");
		
		System.out.println("Using Linear Hashing....");
		
		Random r = new Random();
		HashTable<Integer> hashTable = new HashTable<Integer>(tableSize, false);
		for(int i = 0; i < numElements; i++)
		{
			HashObject<Integer> hashObject = new HashObject<Integer>(r.nextInt());
			hashTable.insert(hashObject);
		}
		HashObject<Integer>[] hashTableArr = hashTable.getTable();
		
		int duplicateCount = 0;
		int probeCount = 0;
		for(HashObject<Integer> hashObject : hashTableArr)
		{
			if(hashObject != null)
			{
				duplicateCount += hashObject.getDuplicateCount();
				probeCount += hashObject.getProbeCount() * (hashObject.getDuplicateCount() + 1);
			}
		}
		float avgProbes = (float) probeCount / numElements;
		
		System.out.println("Input " + numElements + " elements, of which " + duplicateCount + " duplicates");
		System.out.println("load factor = " + loadFactor + ", Avg. no. of probes " + avgProbes);
	}
	
	private static int findTableSize(int minTableSize)
	{
		int start = minTableSize;
        Random r = new Random();
        int x;
        start = 2*(start/2) + 1;
        for(x=start;;x+=2)
        {
            BigInteger exponent = new BigInteger("" + (x-1));
            BigInteger mod = new BigInteger("" + x);

            // draw a random number between 1 and x and test it
            // Checking m-2
            BigInteger a;
            a = new BigInteger("" + (r.nextInt(x-2)+2));
            if(a.modPow(exponent,mod).intValue() != 1) continue;
            a = new BigInteger("" + (r.nextInt(x-2)+2));
            if(a.modPow(exponent,mod).intValue() != 1) continue;

            // Checking m
            x+=2;
            exponent = new BigInteger((x-1)+"");
            mod = new BigInteger(x+"");
            a = new BigInteger("" + (r.nextInt(x-2)+2));
            if(a.modPow(exponent,mod).intValue() != 1) continue;
            a = new BigInteger("" + (r.nextInt(x-2)+2));
            if(a.modPow(exponent,mod).intValue() != 1) continue;

            break;
        }
        return x;
	}

}
