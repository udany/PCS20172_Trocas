package model;

import base.BaseModel;
import base.XmlStore;
import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;

@XmlRootElement(name = "exchange")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Exchange extends BaseModel {
    @Getter @Setter private int dateStarted;

    // states
    @Getter @Setter private boolean canceled;
    @Getter @Setter private boolean user1Accepted;
    @Getter @Setter private boolean user2Accepted;
    @Getter @Setter private boolean user1Concluded;
    @Getter @Setter private boolean user2Concluded;

    // rating
    @Getter @Setter private int user1Rating;
    @Getter @Setter private int user2Rating;


    public static XmlStore<Exchange> store = new XmlStore<Exchange>("store/exchange.xml", Exchange.class);
}