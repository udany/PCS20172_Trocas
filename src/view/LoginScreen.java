package view;
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.text.*;
import javax.swing.*;

import java.awt.*;

public class LoginScreen extends JFrame 
{
    public static void main(String[] args) 
    {
    	LoginScreen loginScreen = new LoginScreen();
    	loginScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	loginScreen.setVisible(true);
    }

    public LoginScreen() 
    {
        super("Login");
        initialize();
    }


    private void initialize() 
    {
        this.setSize(290, 180);
        this.setTitle("Login");
        this.setVisible(true);
        Container screen = getContentPane();
        screen.setLayout(null);
        this.setContentPane(screen);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // LABELS
        JLabel loginlbl = new JLabel("Login: ");
        screen.add(loginlbl);
        loginlbl.setBounds(50, 35, 60, 20);

        JLabel senhalbl = new JLabel("Senha: ");
        screen.add(senhalbl);
        senhalbl.setBounds(50, 60, 60, 20);

        // TEXTFIELDS
        final JTextField loginTxt = new JTextField(6);
        screen.add(loginTxt);
        loginTxt.setBounds(110, 35, 90, 20);

        final JTextField senhaTxt = new JPasswordField(6);
        screen.add(senhaTxt);
        senhaTxt.setBounds(110, 60, 90, 20);


        // BUTTONS
        JButton okBtn = new JButton("OK");
        screen.add(okBtn);
        okBtn.setBounds(100, 95, 80, 30);
        okBtn.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
            	
            }
        });
        
        JButton registerBtn = new JButton("Cadastrar");
        screen.add(registerBtn);
        registerBtn.setBounds(80, 135, 120, 30);
        registerBtn.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
            	setVisible(false);
            	SignupScreen signupScreen = new SignupScreen();
            	signupScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            	signupScreen.setVisible(true);
            }
        });
    }

}

