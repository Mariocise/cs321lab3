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

This project was especially difficult because I had some 
trouble figuring out how to organize the work between PQueue
and MaxHeap. In the end, PQueue ended up being more or less 
a wrapper for MaxHeap.

I would have liked to make MaxHeap work with generic data 
instead of just Process, and I think I know how I would do it,
but I just didn't have time.

I mainly just used git for major milestones, like getting the files
set up, implementing functionality with normal queue, etc.
I hope to interact with git more often in future projects.