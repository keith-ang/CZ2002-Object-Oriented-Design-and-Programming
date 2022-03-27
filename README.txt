The assignment for your group will be to design and develop a : Restaurant Reservation and Point of Sale System (RRPSS).
RRPSS is an application to computerize the processes of making reservation, recording of orders and displaying of sale records. 
It will be solely used by the restaurant staff.

The following are information about the application:
a) Menu items should be categorized according to its type, eg, Main course, drinks, dessert, etc.
b) Menu items can be added with details like name, description, price.
c) Promotional set package comes in a single package price with descriptions of the items to be served.
d) A customer may order a set package and ala carte menu items as well.
e) An order should indicate the staff who created the order.
f) Staff information can be in the form of name, gender, employee ID and job title.
g) Reservation is made by providing details like date, time, #pax, name, contact, etc. The system should check availability and allocate a suitable table.
h) When a reservation is made, a table is reserved till the reservation booking is removed.
i) Table comes in different seating capacity, in even sizes, with minimum of 2 and maximum of 10 pax ("Persons At Table").
j) Order invoice can be printed to list the order details (eg, table number, timestamp) and a complete breakdown of order items details with taxes details.
k) Discounts can be given to customers who hold membership of the restaurant or other entities.
l) Sale revenue report will detail the period, individual sale items (either ala carte or promotional items) and total revenue.

Functional Requirements:
1. Create/Update/Remove menu item
2. Create/Update/Remove promotion
3. Create order
4. View order
5. Add/Remove order item/s to/from order
6. Create reservation booking
7. Check/Remove reservation booking
8. Check table availability
9. Print order invoice
10. Print sale revenue report by period (eg day or month)

Assumptions :
(1) Reservation can only be made in advance. Reservation will be automatically removed XX minutes after the actual booking time*.
(2) The currency will be in Singapore Dollar (SGD) and Good and Services Tax (GST) and service charge must be included in the order invoice.
(3) Once an order invoice is printed*, it is assumed that payment has been made and the table is vacated.
(4) Customer with membership card will be entitled to discount.
(5) There is no requirement for access control and there is no need for authentication (login/logout) in order to use the application.
(6) There is no need to interface with external system, eg Payment, printer, etc.



=============== CZ2002 Object Oriented Design and Programming ===============
================================ Assignment =================================

----------------------------- Group Information -----------------------------
Tutorial Group: MACS
Group Members:
Ang Jun Yi, Keith
Celeste Sew Hui Ting (QIU HUI TING)
Say Yueyang, Symus
Su Xinhui
Wong Li Wen

-------------------------------- Navigation ---------------------------------
Root Folder
|-- Code
|   -- cz2002
|      - *.java
|      - *.class
|   -- src
|      - *.csv
|-- Diagrams
|   - *.jpg
|-- doc
|   - <Javadoc Files>
|- README.txt

------------------------------ Code Execution -------------------------------
- Open a Powershell Window/Command Prompt (Windows) in the directory "Code"
- Ensure that the following .csv files are located in the "src" directory
	CustomerList.csv
	FoodList.csv
	StaffList.csv
- Run the following commands:
	javac .\cz2002\*.java
	java cz2002.RRPSS
