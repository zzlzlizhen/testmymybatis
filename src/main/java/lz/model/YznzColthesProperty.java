package lz.model;

public class YznzColthesProperty {
    private String id;

    private String colthesId;

    private String sizeType;

    private String colourType;

    private Integer purchaseCount;

    private Integer saleCount;

    private String addBy;

    private String addTime;

    private String updateBy;

    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getColthesId() {
        return colthesId;
    }

    public void setColthesId(String colthesId) {
        this.colthesId = colthesId == null ? null : colthesId.trim();
    }

    public String getSizeType() {
        return sizeType;
    }

    public void setSizeType(String sizeType) {
        this.sizeType = sizeType == null ? null : sizeType.trim();
    }

    public String getColourType() {
        return colourType;
    }

    public void setColourType(String colourType) {
        this.colourType = colourType == null ? null : colourType.trim();
    }

    public Integer getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(Integer purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public String getAddBy() {
        return addBy;
    }

    public void setAddBy(String addBy) {
        this.addBy = addBy == null ? null : addBy.trim();
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime == null ? null : addTime.trim();
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }
}