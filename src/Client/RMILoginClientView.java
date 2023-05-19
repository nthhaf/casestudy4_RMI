package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RMILoginClientView extends JFrame implements ActionListener {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public RMILoginClientView()
    {
        super("Login MVC");
        txtUsername = new JTextField(20);
        txtPassword = new JPasswordField(20);
        txtPassword.setEchoChar('*');
        btnLogin = new JButton("Login");

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Username: "));
        panel.add(txtUsername);
        panel.add(new JLabel("Password: "));
        panel.add(txtPassword);
        panel.add(btnLogin);

        this.setContentPane(panel);
        this.setLocation(600,500);
        this.pack();

        this.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
