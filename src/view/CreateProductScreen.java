package view;
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.text.*;
import javax.swing.*;

import java.awt.*;

public class CreateProductScreen extends JFrame
{
    public CreateProductScreen() 
    {
        super("Cadastrar produto");
        initialize();
    }

    private void initialize() 
    {
        this.setSize(290, 180);
        this.setTitle("Cadastrar produto");
        this.setVisible(true);
        Container screen = getContentPane();
        screen.setLayout(null);
        this.setContentPane(screen);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // LABELS
        JLabel nameLabel = new JLabel("Nome: ");
        screen.add(nameLabel);
        nameLabel.setBounds(50, 35, 60, 20);

        JLabel priceLabel = new JLabel("Preço: ");
        screen.add(priceLabel);
        priceLabel.setBounds(50, 60, 60, 20);

        // TEXTFIELDS
        final JTextField nameTxt = new JTextField(6);
        screen.add(nameTxt);
        nameTxt.setBounds(110, 35, 90, 20);

        final JTextField priceTxt = new JTextField(6);
        screen.add(priceTxt);
        priceTxt.setBounds(110, 60, 90, 20);


        // BUTTONS
        JButton registerBtn = new JButton("Cadastrar");
        screen.add(registerBtn);
        registerBtn.setBounds(70, 100, 120, 30);
        registerBtn.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
            	
            }
        });
        
        JButton backBtn = new JButton("Voltar");
        screen.add(backBtn);
        backBtn.setBounds(85, 135, 80, 30);
        backBtn.addActionListener(new java.awt.event.ActionListener() 
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

