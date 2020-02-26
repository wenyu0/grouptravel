package hust.shixun.grouptravel.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    private int orderId;
    private Date createTime;
    private int productId;
    private int userId;
    private double orderPrice;
    private Date payTime;
    private int status;
    private int PTid;
    private int pNum;
    private double currentDiscount;
    private Date travelTime;//产品出发时间
    private int notesId;


}
