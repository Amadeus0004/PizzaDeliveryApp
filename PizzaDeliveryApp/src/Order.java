public class Order {
    private String customerName;
    private String pizzaType;
    private int remainingTicks;
    private boolean completed;
    private DeliveryStaff assignedStaff;

    public Order(String customerName, String pizzaType, int deliveryTime, boolean completed) {
        this.customerName = customerName;
        this.pizzaType = pizzaType;
        this.remainingTicks = deliveryTime; // Assuming deliveryTime is represented in ticks
        this.completed = Boolean.parseBoolean(String.valueOf(completed));
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPizzaType() {
        return pizzaType;
    }

    public int getRemainingTicks() {
        return remainingTicks;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public DeliveryStaff getAssignedStaff() {
        return assignedStaff;
    }

    public void setAssignedStaff(DeliveryStaff assignedStaff) {
        this.assignedStaff = assignedStaff;
    }

    public void setRemainingTicks(int remainingTicks) {
        this.remainingTicks = remainingTicks;
    }
}