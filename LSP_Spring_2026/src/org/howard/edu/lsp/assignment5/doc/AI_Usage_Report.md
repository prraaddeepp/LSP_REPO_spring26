# AI_Usage_Report.md
```
Name: Pradeep Lamichhane 
```
---
### Primary References
1. **Java Documentation – ArrayList**
(https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)
- Used to understand how to store elements dynamically and prevent duplicates using contains().

2. **Java Documentation – Collections Framework**
(https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html)
- Used for sorting elements in toString() and finding max() and min() values.

3. **Stack Overflow**
- *Search Used:* “Java remove integer from ArrayList by value” 
- Helped understand the use of Integer.valueOf() to remove elements correctly.
---
### AI / Internet Source Disclosure
- ***Tool used:*** ChatGPT (GPT-5, OpenAI, April 2026) 
- ***Prompt 1:*** 
 *“I am implementing an IntegerSet class in Java using ArrayList. I need to prevent duplicate elements and support operations like union, intersection, difference, and complement without modifying the original sets. What design approach should I use?”*
- **Purpose:** To decide how to design the IntegerSet class so it behaves like a mathematical set while using ArrayList.
- **AI Answer (Excerpt):**
```
Use an ArrayList<Integer> as the internal structure.
Before adding elements, check if they already exist using contains().
For operations like union and intersection, create a new IntegerSet instead of modifying the existing set.
```
- **Adaptation:** I adapted this advice by using an ArrayList<Integer> to store values, checking for duplicates before insertion, and ensuring all set operations return new IntegerSet objects rather than modifying the original sets.

- ***Prompt 2:***
*“How do I implement union, intersection, difference, and complement methods so they return new sets and do not modify the original sets?”* 
- **Purpose:** To correctly implement set operations according to assignment requirements.
- **AI Answer (Excerpt):**
```
Create a new IntegerSet instance.
Copy elements from the current set.
Add or filter elements based on the operation logic.
Return the new set.
```
- **Adaptation:** I implemented each operation (union, intersect, diff, complement) by creating a new IntegerSet and applying the required logic while preserving the original sets.

- ***Prompt 3:***
*“How should I implement toString() so the output is sorted and formatted like [1, 2, 3]?”*
- **Purpose:** To ensure the output format meets assignment specifications.
- **AI Answer (Excerpt):**
```
Create a copy of the list.
Sort it using Collections.sort().
Return the formatted string.
```
- **Adaptation:** I implemented toString() by copying the internal list, sorting it, and returning the formatted string in ascending order.

- ***Prompt 4:***
*“What is the best way to implement largest() and smallest() methods and handle empty sets?”*
- **Purpose:** To correctly compute maximum and minimum values and handle edge cases.
- **AI Answer (Excerpt):**
```
Use Collections.max() and Collections.min().
Throw an exception if the set is empty.
```
- **Adaptation:** I used Collections.max() and Collections.min() and added checks to throw an exception when the set is empty.
---
