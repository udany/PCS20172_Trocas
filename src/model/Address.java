package model;

import base.BaseModel;
import lombok.*;
import model.enums.State;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "address")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseModel {
    @Getter @Setter private State state;
    @Getter @Setter private String city = "";
    @Getter @Setter private String neighborhood = "";
    @Getter @Setter private String street = "";
    @Getter @Setter private String number = "";
    @Getter @Setter private String complement = "";
    @Getter @Setter private String phone = "";

    public String toString(){
        return street+", "+number+(complement == null || complement.equals("") ? "" : " "+complement) + "\n" +
                neighborhood + "\n" +
                (state == null ? "" : state.getAcronym()) + " - " + city + "\n" +
                phone;
    }
}