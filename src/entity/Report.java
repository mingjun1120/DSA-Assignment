
package entity;

public class Report implements Comparable<Report> {
    private String reportName;

    public Report(String reportName){
        this.reportName = reportName;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportName = '" + reportName + '\'' +
                '}';
    }

    @Override
    public int compareTo(Report o) {
        return this.reportName.compareTo(o.reportName);
    }
}