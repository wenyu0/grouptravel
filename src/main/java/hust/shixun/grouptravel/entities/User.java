package hust.shixun.grouptravel.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private int userId;
    private String openId;
    private Date createTime;
    private Date lastVisitTime;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private int gender;
    private String nickname;
    private String phoneNum;

}
