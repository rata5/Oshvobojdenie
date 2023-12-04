package GUIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckVoteGUI extends JFrame {


        //Components
        private JTextField textField;
        private JLabel messageLabel;
        private JButton button;
        private JLabel topLabel;
        private JPanel upPanel = new JPanel(new GridLayout(1,1));
        private JPanel midPanel = new JPanel(new GridLayout(2,2));
        private JPanel lowPanel = new JPanel(new GridLayout(2,2));

        static JFrame checkVoteFrame = new JFrame("Check Your Vote");


        public CheckVoteGUI(){

            checkVoteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            checkVoteFrame.setSize(300, 150);
            textField = new JTextField(20);
            button = new JButton("Check Vote");
            messageLabel = new JLabel();
            messageLabel.setHorizontalAlignment(JLabel.CENTER);
            checkVoteFrame.setLayout(new FlowLayout());

            topLabel = new JLabel("Enter your Code:");



            //Adding the components ============================================
            checkVoteFrame.add(upPanel);
            checkVoteFrame.add(midPanel);
            checkVoteFrame.add(lowPanel);

            upPanel.add(topLabel);
            midPanel.add(textField);
            midPanel.add(messageLabel);
            lowPanel.add(button);


            //default properties ============================================
            checkVoteFrame.setVisible(true);

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

