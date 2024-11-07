import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Random;
import com.toedter.calendar.JDateChooser;

public class SignupOne extends JFrame implements ActionListener {
    private static final int FRAME_WIDTH = 850;
    private static final int FRAME_HEIGHT = 800;

    long randomFormNumber;
    JTextField fullNameTextField, phoneNumberTextField, emailTextField, addressTextField, cityTextField, stateTextField, zipTextField;
    JButton nextButton;
    JComboBox maritalComboBox;
    JRadioButton maleRadioButton, femaleRadioButton, otherGenderRadioButton;
    JDateChooser dobChooser;

    CreateComponents components;
    SignupOne(){
        components = new CreateComponents();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE ONE");
        getContentPane().setBackground(Color.white);
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        addComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addComponents() {
        addFormNumberLabel();
        addPersonalDetailsLabel();
        addFullNameLabel();
        addFullNameTextField();
        addPhoneNumberLabel();
        addPhoneNumberTextField();
        addDobLabel();
        addDobChooser();
        addGenderLabel();
        addGenderButtonGroup();
        addEmailLabel();
        addEmailTextField();
        addMaritalStatusLabel();
        addMaritalStatusButtonGroup();
        addAddressLabel();
        addAddressTextField();
        addCityLabel();
        addCityTextField();
        addStateLabel();
        addStateTextField();
        addZipCodeLabel();
        addZipTextField();
        addNextButton();
    }

    private void addFormNumberLabel() {
        Random ran = new Random();
        randomFormNumber = Math.abs((ran.nextLong() % 9000L) + 1000L);
        JLabel formNumberLabel = components.createLabel("APPLICATION FORM NO. " + randomFormNumber, 140,20,600,40, 38);
        add(formNumberLabel);
    }

    private void addPersonalDetailsLabel() {
        JLabel personalDetailsLabel = components.createLabel("Page 1: Personal Details", 290,80,400,30, 22);
        add(personalDetailsLabel);
    }

    private void addFullNameLabel() {
        JLabel fullNameLabel = components.createLabel("Full name:", 100,140,150,30,20);
        add(fullNameLabel);
    }

    private void addFullNameTextField() {
        fullNameTextField = components.createTextField(300,140,400,30, 14);
        add(fullNameTextField);
    }

    private void addDobLabel() {
        JLabel dobLabel = components.createLabel("Date of Birth:", 100,190,200,30,20);
        add(dobLabel);
    }

    private void addDobChooser() {
        dobChooser = new JDateChooser();
        dobChooser.setBounds(300,190,400,30);
        dobChooser.setForeground(new Color(105,105,105));
        dobChooser.setBackground(Color.white);
        add(dobChooser);
    }

    private void addGenderLabel() {
        JLabel genderLabel = components.createLabel("Gender:", 100,240,200,30, 20);
        add(genderLabel);
    }

    private void addGenderButtonGroup() {
        maleRadioButton = components.createRadioButton("Male",300,240,100,30,16,Color.white);
        add(maleRadioButton);

        femaleRadioButton = components.createRadioButton("Female",450,240,120,30,16,Color.white);
        add(femaleRadioButton);

        otherGenderRadioButton = components.createRadioButton("Other",620,240,120,30,16,Color.white);
        add(otherGenderRadioButton);

        ButtonGroup genderButtonGroup = new ButtonGroup();
        genderButtonGroup.add(maleRadioButton);
        genderButtonGroup.add(femaleRadioButton);
    }

    private void addEmailLabel() {
        JLabel emailLabel = components.createLabel("Email Address:",100,290,200,30,20);
        add(emailLabel);
    }

    private void addEmailTextField() {
        emailTextField = components.createTextField(300,290,400,30,14);
        add(emailTextField);
    }

    private void addPhoneNumberLabel() {
        JLabel phoneNumberLabel = components.createLabel("Phone Number:", 100,340,200,30,20);
        add(phoneNumberLabel);
    }

    private void addPhoneNumberTextField() {
        phoneNumberTextField = components.createTextField(300,340,400,30,14);
        add(phoneNumberTextField);
    }

    private void addMaritalStatusLabel() {
        JLabel maritalStatusLabel = components.createLabel("Marital Status:",100,390,200,30,20);
        add(maritalStatusLabel);
    }

    private void addMaritalStatusButtonGroup() {
        String maritalValues[] = {"Single", "Married", "Divorced", "Widowed"};
        maritalComboBox = components.createComboBox(maritalValues,300,390,400,30,Color.white);
        add(maritalComboBox);
    }

    private void addAddressLabel() {
        JLabel addressLabel = components.createLabel("Address:",100,440,200,30,20);
        add(addressLabel);
    }

    private void addAddressTextField() {
        addressTextField = components.createTextField(300,440,400,30,14);
        add(addressTextField);
    }

    private void addCityLabel() {
        JLabel cityLabel = components.createLabel("City:",100,490,200,30,20);
        add(cityLabel);
    }

    private void addCityTextField() {
        cityTextField = components.createTextField(300,490,400,30,14);
        add(cityTextField);
    }

    private void addStateLabel() {
        JLabel stateLabel = components.createLabel("State:",100,540,200,30,20);
        add(stateLabel);
    }

    private void addStateTextField() {
        stateTextField = components.createTextField(300,540,400,30,14);
        add(stateTextField);
    }

    private void addZipCodeLabel() {
        JLabel zipCodeLabel = components.createLabel("ZIP Code:",100,590,200,30,20);
        add(zipCodeLabel);
    }

    private void addZipTextField() {
        zipTextField = components.createTextField(300,590,400,30,14);
        add(zipTextField);
    }

    private void addNextButton() {
        nextButton = components.createButton("Next",620,660,80,30,14);
        nextButton.addActionListener(this);
        add(nextButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        next();
    }

    private void next() {
        String formNumber = "" + randomFormNumber;
        String fullName = fullNameTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        String dob = ((JTextField) dobChooser.getDateEditor().getUiComponent()).getText();
        String gender = null;
        if(maleRadioButton.isSelected()){
            gender = "Male";
        } else if (femaleRadioButton.isSelected()) {
            gender = "Female";
        } else if (otherGenderRadioButton.isSelected()) {
            gender = "Other";
        }

        String email = emailTextField.getText();
        String maritalStatus = (String) maritalComboBox.getSelectedItem();
        String address = addressTextField.getText();
        String city = cityTextField.getText();
        String state = stateTextField.getText();
        String zip = zipTextField.getText();

        try{
            if (fullName.isEmpty() ||
                    phoneNumber.isEmpty() ||
                    dob.isEmpty() ||
                    gender == null ||
                    email.isEmpty() ||
                    maritalStatus == null ||
                    address.isEmpty() ||
                    city.isEmpty() ||
                    state.isEmpty() ||
                    zip.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fill in all fields!");
            } else {
                Conn c = new Conn();
                String query = "INSERT INTO signup (form_number, full_name, date_of_birth, gender, email, phone_number, marital_status, address, city, state, zip) " +
                               "VALUES ('" + formNumber + "','" + fullName + "','" + dob + "','" + gender + "','" + email + "','" + phoneNumber + "','" + maritalStatus + "','" + address + "','" + city + "','" + state + "','" + zip + "')";
                c.s.executeUpdate(query);

                setVisible(false);
                new SignupTwo(formNumber).setVisible(true);
            }
        } catch(Exception ae){
            System.out.println(ae);
        }
    }

    public static void main(String[] args){
        new SignupOne();
    }
}