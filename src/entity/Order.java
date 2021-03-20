package entity;
import adt.SortedLinkedList;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Order implements Serializable {
    private String orderID;
    private LocalDateTime orderTime;
    private double orderTotalPrice;
    private OrderDish[] cusOrder;
    private SortedLinkedList<OrderDish> cusOrderWithQtySorted = new SortedLinkedList<>();

    private static final long serialVersionUID = -7551826551697071247L;

    private static int id_no = 1;

    public Order(String orderID, LocalDateTime orderTime, double orderTotalPrice, OrderDish[] cusOrder) {
        this.orderID = orderID;
        this.orderTime = orderTime;
        this.orderTotalPrice = orderTotalPrice;
        this.cusOrder = cusOrder;
        for(OrderDish od : cusOrder) {
            cusOrderWithQtySorted.add(od);
        }
    }

    public Order(LocalDateTime orderTime, double orderTotalPrice, OrderDish[] cusOrder) {
        this.orderID = "OD".concat(String.valueOf(id_no++));
        this.orderTime = orderTime;
        this.orderTotalPrice = orderTotalPrice;
        this.cusOrder = cusOrder;
        this.cusOrderWithQtySorted.add(cusOrder);
    }

    public Order(int orderID, LocalDateTime orderTime, double orderTotalPrice, OrderDish[] cusOrder) {
        this.orderID = "OD".concat(String.valueOf(orderID));
        this.orderTime = orderTime;
        this.orderTotalPrice = orderTotalPrice;
        this.cusOrder = cusOrder;
        this.cusOrderWithQtySorted.add(cusOrder);
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public double getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(double orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public OrderDish[] getCusOrder() {
        return cusOrder;
    }

    public void setCusOrder(OrderDish[] cusOrder) {
        this.cusOrder = cusOrder;
    }

    public SortedLinkedList<OrderDish> getCusOrderWithQtySorted() {
        return cusOrderWithQtySorted;
    }

    public void setCusOrderWithQtySorted(SortedLinkedList<OrderDish> cusOrderWithQtySorted) {
        this.cusOrderWithQtySorted = cusOrderWithQtySorted;
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
