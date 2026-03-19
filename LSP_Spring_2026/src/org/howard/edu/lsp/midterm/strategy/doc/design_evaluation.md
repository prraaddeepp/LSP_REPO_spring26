# Design Evaluation of PriceCalculator

The original `PriceCalculator` class is functional for a small number of customer types, but its design introduces several object-oriented issues that make the system difficult to maintain, extend, and scale as it evolves.

## 1. Overuse of Conditional Logic

The class relies on multiple `if` statements to determine pricing behavior based on the customer type. While this approach may work initially, it becomes problematic as more customer categories or discount rules are added. Each new rule requires modifying the existing method, making the code longer, more complex, and harder to understand.

## 2. Violation of the Open/Closed Principle

The current design violates the Open/Closed Principle, which states that a class should be open for extension but closed for modification. In this implementation, adding a new pricing rule (for example, a seasonal discount or a new membership level) requires editing the existing `PriceCalculator` code. This increases the risk of introducing bugs and makes the system less stable over time.

## 3. Poor Extensibility

Because all pricing logic is centralized in a single method, the system is not easily extensible. As the number of pricing rules grows, the method becomes cluttered with more conditions, making it harder to manage and extend. A better design would allow new pricing strategies to be added without modifying existing code.

## 4. Tight Coupling of Logic

The pricing rules are tightly coupled to the `PriceCalculator` class. This means the class is directly responsible for all discount behaviors, rather than delegating them to separate components. This reduces flexibility and makes it harder to reuse or modify individual pricing rules independently.

## 5. Reduced Maintainability

As the method grows with additional conditions, it becomes harder to read, debug, and maintain. Developers must understand all existing conditions before making changes, which increases the likelihood of errors. Even small updates can affect multiple parts of the logic.

## 6. Limited Reusability and Testability

Because all discount logic is grouped together, it is difficult to test each pricing rule independently. Individual discount behaviors cannot be reused in other parts of the system without extracting them manually. This reduces modularity and makes unit testing less effective.

## Conclusion

Overall, the design of the `PriceCalculator` class is not suitable for a growing system. Its reliance on conditional logic, violation of the Open/Closed Principle, tight coupling, and lack of modularity make it difficult to maintain and extend. A better approach is to use the Strategy Pattern, where each pricing rule is implemented as a separate class. This allows new behaviors to be added without modifying existing code, resulting in a more flexible, maintainable, and scalable design.