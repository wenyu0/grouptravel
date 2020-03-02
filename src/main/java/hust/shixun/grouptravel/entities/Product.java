package hust.shixun.grouptravel.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
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
