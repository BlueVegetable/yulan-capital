package com.yulan.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class PaymentBill {

    private String id;
    private String cid;
    private String cname;
    private Date createTs;
    private String yulanBank;
    private String payerName;
    private BigDecimal payAmount;
    private Date payDate;
    private String imgFileName;
    private Timestamp submitTs;
    private String memo;
    private Timestamp cancelTs;
    private Date sendbackTs;
    private String sendbackReason;
    private Date erpProcessTs;
    private String erpProcessOp;
    private String state;
    private String payerAccount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public Date getCreateTs() {
        return createTs;
    }

    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }

    public String getYulanBank() {
        return yulanBank;
    }

    public void setYulanBank(String yulanBank) {
        this.yulanBank = yulanBank == null ? null : yulanBank.trim();
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName == null ? null : payerName.trim();
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName == null ? null : imgFileName.trim();
    }

    public Date getSubmitTs() {
        return submitTs;
    }

    public void setSubmitTs(Timestamp submitTs) {
        this.submitTs = submitTs;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Date getCancelTs() {
        return cancelTs;
    }

    public void setCancelTs(Timestamp cancelTs) {
        this.cancelTs = cancelTs;
    }

    public Date getSendbackTs() {
        return sendbackTs;
    }

    public void setSendbackTs(Date sendbackTs) {
        this.sendbackTs = sendbackTs;
    }

    public String getSendbackReason() {
        return sendbackReason;
    }

    public void setSendbackReason(String sendbackReason) {
        this.sendbackReason = sendbackReason == null ? null : sendbackReason.trim();
    }

    public Date getErpProcessTs() {
        return erpProcessTs;
    }

    public void setErpProcessTs(Date erpProcessTs) {
        this.erpProcessTs = erpProcessTs;
    }

    public String getErpProcessOp() {
        return erpProcessOp;
    }

    public void setErpProcessOp(String erpProcessOp) {
        this.erpProcessOp = erpProcessOp == null ? null : erpProcessOp.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getPayerAccount() {
        return payerAccount;
    }

    public void setPayerAccount(String payerAccount) {
        this.payerAccount = payerAccount == null ? null : payerAccount.trim();
    }

    @Override
    public String toString() {
        return "PaymentBill{" +
                "id='" + id + '\'' +
                ", cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                ", createTs=" + createTs +
                ", yulanBank='" + yulanBank + '\'' +
                ", payerName='" + payerName + '\'' +
                ", payAmount=" + payAmount +
                ", payDate=" + payDate +
                ", imgFileName='" + imgFileName + '\'' +
                ", submitTs=" + submitTs +
                ", memo='" + memo + '\'' +
                ", cancelTs=" + cancelTs +
                ", sendbackTs=" + sendbackTs +
                ", sendbackReason='" + sendbackReason + '\'' +
                ", erpProcessTs=" + erpProcessTs +
                ", erpProcessOp='" + erpProcessOp + '\'' +
                ", state='" + state + '\'' +
                ", payerAccount='" + payerAccount + '\'' +
                '}';
    }
}