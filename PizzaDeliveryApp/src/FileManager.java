import java.io.*;
import java.util.*;

public class FileManager {
    private static final String ORDERS_FILE = "orders.txt";
    private static final String STAFF_FILE = "delivery_staff.txt";

    // Loads delivery staff data from the staff file
    public List<DeliveryStaff> loadDeliveryStaff() {
        List<DeliveryStaff> staffList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(STAFF_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    DeliveryStaff staff = new DeliveryStaff(parts[0], parts[1]);
                    staff.setAvailable(Boolean.parseBoolean(parts[2]));
                    staffList.add(staff);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing staff file found. Starting with empty staff list.");
        } catch (IOException e) {
            System.err.println("Error reading staff file: " + e.getMessage());
        }
        return staffList; // Returns the list of delivery staff
    }

    // Loads order data from the orders file
    public List<Order> loadOrders() {
        List<Order> orders = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ORDERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    Order order = new Order(
                            parts[0],           // customerName
                            parts[1],            // pizzaType
                            Integer.parseInt(parts[2]), // remainingTicks
                            Boolean.parseBoolean(parts[3]) // completed
                    );

                    // If a staff name is saved, you might want to match it with existing staff
                    orders.add(order);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing orders file found. Starting with empty order list.");
        } catch (IOException e) {
            System.err.println("Error reading orders file: " + e.getMessage());
        }
        return orders; // Returns the list of orders
    }

    // Saves the current list of delivery staff to the staff file
    public void saveDeliveryStaff(List<DeliveryStaff> staffList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STAFF_FILE))) {
            for (DeliveryStaff staff : staffList) {
                writer.write(String.format("%s,%s,%b\n",
                        staff.getName(),
                        staff.getId(),
                        staff.isAvailable()
                ));
            }
            System.out.println("Saved " + staffList.size() + " delivery staff members to " + STAFF_FILE);
        } catch (IOException e) {
            System.err.println("Error saving delivery staff: " + e.getMessage());
        }
    }

    // Saves the current list of orders to the orders file
    public void saveOrders(List<Order> orders) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ORDERS_FILE))) {
            for (Order order : orders) {
                // Save order details: customerName, pizzaType, remainingTicks, completed, assignedStaff
                writer.write(String.format("%s,%s,%d,%b,%s\n",
                        order.getCustomerName(),
                        order.getPizzaType(),
                        order.getRemainingTicks(),
                        order.isCompleted(),
                        order.getAssignedStaff() != null ? order.getAssignedStaff().getName() : "UNASSIGNED"
                ));
            }
            System.out.println("Saved " + orders.size() + " orders to " + ORDERS_FILE);
        } catch (IOException e) {
            System.err.println("Error saving orders: " + e.getMessage());
        }
    }
}