package GUIs;

import javax.swing.*;
import java.awt.*;

public class CheckVoteGUI extends JFrame {

    //Components
    private final JTextField textField;
        private final JLabel messageLabel;
        static JFrame checkVoteFrame = new JFrame("Check Your Vote");


        public CheckVoteGUI(){

            checkVoteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            checkVoteFrame.setSize(300, 150);
            textField = new JTextField(20);
            JButton button = new JButton("Check Vote");
            messageLabel = new JLabel();
            messageLabel.setHorizontalAlignment(JLabel.CENTER);
            checkVoteFrame.setLayout(new FlowLayout());

            JLabel topLabel = new JLabel("Enter your Code:");



            //Adding the components ============================================
            JPanel upPanel = new JPanel(new GridLayout(1, 1));
            checkVoteFrame.add(upPanel);
            JPanel midPanel = new JPanel(new GridLayout(2, 2));
            checkVoteFrame.add(midPanel);
            JPanel lowPanel = new JPanel(new GridLayout(2, 2));
            checkVoteFrame.add(lowPanel);

            upPanel.add(topLabel);
            midPanel.add(textField);
            midPanel.add(messageLabel);
            lowPanel.add(button);


            //default properties ============================================
            checkVoteFrame.setVisible(true);

            button.addActionListener(e -> {

                String inputText = textField.getText().trim();

                if (!inputText.isEmpty()) {

                    messageLabel.setText("userVote");


                } else {

                    messageLabel.setText("Please input your Generated Code!");

                }


            });
        }
    }
