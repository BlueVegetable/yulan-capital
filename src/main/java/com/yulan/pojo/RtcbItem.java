package com.yulan.pojo;

import java.math.BigDecimal;

public class RtcbItem {

    private String rtcbId;
    private Short itemIndex;
    private String itemNo;
    private String productionVersion;
    private String unit;
    private BigDecimal qty;
    private BigDecimal totalmoney;
    private String notes;
    private String process;

    public String getRtcbId() {
        return rtcbId;
    }

    public void setRtcbId(String rtcbId) {
        this.rtcbId = rtcbId == null ? null : rtcbId.trim();
    }

    public Short getItemIndex() {
        return itemIndex;
    }

    public void setItemIndex(Short itemIndex) {
        this.itemIndex = itemIndex;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo == null ? null : itemNo.trim();
    }

    public String getProductionVersion() {
        return productionVersion;
    }

    public void setProductionVersion(String productionVersion) {
        this.productionVersion = productionVersion == null ? null : productionVersion.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(BigDecimal totalmoney) {
        this.totalmoney = totalmoney;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process == null ? null : process.trim();
    }

    @Override
    public String toString() {
        return "RtcbItem{" +
                "rtcbId='" + rtcbId + '\'' +
                ", itemIndex=" + itemIndex +
                ", itemNo='" + itemNo + '\'' +
                ", productionVersion='" + productionVersion + '\'' +
                ", unit='" + unit + '\'' +
                ", qty=" + qty +
                ", totalmoney=" + totalmoney +
                ", notes='" + notes + '\'' +
                ", process='" + process + '\'' +
                '}';
    }
}