# EiffelStore_WebService
#### Description of the problem
Eiffel Corp. has just acquired IfShare, a company specialized in the sale of products (books, clothes, equipment, etc.) from individuals to individuals. It wishes to make its employees benefit from this service.
In order to manage this service, we have to implement the design and implementation of a distributed Java application , based on Java RMI. Products sold on IfShare can be bought and sold by all Eiffel Corp. employees. Employees can add notes about the products and their status upon resale. The application managing the product database and the one managing the employees run in two different JVMs.
When a person requests to purchase a type of product and it is not available, he or she is placed on a waiting list; as soon as the requested product becomes available, the person is notified and purchases the product. If there are several people on the waiting list, the "first come, first served" principle is applied.
In a second step, Eiffel Corp. wants to open its products to the outside world, enriched by the notes and comments of its employees, and make it accessible to the outside world via a Web service called IfService. It offers for sale products that have been sold at least once within Eiffel Corp. The web service allows users to view product prices, check availability, add products to a shopping cart and purchase them. To make a purchase on the web service, another web service Bank is contacted by IfService to check the availability of funds for the purchase and make the payment. The prices of the products are in Euros, but the university allows sales in any currency of the world, and must provide prices in the currency requested by the buyer. The exchange rates used must be found in real time.
#### Functional specification
It will consist on a website based on Java RMI and the use of Web Services that would:
for employees, system:
● System allows employees to view products list, description and price.
● System allows employees to sell a product.
● System allows employees to check the availability of products.
● System allows employees to purchase a product.
● System allows employees to resale a product.
● System allows employees to add note and rate and their status upon resale.
● System allows employees to request a product not available.
● System allows employees to receive a notification of a requested product when it is available.
For customers:
● System Allow customers to view products list, description , price, rate and comments.
● System allows customers to check the availability of products.
● System allows customers to add products to their shopping cart.
● System allows customers to view their shopping cart.
● System allows customers to change the currency from euro to any other currency.
● System allows customers to create a bank account.
● System allows customers to deposit a sum of money in a bank account.
● System allows customers to purchase products.
#### Technical specification
● The EiffelCorp server and IfShare server run in two different JVMs
● The communication between EiffelCorp server and IfShare server must be via RMI.
● The IfShare cannot modify or manage the employees of EiffelCorp.

#### Global architecture

![image](https://user-images.githubusercontent.com/17782939/151020288-54f382cb-8690-439b-923a-a8be1534f98b.png)


#### Main Structure
Our project is structured in sub-projects:
1. Serveur_IfShare: the RMI Server that offers to the Eiffel Corp employees the possibility to sale and purchase products.
2. Serveur_EiffelCorp: RMI server of EiffelCorp including all the methods to manage Employees
3. WebApp_EiffelCorp: a web application providing GUI for the Two RMI servers, where the employees can connect to the platform, sale, purchase and resale products.
4. ClientTestRMI: a RMI client for the two servers, just for test the services in console.
5. EiffelCorp_BuyService: the Dynamic Web Project that offers to the world the possibility to buy products sold at least once, via web service. This project is also an RMI Client for IfShare, to get the information about the products and a Web Service Client for BankWebService to perform the payment, and a web service client for a conversion service.
6. BankWebService : is a Dynamic Web Project that checks the availability of funds for the purchase, making the payment in more currencies.
7. Client_WebApp: Web application, is the client of the web service that allows customers to see and buy products.
