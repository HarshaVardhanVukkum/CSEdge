import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class RegistrationManager extends Frame implements ActionListener {
    TextField nameField, ageField;
    CheckboxGroup genderGroup;
    Checkbox male, female, other;
    Choice courseChoice;
    TextArea displayArea;
    Button registerButton, displayButton, saveButton;
    
    public RegistrationManager() {
        // Create and set up components
        setLayout(new FlowLayout());

        add(new Label("Name:"));
        nameField = new TextField(20);
        add(nameField);

        add(new Label("Age:"));
        ageField = new TextField(5);
        add(ageField);

        add(new Label("Gender:"));
        genderGroup = new CheckboxGroup();
        male = new Checkbox("Male", genderGroup, true);
        female = new Checkbox("Female", genderGroup, false);
        other = new Checkbox("Other", genderGroup, false);
        add(male);
        add(female);
        add(other);

        add(new Label("Course:"));
        courseChoice = new Choice();
        courseChoice.add("Computer Science");
        courseChoice.add("Information Technology");
        courseChoice.add("Electronics");
        add(courseChoice);

        registerButton = new Button("Register");
        registerButton.addActionListener(this);
        add(registerButton);

        displayButton = new Button("Display");
        displayButton.addActionListener(this);
        add(displayButton);

        saveButton = new Button("Save");
        saveButton.addActionListener(this);
        add(saveButton);

        displayArea = new TextArea(10, 50);
        add(displayArea);

        // Set the frame properties
        setTitle("Registration Manager");
        setSize(600, 400);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Register")) {
            String name = nameField.getText();
            String age = ageField.getText();
            String gender = genderGroup.getSelectedCheckbox().getLabel();
            String course = courseChoice.getSelectedItem();
            displayArea.append("Name: " + name + "\nAge: " + age + "\nGender: " + gender + "\nCourse: " + course + "\n\n");
        } else if (command.equals("Display")) {
            // Display action is already handled in the Register button
        } else if (command.equals("Save")) {
            saveToFile();
        }
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("registrations.txt", true))) {
            writer.write(displayArea.getText());
            displayArea.setText("");
            System.out.println("Data saved to file.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new RegistrationManager();
    }
}
