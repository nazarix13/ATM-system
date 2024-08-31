import javax.swing.*;
import java.awt.*;

class CreateComponents {
    private static final String FONT_NAME = "Raleway";
    private static final Color BUTTON_BACKGROUND_COLOR = Color.BLACK;
    private static final Color BUTTON_FOREGROUND_COLOR = Color.WHITE;
    JLabel createLabel(String text, int x, int y, int width, int height, int fontSize) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(FONT_NAME, Font.BOLD, fontSize));
        label.setBounds(x, y, width, height);
        return label;
    }

    JLabel createLabel(String text, int x, int y, int width, int height, int fontSize, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(FONT_NAME, Font.BOLD, fontSize));
        label.setBounds(x, y, width, height);
        label.setForeground(color);
        return label;
    }

    JTextField createTextField(int x, int y, int width, int height, int fontSize) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        textField.setFont(new Font(FONT_NAME, Font.BOLD, fontSize));
        return textField;
    }

    JRadioButton createRadioButton(String text, int x, int y, int width, int height, int fontSize, Color color) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setBounds(x, y, width, height);
        radioButton.setBackground(color);
        radioButton.setFont(new Font(FONT_NAME, Font.BOLD, 16));
        return radioButton;
    }

    JComboBox createComboBox(String incomeValues[], int x, int y, int width, int height, Color backgroundColor) {
        JComboBox comboBox = new JComboBox(incomeValues);
        comboBox.setBounds(x, y, width, height);
        comboBox.setBackground(backgroundColor);
        return comboBox;
    }

    JButton createButton(String text, int x, int y, int width, int height, int fontSize) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(BUTTON_BACKGROUND_COLOR);
        button.setForeground(BUTTON_FOREGROUND_COLOR);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFont(new Font(FONT_NAME, Font.BOLD, fontSize));
        return button;
    }

    JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        return button;
    }

    JCheckBox createCheckBox(String text, int x, int y, int width, int height, int fontSize, Color backgroundColor) {
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setBackground(backgroundColor);
        checkBox.setFont(new Font(FONT_NAME, Font.BOLD, fontSize));
        checkBox.setBounds(x, y, width, height);
        return checkBox;
    }

    JLabel createImageBackgroundLabel(int width, int height) {
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imageBackgroundLabel = new JLabel(i3);
        imageBackgroundLabel.setBounds(0,0, width, height);
        return imageBackgroundLabel;
    }

    JPasswordField createPasswordField(int x, int y, int width, int height, int fontSize) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(x, y, width, height);
        passwordField.setFont(new Font(FONT_NAME, Font.BOLD, fontSize));
        return passwordField;
    }
}
