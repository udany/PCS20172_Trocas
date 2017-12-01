package view;
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.text.*;
import javax.swing.*;

import java.awt.*;

public class SignupScreen extends JFrame 
{
    public static void main(String[] args) 
    {
    	SignupScreen signupScreen = new SignupScreen();
    	signupScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	signupScreen.setVisible(true);
    	signupScreen.setTitle("Cadastre-se");
    	signupScreen.setSize(280, 230);
    }

    public SignupScreen() 
    {
        super("Cadastre-se");
        initialize();
    }


    private void initialize() 
    {
        this.setSize(290, 180);
        this.setTitle("Cadastre-se");
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
        JButton okBtn = new JButton("Cadastrar");
        screen.add(okBtn);
        okBtn.setBounds(70, 100, 120, 30);
        okBtn.addActionListener(new java.awt.event.ActionListener() 
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

