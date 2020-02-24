package hust.shixun.grouptravel.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Admin {
    private int adminId;
    private String adminName;
    private String adminPassword;

}
