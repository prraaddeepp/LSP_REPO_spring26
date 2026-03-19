# Design Evaluation of PriceCalculator

The original `PriceCalculator` class has a design that works for a small number of customer types, but it becomes difficult to maintain and extend as the system grows.

The main problem is that the class contains multiple `if` statements for different pricing behaviors. Each time a new customer type or discount rule is added, the class must be modified. This makes the design less flexible and violates the idea that classes should be open for extension but closed for modification.

The discount logic is also tightly grouped inside one method, which makes testing and reusing individual discount behaviors harder. A better design is to separate each pricing rule into its own strategy class so new pricing behaviors can be added without changing the calculator itself.