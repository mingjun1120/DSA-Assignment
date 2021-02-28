package entity;
import java.util.GregorianCalendar;

public class Order {
    private String orderID;
    private GregorianCalendar orderTime;
    private double orderTotalPrice;
    private OrderDish cusOrder;

    public Order(String orderID, GregorianCalendar orderTime, double orderTotalPrice, OrderDish cusOrder) {
        this.orderID = orderID;
        this.orderTime = orderTime;
        this.orderTotalPrice = orderTotalPrice;
        this.cusOrder = cusOrder;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public GregorianCalendar getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(GregorianCalendar orderTime) {
        this.orderTime = orderTime;
    }

    public double getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(double orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public OrderDish getCusOrder() {
        return cusOrder;
    }

    public void setCusOrder(OrderDish cusOrder) {
        this.cusOrder = cusOrder;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", orderTime=" + orderTime +
                ", orderTotalPrice=" + orderTotalPrice +
                ", cusOrder=" + cusOrder +
                '}';
    }
}
