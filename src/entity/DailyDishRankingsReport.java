package entity;

public class DailyDishRankingsReport implements Comparable<DailyDishRankingsReport> {
    private static final String REPORT_NAME = "Daily Dish Rankings Report";
    private String dishName;
    private int orderedQty;
    private double totalPrice;

    public DailyDishRankingsReport() {
    }

    public DailyDishRankingsReport(String dishName, int orderedQty, double totalPrice) {
        this.dishName = dishName;
        this.orderedQty = orderedQty;
        this.totalPrice = totalPrice;
    }

    public static String getReportName() {
        return REPORT_NAME;
    }

    public int getOrderedQty() {
        return orderedQty;
    }

    public void setOrderedQty(int orderedQty) {
        this.orderedQty = orderedQty;
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
        return "DailyDishRankingsReport{" +
                "dishName='" + dishName + '\'' +
                ", orderQty=" + orderedQty +
                ", totalPrice=" + totalPrice +
                '}';
    }

    @Override
    public int compareTo(DailyDishRankingsReport o) {
        return this.orderedQty - o.orderedQty;
    }
}
