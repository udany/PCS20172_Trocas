package model;

import base.BaseModel;
import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "address")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseModel {
    @Getter @Setter private String state;
    @Getter @Setter private String city;
    @Getter @Setter private String neighborhood;
    @Getter @Setter private String complement;
    @Getter @Setter private int number;
    @Getter @Setter private String phone;
}