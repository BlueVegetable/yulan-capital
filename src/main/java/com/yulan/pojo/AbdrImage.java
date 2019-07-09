package com.yulan.pojo;

public class AbdrImage {

    private String abdrId;

    private Short imageIndex;

    private String specifications;

    private String memo;

    private String imagePath;

    public AbdrImage() {
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getAbdrId() {
        return abdrId;
    }

    public void setAbdrId(String abdrId) {
        this.abdrId = abdrId == null ? null : abdrId.trim();
    }

    public Short getImageIndex() {
        return imageIndex;
    }

    public void setImageIndex(Short imageIndex) {
        this.imageIndex = imageIndex;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications == null ? null : specifications.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}
