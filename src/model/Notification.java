package model;

import base.BaseModel;
import base.XmlStore;
import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;

@XmlRootElement(name = "notification")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification extends BaseModel {
    @Getter @Setter private String text;
    @Getter @Setter private Date date;
    @Getter @Setter private int userId;
    @Getter @Setter private boolean read;


    public static XmlStore<Notification> store = new XmlStore<Notification>("store/notifications.xml", Notification.class);


    @XmlTransient
    public User getUser(){
        return this.userId > 0 ? User.store.GetById(this.userId) : null;
    }

    public static Notification create(User user, String text){
        Notification n = Notification.builder()
                .userId(user.getId())
                .text(text)
                .date(new Date())
                .read(false)
                .build();

        store.Save(n);

        return n;
    }
}