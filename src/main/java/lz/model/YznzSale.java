package lz.model;

public class YznzSale {
    private String id;

    private String colthesId;

    private String colthesPropertyId;

    private String purchasePrice;

    private String realPrice;

    private String addBy;

    private String addTime;

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

    public String getColthesPropertyId() {
        return colthesPropertyId;
    }

    public void setColthesPropertyId(String colthesPropertyId) {
        this.colthesPropertyId = colthesPropertyId == null ? null : colthesPropertyId.trim();
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice == null ? null : purchasePrice.trim();
    }

    public String getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(String realPrice) {
        this.realPrice = realPrice == null ? null : realPrice.trim();
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
}