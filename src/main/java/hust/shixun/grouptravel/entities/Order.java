package hust.shixun.grouptravel.entities;

import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private int PNum;
    private double currentDiscount;
    private Date travelTime;//产品出发时间
    private int notesId;

    public void setCreateTime(String createTime){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.createTime=sdf.parse(createTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setCreateTime(Date createTime){
        this.createTime=createTime;
    }
}
