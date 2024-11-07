import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transactions extends JFrame implements ActionListener {
    private static final int FRAME_WIDTH = 690;
    private static final int FRAME_HEIGHT = 900;
    JButton depositButton, withdrawlButton, fastCashButton, ministatementButton, pinChangeButton, balanceEnquiryButton, exitButton;
    String cardNumber;
    JLabel imageBackgroundLabel;

    CreateComponents components;
    Transactions(String cardNumber) {
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
        addSelectYourTransactionLabel();
        addDepositButton();
        addWithdrawlButton();
        addFastCashButton();
        addMinistatementButton();
        addPinChangeButton();
        addBalanceEnquiryButton();
        addExitButton();
    }

    private void addImageBackgroundLabel() {
        imageBackgroundLabel = components.createImageBackgroundLabel(FRAME_WIDTH,FRAME_HEIGHT);
        add(imageBackgroundLabel);
    }

    private void addSelectYourTransactionLabel() {
        JLabel selectYourTransactionLabel = components.createLabel("Please Select Your Transaction",210,300,700,35,16,Color.white);
        imageBackgroundLabel.add(selectYourTransactionLabel);
    }

    private void addDepositButton() {
        depositButton = components.createButton("Deposit",170,405,150,30);
        depositButton.addActionListener(this);
        imageBackgroundLabel.add(depositButton);
    }

    private void addWithdrawlButton() {
        withdrawlButton = components.createButton("Cash withdrawl",370,405,150,30);
        withdrawlButton.addActionListener(this);
        imageBackgroundLabel.add(withdrawlButton);
    }

    private void addFastCashButton() {
        fastCashButton = components.createButton("Fast Cash",170,457,150,30);
        fastCashButton.addActionListener(this);
        imageBackgroundLabel.add(fastCashButton);
    }

    private void addMinistatementButton() {
        ministatementButton = components.createButton("Mini Statement",370,457,150,30);
        ministatementButton.addActionListener(this);
        imageBackgroundLabel.add(ministatementButton);
    }

    private void addPinChangeButton() {
        pinChangeButton = components.createButton("Pin Change",170,510,150,30);
        pinChangeButton.addActionListener(this);
        imageBackgroundLabel.add(pinChangeButton);
    }

    private void addBalanceEnquiryButton() {
        balanceEnquiryButton = components.createButton("Balance Enquiry",370,510,150,30);
        balanceEnquiryButton.addActionListener(this);
        imageBackgroundLabel.add(balanceEnquiryButton);
    }

    private void addExitButton() {
        exitButton = components.createButton("Exit",370,563,150,30);
        exitButton.addActionListener(this);
        imageBackgroundLabel.add(exitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == exitButton) {
            System.exit(0);
        } else if (e.getSource() == depositButton) {
            setVisible(false);
            new Deposit(cardNumber).setVisible(true);
        } else if (e.getSource() == withdrawlButton) {
            setVisible(false);
            new Withdrawl(cardNumber).setVisible(true);
        } else if (e.getSource() == fastCashButton) {
            setVisible(false);
            new FastCash(cardNumber).setVisible(true);
        } else if (e.getSource() == pinChangeButton) {
            setVisible(false);
            new PinChange(cardNumber).setVisible(true);
        } else if (e.getSource() == balanceEnquiryButton) {
            setVisible(false);
            new BalanceEnquiry(cardNumber).setVisible(true);
        } else if (e.getSource() == ministatementButton) {
            setVisible(false);
            new MiniStatement(cardNumber).setVisible(true);
        }
    }

    public static void main(String[] args){
        new Transactions("");
    }
}
