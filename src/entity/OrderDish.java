package entity;

public class OrderDish {

    private int qty;

    public OrderDish(int qty) {
        this.qty = qty;
    }
    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "OrderDish{" +
                "qty=" + qty +
                '}';
    }
}
