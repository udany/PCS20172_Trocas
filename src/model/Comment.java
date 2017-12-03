package model;

import base.BaseModel;
import base.XmlStore;
import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "comment")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseModel {
    @Getter @Setter private String text;
    @Getter @Setter private int date;


    public static XmlStore<Comment> store = new XmlStore<Comment>("store/comment.xml", Comment.class);
}