import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.sql.ResultSet;

public class FastCash extends JFrame implements ActionListener {
    private static final int FRAME_WIDTH = 690;
    private static final int FRAME_HEIGHT = 900;
    JButton fastcash100Button, fastcash500Button, fastcash1000Button, fastcash2000Button, fastcash5000Button, fastcash10000Button, backButton;
    JLabel imageBackgroundLabel;
    String cardNumber;

    CreateComponents components;
    FastCash(String cardNumber) {
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
        addWithdrawlAmountLabel();
        addWithdraw100Button();
        addWithdraw500Button();
        addFastcash1000Button();
        addFastcash2000Button();
        addFastcash5000Button();
        addFastcash10000Button();
        addBackButton();
    }

    private void addImageBackgroundLabel() {
        imageBackgroundLabel = components.createImageBackgroundLabel(FRAME_WIDTH,FRAME_HEIGHT);
        add(imageBackgroundLabel);
    }

    private void addWithdrawlAmountLabel() {
        JLabel withdrawlAmountLabel = components.createLabel("Select Withdrawl Amount",210,300,700,35,16,Color.white);
        imageBackgroundLabel.add(withdrawlAmountLabel);
    }

    private void addWithdraw100Button() {
        fastcash100Button = components.createButton("$100",170,405,150,30);
        fastcash100Button.addActionListener(this);
        imageBackgroundLabel.add(fastcash100Button);
    }

    private void addWithdraw500Button() {
        fastcash500Button = components.createButton("$500",370,405,150,30);
        fastcash500Button.addActionListener(this);
        imageBackgroundLabel.add(fastcash500Button);
    }

    private void addFastcash1000Button() {
        fastcash1000Button = components.createButton("$1000",170,457,150,30);
        fastcash1000Button.addActionListener(this);
        imageBackgroundLabel.add(fastcash1000Button);
    }

    private void addFastcash2000Button() {
        fastcash2000Button = components.createButton("$2000",370,457,150,30);
        fastcash2000Button.addActionListener(this);
        imageBackgroundLabel.add(fastcash2000Button);
    }

    private void addFastcash5000Button() {
        fastcash5000Button = components.createButton("$5000",170,510,150,30);
        fastcash5000Button.addActionListener(this);
        imageBackgroundLabel.add(fastcash5000Button);
    }

    private void addFastcash10000Button() {
        fastcash10000Button = components.createButton("$10000",370,510,150,30);
        fastcash10000Button.addActionListener(this);
        imageBackgroundLabel.add(fastcash10000Button);
    }

    private void addBackButton() {
        backButton = components.createButton("Back",370,563,150,30);
        backButton.addActionListener(this);
        imageBackgroundLabel.add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton) {
            setVisible(false);
            new Transactions(cardNumber);
        } else {
            String amount = ((JButton) e.getSource()).getText().substring(1);;
            Conn c = new Conn();
            try{
                ResultSet rs = c.s.executeQuery("select * from transactions where card_number = '"+ cardNumber +"'");
                int balance = 0;
                while (rs.next()){
                    if (rs.getString("type").equals("Deposit")) {
                        balance += Integer.parseInt(rs.getString("amount"));
                    } else {
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }

                if(e.getSource() != backButton && balance < Integer.parseInt(amount)) {
                    JOptionPane.showMessageDialog(null, "Insuffient Balance");
                    return;
                }

                Date date = new Date();
                String query = "insert into transactions values('"+ cardNumber +"', '"+date+"', 'Withdrawl', '"+amount+"')";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "$"+amount+" Debited Successfully");

                setVisible(false);
                new Transactions(cardNumber).setVisible(true);
            } catch (Exception ae) {
                System.out.println(ae);
            }
        }
    }

    public static void main(String[] args){
        new FastCash("");
    }
}
