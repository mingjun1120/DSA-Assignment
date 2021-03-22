package main;

import entity.DailyReport;
import entity.Order;

import java.time.LocalDateTime;

public class DailyDishRankings implements Comparable<DailyDishRankings> {
    private static final String reportName = "Daily Dish Rankings";
    private String dishName;
    private int orderQty;
    private double totalPrice;

    public DailyDishRankings() {
    }

    public DailyDishRankings(String dishName, int orderQty, double totalPrice) {
        this.dishName = dishName;
        this.orderQty = orderQty;
        this.totalPrice = totalPrice;
    }

    public static String getReportName() {
        return reportName;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    @Override
    public String toString() {
        return "DailyDishRankings{" +
                "dishName='" + dishName + '\'' +
                ", orderQty=" + orderQty +
                ", totalPrice=" + totalPrice +
                '}';
    }

    @Override
    public int compareTo(DailyDishRankings o) {
        return this.orderQty - o.orderQty;
    }
}
