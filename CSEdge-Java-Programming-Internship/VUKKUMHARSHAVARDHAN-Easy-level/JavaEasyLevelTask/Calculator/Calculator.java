import java.awt.*;
import java.awt.event.*;

public class Calculator extends Frame implements ActionListener {
    TextField display;
    Panel panel;
    String[] buttons = {
        "7", "8", "9", "/", 
        "4", "5", "6", "*", 
        "1", "2", "3", "-", 
        "0", ".", "=", "+" 
    };
    Button[] button = new Button[buttons.length];
    String operator = "";
    double num1 = 0, num2 = 0, result = 0;

    public Calculator() {
        // Create the display
        display = new TextField();
        display.setEditable(false);
        add(display, "North");

        // Create the panel and buttons
        panel = new Panel();
        panel.setLayout(new GridLayout(4, 4));
        for (int i = 0; i < buttons.length; i++) {
            button[i] = new Button(buttons[i]);
            button[i].addActionListener(this);
            panel.add(button[i]);
        }
        add(panel);

        // Set the frame properties
        setTitle("Calculator");
        setSize(400, 400);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
            display.setText(display.getText() + command);
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
            }
            display.setText(String.valueOf(result));
            operator = "";
        } else {
            if (!operator.equals("")) {
                return;
            }
            operator = command;
            num1 = Double.parseDouble(display.getText());
            display.setText("");
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
