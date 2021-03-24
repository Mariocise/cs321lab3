Sam Humrichouse - Programming Assignment #2

Github link: https://github.com/Mariocise/cs321lab2

---

Description: 
This project simulates scheduling processes for a CPU.

---

Installation instructions:

From the directory containing all source files, compile the
driver class (and all dependencies) with the command:
$ javac CPUScheduling.java

---

Usage:

Run the compiled class file with the command:
$ java CPUScheduling <maxProcessTime> <maxPriorityLevel> <timeToIncrementPriority> <simulationTime> <processArrivalRate>
     
maxProcessTime: Largest possible time units required to finish a process.
maxPriorityLevel: Highest possible priority for a process.
timeToIncrementPriority: if a process didn't get any CPU time for this many time units, 
	the process's priority will increase by one
simulationTime: The total time units for the simulation
processArrivalRate: How likely it is for a process to be generated on each time unit

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