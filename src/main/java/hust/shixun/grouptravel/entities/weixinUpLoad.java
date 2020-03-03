package hust.shixun.grouptravel.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class weixinUpLoad {
    private int productId;
    private String title;
    private String content;
    private Date writeTime;
    private double rate;
    private int orderId;
}
