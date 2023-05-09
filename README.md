# dainty-knee-9326

# Consumer Management System
The Consumer Management System is a Java-based application that allows both consumers and administrators to manage various aspects of the billing and complaint processes. The system includes two interfaces: the Consumer UI and the Admin UI.

# Consumer UI
The Consumer UI is designed for individual consumers to manage their accounts, view their bills, pay their bills, and file complaints. The system includes the following functionalities for consumers:

**Sign Up:** The consumer can create a new account by providing personal information such as name, address, phone number, and email address.

**Log In:** Once the consumer has an account, they can log in by providing their username and password.

**View Profile:** The consumer can view their profile information, including personal details.

**View Bills:** The consumer can view their bills and see details such as total amount due, due date, and payment status.

**Pay Bill:** The consumer can pay their bills.

**File Complaint:** The consumer can file a complaint if they encounter any issues with their Connection, billing, payment, or Any others.

# Admin UI
**The Admin UI is designed for system administrators to manage consumer accounts, bills, and complaints. The system includes the following functionalities for admins:**

**View Consumers:** The admin can view a list of all consumers registered in the system, along with their personal and billing information.

**View Bills:** The admin can view a list of all bills generated in the system, along with details such as consumer name, total amount due, and payment status.

**View Consumer Bill:** The admin can view the bill of a specific consumer by providing the consumer ID.

**View Paid and Pending Bills:** The admin can view a list of paid and pending bills separately.

**Create Bill:** The admin can create a new bill for a consumer by providing the consumer ID and bill details such as units consumed and total amount due if there is already a pending bill of a consumer then its redirect update previous bill.

**View Complaints:** The admin can view a list of all complaints filed by consumers, along with details such as consumer name, complaint type, and status.

**Resolve Complaint:** The admin can resolve a complaint by updating its status as resolved or in process.

# Services
**The Consumer Management System includes several services to manage various aspects of the billing and complaint processes. These services include:**

**ConsumerService:** Manages consumer accounts, including registration, login, and profile management.

**BillService:** Manages billing processes, including bill generation, payment, and status tracking.

**TransactionService:** Manages transactions made by consumers, including payment and refund processes.

**ComplaintService:** Manages complaint processes, including filing, tracking, and resolution.

# Classes
**The Consumer Management System includes several classes to store and manage data related to consumers, bills, transactions, and complaints. These classes include:**

**Consumer:** Stores information related to individual consumers, including personal details and billing information.

**Bill:** Stores information related to bills generated for individual consumers, including units consumed, total amount due, and payment status.

**Transaction:** Stores information related to transactions made by individual consumers, including payment and refund details.

**Complaint:** Stores information related to complaints filed by individual consumers, including type, description, and status.

# Database 
The Consumer Management System uses a relational database to store and manage data related to consumers, bills, transactions, and complaints.

The database is designed using the MySQL database management system and includes several tables to store information related to different aspects of the system.

The database is hosted on a remote server and is accessed by the application through a JDBC driver.

![ER](https://github.com/RADHIKESHS/dainty-knee-9326/assets/116291105/a8eba950-b3c4-456e-96db-b2fbd3421c64)

# Exceptions
**The Consumer Management System includes several custom exceptions to handle errors and exceptions that may occur during runtime. These exceptions include:**

**BillNotFoundException:** Thrown if a requested bill is not found in the system.

**SomethingWentWrongException:** Thrown if an unexpected error occurs during runtime.

**ConsumerNotFoundException:** Thrown if a requested consumer is not found in the system.

**ComplaintNotFoundException:** Thrown if a requested complaint is not found in the system.

**InvalidDataException:** Thrown if the provided data is invalid or does not meet the system's requirements.

# Getting Started
To get started with the Consumer Management System, you will need to have Java 8 or higher installed on your system. You can download Java from the official Java website.

Once you have Java installed, you can clone the project repository from GitHub using the following command:

# Bash
**Copy code**
git clone **[https://github.com/yourusername/consumer-management-system.git](https://github.com/RADHIKESHS/dainty-knee-9326)**
After cloning the repository, you can open it in your favorite Java IDE and run the ConsumerManagementSystemApplication class to start the application.


**Thank you for using the Consumer Management System!**

