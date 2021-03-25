package entity;
import java.time.LocalDateTime;

public class DailySalesReport implements Comparable<DailySalesReport>{
    private static final String REPORT_NAME = "Daily Report";
    private LocalDateTime cusOrderDateTime;
    private Order customerOrder;

    public DailySalesReport(LocalDateTime cusOrderDateTime, Order customerOrder) {
        this.cusOrderDateTime = cusOrderDateTime;
        this.customerOrder = customerOrder;
    }

    public static String getReportName() {
        return REPORT_NAME;
    }

    public LocalDateTime getCusOrderDateTime() {
        return cusOrderDateTime;
    }

    public void setCusOrderDateTime(LocalDateTime cusOrderDateTime) {
        this.cusOrderDateTime = cusOrderDateTime;
    }

    public Order getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(Order customerOrder) {
        this.customerOrder = customerOrder;
    }

    @Override
    public int compareTo(DailySalesReport o) {
        if (customerOrder.getOrderTime().isEqual(o.customerOrder.getOrderTime())) {
            return 0;
        } else if (customerOrder.getOrderTime().isBefore(o.customerOrder.getOrderTime())) {
            return -1;
        } else { // Means customerOrder.getOrderTime().isBefore(o.customerOrder.getOrderTime())
            return 1;
        }
    }
}
