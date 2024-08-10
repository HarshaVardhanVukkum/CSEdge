package OnlineQuizApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnlineQuizApplication extends JFrame implements ActionListener {
    private JLabel questionLabel;
    private JRadioButton[] options;
    private ButtonGroup group;
    private JButton nextButton, submitButton;
    private int currentQuestionIndex = 0;
    private int score = 0;

    private String[][] questions = {
            {"What is the capital of France?", "Paris", "London", "Berlin", "Madrid"},
            {"Which planet is known as the Red Planet?", "Earth", "Mars", "Jupiter", "Saturn"},
            {"What is the largest ocean on Earth?", "Atlantic", "Indian", "Arctic", "Pacific"},
            {"Who wrote 'Romeo and Juliet'?", "Shakespeare", "Dickens", "Hemingway", "Austen"}
    };

    private int[] correctAnswers = {0, 1, 3, 0};

    public OnlineQuizApplication() {
        setLayout(new FlowLayout());

        questionLabel = new JLabel(questions[currentQuestionIndex][0]);
        add(questionLabel);

        options = new JRadioButton[4];
        group = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton(questions[currentQuestionIndex][i + 1]);
            group.add(options[i]);
            add(options[i]);
        }

        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        add(nextButton);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        add(submitButton);

        setTitle("Online Quiz Application");
        setSize(400, 300);
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == nextButton) {
            if (options[correctAnswers[currentQuestionIndex]].isSelected()) {
                score++;
            }
            currentQuestionIndex++;
            if (currentQuestionIndex < questions.length) {
                updateQuestion();
            } else {
                displayResults();
            }
        } else if (ae.getSource() == submitButton) {
            if (options[correctAnswers[currentQuestionIndex]].isSelected()) {
                score++;
            }
            displayResults();
        }
    }

    private void updateQuestion() {
        group.clearSelection();
        questionLabel.setText(questions[currentQuestionIndex][0]);
        for (int i = 0; i < 4; i++) {
            options[i].setText(questions[currentQuestionIndex][i + 1]);
        }
    }

    private void displayResults() {
        JOptionPane.showMessageDialog(this, "Your score: " + score + "/" + questions.length);
        System.exit(0);
    }

    public static void main(String[] args) {
        new OnlineQuizApplication();
    }
}
