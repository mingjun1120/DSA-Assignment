package entity;

import java.time.LocalDateTime;
import java.io.Serializable;

public class TransHist implements Serializable {
    private String tranID;
    private LocalDateTime tranTime;
    private Order tranDetail;

    private static int id_no = 1;
    private static final String tranType = "Cash";

    public TransHist(LocalDateTime tranTime, Order tranDetail) {
        this.tranID = "T".concat(String.valueOf(id_no++));
        this.tranTime = tranTime;
        this.tranDetail = tranDetail;
    }

    public String getTranID() {
        return tranID;
    }

    public void setTranID(String tranID) {
        this.tranID = tranID;
    }

    public LocalDateTime getTranTime() {
        return tranTime;
    }

    public void setTranTime(LocalDateTime tranTime) {
        this.tranTime = tranTime;
    }

    public static String getTranType() {
        return tranType;
    }

    public Order getTranDetail() {
        return tranDetail;
    }

    public void setTranDetail(Order tranDetail) {
        this.tranDetail = tranDetail;
    }

    @Override
    public String toString() {
        return "TransHist{" +
                "tranID='" + tranID + '\'' +
                ", tranTime=" + tranTime +
                ", tranDetail=" + tranDetail +
                '}';
    }
}


