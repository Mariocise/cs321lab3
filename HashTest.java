import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * Driver class to test functionality of HashTable
 * 
 * @author Sam Humrichouse
 *
 */

public class HashTest {

	public static void main(String[] args) {
		
		final int MIN_TABLE_SIZE = 95500;
		final String WORD_LIST_FILENAME = "word-list";
		
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
		boolean debugLevelOne = false;
		if (args.length == 3)
		{
			if (Integer.parseInt(args[2]) == 1)
			{
				debugLevelOne = true;
			}
		}
		
		int tableSize = findTableSize(MIN_TABLE_SIZE);
		System.out.println("A good table size is found: " + tableSize);
		
		int numElements = (int) (loadFactor * tableSize);
		
		String dataSourceStr;
		if (inputType == 1)
		{
			dataSourceStr = "java.util.Random";
		}
		else if (inputType == 2)
		{
			dataSourceStr = "System.currentTimeMillis()";
		}
		else
		{
			dataSourceStr = WORD_LIST_FILENAME;
		}
		System.out.println("Data source type: " + dataSourceStr);
		System.out.println("\n");
		
		System.out.println("Using Linear Hashing....");
		
		if (inputType == 1)
		{
			Random r = new Random();
			HashTable<Integer> hashTable = new HashTable<Integer>(tableSize, false);
			for(int i = 0; i < numElements; i++)
			{
				HashObject<Integer> hashObject = new HashObject<Integer>(r.nextInt());
				hashTable.insert(hashObject);
			}
		
			performTest(hashTable, numElements, tableSize, loadFactor, debugLevelOne);
		}
		
		else if (inputType == 2)
		{
			HashTable<Long> hashTable = new HashTable<Long>(tableSize, false);
			for(int i = 0; i < numElements; i++)
			{
				HashObject<Long> hashObject = new HashObject<Long>(System.currentTimeMillis());
				hashTable.insert(hashObject);
			}
		
			performTest(hashTable, numElements, tableSize, loadFactor, debugLevelOne);
		}
		
		else
		{
			File file = new File(WORD_LIST_FILENAME);
			Scanner fileScanner;
			try 
			{
				fileScanner = new Scanner(file);
			
				HashTable<String> hashTable = new HashTable<String>(tableSize, false);
				int newNumElements = 0;
				for(int i = 0; i < numElements; i++)
				{
					if(fileScanner.hasNextLine())
					{
						boolean isDuplicate = true;
						while(isDuplicate && fileScanner.hasNextLine())
						{
							HashObject<String> hashObject = new HashObject<String>(fileScanner.nextLine());
							isDuplicate = hashTable.insert(hashObject);
							newNumElements++;
						}
					}
					else
					{
						break;
					}
				}
				fileScanner.close();
				numElements = newNumElements;
				
				performTest(hashTable, numElements, tableSize, loadFactor, debugLevelOne);
			} 
			catch (FileNotFoundException e) 
			{	
				System.out.println("file \"" + WORD_LIST_FILENAME + "\" not found");
				System.exit(1);
			}
		}
		
		
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
	
	private static <T> void performTest(HashTable<T> hashTable, int numElements, int tableSize, float loadFactor, boolean debugLevelOne)
	{
		HashObject<T>[] hashTableArr = hashTable.getTable();
		printResults(hashTableArr, numElements, loadFactor);
		if(debugLevelOne)
		{
			outputTable(hashTableArr, true);
		}
		
		System.out.println("Using Double Hashing....");
		
		hashTable = new HashTable<T>(tableSize, true);
		for(HashObject<T> hashObject : hashTableArr)
		{
			if (hashObject != null)
			{
				for(int i = 0; i < hashObject.getDuplicateCount() + 1; i++)
				{
					HashObject<T> newHashObject = new HashObject<T>(hashObject.getObject());
					hashTable.insert(newHashObject);
				}
			}
		}
		hashTableArr = hashTable.getTable();
		
		printResults(hashTableArr, numElements, loadFactor);
		if(debugLevelOne)
		{
			outputTable(hashTableArr, false);
		}
	}
	
	private static <T> void printResults(HashObject<T>[] hashTableArr, int numElements, float loadFactor)
	{
		int duplicateCount = 0;
		int probeCount = 0;
		for(HashObject<T> hashObject : hashTableArr)
		{
			if(hashObject != null)
			{
				duplicateCount += hashObject.getDuplicateCount();
				probeCount += hashObject.getProbeCount();
			}
		}
		float avgProbes = (float) probeCount / (numElements - duplicateCount);
		
		System.out.println("Input " + numElements + " elements, of which " + duplicateCount + " duplicates");
		System.out.println("load factor = " + loadFactor + ", Avg. no. of probes " + avgProbes);
		System.out.println();
	}
	
	private static <T> void outputTable(HashObject<T>[] hashTableArr, boolean isLinearDump)
	{
		FileWriter writer;
		try {
			writer = new FileWriter((isLinearDump) ? "linear-dump" : "double-dump");
			writer.write("");
			writer.close();
			
			writer = new FileWriter((isLinearDump) ? "linear-dump" : "double-dump", true);
			for(int i = 0; i < hashTableArr.length; i++)
			{
				HashObject<T> hashObject = hashTableArr[i];
				if (hashObject != null)
				{
					String output = "table[" + i + "]: " + hashObject.getObject().toString() + " " 
							+ hashObject.getDuplicateCount() + " " + hashObject.getProbeCount() + "\n";
					writer.write(output);
				}
			}
			writer.close();
		} catch (IOException e1) {
			System.out.println("An error occurred while outputting table");
			return;
		}
	}

}
