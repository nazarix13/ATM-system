import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SignupThree extends JFrame implements ActionListener {
    private static final int FRAME_WIDTH = 850;
    private static final int FRAME_HEIGHT = 800;
    JRadioButton savingAccountRadioButton, checkingAccountRadioButton, moneyMarketAccountRadioButton, certificateOfDepositRadioButton;
    JCheckBox atmCardCheckBox, onlineBanking, mobileBankingCheckBox, emailSmsAlertsCheckBox, checkbookCheckBox, statementsCheckBox, termsAndConditionsCheckBox;
    JButton submitButton, cancelButton;
    String formNumber;

    CreateComponents components;
    SignupThree(String formNumber) {
        components = new CreateComponents();
        this.formNumber = formNumber;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE THREE");
        getContentPane().setBackground(Color.white);
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        addComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addComponents() {
        addAccountDetailsLabel();
        addAccountTypeLabel();
        addAccountTypeButtonGroup();
        addCardNumberLabel();
        addNumberLabel();
        addCardDetailLabel();
        addPinLabel();
        addPinNumberLabel();
        addPinDetailLabel();
        addServicesRequiredLabel();
        addCheckBoxes();
        addSubmitButton();
        addCancelButton();
    }

    private void addAccountDetailsLabel() {
        JLabel accountDetailsLabel = components.createLabel("Page 3: Account Details",280,40,400,40,22);
        add(accountDetailsLabel);
    }

    private void addAccountTypeLabel() {
        JLabel accountTypeLabel = components.createLabel("Account Type:",100,140,200,40,22);
        add(accountTypeLabel);
    }

    private void addAccountTypeButtonGroup() {
        savingAccountRadioButton = components.createRadioButton("Saving Account",100,180,250,40,16,Color.white);
        add(savingAccountRadioButton);

        checkingAccountRadioButton = components.createRadioButton("Checking Account",350,180,250,40,16,Color.white);
        add(checkingAccountRadioButton);

        moneyMarketAccountRadioButton = components.createRadioButton("Money Market Account",100,220,250,40,16,Color.white);
        add(moneyMarketAccountRadioButton);

        certificateOfDepositRadioButton = components.createRadioButton("Certificate of Deposit (CD)",350,220,300,40,16,Color.white);
        add(certificateOfDepositRadioButton);

        ButtonGroup accountTypeButtonGroup = new ButtonGroup();
        accountTypeButtonGroup.add(savingAccountRadioButton);
        accountTypeButtonGroup.add(checkingAccountRadioButton);
        accountTypeButtonGroup.add(moneyMarketAccountRadioButton);
        accountTypeButtonGroup.add(certificateOfDepositRadioButton);
    }

    private void addCardNumberLabel() {
        JLabel cardNumberLabel = components.createLabel("Card Number",100,300,200,30,22);
        add(cardNumberLabel);
    }

    private void addNumberLabel() {
        JLabel numberLabel = components.createLabel("XXXX-XXXX-XXXX-XXXX",330,300,300,30,22);
        add(numberLabel);
    }

    private void addCardDetailLabel() {
        JLabel cardDetailLabel = components.createLabel("Your 16 Digit Card Number",100,330,300,20,12);
        add(cardDetailLabel);
    }

    private void addPinLabel() {
        JLabel pinLabel = components.createLabel("PIN:",100,370,300,30,22);
        add(pinLabel);
    }

    private void addPinNumberLabel() {
        JLabel pinNumberLabel = components.createLabel("XXXX",330,370,300,30,22);
        add(pinNumberLabel);
    }

    private void addPinDetailLabel() {
        JLabel pinDetailLabel = components.createLabel("Your 4 Digit Password",100,400,300,20,12);
        add(pinDetailLabel);
    }

    private void addServicesRequiredLabel() {
        JLabel servicesRequiredLabel = components.createLabel("Services Required:",100,450,300,30,22);
        add(servicesRequiredLabel);
    }

    private void addCheckBoxes() {
        atmCardCheckBox = components.createCheckBox("ATM/Debit Card",100,500,200,30,16,Color.white);
        add(atmCardCheckBox);

        onlineBanking = components.createCheckBox("Online Banking",350,500,200,30,16,Color.white);
        add(onlineBanking);

        mobileBankingCheckBox = components.createCheckBox("Mobile Banking",100,550,200,30,16,Color.white);
        add(mobileBankingCheckBox);

        emailSmsAlertsCheckBox = components.createCheckBox("Email & SMS Alerts",350,550,200,30,16,Color.white);
        add(emailSmsAlertsCheckBox);

        checkbookCheckBox = components.createCheckBox("Checkbook",100,600,200,30,16,Color.white);
        add(checkbookCheckBox);

        statementsCheckBox = components.createCheckBox("E-Statements",350,600,200,30,16,Color.white);
        add(statementsCheckBox);

        termsAndConditionsCheckBox = components.createCheckBox("I hereby declares that the above entered details are correct to the best of my knowledge.",
                100,680,600,20,12,Color.white);
        add(termsAndConditionsCheckBox);
    }

    private void addSubmitButton() {
        submitButton = components.createButton("Submit",250,720,100,30,14);
        submitButton.addActionListener(this);
        add(submitButton);
    }

    private void addCancelButton() {
        cancelButton = components.createButton("Cancel",500,720,100,30,14);
        cancelButton.addActionListener(this);
        add(cancelButton);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitButton) {
            String accountType = null;
            if(savingAccountRadioButton.isSelected()){
                accountType = "Saving Account";
            }
            else if(checkingAccountRadioButton.isSelected()){
                accountType = "Fixed Deposit Account";
            }
            else if(moneyMarketAccountRadioButton.isSelected()){
                accountType = "Current Account";
            }else if(certificateOfDepositRadioButton.isSelected()){
                accountType = "Recurring Deposit Account";
            }

            Random random = new Random();
            String cardNumber = "" + Math.abs((random.nextLong() % 90000000L) + 5040936000000000L);

            String pinNumber = "" + Math.abs((random.nextLong() % 9000L) + 1000L);

            String facility = "";
            if(atmCardCheckBox.isSelected()){
                facility += " ATM Card";
            }
            if(onlineBanking.isSelected()){
                facility += " Internet Banking";
            }
            if(mobileBankingCheckBox.isSelected()){
                facility += " Mobile Banking";
            }
            if(emailSmsAlertsCheckBox.isSelected()){
                facility += " EMAIL Alerts";
            }
            if(checkbookCheckBox.isSelected()){
                facility += " Cheque Book";
            }
            if(statementsCheckBox.isSelected()){
                facility += " E-Statement";
            }

            try{
                if(accountType.equals("")) {
                    JOptionPane.showMessageDialog(null, "Fill all the required fields");
                } else {
                    Conn conn = new Conn();
                    String query = "UPDATE signup SET account_type = '" + accountType + "', card_number = '" + cardNumber + "', pin_number = '" + pinNumber + "', facility = '" + facility + "' WHERE form_number = '" + formNumber + "'";
                    conn.s.executeUpdate(query);

                    JOptionPane.showMessageDialog(null, "Card Number: " + cardNumber + "\n Pin: " + pinNumber);

                    setVisible(false);
                    new Transactions(cardNumber).setVisible(true);
                }
            } catch (Exception ae){
                System.out.println(ae);
            }
        } else if (e.getSource() == cancelButton) {
            setVisible(false);
            new Login().setVisible(true);
        }
    }

    public static void main(String[] args){
        new SignupThree("");
    }
}
