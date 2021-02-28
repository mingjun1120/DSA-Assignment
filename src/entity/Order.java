package entity;
import java.util.GregorianCalendar;

public class Order {
    private String orderID;
    private GregorianCalendar orderTime;
    private double orderTotalPrice;
    private int quantity;

    public Order(String orderID, GregorianCalendar orderTime, double orderTotalPrice, int quantity) {
        this.orderID = orderID;
        this.orderTime = orderTime;
        this.orderTotalPrice = orderTotalPrice;
        this.quantity = quantity;
    }

    public String getOrderID() {
        return orderID;
    }

    public GregorianCalendar getOrderTime() {
        return orderTime;
    }

    public double getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setOrderTime(GregorianCalendar orderTime) {
        this.orderTime = orderTime;
    }

    public void setOrderTotalPrice(double orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", orderTime=" + orderTime +
                ", orderTotalPrice=" + orderTotalPrice +
                ", quantity=" + quantity +
                '}';
    }
}
