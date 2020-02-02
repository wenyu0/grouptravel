package hust.shixun.grouptravel.entities;

import java.util.Date;

public class Notes {
    private int notesId;
    private String title;
    private String content;
    private Date writeTime;
    private double rate;

    public int getNotesId() {
        return notesId;
    }

    public void setNotesId(int notesId) {
        this.notesId = notesId;
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

    public Notes(int notesId, String title, String content, Date writeTime, double rate) {
        this.notesId = notesId;
        this.title = title;
        this.content = content;
        this.writeTime = writeTime;
        this.rate = rate;
    }

    public Notes() {
    }

    @Override
    public String toString() {
        return "Notes{" +
                "notesId=" + notesId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writeTime=" + writeTime +
                ", rate=" + rate +
                '}';
    }
}
