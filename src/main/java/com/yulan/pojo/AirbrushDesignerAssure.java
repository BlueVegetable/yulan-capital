package com.yulan.pojo;

import java.util.Date;
import java.util.List;

public class AirbrushDesignerAssure {

    private String id;

    private String erpCreator;

    private Date createTs;

    private String cid;

    private String cname;

    private String customerAgent;

    private String officeTel;

    private Date reassureTs;

    private String sendbackReason;

    private Short imageCount;

    private Short imageMaxIndex;

    private String state;

    private int total;

    private String realName;

    private List<AbdrImage> abdrImage;

    public AirbrushDesignerAssure() {
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public List<AbdrImage> getAbdrImage() {
        return abdrImage;
    }

    public void setAbdrImage(List<AbdrImage> abdrImage) {
        this.abdrImage = abdrImage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

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

    public Date getCreateTs() {
        return createTs;
    }

    public void setCreateTs(Date createTs) {
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

    public String getCustomerAgent() {
        return customerAgent;
    }

    public void setCustomerAgent(String customerAgent) {
        this.customerAgent = customerAgent == null ? null : customerAgent.trim();
    }

    public String getOfficeTel() {
        return officeTel;
    }

    public void setOfficeTel(String officeTel) {
        this.officeTel = officeTel == null ? null : officeTel.trim();
    }

    public Date getReassureTs() {
        return reassureTs;
    }

    public void setReassureTs(Date reassureTs) {
        this.reassureTs = reassureTs;
    }

    public String getSendbackReason() {
        return sendbackReason;
    }

    public void setSendbackReason(String sendbackReason) {
        this.sendbackReason = sendbackReason == null ? null : sendbackReason.trim();
    }

    public Short getImageCount() {
        return imageCount;
    }

    public void setImageCount(Short imageCount) {
        this.imageCount = imageCount;
    }

    public Short getImageMaxIndex() {
        return imageMaxIndex;
    }

    public void setImageMaxIndex(Short imageMaxIndex) {
        this.imageMaxIndex = imageMaxIndex;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }


}
