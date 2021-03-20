package entity;
import java.time.LocalDateTime;

public class DemoReport implements Comparable<DemoReport> {
    private String reportName;
    private LocalDateTime reportDate;
    private Order reportDetail;

    public DemoReport() {
    }

    public DemoReport(String reportName) {
        this.reportName = reportName;
        //this.reportDate = reportDate;
    }

//    public String getReportID() {
//        return reportID;
//    }
//
//    public void setReportID(String reportID) {
//        this.reportID = reportID;
//    }

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
        return reportName;
    }

    @Override
    public int compareTo(DemoReport o) {
        return this.reportName.compareTo(o.reportName);
    }
}