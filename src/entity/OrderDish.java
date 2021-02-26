package entity;

public class OrderDish {

    private int qty;
    private Dish chosenDish;

    public OrderDish(int qty, Dish chosenDish) {
        this.qty = qty;
        this.chosenDish = chosenDish;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Dish getChosenDish() {
        return chosenDish;
    }

    public void setChosenDish(Dish chosenDish) {
        this.chosenDish = chosenDish;
    }

    @Override
    public String toString() {
        return "Chosen Dish: " + chosenDish.getName() + " Quantity: " + qty;
    }
}
