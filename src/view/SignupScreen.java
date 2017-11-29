package view;
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.text.*;
import javax.swing.*;

import model.User;

import java.awt.*;

public class SignupScreen extends JFrame {
	
	int controla;

    public static void main(String[] args) 
    {
    	LoginScreen thisClass = new LoginScreen();
    	thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	thisClass.setVisible(true);
    	thisClass.setTitle("Cadastro");
    	thisClass.setSize(289, 195);
    }

    public SignupScreen() 
    {
        super("Cadastro");
        initialize();
    }


    private void initialize() {
        this.setSize(290, 300);
        this.setTitle("Login");
        this.setVisible(true);
        Container screen = getContentPane();
        screen.setLayout(null);
        this.setContentPane(screen);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // labels
        JLabel titleLabel = new JLabel("Forneça Login, email e senha");
        screen.add(titleLabel);
        titleLabel.setBounds(10, 5, 200, 20);

        JLabel loginLabel = new JLabel("Login: ");
        screen.add(loginLabel);
        loginLabel.setBounds(10, 35, 60, 20);
        
        JLabel emailLabel = new JLabel("Email: ");
        screen.add(emailLabel);
        emailLabel.setBounds(10, 60, 60, 20);

        JLabel passwordLabel = new JLabel("Senha: ");
        screen.add(passwordLabel);
        passwordLabel.setBounds(10, 90, 60, 20);

        // text fields
        final JTextField loginTxt = new JTextField(6);
        screen.add(loginTxt);
        loginTxt.setBounds(75, 35, 90, 20);
        
        final JTextField emailTxt = new JTextField(6);
        screen.add(emailTxt);
        emailTxt.setBounds(75, 60, 90, 20);

        final JTextField passwordTxt = new JPasswordField(6);
        screen.add(passwordTxt);
        passwordTxt.setBounds(75, 90, 90, 20);


        // buttons and actions
        JButton submit = new JButton("OK");
        screen.add(submit);
        //submit.setBounds(30, 95, 80, 30);
        submit.addActionListener(new ActionListener()
        {
        	@Override
            public void actionPerformed(ActionEvent e) 
            {
            	if (loginTxt.getText().equals("") || passwordTxt.getText().equals(""))
                    JOptionPane.showMessageDialog(null, "Campos login e senha são obrigatórios");
            	
            	else if(passwordTxt.getText().length() < 8)
            		JOptionPane.showMessageDialog(null, "Sua senha precisa ter, no mínimo, 8 caractéres");
            	
            	//criar aqui uma condicional para caso ja exista aquele nome de usuario no xml
            	
            	else
            	{
            		User user = new User();
            		user.setName() = loginTxt.getText();
            		user.setEmail() = emailTxt.getText();
            		user.setPassword() = passwordTxt.getText();
            	}
            		
            }
        });
    }

}