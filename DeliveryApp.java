import java.util.*;

public class DeliveryApp {
    private static List<DeliveryStaff> deliveryStaffList = new ArrayList<>();
    private static List<Order> orders = new ArrayList<>();
    private static final FileManager fileManager = new FileManager();
    private static int ticks = 0;

    public static void main(String[] args) {
        deliveryStaffList = fileManager.loadDeliveryStaff(); // Load delivery staff from file
        orders = fileManager.loadOrders(); // Load orders from file
        Scanner scanner = new Scanner(System.in);
        boolean running = true;


        //main loop for the application
        while (running) {
            displayMenu(); //Display the menu options
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addDeliveryStaff(scanner); // Add new delivery staff
                    updateTicks(); // Update ticks and manage order status
                    break;
                case 2:
                    placeOrder(scanner); //Place a new order
                    updateTicks();
                    break;
                case 3:
                    showOrders();// Show current orders
                    updateTicks();
                    break;
                case 4:
                    showEmployees(); // Show current employees
                    updateTicks();
                    break;
                case 5:
                    closeStore(); // Save state and close the application
                    running = false; // Exit the loop
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    //Updates the ticks and manages order status
    private static void updateTicks() {
        ticks++;

        // Periodically save the state (e.g., every 5 ticks)
        if (ticks % 5 == 0) {
            fileManager.saveDeliveryStaff(deliveryStaffList);
            fileManager.saveOrders(orders);
            System.out.println("Periodic save at tick " + ticks);
        }

        //Iterate through orders to update their status based oon remaining ticks
        Iterator<Order> iterator = orders.iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (!order.isCompleted()) {
                int remainingTicks = order.getRemainingTicks();
                if (remainingTicks > 0) {
                    order.setRemainingTicks(remainingTicks - 1); // Decrease remaining ticks
                } else {
                    // Pizza delivery completed
                    order.setCompleted(true);
                    DeliveryStaff assignedStaff = order.getAssignedStaff();
                    if (assignedStaff != null) {
                        assignedStaff.setAvailable(true); // Mark staff as available
                        System.out.println("Pizza for " + order.getCustomerName() + " has been delivered by " + assignedStaff.getName() + "!");
                    }
                }
            }
        }
    }

    // Displays the main menu options to the user
    private static void displayMenu() {
        System.out.println("\n=== Delivery App Menu ===");
        System.out.println("1. Add Delivery Staff");
        System.out.println("2. Place Order");
        System.out.println("3. Show Orders");
        System.out.println("4. Show Employees");
        System.out.println("5. Close Store");
        System.out.print("Enter your choice: ");
    }

    // Adds a new delivery staff member based on user input
    private static void addDeliveryStaff(Scanner scanner) {
        System.out.print("Enter staff name: ");
        String name = scanner.nextLine();
        System.out.print("Enter staff ID: ");
        String id = scanner.nextLine();

        // Check if staff with same ID already exists
        boolean staffExists = deliveryStaffList.stream()
                .anyMatch(staff -> staff.getId().equals(id));

        if (staffExists) {
            System.out.println("A staff member with this ID already exists.");
            return;
        }

        DeliveryStaff staff = new DeliveryStaff(name, id);
        deliveryStaffList.add(staff);
        System.out.println("Delivery staff " + staff.getName() + " added successfully.");
    }

    // Place a new order based on user input
    private static void placeOrder(Scanner scanner) {
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();

        System.out.println("Enter pizza type (1: MARGHERITA, 2: PEPPERONI, 3: VEGGIE, 4: BBQ_CHICKEN): ");
        int pizzaTypeInput = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        PizzaType pizzaType; // Variable to hold the selected pizza
        switch (pizzaTypeInput) {
            case 1:
                pizzaType = PizzaType.MARGHERITA;
                break;
            case 2:
                pizzaType = PizzaType.PEPPERONI;
                break;
            case 3:
                pizzaType = PizzaType.VEGGIE;
                break;
            case 4:
                pizzaType = PizzaType.BBQ_CHICKEN;
                break;
            default:
                System.out.println("Invalid pizza type. Please try again.");
                return;
        }

        // Find an available delivery staff member
        DeliveryStaff availableStaff = deliveryStaffList.stream()
                .filter(DeliveryStaff::isAvailable)
                .findFirst()
                .orElse(null);

        if (availableStaff != null) {
            // Create a new order and assign it to the available staff
            Order order = new Order(customerName, pizzaType.name(), pizzaType.getDeliveryTime(), false);
            orders.add(order);

            // Mark the staff member as unavailable
            availableStaff.setAvailable(false);
            order.setAssignedStaff(availableStaff); // Assign staff to the order

            System.out.println("Order placed successfully and assigned to " + availableStaff.getName() + ".");
        } else {
            System.out.println("No available delivery staff. Please try again later.");
        }
    }

    // Displays the current orders to the user
    private static void showOrders() {
        System.out.println("\n=== Current Orders ===");
        for (Order order : orders) {
            String staffName = order.getAssignedStaff() != null ? order.getAssignedStaff().getName() : "Unassigned";
            System.out.println("Customer: " + order.getCustomerName() +
                    ", Pizza: " + order.getPizzaType() +
                    ", Remaining Ticks: " + order.getRemainingTicks() +
                    ", Completed: " + order.isCompleted() +
                    ", Assigned to: " + staffName);
        }
    }

    // Displays the current employees to the user
    private static void showEmployees() {
        System.out.println("\n=== Current Delivery Staff ===");
        for (DeliveryStaff staff : deliveryStaffList) {
            System.out.println(staff.getName() + ", ID: " + staff.getId() + ", Available: " + staff.isAvailable());
        }
    }

    // Saves the current state and closes the application
    private static void closeStore() {
        // Ensure all current state is saved before closing
        fileManager.saveDeliveryStaff(deliveryStaffList);
        fileManager.saveOrders(orders);
        System.out.println("Store closed. Final state saved.");
    }
}