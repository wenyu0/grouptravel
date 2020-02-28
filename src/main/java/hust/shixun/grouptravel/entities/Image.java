package hust.shixun.grouptravel.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Image {
    private int imageId;
    private String imageUrl;

    public Image(int imageId, String imageUrl) {
        this.imageId = imageId;
        this.imageUrl = imageUrl;
    }

    public Image(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
