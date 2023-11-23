package GUIs;

import Logic.RandomCodeGenerator;
import utility.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CheckVoteGUI extends JFrame {


        //Components
        private JTextField textField;
        private JLabel messageLabel;
        private JButton button;
        private JLabel topLabel;
        private JPanel upPanel = new JPanel(new GridLayout(1,1));
        private JPanel midPanel = new JPanel(new GridLayout(2,2));
        private JPanel lowPanel = new JPanel(new GridLayout(2,2));

        JFrame randomCodeFrame = new JFrame("Check Your Vote");


        public CheckVoteGUI(){

            randomCodeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            randomCodeFrame.setSize(300, 150);
            textField = new JTextField(20);
            button = new JButton("Check Vote");
            messageLabel = new JLabel();
            messageLabel.setHorizontalAlignment(JLabel.CENTER);
            randomCodeFrame.setLayout(new FlowLayout());

            topLabel = new JLabel("Enter your Code:");



            //Adding the components ============================================
            randomCodeFrame.add(upPanel);
            randomCodeFrame.add(midPanel);
            randomCodeFrame.add(lowPanel);

            upPanel.add(topLabel);
            midPanel.add(textField);
            midPanel.add(messageLabel);
            lowPanel.add(button);


            //default properties ============================================
            randomCodeFrame.setVisible(true);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String inputText = textField.getText().trim();

                    if (!inputText.isEmpty()) {

                        messageLabel.setText("userVote");


                    } else {

                        messageLabel.setText("Please input your Generated Code!");

                    }


                }
            });
        }
    }

