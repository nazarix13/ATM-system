import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PinChange extends JFrame implements ActionListener {
    private static final int FRAME_WIDTH = 690;
    private static final int FRAME_HEIGHT = 900;
    JPasswordField pinPasswordField, rePinPasswordField;
    JButton changeButton, backButton;
    JLabel imageBackgroundLabel;
    String cardNumber;
    CreateComponents components;
    PinChange(String cardNumber) {
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
        addChangeYourPinLabel();
        addNewPinLabel();
        addPinPasswordField();
        addReEnterNewPinLabel();
        addRePinPasswordField();
        addChangeButton();
        addBackButton();
    }

    private void addImageBackgroundLabel() {
        imageBackgroundLabel = components.createImageBackgroundLabel(FRAME_WIDTH,FRAME_HEIGHT);
        add(imageBackgroundLabel);
    }

    private void addChangeYourPinLabel() {
        JLabel changeYourPinLabel = new JLabel("Change your PIN");
        changeYourPinLabel.setForeground(Color.WHITE);
        changeYourPinLabel.setFont(new Font("System", Font.BOLD, 16));
        changeYourPinLabel.setBounds(250,280,500,35);
        imageBackgroundLabel.add(changeYourPinLabel);
    }

    private void addNewPinLabel() {
        JLabel newPinLabel = components.createLabel("New PIN",175,320,180,25,16,Color.white);
        imageBackgroundLabel.add(newPinLabel);
    }

    private void addPinPasswordField() {
        pinPasswordField = components.createPasswordField(340,320,180,25,25);
        pinPasswordField.setDocument(new PlainDocument() {
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
        imageBackgroundLabel.add(pinPasswordField);
    }

    private void addReEnterNewPinLabel() {
        JLabel reEnterNewPinLabel = components.createLabel("Re-Enter New PIN",175,360,180,25,16,Color.white);
        imageBackgroundLabel.add(reEnterNewPinLabel);
    }

    private void addRePinPasswordField() {
        rePinPasswordField = components.createPasswordField(340,360,180,25,25);
        rePinPasswordField.setDocument(new PlainDocument() {
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
        imageBackgroundLabel.add(rePinPasswordField);
    }

    private void addChangeButton() {
        changeButton = components.createButton("Change",370,510,150,30);
        changeButton.addActionListener(this);
        imageBackgroundLabel.add(changeButton);
    }

    private void addBackButton() {
        backButton = components.createButton("Back",370,563,150,30);
        backButton.addActionListener(this);
        imageBackgroundLabel.add(backButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == changeButton) {
            try {
                String npin = pinPasswordField.getText();
                String rpin = rePinPasswordField.getText();

                if(!npin.equals(rpin)) {
                    JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                    return;
                }

                if(npin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter new PIN");
                    return;
                }

                if(rpin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please re-enter PIN");
                    return;
                }

                Conn conn = new Conn();
                String query = "update signup set pin_number = '"+rpin+"' where card_number = '"+ cardNumber +"' ";
                conn.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "PIN changed successfully");
                setVisible(false);
                new Transactions(rpin).setVisible(true);
            } catch (Exception ae) {

            }
        } else {
            setVisible(false);
            new Transactions(cardNumber).setVisible(true);
        }

    }

    public static void main(String[] args){
        new PinChange("");
    }
}
