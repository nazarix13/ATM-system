import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;

public class Withdrawl extends JFrame implements ActionListener {
    private static final int FRAME_WIDTH = 690;
    private static final int FRAME_HEIGHT = 900;

    JTextField amountTextField;
    JButton withdrawButton, backButton;
    JLabel imageBackgroundLabel;
    String cardNumber;

    CreateComponents components;
    Withdrawl(String cardNumber){
        components = new CreateComponents();
        this.cardNumber = cardNumber;

        setLayout(null);
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        addComponents();
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
    }

    private void addComponents() {
        addImageBackgroundLabel();
        addAmountOfWithdrawLabel();
        addAmountTextField();
        addWithdrawButton();
        addBackButton();
    }

    private void addImageBackgroundLabel() {
        imageBackgroundLabel = components.createImageBackgroundLabel(FRAME_WIDTH,FRAME_HEIGHT);
        add(imageBackgroundLabel);
    }

    private void addAmountOfWithdrawLabel() {
        JLabel amountOfWithdrawLabel = components.createLabel("Enter the amount you want to withdraw",180,300,400,20,16,Color.white);
        imageBackgroundLabel.add(amountOfWithdrawLabel);
    }

    private void addAmountTextField() {
        amountTextField = components.createTextField(185,350,320,30,22);
        amountTextField.setDocument(new PlainDocument() {
            String chars = "0123456789";
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (chars.indexOf(str) != -1) {
                    super.insertString(offs, str, a);
                }
            }
        });
        imageBackgroundLabel.add(amountTextField);
    }

    private void addWithdrawButton() {
        withdrawButton = components.createButton("Withdraw",370,510,150,30);
        withdrawButton.addActionListener(this);
        imageBackgroundLabel.add(withdrawButton);
    }

    private void addBackButton() {
        backButton = components.createButton("Back",370,563,150,30);
        backButton.addActionListener(this);
        imageBackgroundLabel.add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getSource() == withdrawButton){
                withdraw();
            } else if (e.getSource() == backButton) {
                back();
            }
        } catch (Exception ae) {
            System.out.println(ae);
        }
    }

    private void withdraw() throws SQLException {
        String number = amountTextField.getText();
        Date date = new Date();
        if(number.equals("")){
            JOptionPane.showMessageDialog(null, "Please enter the amount you want to withdraw");
        } else {
            Conn conn = new Conn();

            String query = "insert into transactions values('"+ cardNumber +"', '"+date+"', 'Withdrawl', '"+number+"')";
            conn.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "$"+number+" Withdraw Successfully");
            setVisible(false);
            new Transactions(cardNumber).setVisible(true);
        }
    }

    private void back() {
        setVisible(false);
        new Transactions(cardNumber).setVisible(true);
    }

    public static void main(String[] args){
        new Withdrawl("");
    }
}
