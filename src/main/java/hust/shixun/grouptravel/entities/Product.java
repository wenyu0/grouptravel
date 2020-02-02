package hust.shixun.grouptravel.entities;

public class Product {
    private int productId;
    private String productName;
    private int cityId;
    private Double price;
    private String description;
    private int maxNum;
    private int maxDiscount;
    private int productTime;//产品行程时间
    private int transportationId;
    private int themeId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public int getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(int maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public int getProductTime() {
        return productTime;
    }

    public void setProductTime(int productTime) {
        this.productTime = productTime;
    }

    public int getTransportationId() {
        return transportationId;
    }

    public void setTransportationId(int transportationId) {
        this.transportationId = transportationId;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public Product(int productId, String productName, int cityId, Double price, String description, int maxNum, int maxDiscount, int productTime, int transportationId, int themeId) {
        this.productId = productId;
        this.productName = productName;
        this.cityId = cityId;
        this.price = price;
        this.description = description;
        this.maxNum = maxNum;
        this.maxDiscount = maxDiscount;
        this.productTime = productTime;
        this.transportationId = transportationId;
        this.themeId = themeId;
    }

    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", cityId=" + cityId +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", maxNum=" + maxNum +
                ", maxDiscount=" + maxDiscount +
                ", productTime=" + productTime +
                ", transportationId=" + transportationId +
                ", themeId=" + themeId +
                '}';
    }
}
