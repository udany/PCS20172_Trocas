package view;
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.text.*;
import javax.swing.*;

import model.Product;

import java.awt.*;
import java.util.ArrayList;

public class ShowProductsScreen extends JFrame
{
	// TEST
	public static void main(String[] args) 
    {
		ArrayList<Product> products = new ArrayList<Product>();
		products = Product.generate();
    	ShowProductsScreen productsScreen = new ShowProductsScreen(products);
    	productsScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	productsScreen.setVisible(true);
    }
	
    public ShowProductsScreen(ArrayList<Product> products) 
    {
        super("Produtos");
        initialize(products);
    }
    
    public ShowProductsScreen() 
    {
        super("Produtos");
        initialize(Product.generate());
    }

    private void initialize(ArrayList<Product> products) 
    {
        this.setSize(290, 180);
        this.setTitle("Produtos");
        this.setVisible(true);
        Container screen = getContentPane();
        screen.setLayout(null);
        this.setContentPane(screen);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        int height = 30;
        for (Product product : products) 
        {
        	final Product current = product;
        	// product details
        	JLabel nameLabel = new JLabel(product.getName());
            screen.add(nameLabel);
            nameLabel.setBounds(50, height, 150, 20);
            
            // button to product page
            JButton productBtn = new JButton("ver");
            screen.add(productBtn);
            productBtn.setBounds(190, height, 70, 30);
            productBtn.addActionListener(new java.awt.event.ActionListener() 
            {
                public void actionPerformed(java.awt.event.ActionEvent e) 
                {
                	setVisible(false);
                	ProductScreen productScreen = new ProductScreen(current);
                	productScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                	productScreen.setVisible(true);
                }
            });
            height = height + 30;
        }
    }

}
