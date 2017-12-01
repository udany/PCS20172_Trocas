package view;
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.text.*;
import javax.swing.*;

import model.Product;

import java.awt.*;
import java.util.ArrayList;

public class ProductScreen extends JFrame
{
	 public ProductScreen(Product product) 
	 {
		 super(product.getName());
		 initialize(product);
	 }
	 
	 private void initialize(Product product) 
	 {
		 this.setSize(290, 180);
		 this.setTitle(product.getName());
		 this.setVisible(true);
		 Container screen = getContentPane();
		 screen.setLayout(null);
		 this.setContentPane(screen);
		 this.setLocationRelativeTo(null);
		 this.setResizable(false);
		 
		 JLabel nameLabel = new JLabel(product.getName());
         screen.add(nameLabel);
         nameLabel.setBounds(50, 100, 150, 20);

         // back button
		 JButton backBtn = new JButton("Voltar");
		 screen.add(backBtn);
		 backBtn.setBounds(150, 135, 80, 30);
		 backBtn.addActionListener(new java.awt.event.ActionListener() 
		 {
			 public void actionPerformed(java.awt.event.ActionEvent e) 
			 {
				 setVisible(false);
				 ShowProductsScreen productsScreen = new ShowProductsScreen();
				 productsScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 productsScreen.setVisible(true);
			 }
		 });
		 
		 // request exchange button
		 JButton exchangeBtn = new JButton("Trocar!");
		 screen.add(exchangeBtn);
		 exchangeBtn.setBounds(50, 135, 100, 30);
		 exchangeBtn.addActionListener(new java.awt.event.ActionListener() 
		 {
			 public void actionPerformed(java.awt.event.ActionEvent e) 
			 {
				 setVisible(false);
				 LoginScreen loginScreen = new LoginScreen();
				 loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 loginScreen.setVisible(true);
			 }
		 });
	 }
}
