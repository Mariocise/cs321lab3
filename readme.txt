Sam Humrichouse - Programming Assignment #2

Github link: https://github.com/Mariocise/cs321lab3

---

Description: 
This project simulates scheduling processes for a CPU.

---

Installation instructions:

From the directory containing all source files, compile the
driver class (and all dependencies) with the command:
$ javac HashTest.java

---

Usage:

Run the compiled class file with the command:
$ java HashTest <input type> <load factor> [<debug level>]
     
<input type>: type of input (from java.util.Random, System.currentTimeMillis() or word-list.
<load factor>: value between 0 and 1 - ratio of elements to table size.
[<debug level>]: 0 to print summary, 1 to print summary and hash tables.

Console output will give the results after the program finishes.
---

Report:

In this project, setting up HashTable and HashObject actually
proved to be fairly easy. I ran into a lot of road blocks with 
HashTest. For example, I had some trouble getting the average probe count
to match what was expected for the word-list because I wasn't understanding
how the average probe count was supposed to be calculated. I ended
up experimenting with different methods until one stuck.

For the most part, I used a git commit after getting basic functionality 
for each class and then another one after some testing and bug fixing.

The test results for linear probing matched very closely to what was
expected. The test results for double hashing seemed to have more significant
differences from what was expected. I'm not sure how my hashing method might
have been different from whatever was used for the sample, but the differences
must have been minor, since my average number of probes was still very close.
---

Tables:

Input source 1: random number

alpha	     linear    double
-----------------------------
0.5          1.500     1.389
0.6	         1.743     1.523
0.7	         2.187     1.718 
0.8	         2.948     2.016
0.9	         5.378     2.569
0.95	    10.179     3.142
0.98	    25.011     3.976
0.99	    43.447     4.698


Input source 2: current time

alpha        linear    double
-----------------------------
0.5          1.0       1.0
0.6          1.0       1.0
0.7          1.0       1.0
0.8          1.0       1.0
0.9          1.0       1.0
0.95         1.0       1.0
0.98         1.0       1.0
0.99         1.0       1.0 


Input source 3: word-list

alpha        linear    double
-----------------------------
0.5          1.596     1.391
0.6	         2.149     1.535
0.7	         3.603     1.726
0.8	         6.708     2.024
0.9	        19.814     2.575 
0.95	   110.595     3.202 
0.98	   324.042     4.077
0.99	   471.657     4.744

