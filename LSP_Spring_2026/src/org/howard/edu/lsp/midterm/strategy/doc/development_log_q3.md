# Development Log-Question 3 
```
**Name:** Pradeep Lamichhane 
**Course:** Large Scale Programming (Spring 2026) 
**Midterm – Question 3**
```
---
### Primary References
1. **Strategy Pattern – Refactoring Guru**
(https://refactoring.guru/design-patterns/strategy)
- Provided clear explanation and structure for implementing the Strategy Pattern correctly.
2. **Java Design Patterns**
(https://www.baeldung.com/java-strategy-pattern)
- Used to understand real-world implementation of Strategy Pattern in Java.
3. **Oracle Java Documentation**
(https://docs.oracle.com/javase/tutorial/java/IandI/polymorphism.html)
- Helped understand polymorphism and interface-based design. 
---
### AI / Internet Source Disclosure
- ***Tool used:*** ChatGPT (GPT-5, OpenAI, March 2026) 
- ***Prompt 1:*** 
 *“I am refactoring this Java `PriceCalculator` class for a midterm. Help me explain why using multiple `if` statements for customer pricing causes extensibility and maintenance problems.”*
- **Purpose:**  To understand and clearly explain the design flaws in the original `PriceCalculator` implementation for the `design_evaluation.md` file.
- **AI Answer (Excerpt):**
```
Using multiple if statements for customer pricing is a problem because the class becomes harder to extend, maintain, and test as pricing rules grow.
If a PriceCalculator looks like this:
if (customerType.equals("REGULAR")) {
   // one pricing rule
}
if (customerType.equals("PREMIUM")) {
   // another pricing rule
}
if (customerType.equals("VIP")) {
   // another pricing rule
}
the first issue is poor extensibility. Every time a new customer type is added, such as STUDENT or BUSINESS, you have to go back into the same class and edit the existing code. That means the class is not closed for modification. Instead of adding new behavior cleanly, you keep changing old logic, which increases the chance of breaking something that already works.
The second issue is maintenance difficulty. As more customer categories, discounts, tax rules, or special cases are added, the chain of if statements becomes long and messy. It gets harder to read and understand which rule applies to which customer. Over time, this can turn into “spaghetti logic,” especially if there are nested conditions.
Another problem is higher risk of bugs. With many if statements, developers may accidentally:
forget to handle a new customer type
duplicate logic in multiple places
apply conflicting rules
put conditions in the wrong order
For example, if one condition is too broad, it may run when a more specific rule should have applied.
It also hurts testability. Each pricing branch must be tested separately, and as the number of conditions increases, the number of test cases grows. A change for one customer type may force retesting the whole class.
This design also violates the Open/Closed Principle. The calculator should ideally allow new pricing behaviors to be added without modifying the main class every time. Multiple if statements do the opposite, because each new pricing rule requires editing the existing method.
A better design is to separate pricing rules into different classes, such as:
RegularPricing
PremiumPricing
VipPricing
Then the system can choose the correct pricing strategy based on customer type. This makes the code easier to extend and maintain.
A good midterm-style explanation would be:
Using multiple if statements for customer pricing creates extensibility and maintenance problems because the class must be modified every time a new customer type or pricing rule is introduced. As the number of conditions grows, the code becomes harder to read, test, and debug. This increases the risk of errors and violates the Open/Closed Principle, since the class is not easily extendable without changing existing logic. A better approach is to use separate pricing classes or strategies for each customer type.
```
- **Adaptation:** I adapted this explanation by writing my own evaluation of the PriceCalculator class, focusing on how the repeated if conditions make the system difficult to extend and maintain. I explained that adding a new customer type would require modifying the existing method, which violates the Open/Closed Principle. I also highlighted issues such as reduced readability, increased risk of bugs, and poor scalability as the number of pricing rules grows.
- ***Prompt 2:***
*“How to implement multiple pricing strategies in Java”* 
- **Purpose:** To learn how to refactor the existing design using the Strategy Pattern and correctly implement multiple pricing behaviors in separate classes.
- **AI Answer (Excerpt):**
```
Use the Strategy Pattern.
Instead of putting all pricing rules inside one class with many if statements, create:
one common pricing interface
one class for each pricing rule
a calculator that uses the chosen strategy
1. Create a pricing interface
public interface PricingStrategy {
   double calculatePrice(double basePrice);
}
This says every pricing strategy must know how to calculate a final price.
2. Create separate strategy classes
Regular customer
public class RegularPricing implements PricingStrategy {
   @Override
   public double calculatePrice(double basePrice) {
       return basePrice;
   }
}
Premium customer
public class PremiumPricing implements PricingStrategy {
   @Override
   public double calculatePrice(double basePrice) {
       return basePrice * 0.90; // 10% discount
   }
}
VIP customer
public class VipPricing implements PricingStrategy {
   @Override
   public double calculatePrice(double basePrice) {
       return basePrice * 0.80; // 20% discount
   }
}
3. Create the calculator class
public class PriceCalculator {
   private PricingStrategy pricingStrategy;
   public PriceCalculator(PricingStrategy pricingStrategy) {
       this.pricingStrategy = pricingStrategy;
   }
   public double calculate(double basePrice) {
       return pricingStrategy.calculatePrice(basePrice);
   }
   public void setPricingStrategy(PricingStrategy pricingStrategy) {
       this.pricingStrategy = pricingStrategy;
   }
}
This class does not care whether the customer is regular, premium, or VIP. It just uses whatever strategy is given.
4. Use it in main
public class Main {
   public static void main(String[] args) {
       double basePrice = 100.0;
       PriceCalculator calculator = new PriceCalculator(new RegularPricing());
       System.out.println("Regular price: " + calculator.calculate(basePrice));
       calculator.setPricingStrategy(new PremiumPricing());
       System.out.println("Premium price: " + calculator.calculate(basePrice));
       calculator.setPricingStrategy(new VipPricing());
       System.out.println("VIP price: " + calculator.calculate(basePrice));
   }
}
Output
Regular price: 100.0
Premium price: 90.0
VIP price: 80.0
Why this is better than multiple if statements
With multiple if statements, every time you add a new customer type, you must modify the calculator class.
With strategies:
each pricing rule is in its own class
the calculator stays unchanged
adding a new pricing type is easy
the code is cleaner and easier to test
For example, adding a student discount only needs a new class:
public class StudentPricing implements PricingStrategy {
   @Override
   public double calculatePrice(double basePrice) {
       return basePrice * 0.85;
   }
}
No need to rewrite PriceCalculator.
Midterm explanation
Multiple pricing strategies in Java can be implemented using the Strategy Pattern. A common interface defines the pricing behavior, and each customer type has its own class implementing that interface. The PriceCalculator uses a strategy object instead of hard-coded if statements. This improves extensibility, reduces maintenance problems, and follows the Open/Closed Principle because new pricing rules can be added without modifying existing calculator logic.
Here is the full copy-paste version in one place:
interface PricingStrategy {
   double calculatePrice(double basePrice);
}
class RegularPricing implements PricingStrategy {
   @Override
   public double calculatePrice(double basePrice) {
       return basePrice;
   }
}
class PremiumPricing implements PricingStrategy {
   @Override
   public double calculatePrice(double basePrice) {
       return basePrice * 0.90;
   }
}
class VipPricing implements PricingStrategy {
   @Override
   public double calculatePrice(double basePrice) {
       return basePrice * 0.80;
   }
}
class PriceCalculator {
   private PricingStrategy pricingStrategy;
   public PriceCalculator(PricingStrategy pricingStrategy) {
       this.pricingStrategy = pricingStrategy;
   }
   public double calculate(double basePrice) {
       return pricingStrategy.calculatePrice(basePrice);
   }
   public void setPricingStrategy(PricingStrategy pricingStrategy) {
       this.pricingStrategy = pricingStrategy;
   }
}
public class Main {
   public static void main(String[] args) {
       double basePrice = 100.0;
       PriceCalculator calculator = new PriceCalculator(new RegularPricing());
       System.out.println("Regular price: " + calculator.calculate(basePrice));
       calculator.setPricingStrategy(new PremiumPricing());
       System.out.println("Premium price: " + calculator.calculate(basePrice));
       calculator.setPricingStrategy(new VipPricing());
       System.out.println("VIP price: " + calculator.calculate(basePrice));
   }
}
I can also give you a version using Customer objects and enums.
```
- **Adaptation:** I adapted this approach by implementing a PricingStrategy interface in my code and creating separate classes such as RegularStrategy, MemberStrategy, VIPStrategy, and HolidayStrategy. Each class contains its own pricing logic. I then modified the PriceCalculator class to act as a context that uses a strategy object instead of if statements. I also created a Driver class that switches between strategies and produces the required output format for a base price of 100.0.
---

