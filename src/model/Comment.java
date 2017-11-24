package model;

import base.BaseModel;
import base.BaseStore;
import base.XmlStore;
import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;

@XmlRootElement(name = "comment")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseModel {
    @Getter @Setter private String text;
    @Getter @Setter private int date;


    public static XmlStore<Comment> store = new XmlStore<Comment>("store/comment.xml", Comment.class);
}