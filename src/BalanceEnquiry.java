import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class BalanceEnquiry extends JFrame implements ActionListener {
    private static final int FRAME_WIDTH = 690;
    private static final int FRAME_HEIGHT = 900;

    String cardNumber;
    JButton backButton;
    JLabel imageBackgroundLabel;

    CreateComponents components;
    BalanceEnquiry(String cardNumber) {
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
        addBackButton();
        showBalance();
    }

    private void addImageBackgroundLabel() {
        imageBackgroundLabel = components.createImageBackgroundLabel(FRAME_WIDTH,FRAME_HEIGHT);
        add(imageBackgroundLabel);
    }

    private void addBackButton() {
        backButton = components.createButton("Back",370,563,150,30);
        backButton.addActionListener(this);
        imageBackgroundLabel.add(backButton);
    }

    private void showBalance() {
        Conn c = new Conn();
        int balance = 0;
        try {
            ResultSet rs = c.s.executeQuery("select * from transactions where card_number = '" + cardNumber + "'");

            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
        } catch(Exception ae){
            System.out.println(ae);
        }

        JLabel balanceLabel = components.createLabel("Your Current Account Balance is $"+balance, 170, 300, 400,30,16, Color.white);
        imageBackgroundLabel.add(balanceLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new Transactions(cardNumber).setVisible(true);
    }

    public static void main(String[] args){
        new BalanceEnquiry("");
    }
}
