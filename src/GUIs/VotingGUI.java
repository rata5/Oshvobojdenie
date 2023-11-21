package GUIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Logic.RandomCodeGenerator;

public class VotingGUI extends JFrame {

    private JTextField textField;
    private JButton button;
    private JLabel messageLabel;
    JFrame randomCodeFrame = new JFrame("Random Code Generator");


    public VotingGUI(){
        randomCodeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        randomCodeFrame.setSize(300, 150);

        textField = new JTextField(20);
        button = new JButton("Get Code");
        messageLabel = new JLabel();

        randomCodeFrame.setLayout(new java.awt.FlowLayout());

        randomCodeFrame.add(textField);
        randomCodeFrame.add(button);
        randomCodeFrame.add(messageLabel);

        randomCodeFrame.setVisible(true);


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String inputText = textField.getText().trim();

                if (!inputText.isEmpty()) {

                    String generatedCode = RandomCodeGenerator.generateRandomCode();

                    textField.setText(generatedCode);
                    messageLabel.setText("");

                } else {

                    messageLabel.setText("Please input your EGN!");

                }
            }
        });

    }

}