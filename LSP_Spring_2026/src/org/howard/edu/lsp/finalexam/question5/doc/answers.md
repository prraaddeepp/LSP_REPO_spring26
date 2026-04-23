Heuristic 1:

Name:
Keep related data and behavior together in the same class.

Explanation:
This improves readability and maintainability because a class should be responsible for managing the data it owns. In lecture, this idea was explained as placing methods close to the information they use, instead of spreading behavior across unrelated classes. This makes the program easier to understand because someone reading the code can see what an object knows and what it does in one place.

Heuristic 2:

Name:
Hide implementation details and expose only necessary public methods.

Explanation:
This improves maintainability because outside classes should not depend on internal details that may change later. In lecture, this was illustrated through encapsulation, where fields are kept private and public methods provide controlled access. This makes code safer because changes inside a class do not require many changes in other classes.

Heuristic 3:

Name:
Distribute system intelligence horizontally and avoid one class doing too much work.

Explanation:
This improves readability and maintainability because responsibilities are shared among appropriate classes instead of being placed into one large controller or manager class. In lecture, this was discussed as avoiding overly complex classes that know too much or do too much. When responsibilities are distributed properly, each class is smaller, clearer, and easier to test or modify.