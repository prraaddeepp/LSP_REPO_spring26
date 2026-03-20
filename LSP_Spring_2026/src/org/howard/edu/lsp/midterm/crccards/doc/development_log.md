# Development Log-Question 1
```
Name: Pradeep Lamichhane 
Course: Large Scale Programming (Spring 2026) 
Midterm – Question 1
```
---
### Primary References
1. **Java Documentation – Collections Framework**
(https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html)
- Used to understand how to use List, ArrayList, and Map for storing and retrieving tasks efficiently.
2. **Java HashMap Documentation**
(https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html)
- Helped in choosing HashMap to store tasks using taskId as key, ensuring fast lookup and preventing duplicates.
3. **Stack Overflow**
- *Search Used:* “Java return list filtered by condition” 
- Provided examples for filtering collections, which helped implement getTasksByStatus.
---
### AI / Internet Source Disclosure
- ***Tool used:*** ChatGPT (GPT-5, OpenAI, March 2026) 
- ***Prompt 1:*** 
 *“"I am implementing a `TaskManager` class in Java. I need to store `Task` objects, prevent duplicate task IDs, return `null` when a task is not found, and return a `List<Task>` from `getTasksByStatus`. What internal data structure should I use and why?”*
- **Purpose:** To decide which internal data structure would best satisfy the assignment requirements for storing tasks, checking duplicates, searching by ID, and filtering tasks by status. 
- **AI Answer (Excerpt):**
```
Use a Map<Integer, Task> for the main storage, usually a HashMap<Integer, Task>.
Why this fits your requirements:
Prevent duplicate task IDs: the task ID can be the key, and a map naturally allows only one value per key. Before adding, check whether the ID already exists.
Find a task by ID: lookup is simple and fast with map.get(taskId).
Return null when not found: HashMap.get(key) already returns null if the key does not exist, which matches your requirement.
Store Task objects cleanly: each key maps directly to a Task.
For getTasksByStatus, you can iterate through map.values(), collect matching tasks into a List<Task>, and return that list.
A good design is:
HashMap<Integer, Task> tasksById as the main internal structure
ArrayList<Task> only for the result returned by getTasksByStatus
Example idea:
private Map<Integer, Task> tasks = new HashMap<>();
Why not just use a List<Task> internally?
You would have to loop through the whole list to check duplicates
Finding by ID would be slower
Removing or updating by ID would also be less efficient
So the best choice is:
Use a HashMap<Integer, Task> internally, and create an ArrayList<Task> when returning tasks filtered by status.
I can also show you how the full TaskManager class would look with this design.
```
- **Adaptation:** I adapted this advice by using a HashMap<String, Task> instead of HashMap<Integer, Task> because my assignment uses task IDs like "T1" and "T2", which are strings. I used the map to store tasks by taskId, check for duplicates before inserting, return a task with get(taskId) in findTask, and iterate over map.values() to build a List<Task> in getTasksByStatus.
- ***Prompt 2:***
*“In my setStatus(String status) method, I am checking for valid values (OPEN, IN_PROGRESS, COMPLETE). what conditional logic correctly sets UNKNOWN for invalid inputs?”* 
- **Purpose:** To make sure my setStatus method followed the assignment rule exactly by allowing only valid status values and changing all invalid inputs to UNKNOWN.
- **AI Answer (Excerpt):**
```
To correctly set "UNKNOWN" for invalid inputs, your condition should accept only the valid values and reject everything else.
✅ Correct Conditional Logic
public void setStatus(String status) {
   if (status != null &&
       (status.equals("OPEN") ||
        status.equals("IN_PROGRESS") ||
        status.equals("COMPLETE"))) {
       this.status = status;
   } else {
       this.status = "UNKNOWN";
   }
}
✔ Why this works
Uses OR (||) → allows any one valid status
Includes a null check → prevents NullPointerException
Any value outside the three valid ones → automatically becomes "UNKNOWN"
❌ What to avoid
// WRONG
if (status.equals("OPEN") &&
   status.equals("IN_PROGRESS") &&
   status.equals("COMPLETE"))
This will always be false → status will always become "UNKNOWN"
💡 Cleaner Alternative (Recommended)
private static final Set<String> VALID_STATUSES =
   Set.of("OPEN", "IN_PROGRESS", "COMPLETE");
public void setStatus(String status) {
   this.status = (status != null && VALID_STATUSES.contains(status))
                 ? status
                 : "UNKNOWN";
}
✅ Final Answer (short version)
Use:
if (status != null &&
  (status.equals("OPEN") ||
   status.equals("IN_PROGRESS") ||
   status.equals("COMPLETE")))
Otherwise → set "UNKNOWN".
If you want, I can help you turn this into an enum-based design
```
- **Adaptation:**
- ***Prompt 3:***
```
*“Explain why TaskManager collaborates with Task, but Task does not collaborate with TaskManager. Explain based on these CRC Cards:
1. Class: Tasks
Responsibilities:
	• store task information
	• update task status
	• provide task details
2. Class: TaskManager
Responsibilities:
	• store tasks
	• add new tasks
	• find a task by ID
	• return tasks by status”*
```
- **Purpose:** To help me write the required crccards.md explanation in a way that clearly connects the collaboration to the responsibilities listed in the CRC cards.
- **AI Answer (Excerpt):**
```
This is a classic CRC (Class–Responsibility–Collaborator) design question about direction of dependency.
🧠 Key Idea (One-line answer)
TaskManager collaborates with Task because it manages and uses Task objects, but Task does not collaborate with TaskManager because a task is independent and should not know about the system managing it.
📦 Role of Each Class
🔹 Task
Responsibilities:
Store its own data (ID, name, status)
Update its own status
Provide its own details
👉 This is a self-contained object (no external control logic)
🔹 TaskManager
Responsibilities:
Store multiple tasks
Add tasks
Search tasks
Filter tasks by status
👉 This is a controller/manager class that operates on Task objects
🔗 Why TaskManager Collaborates with Task
TaskManager stores Task objects
Calls methods like:
task.getId();
task.getStatus();
task.setStatus();
Needs Task to:
retrieve information
update status
filter results
👉 So, TaskManager depends on Task → collaboration exists
🚫 Why Task Does NOT Collaborate with TaskManager
A Task:
does not need to know where it is stored
does not need to manage other tasks
does not perform searching or filtering
👉 It only manages its own state
⚠️ Design Principle Behind This
✅ Low Coupling
Task is independent → reusable and simple
If Task knew about TaskManager, it would create tight coupling
✅ Single Responsibility Principle (SRP)
Task → handles task data
TaskManager → handles task management
🚨 What would go wrong if Task collaborated with TaskManager?
Example (bad design):
class Task {
   TaskManager manager; // ❌ bad
   void deleteMyself() {
       manager.removeTask(this);
   }
}
Problems:
Circular dependency
Harder to test and reuse
Breaks clean architecture
🎯 Final Explanation (Exam-ready)
TaskManager collaborates with Task because it is responsible for storing, adding, searching, and filtering tasks, which requires it to interact with Task objects and call their methods. However, Task does not collaborate with TaskManager because it is only responsible for maintaining its own data and behavior, such as updating status and providing details. Allowing Task to depend on TaskManager would violate the single responsibility principle and increase coupling, making the design less modular and harder to maintain.
```
- **Adaptation:** I adapted this explanation into my own crccards.md response by focusing on the actual responsibilities from the assignment. In my final answer, I explained that TaskManager collaborates with Task because it stores and searches Task objects and checks their status, while Task only represents a single task and manages its own data, so it does not need to know anything about TaskManager.
---
