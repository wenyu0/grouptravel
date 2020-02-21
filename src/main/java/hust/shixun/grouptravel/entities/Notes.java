package hust.shixun.grouptravel.entities;

import java.util.Date;

public class Notes {
    private int notesId;
    private int productId;
    private String title;
    private String content;
    private Date writeTime;
    private double rate;

    @Override
    public String toString() {
        return "Notes{" +
                "notesId=" + notesId +
                ", productId=" + productId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writeTime=" + writeTime +
                ", rate=" + rate +
                '}';
    }

    public int getNotesId() {
        return notesId;
    }

    public void setNotesId(int notesId) {
        this.notesId = notesId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(Date writeTime) {
        this.writeTime = writeTime;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Notes() {
    }

    public Notes(int notesId, int productId, String title, String content, Date writeTime, double rate) {
        this.notesId = notesId;
        this.productId = productId;
        this.title = title;
        this.content = content;
        this.writeTime = writeTime;
        this.rate = rate;
    }
}
