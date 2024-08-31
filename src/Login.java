import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 480;

    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 30;
    private static final int BIG_BUTTON_WIDTH = 230;

    private static final Color FRAME_BACKGROUND_COLOR = Color.WHITE;

    JButton signInButton, clearButton, signUpButton;
    JTextField cardNumberField;
    JPasswordField pinTextField;

    CreateComponents components;

    public Login() {
        components = new CreateComponents();
        setTitle("AUTOMATED TELLER MACHINE");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponents();
        getContentPane().setBackground(FRAME_BACKGROUND_COLOR);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addComponents() {
        addLogoLabel();
        addWelcomeLabel();
        addCardNumberLabel();
        addCardNumberField();
        addPinLabel();
        addPinPasswordField();
        addSignInButton();
        addClearButton();
        addSignUpButton();
    }

    private void addLogoLabel() {
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel logoLabel = new JLabel(i3);
        logoLabel.setBounds(70, 10, 100, 100);
        add(logoLabel);
    }

    private void addWelcomeLabel() {
        JLabel welcomeLabel = components.createLabel("Welcome to ATM",200, 40, 400, 40,38);
        add(welcomeLabel);
    }

    private void addCardNumberLabel() {
        JLabel cardNumberLabel = components.createLabel("Card No:",120, 150, 400, 30,28);
        add(cardNumberLabel);
    }

    private void addCardNumberField() {
        cardNumberField = components.createTextField(300, 150, 230, 30, 14);
        add(cardNumberField);
    }

    private void addPinLabel() {
        JLabel pinLabel = components.createLabel("PIN:", 120, 220, 250, 30, 28);
        add(pinLabel);
    }

    private void addPinPasswordField() {
        pinTextField = components.createPasswordField(300, 220, 230, 30,14);
        pinTextField.setDocument(new PlainDocument() {
            String chars = "0123456789";
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (chars.indexOf(str) != -1) {
                    if (getLength()< 4) {
                        super.insertString(offs, str, a);
                    }
                }
            }
        });
        add(pinTextField);
    }

    private void addSignInButton() {
        signInButton = components.createButton("SIGN IN", 300, 300, BUTTON_WIDTH, BUTTON_HEIGHT,14);
        signInButton.addActionListener(this);
        add(signInButton);
    }

    private void addClearButton() {
        clearButton = components.createButton("CLEAR", 430, 300, BUTTON_WIDTH, BUTTON_HEIGHT,14);
        clearButton.addActionListener(this);
        add(clearButton);
    }

    private void addSignUpButton() {
        signUpButton = components.createButton("SIGN UP", 300, 350, BIG_BUTTON_WIDTH, BUTTON_HEIGHT,14);
        signUpButton.addActionListener(this);
        add(signUpButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {
            clearFields();
        } else if (e.getSource() == signInButton) {
            signIn();
        } else if (e.getSource() == signUpButton) {
            signUp();
        }
    }

    private void clearFields() {
        cardNumberField.setText("");
        pinTextField.setText("");
    }

    private void signIn() {
        Conn databaseConnector = new Conn();
        String cardNumber = cardNumberField.getText();
        String pinNumber = pinTextField.getText();
        String query = "select * from signup where card_number = '"+cardNumber+"' and pin_number = '"+pinNumber+"'";
        try{
            ResultSet rs = databaseConnector.s.executeQuery(query);
            if(rs.next()){
                setVisible(false);
                new Transactions(cardNumber).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect Card Number or Pin");
            }
        } catch (Exception ae) {
            System.out.println(ae);
        }
    }

    private void signUp() {
        setVisible(false);
        new SignupOne().setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }
}