# PizzaDeliveryApp

## Overview
The Pizza Delivery App is a simple Java-based application designed to manage pizza delivery operations. It allows users to add delivery staff, place orders, and track the status of those orders. The application periodically saves the current state of delivery staff and orders to text files, ensuring that data is not lost between sessions.

## Features

- **Manage Delivery Staff**: Add new delivery staff members and track their availability.
- **Place Orders**: Customers can place orders for different types of pizzas, which are then assigned to available delivery staff.
- **Track Orders**: View the current status of all orders, including customer name, pizza type, remaining delivery time, and assigned delivery staff.
- **Periodic Data Saving**: Automatically saves the state of delivery staff and orders every 5 ticks to prevent data loss.
- **File Management**: Loads and saves delivery staff and order data from/to text files.

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- File I/O for data persistence

## File Structure

- **DeliveryStaff.java**: Class representing delivery staff members with attributes such as name, ID, and availability status.
- **Order.java**: Class representing customer orders with attributes including customer name, pizza type, remaining delivery time, completion status, and assigned delivery staff.
- **PizzaType.java**: Enum defining different types of pizzas and their respective delivery times.
- **FileManager.java**: Handles loading and saving of delivery staff and order data to/from text files.
- **DeliveryApp.java**: Main application class that provides the user interface and handles user interactions.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) installed on your machine.
- A text editor or Integrated Development Environment (IDE) for Java development (e.g., IntelliJ IDEA, Eclipse).

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Amadeus0004/PizzaDeliveryApp.git

## Usage

1. **Starting the Application**: Upon starting the application, you will be presented with a menu.
2. **Menu Options**:
- **Add Delivery Staff**: Enter the name and ID of the new delivery staff member.
- **Place Order**: Enter the customer name and select the type of pizza to order. The order will be assigned to the first available delivery staff.
- **Show Orders**: Displays all current orders with details such as customer name, pizza type, remaining delivery time, and completion status.
- **Show Employees**: Displays a list of all delivery staff members along with their availability status.
- **Close Store**: Saves all current data and exits the application.
3. **Data Persistence**: The application saves the state of delivery staff and orders every 5 ticks, ensuring that data is not lost if the application is closed unexpectedly.

## Example Usages

- When you choose to add delivery staff, you will be prompted to enter the staff's name and ID. If a staff member with the same ID already exists, you will be notified.
- While placing an order, you will select from a predefined list of pizza types. If no delivery staff are available, you will be informed to try again later.

## Contributing
Contributions are welcome! If you have suggestions for improvements or new features, please open an issue or submit a pull request.

## License
This project is licensed under the MIT License. See the LICENSE file for details.
