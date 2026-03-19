# Improved Design Using CRC Cards

Class: Order

Responsibilities:
- Store order data such as customer name, email, item, and price
- Provide access to order information
Collaborators:
- OrderProcessor

Class: TaxCalculator

Responsibilities:
- Calculate tax for an order
Collaborators:
- OrderProcessor
- Order

Class: DiscountService

Responsibilities:
- Apply discount rules to an order total
Collaborators:
- OrderProcessor
- Order

Class: ReceiptPrinter

Responsibilities:
- Print or generate a receipt for an order
Collaborators:
- OrderProcessor
- Order

Class: OrderRepository

Responsibilities:
- Save order information to a file or storage system
Collaborators:
- OrderProcessor
- Order

Class: EmailService

Responsibilities:
- Send order confirmation messages
Collaborators:
- OrderProcessor
- Order

Class: OrderLogger

Responsibilities:
- Log order processing activity
Collaborators:
- OrderProcessor

Class: OrderProcessor

Responsibilities:
- Coordinate the overall order workflow
- Ask the appropriate helper classes to perform each task
Collaborators:
- Order
- TaxCalculator
- DiscountService
- ReceiptPrinter
- OrderRepository
- EmailService
- OrderLogger