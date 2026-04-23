# AI Usage - Question 1
**Name:** Pradeep Lamichhane
**Final Exam – Question 1**
---
### AI / Internet Source Disclosure
- ***Tool used:*** ChatGPT (GPT-5, OpenAI, April 2026)
- ***Prompt 1:***
```
"Help answer a Java concurrency exam question about shared resources and synchronization."
```
- **Purpose:**
```
To understand how to identify shared resources and explain race conditions clearly.
```
- **How AI Helped:**
```
AI helped clarify what shared resources are and how race conditions occur in multi-threaded programs.
```
- **Reflection:**
```
When I worked through this, I realized I was only thinking about variables individually and not how multiple threads interact with them. I learned that concurrency issues come from shared access without coordination. 
```
- ***Prompt 2:***
```
"Explain whether synchronized methods fix a race condition."
```
- **Purpose:**
```
To evaluate whether different synchronization approaches solve concurrency problems.
```
- **How AI Helped:**
```
AI explained that synchronizing only one method may not protect all shared resources.
```
- **Reflection:**
```
As I analyzed this, I realized that protecting just one method is not enough if the entire operation is not atomic. I learned that the full critical section must be synchronized. 
```
- ***Prompt 3:***
```
"Give an alternative concurrency solution using ReentrantLock."
```
- **Purpose:**
```
To understand a non-synchronized approach for thread safety.
```
- **How AI Helped:**
```
AI provided a simple example of using locks with try-finally blocks.
```
- **Reflection:**
```
When I applied this idea, I realized locks give more control than synchronized methods. I learned how explicit locking can manage concurrency more flexibly. 
```
---
