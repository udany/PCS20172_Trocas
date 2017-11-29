package view;
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.text.*;
import javax.swing.*;

import java.awt.*;

public class LoginScreen extends JFrame {
	
	int controla;

    public static void main(String[] args) 
    {
    	LoginScreen thisClass = new LoginScreen();
    	thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	thisClass.setVisible(true);
    	thisClass.setTitle("Login");
    	thisClass.setSize(289, 195);
    }

    public LoginScreen() 
    {
        super("Login");
        initialize();
    }


    private void initialize() {
        this.setSize(290, 180);
        this.setTitle("Login");
        this.setVisible(true);
        Container tela = getContentPane();
        tela.setLayout(null);
        this.setContentPane(tela);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // LABELS
        JLabel titulolbl = new JLabel("Forneça Login e senha");
        tela.add(titulolbl);
        titulolbl.setBounds(10, 5, 200, 20);

        JLabel loginlbl = new JLabel("Login: ");
        tela.add(loginlbl);
        loginlbl.setBounds(10, 35, 60, 20);

        JLabel senhalbl = new JLabel("Senha: ");
        tela.add(senhalbl);
        senhalbl.setBounds(10, 60, 60, 20);

        // TEXTFIELDS
        final JTextField loginTxt = new JTextField(6);
        tela.add(loginTxt);
        loginTxt.setBounds(75, 35, 90, 20);


        final JTextField senhaTxt = new JPasswordField(6);
        tela.add(senhaTxt);
        senhaTxt.setBounds(75, 60, 90, 20);


        // BOTÕES
        JButton okbtn = new JButton("OK");
        tela.add(okbtn);
        okbtn.setBounds(30, 95, 80, 30);
        okbtn.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent e) 
            {
            	
            }
        });
    }

}

