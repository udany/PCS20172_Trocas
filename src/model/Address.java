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
        StringBuilder sb = new StringBuilder();

        if (!street.equals("")){
            sb.append(street);
        }
        if (!street.equals("")){
            if (sb.length() > 0) sb.append(", ");
            sb.append(number);
        }

        if (!complement.equals("")){
            if (sb.length() > 0) sb.append(" ");
            sb.append(complement);
        }

        if (!neighborhood.equals("")){
            if (sb.length() > 0) sb.append("\n");
            sb.append(neighborhood);
        }

        if (state!=null){
            if (sb.length() > 0) sb.append("\n");
            sb.append(state.getAcronym());
        }

        if (!city.equals("")){
            if (sb.length() > 0) sb.append(" - ");
            sb.append(city);
        }

        if (!phone.equals("")){
            if (sb.length() > 0) sb.append("\n");
            sb.append(phone);
        }

        return sb.toString();
    }
}