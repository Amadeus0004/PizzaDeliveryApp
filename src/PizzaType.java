public enum PizzaType {
    MARGHERITA(3), PEPPERONI(4), VEGGIE(2), BBQ_CHICKEN(5);

    private final int deliveryTime;

    PizzaType(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }
}