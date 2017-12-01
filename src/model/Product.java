package model;

//import base.BaseModel;
import base.BaseStore;
import base.XmlStore;
//import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

@XmlRootElement(name = "product")
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class Product //extends BaseModel 
{
//    @Getter @Setter private String name;
//    @Getter @Setter private String description;
//    @Getter @Setter private int condition;

    //public static XmlStore<Product> store = new XmlStore<Product>("store/product.xml", Product.class);
	
	private String name;
    private String description;
    private int condition;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public static ArrayList<Product> generate()
	{
		ArrayList<Product> products = new ArrayList<Product>();
    	
    	Product p1 = new Product();
    	p1.setName("Violão Di Giorgio");
    	
    	Product p2 = new Product();
    	p2.setName("Os trabalhadores do mar");
    	
    	products.add(p1);
    	products.add(p2);
    	return products;
	}
}