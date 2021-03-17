package entity;
import java.time.LocalDateTime;

public class Report implements Comparable<Report> {
    private String reportID;
    private String reportName;
    private Order orderInformation;
    private LocalDateTime reportDate;
    private static int id_no = 1;

    public Report(String reportName, Order orderInformation, LocalDateTime reportDate) {
        this.reportID = "RE".concat(String.valueOf(id_no++));
        this.reportName = reportName;
        this.reportDate = reportDate;
        this.orderInformation = orderInformation;
    }

    public Report(String reportID, String reportName, Order orderInformation, LocalDateTime reportDate) {
        this.reportID = "RE".concat(String.valueOf(reportID));
        this.reportName = reportName;
        this.reportDate = reportDate;
        this.orderInformation = orderInformation;
    }

    public Order getOrderInformation() {
        return orderInformation;
    }

    public void setOrderInformation(Order orderInformation) {
        this.orderInformation = orderInformation;
    }

    public String getReportID() {
        return reportID;
    }

    public void setReportID(String reportID) {
        this.reportID = reportID;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public LocalDateTime getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDateTime reportDate) {
        this.reportDate = reportDate;
    }

    @Override
    public String toString() {
        return reportID + reportDate + orderInformation + reportName;
    }

    @Override
    public int compareTo(Report o) {
        return this.reportName.compareTo(o.reportName);
    }
}