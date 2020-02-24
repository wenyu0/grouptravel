package hust.shixun.grouptravel.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Notes {
    private int notesId;
    private int productId;
    private String title;
    private String content;
    private Date writeTime;
    private double rate;
}
