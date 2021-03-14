package entity;

import java.time.LocalDateTime;

public class TransHist extends Order {
    private int totalTransMade;
    private double totalTransPrice;

    public TransHist(String orderID, LocalDateTime orderTime, double orderTotalPrice, OrderDish[] cusOrder, int totalTransMade, double totalTransPrice) {
        super(orderID, orderTime, orderTotalPrice, cusOrder);
        this.totalTransMade = totalTransMade;
        this.totalTransPrice = totalTransPrice;

    }

    public double getTotalTransMade() {
        return totalTransMade;
    }

    public void setTotalTransMade(int totalTransMade) {

        this.totalTransMade = totalTransMade;
    }

    public double getTotalTransPrice() {
        return totalTransPrice;
    }

    public void setTotalTransPrice(double totalTransPrice) {

        this.totalTransPrice = totalTransPrice;
    }

    @Override
    public String toString() {
        super.toString();
        return "TransHist{" +
                "totalTransMade:" + totalTransMade +
                ", totalTransPrice:" + totalTransPrice +
                '}';
    }
}


