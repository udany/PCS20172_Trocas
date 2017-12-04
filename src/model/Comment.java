package model;

import base.BaseModel;
import base.XmlStore;
import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;

@XmlRootElement(name = "comment")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseModel {
    @Getter @Setter private String text;
    @Getter @Setter private Date date;
    @Getter @Setter private int userId;


    public static XmlStore<Comment> store = new XmlStore<Comment>("store/comment.xml", Comment.class);


    @XmlTransient
    public User getUser(){
        return this.userId > 0 ? User.store.GetById(this.userId) : null;
    }
}