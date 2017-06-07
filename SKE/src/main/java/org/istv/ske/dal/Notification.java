package org.istv.ske.dal;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by Quentin on 06/06/2017.
 */
@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;
    private String content;
    private String typeNotif;
    private java.sql.Date createDate;

    @ManyToOne
    private User user;

    public Notification() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getTypeNotif() {
        return typeNotif;
    }

    public void setTypeNotif(String typeNotif) {
        this.typeNotif = typeNotif;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", typeNotif='" + typeNotif + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
