# Development Log-Question 2
```
**Name:** Pradeep Lamichhane 
**Course:** Large Scale Programming (Spring 2026) 
**Midterm – Question 2**
```
---
### Primary References
1. **Riel, A. J. (1996). *Object-Oriented Design Heuristics*. Addison-Wesley.**
- Referenced for identifying poor design practices and explaining why the current class structure is problematic.
2. **Object-Oriented Design Principles**
(https://en.wikipedia.org/wiki/SOLID)
- Used to understand principles like Single Responsibility and Open/Closed Principle for evaluating the design.
3. **Stack Overflow**
(https://stackoverflow.com)
- *Search Used:* why is single responsibility principle important
- Helped reinforce understanding of maintainability and extensibility issues.
---
### AI / Internet Source Disclosure
- ***Tool used:*** ChatGPT (GPT-5, OpenAI, March 2026) 
- ***Prompt 1:***
```
 *“Explain problems in this Java class violating OOP principles:
import java.io.FileWriter;
import java.util.Date;
public class OrderProcessor {
   // Order data (poor encapsulation)
   public String customerName;
   public String email;
   public String item;
   public double price;
   public void processOrder() {
       // calculate tax
       double tax = price * 0.07;
       double total = price + tax;
       // print receipt
       System.out.println("Customer: " + customerName);
       System.out.println("Item: " + item);
       System.out.println("Total: " + total);
       // save order to file
       try {
           FileWriter writer = new FileWriter("orders.txt", true);
           writer.write(customerName + "," + item + "," + total + "\n");
           writer.close();
       } catch(Exception e) {
           e.printStackTrace();
       }
       // send confirmation email
       System.out.println("Sending confirmation email to " + email);
       // apply discount
       if(price > 500) {
           total = total * 0.9;
       }
       // log activity
       System.out.println("Order processed at " + new Date());
   }
}
”*
```
- **Purpose:**  To analyze the provided `OrderProcessor` class and identify violations of object-oriented design principles so that I could clearly explain them in `evaluation.md`.
- **AI Answer (Excerpt):**
```
This class violates several core OOP principles.
The biggest problem is poor encapsulation. The fields customerName, email, item, and price are all public, so any other class can change them directly. That means the object cannot protect its own state or validate values like a negative price or an invalid email.
It also violates the Single Responsibility Principle. OrderProcessor is doing too many unrelated jobs in one class and one method:
storing order data
calculating tax
applying discount
printing a receipt
saving to a file
sending an email
logging activity
These are separate responsibilities and should not all live inside one method.
There is also tight coupling to specific implementations. The class directly uses FileWriter, System.out.println, and Date. That makes it harder to test, harder to change, and less flexible. For example, if later you want to save orders to a database instead of a file, you would have to modify this class directly.
It breaks the Open/Closed Principle too. If tax rules change, discount rules change, logging changes, or email sending changes, you must edit processOrder(). A better design would allow these behaviors to be extended through separate classes instead of constantly modifying one large method.
There is a low cohesion problem as well. A cohesive class should focus on one clear purpose, but this class mixes business logic, input/output, persistence, notification, and logging. Those concerns do not naturally belong together.
Another issue is order of logic. The receipt is printed and the order is saved before the discount is applied. That means the displayed total and saved total may be wrong if the price is over 500. So the method is not only poorly designed but also logically inconsistent.
It also lacks abstraction. There is no separate Order object representing the order itself, and no separate services like ReceiptPrinter, OrderRepository, or EmailService. Everything is dumped into one procedural method instead of being modeled with interacting objects.
A cleaner OOP design would separate responsibilities like this:
Order class: stores customer, item, and price safely using private fields
PricingService or DiscountService: calculates tax and discounts
ReceiptPrinter: prints receipt
OrderRepository: saves order
EmailService: sends confirmation
Logger: logs processing activity
So in summary, the class mainly violates:
Encapsulation
Single Responsibility Principle
Low cohesion
Open/Closed Principle
Abstraction
Loose coupling
A short exam-style answer would be:
OrderProcessor violates OOP because it exposes data through public fields, reducing encapsulation, and combines many unrelated responsibilities in one class, violating single responsibility. It is tightly coupled to file handling, console output, email notification, and logging, making it hard to test and maintain. It also has low cohesion and poor abstraction because it does not separate order data from processing services. Additionally, the discount is applied after printing and saving, which can lead to incorrect results.
```
- **Adaptation:** I adapted this explanation by writing my own evaluation in evaluation.md, focusing specifically on issues visible in the given code. I explained that the class violates encapsulation because of public fields, violates the Single Responsibility Principle by combining multiple unrelated tasks (tax calculation, file writing, printing, email, logging), and has tight coupling to specific implementations like FileWriter and System.out. I also included the logical flaw where the discount is applied after printing and saving, which can lead to incorrect output.
- ***Prompt 2:***
*“Give CRC card redesign for order processing system”* 
- **Purpose:** To redesign the system into a cleaner object-oriented structure and create proper CRC cards for design.md.
- **AI Answer (Excerpt):**
```
Here is a clean CRC card redesign for the order processing system.
CRC Card Redesign for Order Processing System
1. Class: Order
Responsibilities:
store order details
store customer information
store item and price
provide order data to other classes
Collaborators:
PricingService
ReceiptGenerator
OrderRepository
NotificationService
2. Class: PricingService
Responsibilities:
calculate tax
apply discounts
calculate final total
Collaborators:
Order
3. Class: ReceiptGenerator
Responsibilities:
generate receipt details
display or format order summary
show final total to user
Collaborators:
Order
PricingService
4. Class: OrderRepository
Responsibilities:
save order records
write order data to file or database
retrieve stored orders if needed
Collaborators:
Order
5. Class: NotificationService
Responsibilities:
send confirmation email
notify customer after successful processing
Collaborators:
Order
6. Class: LoggerService
Responsibilities:
log order processing activity
record processing time and events
track errors if processing fails
Collaborators:
OrderProcessor
7. Class: OrderProcessor
Responsibilities:
coordinate the order workflow
call pricing, receipt, storage, notification, and logging services
ensure order is processed in correct sequence
Collaborators:
Order
PricingService
ReceiptGenerator
OrderRepository
NotificationService
LoggerService
Why this redesign is better
This redesign improves the system because each class has one clear responsibility:
Order handles only order data
PricingService handles pricing rules
ReceiptGenerator handles receipt creation
OrderRepository handles storage
NotificationService handles emails
LoggerService handles logging
OrderProcessor only manages the workflow
This gives:
better encapsulation
better single responsibility
lower coupling
higher cohesion
easier testing and maintenance
```
- **Adaptation:** I adapted this redesign by creating my own CRC cards in design.md, organizing the system into separate classes such as Order, PricingService, ReceiptGenerator, OrderRepository, and NotificationService. I ensured that each class had a single responsibility and clearly defined collaborators. I also simplified and adjusted the responsibilities to match the level of detail expected in the assignment while maintaining separation of concerns.
- ***Prompt 3:***
*“Explain single responsibility principle with examples.”*
- **Purpose:** To strengthen my understanding of the Single Responsibility Principle and apply it correctly when evaluating the design and proposing improvements.
- **AI Answer (Excerpt):**
```
🧠 Single Responsibility Principle (SRP)
Definition:
A class should have only one reason to change, meaning it should have only one responsibility or job.
🎯 Simple Intuition
👉 One class = one job
👉 If a class is doing multiple unrelated things → it violates SRP
❌ Bad Example (Violates SRP)
class OrderProcessor {
   public void processOrder() {
       // calculate total
       // print receipt
       // save to file
       // send email
   }
}
🚨 Problems:
Handles pricing, printing, saving, and emailing
If any one of these changes → you must modify this class
Hard to test and maintain
✅ Good Example (Follows SRP)
class Order {
   private double price;
   public double getPrice() { return price; }
}
class PricingService {
   public double calculateTotal(Order order) {
       return order.getPrice() * 1.07;
   }
}
class ReceiptPrinter {
   public void print(Order order, double total) {
       System.out.println("Total: " + total);
   }
}
class EmailService {
   public void sendEmail(String email) {
       System.out.println("Sending email to " + email);
   }
}
✔ Why this is better:
Each class has one clear responsibility
Changes are isolated:
Tax changes → only PricingService
Email changes → only EmailService
Easier to test and extend
📌 Real-Life Analogy
Think of a restaurant:
👨‍🍳 Chef → cooks food
🧾 Cashier → handles billing
🚚 Delivery → delivers food
If one person did everything → messy, inefficient ❌
Separate roles → clean and efficient ✅
⚠️ How to Identify SRP Violations
Ask:
“Does this class do more than one thing?”
“Are there multiple reasons this class might change?”
If YES → SRP is violated
🎯 Another Example (Common in Interviews)
❌ Bad
class User {
   String name;
   void saveToDatabase() { }
   void sendEmail() { }
}
👉 Mixes:
data
database logic
communication logic
✅ Good
class User {
   String name;
}
class UserRepository {
   void save(User user) { }
}
class EmailService {
   void send(User user) { }
}
🧾 Final Exam-Ready Answer
The Single Responsibility Principle states that a class should have only one responsibility and one reason to change. This improves maintainability, readability, and testability. For example, a class that processes orders should not also handle printing receipts or sending emails. Instead, these responsibilities should be separated into different classes such as a pricing service, receipt printer, and email service. This reduces coupling and makes the system easier to modify and extend.
```
- **Adaptation:** I applied this concept directly in my evaluation by explaining that OrderProcessor violates the Single Responsibility Principle because it performs multiple unrelated tasks in one method. I used this understanding to justify why the class should be broken into smaller classes such as pricing, storage, and notification components. I also incorporated this principle into my redesigned CRC cards by ensuring each class has one clear responsibility.
---
