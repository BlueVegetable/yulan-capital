package com.yulan.pojo;

import java.sql.Timestamp;
import java.util.List;

public class ReturnCompensationBill {

    private String id;
    private String erpCreator;
    private String erpCreatorname;
    private Timestamp createTs;
    private String cid;
    private String cname;
    private Timestamp reassureTs;
    private String sendbackReason;
    private Short itemCount;
    private Short itemMaxIndex;
    private String state;
    private String printed;
    private List<RtcbItem> rtcbItems;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getErpCreator() {
        return erpCreator;
    }

    public void setErpCreator(String erpCreator) {
        this.erpCreator = erpCreator == null ? null : erpCreator.trim();
    }

    public String getErpCreatorname() {
        return erpCreatorname;
    }

    public void setErpCreatorname(String erpCreatorname) {
        this.erpCreatorname = erpCreatorname == null ? null : erpCreatorname.trim();
    }

    public Timestamp getCreateTs() {
        return createTs;
    }

    public void setCreateTs(Timestamp createTs) {
        this.createTs = createTs;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public Timestamp getReassureTs() {
        return reassureTs;
    }

    public void setReassureTs(Timestamp reassureTs) {
        this.reassureTs = reassureTs;
    }

    public String getSendbackReason() {
        return sendbackReason;
    }

    public void setSendbackReason(String sendbackReason) {
        this.sendbackReason = sendbackReason == null ? null : sendbackReason.trim();
    }

    public Short getItemCount() {
        return itemCount;
    }

    public void setItemCount(Short itemCount) {
        this.itemCount = itemCount;
    }

    public Short getItemMaxIndex() {
        return itemMaxIndex;
    }

    public void setItemMaxIndex(Short itemMaxIndex) {
        this.itemMaxIndex = itemMaxIndex;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getPrinted() {
        return printed;
    }

    public void setPrinted(String printed) {
        this.printed = printed == null ? null : printed.trim();
    }

    public List<RtcbItem> getRtcbItems() {
        return rtcbItems;
    }

    public void setRtcbItems(List<RtcbItem> rtcbItems) {
        this.rtcbItems = rtcbItems;
    }

    @Override
    public String toString() {
        return "ReturnCompensationBill{" +
                "id='" + id + '\'' +
                ", erpCreator='" + erpCreator + '\'' +
                ", erpCreatorname='" + erpCreatorname + '\'' +
                ", createTs=" + createTs +
                ", cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                ", reassureTs=" + reassureTs +
                ", sendbackReason='" + sendbackReason + '\'' +
                ", itemCount=" + itemCount +
                ", itemMaxIndex=" + itemMaxIndex +
                ", state='" + state + '\'' +
                ", printed='" + printed + '\'' +
                '}';
    }
}