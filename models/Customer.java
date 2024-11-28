package models;

import java.util.List;

public record Customer(String name, String id) implements User {

    public void placeOrder(String foodName, FoodManager foodManager, List<Order> orders) {
        FoodItem foodItem = foodManager.getFoodItems().stream()
                .filter(item -> item.getName().equalsIgnoreCase(foodName))
                .findFirst()
                .orElse(null);

        if (foodItem == null) {
            System.out.println("Food item not found.");
            return;
        }

        Order order = new Order(name, foodItem.getName(), foodItem.getDeliveryTime(), false);
        orders.add(order);
        System.out.println("The order has been added to the list");
    }

    public void checkOrderStatus(List<Order> orders) {
        orders.stream()
                .filter(order -> order.getCustomerName().equals(this.name))
                .forEach(order -> {
                    String staffName = order.getAssignedStaff() != null ? order.getAssignedStaff().name() : "Unassigned";
                    System.out.println("Order for " + order.getCustomerName() + ": " + order.getFoodType() +
                            ", Remaining Ticks: " + order.getRemainingTicks() +
                            ", Completed: " + order.isCompleted() +
                            ", Assigned to: " + staffName);
                });
    }
}