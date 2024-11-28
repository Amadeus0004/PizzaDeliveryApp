# Pizza Delivery Management System

## Overview
The Pizza Delivery App is a simple Java-based application designed to manage pizza delivery operations. It allows users to add delivery staff, place orders, and track the status of those orders. The application periodically saves the current state of delivery staff and orders to text files, ensuring that data is not lost between sessions.

## Features
- **Customer Features**:
  - View menu items
  - Place orders for food items
  - Check the status of orders
  
- **Delivery Staff Features**:
  - View available orders for delivery
  - Start delivery and follow navigation instructions
  - Mark orders as completed
  
- **Food Manager Features**:
  - Load and save menu from a file
  - Add new food items to the menu
  - View current menu items
 
## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- File I/O for data persistence

## System Architecture
### Key Components
- **Models**:
  - `User ` Interface: Base interface for all user types
  - `Customer`: Represents customers and their interactions
  - `DeliveryStaff`: Manages delivery operations
  - `FoodManager`: Handles food item management
  - `Order`: Represents individual food orders
  - `FoodItem`: Represents items on the menu

### Authentication System
- Managed by `AuthenticationManager`
- Features user registration, login validation, and password management

### File Management
- Handled by `FileManager`, which manages the persistence of user data and menu items using text files.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) installed on your machine.
- A text editor or Integrated Development Environment (IDE) for Java development (e.g., IntelliJ IDEA, Eclipse).

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Amadeus0004/PizzaDeliveryApp.git

## Usage

1. **Register** as a customer, delivery staff, or food manager.
2. **Login** using your credentials.
3. **Access the menu** based on your role:
   - Customers can place orders and check order status.
   - Delivery staff can view and manage deliveries.
   - Food managers can manage the menu.

## Contributing
Contributions are welcome! If you have suggestions for improvements or new features, please open an issue or submit a pull request.

## License
This project is licensed under the MIT License. See the LICENSE file for details.
