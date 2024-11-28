import managers.AuthenticationManager;
import models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String ORDERS_FILE = "orders.txt";
    private static final String STAFF_FILE = "delivery_staff.txt";
    private static final String CUSTOMERS_FILE = "customers.txt";
    private static final String FOOD_MANAGERS_FILE = "food_managers.txt";

    public List<DeliveryStaff> loadDeliveryStaff() {
        List<DeliveryStaff> staffList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(STAFF_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) { // Added for password and available flag
                    DeliveryStaff staff = new DeliveryStaff(parts[0], parts[1]);
                    staff.setAvailable(Boolean.parseBoolean(parts[2])); // Handle available flag
                    String password = parts[3]; // Extract password
                    AuthenticationManager authManager = new AuthenticationManager();
                    authManager.registerUser(parts[1], password); // Register user with password
                    staffList.add(staff);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing staff file found. Starting with empty staff list.");
        } catch (IOException e) {
            System.err.println("Error reading staff file: " + e.getMessage());
        }
        return staffList;
    }

    public void saveFoodManagers(List<FoodManager> foodManagers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FOOD_MANAGERS_FILE))) {
            for (FoodManager manager : foodManagers) {
                String password = AuthenticationManager.getUserPassword(manager.id());
                writer.write(String.format("%s,%s,%s\n",
                        manager.name(),
                        manager.id(),
                        password != null ? password : ""
                ));
            }
            System.out.println("Saved " + foodManagers.size() + " food managers to " + FOOD_MANAGERS_FILE);
        } catch (IOException e) {
            System.err.println("Error saving food managers: " + e.getMessage());
        }
    }

    public List<FoodManager> loadFoodManagers() {
        List<FoodManager> foodManagerList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FOOD_MANAGERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) { // Added for password
                    FoodManager foodManager = new FoodManager(parts[0], parts[1]);
                    AuthenticationManager authManager = new AuthenticationManager();
                    authManager.registerUser(parts[1], parts[2]);
                    foodManagerList.add(foodManager);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing food managers file found. Starting with empty food managers list.");
        } catch (IOException e) {
            System.err.println("Error reading food managers file: " + e.getMessage());
        }
        return foodManagerList;
    }

    public List<Order> loadOrders() {
        List<Order> orders = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ORDERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    Order order = new Order(
                            parts[0],
                            parts[1],
                            Integer.parseInt(parts[2]),
                            Boolean.parseBoolean(parts[3])
                    );
                    orders.add(order);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing orders file found. Starting with empty order list.");
        } catch (IOException e) {
            System.err.println("Error reading orders file: " + e.getMessage());
        }
        return orders;
    }

    public List<Customer> loadCustomers() {
        List<Customer> customerList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    Customer customer = new Customer(parts[0], parts[1]);
                    AuthenticationManager authManager = new AuthenticationManager();
                    authManager.registerUser (parts[1], parts[2]);
                    customerList.add(customer);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing customers file found. Starting with empty customer list.");
        } catch (IOException e) {
            System.err.println("Error reading customers file: " + e.getMessage());
        }
        return customerList;
    }

    public void saveDeliveryStaff(List<DeliveryStaff> staffList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STAFF_FILE))) {
            for (DeliveryStaff staff : staffList) {
                String password = AuthenticationManager.getUserPassword(staff.id());
                writer.write(String.format("%s,%s,%b,%s\n",
                        staff.name(),
                        staff.id(),
                        staff.isAvailable(),
                        password != null ? password : ""
                ));
            }
            System.out.println("Saved " + staffList.size() + " delivery staff members to " + STAFF_FILE);
        } catch (IOException e) {
            System.err.println("Error saving delivery staff: " + e.getMessage());
        }
    }

    public void saveOrders(List<Order> orders) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ORDERS_FILE))) {
            for (Order order : orders) {
                writer.write(String.format("%s,%s,%d,%b\n",
                        order.getCustomerName(),
                        order.getFoodType(),
                        order.getRemainingTicks(),
                        order.isCompleted()
                ));
            }
            System.out.println("Saved " + orders.size() + " orders to " + ORDERS_FILE);
        } catch (IOException e) {
            System.err.println("Error saving orders: " + e.getMessage());
        }
    }

    public void saveCustomers(List<Customer> customers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CUSTOMERS_FILE))) {
            for (Customer customer : customers) {
                String password = AuthenticationManager.getUserPassword(customer.id());
                writer.write(String.format("%s,%s,%s\n",
                        customer.name(),
                        customer.id(),
                        password != null ? password : ""
                ));
            }
            System.out.println("Saved " + customers.size() + " customers to " + CUSTOMERS_FILE);
        } catch (IOException e) {
            System.err.println("Error saving customers: " + e.getMessage());
        }
    }
}