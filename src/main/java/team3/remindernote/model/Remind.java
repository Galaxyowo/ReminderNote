package team3.remindernote.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Remind {

    private Integer id;

    private String title;

    private String content;

    private Timestamp createdDate;

    private Timestamp remindTime;

    private String linkFile;

    private String typeFile;

    public Remind() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }


    public Timestamp getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(Timestamp remindTime) {
        this.remindTime = remindTime;
    }

    public String getLinkFile() {
        return linkFile;
    }

    public void setLinkFile(String linkFile) {
        this.linkFile = linkFile;
    }

    public String getTypeFile() {
        return typeFile;
    }

    public void setTypeFile(String typeFile) {
        this.typeFile = typeFile;
    }

    @Override
    public String toString() {
        return "Remind{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                ", remindTime=" + remindTime +
                ", linkFile='" + linkFile + '\'' +
                ", typeFile='" + typeFile + '\'' +
                '}';
    }
}
