import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {
    private static final int FRAME_WIDTH = 690;
    private static final int FRAME_HEIGHT = 900;
    JTextField amountTextField;
    JButton depositButton, backButton;
    JLabel imageBackgroundLabel;
    String cardNumber;
    CreateComponents components;
    Deposit(String cardNumber){
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
        addAmountOfDepositLabel();
        addAmountTextField();
        addDepositButton();
        addBackButton();
    }

    private void addImageBackgroundLabel() {
        imageBackgroundLabel = components.createImageBackgroundLabel(FRAME_WIDTH,FRAME_HEIGHT);
        add(imageBackgroundLabel);
    }

    private void addAmountOfDepositLabel() {
        JLabel amountOfDepositLabel = components.createLabel("Enter the amount you want to deposit",190,300,400,20,16,Color.white);
        imageBackgroundLabel.add(amountOfDepositLabel);
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

    private void addDepositButton() {
        depositButton = components.createButton("Deposit",370,510,150,30);
        depositButton.addActionListener(this);
        imageBackgroundLabel.add(depositButton);
    }

    private void addBackButton() {
        backButton = components.createButton("Back",370,563,150,30);
        backButton.addActionListener(this);
        imageBackgroundLabel.add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getSource() == depositButton){
                String number = amountTextField.getText();
                Date date = new Date();
                if(number.equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter the amount you want to deposit");
                } else {
                    Conn conn = new Conn();

                    String query = "insert into transactions values('"+ cardNumber +"', '"+date+"', 'Deposit', '"+number+"')";
                    conn.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "$"+number+" Deposited Successfully");
                    setVisible(false);
                    new Transactions(cardNumber).setVisible(true);
                }
            } else if (e.getSource() == backButton) {
                setVisible(false);
                new Transactions(cardNumber).setVisible(true);
            }
        } catch (Exception ae) {
            System.out.println(ae);
        }
    }

    public static void main(String[] args){
        new Deposit("");
    }
}