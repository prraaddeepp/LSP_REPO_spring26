# AI_Usage_Report.md

Name: Pradeep Lamichhane

---

## Primary References

1. **Java Documentation – ArrayList**  
https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html  
- Used to understand dynamic storage, iteration, and duplicate checking using `contains()`.

2. **Java Documentation – Collections Framework**  
https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html  
- Used for sorting elements and retrieving maximum and minimum values using `max()` and `min()`.

3. **Stack Overflow**  
- Search: “Java remove integer from ArrayList by value”  
- Helped clarify correct usage of `Integer.valueOf()` when removing elements.

---

## AI / Internet Source Disclosure

- **Tool used:** ChatGPT (OpenAI, April 2026)

### Prompt 1  
“I am implementing an IntegerSet class in Java using ArrayList. How can I ensure it behaves like a mathematical set and avoids duplicates?”

- **Purpose:** To determine an appropriate internal data structure and design approach.  
- **Summary of AI Response:** Suggested using `ArrayList<Integer>` with duplicate checks using `contains()`.  
- **Adaptation:** Implemented duplicate prevention in the `add()` method.

---

### Prompt 2  
“How should I implement union, intersection, difference, and complement operations so they do not modify the original sets?”

- **Purpose:** To correctly design set operations.  
- **Summary of AI Response:** Recommended creating new set instances for each operation.  
- **Adaptation:** All operations return new `IntegerSet` objects instead of modifying existing ones.

---

### Prompt 3  
“How can I ensure my equals method works even if elements are in different order?”

- **Purpose:** To correctly compare sets.  
- **Summary of AI Response:** Suggested sorting copies before comparison.  
- **Adaptation:** Implemented sorting of temporary lists before comparison.

---

### Prompt 4  
“How should I implement toString() so the output is sorted and formatted correctly?”

- **Purpose:** To match required output format `[1, 2, 3]`.  
- **Summary of AI Response:** Copy list, sort, and return formatted string.  
- **Adaptation:** Implemented sorted output using `Collections.sort()`.

---

### Prompt 5  
“What is the best way to implement largest() and smallest() and handle edge cases?”

- **Purpose:** To correctly handle extreme values and empty sets.  
- **Summary of AI Response:** Use `Collections.max()` and `Collections.min()` with exception handling.  
- **Adaptation:** Implemented both methods with checks for empty sets and exception handling.

---

### Prompt 6  
“How should I test my IntegerSet class using JUnit 5?”

- **Purpose:** To understand how to structure unit tests.  
- **Summary of AI Response:** Suggested writing test cases for each method including normal and edge cases using assertions.  
- **Adaptation:** Created JUnit test cases covering all required scenarios such as duplicates, empty sets, and exception cases.

---

## Additional Notes

- AI assistance was used primarily for design guidance and testing strategies.  
- All implementations and test cases were reviewed, modified, and verified independently.  
- Final code reflects my understanding of Java collections and object-oriented design.