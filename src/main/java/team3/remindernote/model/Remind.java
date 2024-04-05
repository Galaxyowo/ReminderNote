package team3.remindernote.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Remind {

    private Integer id;

    private String title;


    private Timestamp createdDate;

    private Timestamp remindTime;
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

    @Override
    public String toString() {
        return "Remind{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createdDate=" + createdDate +
                ", remindTime=" + remindTime +
                '}';
    }
}
